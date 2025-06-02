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
