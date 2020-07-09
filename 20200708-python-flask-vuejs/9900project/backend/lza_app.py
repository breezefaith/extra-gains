
from flask_cors import CORS
from flask import Flask,request,g,jsonify
import json
import time
import os
import pymysql
from flask import render_template

def db_connect():
    connection = pymysql.connect(host='localhost',
                                 user='root',
                                 password='',
                                 db='starting_point',
                                 charset='utf8',
                                 cursorclass=pymysql.cursors.DictCursor)
    return connection
app = Flask(__name__)
cors = CORS(app, resources={r"/*": {"origins": "*"}})
@app.route('/')
def hello_world():
    return 'Successful!'

@app.route('/rec_bookInfo',methods=['GET','POST'])
def extract_recInfo():
    connection = db_connect()
    result = 'Nothing Post!'
    # data = request.get_json()
    # bookname = data['bookname']
    # pic = data['pic']
    cursor = connection.cursor()

    sql = "SELECT * FROM `book_info` ORDER BY `read_count` DESC "
    cursor.execute(sql)
    result = cursor.fetchall()
    # print(result)
    connection.close()
    res = {'state': "True", 'msg': result}
    # print(res['msg'][0]['book_name'])
    return jsonify(res)



##启动运行
if __name__ == '__main__':
    app.run(debug=True)
