import pymysql
from account import Account

class AccountDao:
    def __init__(self, con):
        self.__con = con

    def insert(self, name_on_account, balance):
        self.__con.begin()
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

    def find_by_id(self, account_no):
        try:
            with self.__con.cursor() as cur:
                sql = "SELECT * FROM account WHERE account_no = '%d'" % account_no
                cur.execute(sql)
                rows = cur.fetchall()
            return Account(rows[0][0], rows[0][1], rows[0][2], rows[0][3])
        except Exception as e:
            return None

    def find_by_id_lock(self, account_no):
        try:
            with self.__con.cursor() as cur:
                sql = "SELECT * FROM account WHERE account_no = '%d' FOR UPDATE" % account_no
                cur.execute(sql)
                rows = cur.fetchall()
            return Account(rows[0][0], rows[0][1], rows[0][2], rows[0][3])
        except Exception as e:
            return None

    def add_balance(self, account_no, amount):
        try:
            with self.__con.cursor() as cur:
                sql = "UPDATE account SET balance = balance + '%d' WHERE account_no = '%d'" % (amount, account_no)
                cur.execute(sql)
        except Exception as e:
            self.__con.rollback()
            return None

    def reduce_balance(self, account_no, amount):
        try:
            with self.__con.cursor() as cur:
                sql = "UPDATE account SET balance = balance - '%d' WHERE account_no = '%d'" % (amount, account_no)
                cur.execute(sql)
        except Exception as e:
            self.__con.rollback()
            return None

    def begin(self):
        self.__con.begin()

    def commit(self):
        self.__con.commit()

    def rollback(self):
        self.__con.rollback()

    def close(self):
        self.__con.close()

