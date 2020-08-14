from flask import Flask,request,g,jsonify
import json
import time
import os
import mysql.connector
import pymysql
from flask_cors import CORS
import pandas as pd
import numpy as np


def db_connect():
    connection = pymysql.connect(host='localhost',
                                 user='root',
                                 password='aaaaaa',
                                #  password='',
                                 db='starting_point',
                                 charset='utf8',
                                 cursorclass=pymysql.cursors.DictCursor)
    return connection

app = Flask(__name__)
cors = CORS(app, resources={r"/*": {"origins": "*"}})

@app.route('/register',methods=['GET','POST'])
def register():
    connection = db_connect()
    result = 'Nothing Post!'
    if request.method == 'POST':
        data = request.get_json()
        username = data['username']
        password = data['password']
        email = data['email']
        nickname = username
    # username = 'test'
    # password = '1234'
    # email = '123@gmail.com'
    # nickname = 'test_nick'
    try:
        with connection.cursor() as cursor:
            sql = "INSERT INTO `user_info` (`user_name`,`password`,`email`,nick_name) VALUES (%s,%s,%s,%s)"
            cursor.execute(sql,(username,password,email,nickname))
        connection.commit()
        with connection.cursor() as cursor:
            sql = "SELECT * FROM `user_info` WHERE user_name = %s and password = %s"
            cursor.execute(sql, (username, password))
            result = cursor.fetchone()
            result.pop('password')
            res = {'state':"True",'msg':result}

    except Exception:
        print("username has been used", Exception)
        connection.rollback()
        res = {'state':'False','msg':'False, username has been used'}
        return jsonify(res)
    finally:
        connection.close()
    return jsonify(res)

@app.route('/signin',methods=['GET','POST'])
def login():
    connection = db_connect()
    result = 'Nothing Post!'
    if request.method == 'POST':
        data = request.get_json()
        username = data['username']
        password = data['password']
    # email = data['email']
    # username = 'test'
    # password = '1234'
    try:
        with connection.cursor() as cursor:
            sql = "SELECT * FROM `user_info` WHERE user_name = %s and password = %s"
            cursor.execute(sql,(username,password))
            result = cursor.fetchone()
            result.pop('password')
    except Exception:
        connection.rollback()
        res = {'state': 'False', 'msg': 'something wrong'}
        return jsonify(res)
    finally:
        connection.close()
    if result is None:
        res = {'state': 'False', 'msg': 'wrong username/password'}
        return jsonify(res)
    res = {'state': "True", 'msg': result}
    return jsonify(res)


@app.route('/collection',methods=['GET','POST'])
def collection():
    connection = db_connect()
    result = 'Nothing Post!'
    if request.method == 'POST':
        data = request.get_json()
        user_id = data['user_id']
    # user_id = 10027
    try:
        with connection.cursor() as cursor:
            sql = "SELECT * FROM collection_list WHERE user_id = %s"
            cursor.execute(sql,(user_id))
            result = cursor.fetchall()
    except Exception:
        connection.rollback()
        res = {'state': 'False', 'msg': 'something wrong'}
        return jsonify(res)
    finally:
        connection.close()
    if result is None:
        res = {'state': 'False', 'msg': 'wrong username/password'}
        return jsonify(res)
    res = {'state': "True", 'msg': result}
    print(result)
    return jsonify(res)

@app.route('/add_collection',methods=['GET','POST'])
def add_collection():
    connection = db_connect()
    if request.method == 'POST':
        data = request.get_json()
        collection_name = data['collection_name']
        user_id = int(data['user_id'])
        collection_pics = data['collection_pics']
        collection_comments = data['collection_comments']
        # print(collection_name, user_id, collection_pics, collection_comments)
    try:
        with connection.cursor() as cursor:
            sql = "INSERT INTO collection_list(collection_name,user_id,collection_pics,collection_comments) VALUES (%s,%s,%s,%s)"
            cursor.execute(sql, (collection_name, user_id, collection_pics, collection_comments))
        connection.commit()

    except Exception:
        print("collection name has been used", Exception)
        connection.rollback()
        res = {'state':'False'}
        return jsonify(res)
    finally:
        connection.close()
    res = {'state':'True'}
    return jsonify(res)

@app.route('/topten',methods=['GET','POST'])
def topten():
    connection = db_connect()
    try:
        books = pd.read_csv('toptenbook.txt')
        datas = np.array(books)
        data = []
        for a in datas:
            info = a[0].split('"')
            aaa = []
            for i in info:
                if i.strip():
                    aaa.append(i.strip())
            data.append(aaa)
        # print(data)
        with connection.cursor() as cursor:
            sql = "INSERT INTO book_info(book_name,book_pics,book_type,book_rate) VALUES (%s,%s,%s,%s)"
            for each in data:
                bookname = each[0]
                bookpics = each[1]
                booktype = each[2]
                bookrate = each[3]
                cursor.execute(sql,(bookname,bookpics,booktype,bookrate))
        connection.commit()
    except Exception as e:
        print("what's the error", e)
        connection.rollback()
        res = {'state':'False', 'msg': 'create bookinfo error'}
        return jsonify(res)

    try:
        tags = ['Science', 'Historical','Classics', 'Comic', 'Novel','Literary', 'Romance',
                'Thrillers', 'Women\'s','Biographies','Cookbooks', 'Essays', 'Poetry', 'Memoir']
        result = []
        with connection.cursor() as cursor:
            sql = "SELECT * FROM `book_info` WHERE book_type = %s order by book_rate desc"
            for _ in tags:
                cursor.execute(sql,_)
                output = cursor.fetchmany(10)
                print(output)
                result.extend(output)

            res = {'state':'True', 'msg':result}
    except Exception as e:
        print("what's the error", e)
        connection.rollback()
        res = {'state':'False', 'msg': 'get bookinfo error'}
        return jsonify(res)
    finally:
        connection.close()

    return jsonify(res)
if __name__ == '__main__':
    app.run(port=5000)
