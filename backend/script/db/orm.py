from sqlalchemy.orm import declarative_base
from sqlalchemy.orm import Mapped, mapped_column, relationship
from sqlalchemy import ForeignKey
from datetime import datetime

DB_Base = declarative_base()

# Intermediate tables
class StudentPrize(DB_Base):
    __tablename__ = 'student_prize'
    student_id: Mapped[int] = mapped_column(ForeignKey('student.id'), primary_key=True)
    prize_id: Mapped[int] = mapped_column(ForeignKey('prize.id'), primary_key=True)

class UserCourse(DB_Base):
    __tablename__ = 'user_course'
    user_id: Mapped[int] = mapped_column(ForeignKey('user.id'), primary_key=True)
    course_id: Mapped[int] = mapped_column(ForeignKey('course.id'), primary_key=True)

class CorurseDayOfWeek(DB_Base):
    __tablename__ = 'course_day_of_week'
    course_id = mapped_column(ForeignKey('course.id'), primary_key=True)
    day_of_week_id = mapped_column(ForeignKey('day_of_week.id'), primary_key=True)

# Entity tables
class Prize(DB_Base):
    __tablename__ = 'prize'
    id: Mapped[int] = mapped_column(primary_key=True)
    name: Mapped[str]

class Course(DB_Base):
    __tablename__ = 'course'
    id: Mapped[int] = mapped_column(primary_key=True) 
    name: Mapped[str]
    user: Mapped[list['User']] = relationship('User', secondary='user_course', back_populates='courses')
    daysOfWeek: Mapped[list['DayOfWeek']] = relationship('DayOfWeek', secondary='course_day_of_week', back_populates='courses')
    messages: Mapped[list['Message']] = relationship('Message')


class Group(DB_Base):
    __tablename__ = 'group'
    id: Mapped[int] = mapped_column(primary_key=True)
    name: Mapped[str]

class User(DB_Base):
    __tablename__= 'user'
    id: Mapped[int] = mapped_column(primary_key=True)
    login: Mapped[str]
    password: Mapped[str]
    photo_url: Mapped[str | None]

    first_name: Mapped[str]
    last_name: Mapped[str]
    patronymic: Mapped[str]

    courses: Mapped[list[Course]] = relationship("Course", secondary='user_course', back_populates='user')
    access_token: Mapped[str | None]
    role: Mapped[str]
    __mapper_args__ = {
        "polymorphic_identity": "user",
        "polymorphic_on": "role",
    }

class Teacher(User):
    __tablename__ = 'teacher'
    id: Mapped[int] = mapped_column(ForeignKey('user.id'), primary_key=True)
    department: Mapped[str]
    faculty: Mapped[str]
    relations: Mapped[list['Relation']] = relationship('Relation')
    __mapper_args__ = {
        "polymorphic_identity": "teacher",
    }

class Student(User):
    __tablename__ = 'student'
    id: Mapped[int] = mapped_column(ForeignKey('user.id'), primary_key=True)
    score: Mapped[int]
    group: Mapped[Group] = mapped_column(ForeignKey("group.id"))
    prizes: Mapped[list[Prize]] = relationship("Prize", secondary="student_prize")
    __mapper_args__ = {
        "polymorphic_identity": "student",
    }

class RelationKind:
    LIKES = 0
    DISLIKES = 1
    IGNORES = 2

class Relation(DB_Base):
    __tablename__ = 'relation'
    id: Mapped[int] = mapped_column(primary_key=True)
    kind: Mapped[int]
    date: Mapped[datetime]
    student: Mapped[Teacher] = mapped_column(ForeignKey('student.id'))
    teacher: Mapped[Teacher] = mapped_column(ForeignKey('teacher.id'))


class Opinion(DB_Base):
    __tablename__ = 'opinion'
    id: Mapped[int] = mapped_column(primary_key=True)
    simplicity: Mapped[int]
    actuality: Mapped[int]
    involvment: Mapped[int]
    mood: Mapped[int]
    description: Mapped[str]
    date: Mapped[datetime]

class DayOfWeek(DB_Base):
    __tablename__ = 'day_of_week'
    id: Mapped[int] = mapped_column(primary_key=True)
    week_number: Mapped[int]
    day_number: Mapped[int]
    courses: Mapped[list[Course]] = relationship('DayOfWeek', secondary='course_day_of_week', back_populates='daysOfWeek')

class Message(DB_Base):
    __tablename__ = 'message'
    id: Mapped[int] = mapped_column(primary_key=True)
    contents: Mapped[str]
    date_of_creation: Mapped[datetime]
    course: Mapped[Course] = mapped_column(ForeignKey('course.id'))