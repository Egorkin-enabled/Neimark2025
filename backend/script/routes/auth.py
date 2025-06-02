from flask import Flask, request, Response
from db.orm import User
from sqlalchemy.orm import Session
import pydantic
import hashlib
import random
import base64
import io
import string

def use_authorization(engine):
    
    def wrapper(inner_func):
        def handler():
            auth = request.headers['Authorization']
            
            splitted = auth.split(' ')
            
            if splitted[0].lower() != 'bearer':
                return Response(status=403)

            bearer = splitted[1]

            with Session(engine) as session:
                user = session.query(User).where(User.access_token == bearer).first()
                if not user:
                    return Response(status=403)

                return inner_func(engine, user)

        handler.__name__ = inner_func.__name__ + '_auth_wrapped'
        return handler
    return wrapper

def init_auth(app: Flask, engine):
    class LoginRequest(pydantic.BaseModel):
        login: str
        password: str

    @app.route('/auth/log_in', methods=['PUT'])
    def auth():
        login = LoginRequest(**request.get_json())

        with Session(engine) as session:
            user = session.query(User).where(
                User.login == login.login,
                User.password == login.password
            ).first()

            if not user:
                return Response(status=404)
            
            if not user.access_token:
                user.access_token = ''.join(random.choice(string.ascii_letters) for _ in range(32))
            
            session.commit()

            return { "access_token": user.access_token }, 200


