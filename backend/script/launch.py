from flask import Flask
from sqlalchemy import create_engine
import config

database = create_engine(config.CONNECTION_STRING)
app = Flask(__name__)

if __name__ == '__main__':
    app.run()