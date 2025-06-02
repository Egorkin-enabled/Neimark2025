from flask import Flask, request, Response
from db.orm import User, Student, Course
from sqlalchemy.orm import Session
import pydantic
from datetime import datetime
import hashlib
import random
import base64
import io
import string

from routes.auth import use_authorization

class PageRequest(pydantic.BaseModel):
    page_number: int
    page_size: int


def parse_paginator_from_query():
    return PageRequest({
        'page_number': request.args.get('page_number'),
        'page_size': request.args.get('page_size')
        })

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
        
    @app.route('/daily_courses')
    @use_authorization(engine)
    def get_courses(engine, user: User):
        # page = parse_paginator_from_query()
        courses = []
        with Session(engine) as session:
            time = datetime.now()
            _, weeknumber, weekday = time.isocalendar()
            for course in user.courses:
                has_any = False
                for day in course.daysOfWeek:
                    if day.week_number == (weeknumber - 1) % 2 and day.day_number == (weekday - 1):
                        has_any = True
                        break
                if has_any:
                    courses.append(
                        {
                            'id': course.id,
                            'title': course.name,
                        }
                    )
