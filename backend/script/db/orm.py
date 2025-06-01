from sqlalchemy.orm import declarative_base
from sqlalchemy.orm import Mapped, mapped_column

Base = declarative_base()

class User(Base):
    __tablename__= 'user'
    id: Mapped[int] = mapped_column(primary_key=True)
    login: Mapped[str]
    password_hash: Mapped[str]
    password_salt: Mapped[str]

class Teacher(User):
    id: Mapped[int] = mapped_column(ForeingKey('user.id'))