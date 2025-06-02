from flask import Flask
from sqlalchemy import create_engine
import config
from db.orm import DB_Base

from routes.auth import init_auth

database = create_engine(config.CONNECTION_STRING)
DB_Base.metadata.create_all(database)

app = Flask(__name__)

init_auth(app, database)

if __name__ == '__main__':
    app.run()