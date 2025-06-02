from flask import Flask, request, Response
from db.orm import User, Student
from sqlalchemy.orm import Session
import pydantic
import hashlib
import random
import base64
import io
import string

from routes.auth import use_authorization

def init_stats(app, engine):
    @app.route('/scores', methods=['GET'])
    @use_authorization(engine)
    def get_scores(engine, user: User):
        return { "scores": user.score }, 200

    class UserOut(pydantic.BaseModel):
        id: int
        role: str
        photo_url: str
        first_name: str
        last_name: str
        patronymic: str
        role: str


    @app.route('/users/<user_id>')
    def get_user(user_id: int):
        with Session(engine) as session:
            user = session.query(User).where(
                User.id == user_id
            ).first()

            if not user:
                return Response(status=404)

            return {
                "id": user.id,
                "role": user.role,
                "photo_url": user.photo_url,
                "first_name": user.first_name,
                "last_name": user.last_name,
                "patronymic": user.patronymic
            }, 200