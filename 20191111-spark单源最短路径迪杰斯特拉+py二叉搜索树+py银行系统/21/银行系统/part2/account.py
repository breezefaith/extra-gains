class Account:
    def __init__(self, no=None, name=None, balance=0, date=None):
        self.__account_no = no
        self.__name_on_account = name
        self.__balance = balance
        self.__account_open_date = date

    def get_account_no(self):
        return self.__account_no

    def get_name_on_account(self):
        return self.__name_on_account

    def get_balance(self):
        return self.__balance

    def get_account_open_date(self):
        return self.__account_open_date

    def set_account_no(self, no):
        self.__account_no = no

    def set_name_on_account(self, name):
        self.__name_on_account = name

    def set_balance(self, ban):
        self.__balance = ban

    def set_account_open_date(self, date):
        self.__account_open_date = date

    def get_info(self):
        s = ""
        s = s + "Account number: %d\n" % self.__account_no
        s = s + "Name on account: %s\n" % self.__name_on_account
        s = s + "Balance: %f\n" % self.__balance
        s = s + "Account opened date: %s" % self.__account_open_date
        return s
