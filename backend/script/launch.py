from flask import Flask
from sqlalchemy import create_engine
import config
from db.orm import DB_Base

database = create_engine(config.CONNECTION_STRING)
DB_Base.metadata.create_all(database)

app = Flask(__name__)

if __name__ == '__main__':
    app.run()