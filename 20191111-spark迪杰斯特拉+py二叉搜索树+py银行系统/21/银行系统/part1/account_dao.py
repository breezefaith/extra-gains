import time
from account import Account
import datetime

from account import Account


class AccountDao:
    def __init__(self, con):
        self.__con = con

    def insert(self, name_on_account, balance):
        try:
            with self.__con.cursor() as cur:
                sql = "INSERT INTO account(name_on_account, balance) VALUES('%s', '%f')" % (name_on_account, balance)
                cur.execute(sql)
                last_id = cur.lastrowid
            return last_id
        except Exception as e:
            self.__con.rollback()
            return None
        finally:
            self.__con.commit()
            print("current time:" + datetime.datetime.now().strftime('%Y-%m-%d %H:%M:%S'))

    def find_by_id(self, account_no):
        try:
            with self.__con.cursor() as cur:
                sql = "SELECT * FROM account WHERE account_no = '%d'" % account_no
                cur.execute(sql)
                rows = cur.fetchall()
            return Account(rows[0][0], rows[0][1], rows[0][2], rows[0][3])
        except Exception as e:
            return None
        finally:
            self.__con.commit()
            print("current time:" + datetime.datetime.now().strftime('%Y-%m-%d %H:%M:%S'))
