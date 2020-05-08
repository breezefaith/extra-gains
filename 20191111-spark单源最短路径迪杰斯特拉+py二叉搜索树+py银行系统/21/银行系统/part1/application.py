import time
import pymysql
from account_dao import AccountDao


class Application:
    def __init__(self):
        self.__con = pymysql.connect(host='127.0.0.1', user='root', password='mysql_990524',
                                     database='cse4701f19_project2',
                                     port=3306)
        self.__accountDao = AccountDao(self.__con)

    def display_menu(self):
        print("Main Menu")
        print("1 - Create Account")
        print("2 - Check Balance")
        print("3 - Deposit")
        print("4 - Withdraw")
        print("5 - Transfer")
        print("0 - Quit")

    def display_account(self, account):
        print(account.get_info())

    def create_account(self):
        name = input("Name on account: ")
        ban = float(input("Enter initial balance: "))

        print()
        last_id = self.__accountDao.insert(name, ban)
        if last_id is not None:
            acc = self.__accountDao.find_by_id(last_id)
            print("---Account successfully created---")
            self.display_account(acc)
        else:
            print("---Account is failed to be created---")
            print()
        print("-----")

    def check_balance(self):
        no = int(input("Enter account number: "))
        acc = self.__accountDao.find_by_id(no)
        print("---Checking account balance---")
        if acc is not None:
            self.display_account(acc)
        else:
            print("There is no account with account_no %d" % no)
        print("-----")

    def deposit(self):
        # todo
        return

    def withdraw(self):
        # todo
        return

    def transfer(self):
        # todo
        return

    def quit(self):
        self.__con.close()
        print("Goodbye")

    def run(self):
        while True:
            self.display_menu()
            choice = int(input("Enter your choice:"))
            if choice < 0 or choice > 5:
                print("Invalid choice! Please reenter!")
                continue

            print()
            if choice == 1:
                self.create_account()
            elif choice == 2:
                self.check_balance()
            elif choice == 3:
                self.deposit()
            elif choice == 4:
                self.withdraw()
            elif choice == 5:
                self.transfer()
            elif choice == 0:
                self.quit()
                break
            print()


if __name__ == '__main__':
    app = Application()
    app.run()
