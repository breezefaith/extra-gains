import time
import pymysql
from account_dao import AccountDao


class Application:

    def __init__(self):
        con = pymysql.connect(host='127.0.0.1', user='root', password='mysql_990524',
                              database='cse4701f19_project2',
                              port=3306)
        # con.autocommit(0)
        self.__accountDao = AccountDao(con)

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
        no = int(input("Enter account number: "))
        self.__accountDao.begin()
        acc = self.__accountDao.find_by_id_lock(no)
        if acc is not None:
            self.display_account(acc)
            dep = int(input("\nEnter deposit amount: "))
            self.__accountDao.add_balance(no, dep)
            self.__accountDao.commit()
            acc = self.__accountDao.find_by_id(no)
            if acc is not None:
                print("\nNew balance: %f" % acc.get_balance())
            else:
                print("\nFailed to deposit")
        else:
            print("There is no account with account_no %d" % no)
            self.__accountDao.rollback()
        return

    def withdraw(self):
        no = int(input("Enter account number: "))
        self.__accountDao.begin()
        acc = self.__accountDao.find_by_id_lock(no)
        if acc is not None:
            self.display_account(acc)
            wit = int(input("Enter withdrawal amount: "))
            if wit > acc.get_balance():
                print("lack of balance!")
                self.__accountDao.rollback()
            else:
                self.__accountDao.reduce_balance(no, wit)
                self.__accountDao.commit()
                acc = self.__accountDao.find_by_id(no)
                if acc is not None:
                    print("\nNew balance: %f" % acc.get_balance())
                else:
                    print("\nFailed to withdraw")
        else:
            print("There is no account with account_no %d" % no)
            self.__accountDao.rollback()
        return

    def transfer(self):
        no1 = int(input("Enter your account number: "))
        acc1 = self.__accountDao.find_by_id(no1)
        if acc1 is not None:
            self.display_account(acc1)
            no2 = int(input("Enter target account number: "))
            acc2 = self.__accountDao.find_by_id(no2)
            if acc2 is not None:
                tra = int(input("Enter transfer amount: "))
                if tra > acc1.get_balance():
                    print("lack of balance!")
                else:
                    self.__accountDao.begin()
                    self.__accountDao.reduce_balance(no1, tra)
                    time.sleep(10)
                    self.__accountDao.add_balance(no2, tra)
                    time.sleep(10)
                    confirm = int(input("Are you sure? (1 is Yes/0 is No): "))
                    if confirm == 1:
                        self.__accountDao.commit()
                        print("Transfer completed.")
                        print("\nNew balance: %f" % self.__accountDao.find_by_id(no1).get_balance())
                    else:
                        self.__accountDao.rollback()
                        # self.__accountDao.rollback()
                        print("Transfer cancel.")
                        print("\nNew balance: %f" % self.__accountDao.find_by_id(no1).get_balance())
            else:
                print("There is no account with account_no %d" % no2)
        else:
            print("There is no account with account_no %d" % no1)
        return

    def quit(self):
        self.__accountDao.close()
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