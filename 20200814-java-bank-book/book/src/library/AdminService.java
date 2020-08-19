package library;

import library.*;
import library.Tool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdminService {
    Connection con = Tool.getConnection();

    public void showMenu() {
        System.out.println("     ADMINISTRATIVE MENU");
        System.out.println("(1) Add a book");
        System.out.println("(2) Update book holdings");
        System.out.println("(3) Search book");
        System.out.println("(4) New patron");
        System.out.println("(5) Print branch information");
        System.out.println("(6) Print top 10 frequently check-out books");
        System.out.println("(7) Quit");
    }

    public void addBook() {
        Scanner scanner = new Scanner(System.in);
        try {
            con.setAutoCommit(false);
            Book book = new Book();
            book.setBookId(Tool.getFixLenthString(11));
            System.out.println("Book Title:");
            book.setTitle(scanner.nextLine());
            System.out.println("Author:");
            String author = scanner.nextLine();
            System.out.println("Publisher:");
            String publisherName = scanner.nextLine();
            Publisher publisher = null;
            //add publisher
            {
                String sql = "select \"NAME\", \"address\", \"phone\" from PUBLISHERS where \"NAME\" = ?";
                PreparedStatement preparedStatement = con.prepareStatement(sql);
                preparedStatement.setString(1, publisherName);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    publisher = new Publisher();
                    publisher.setName(resultSet.getString(1));
                    publisher.setAddress(resultSet.getString(2));
                    publisher.setPhone(resultSet.getString(3));
                }
            }

            if (publisher == null) {
                System.out.println("This publisher doesn't exist, so we'll create a new publisher.");
                publisher = new Publisher();
                publisher.setName(publisherName);
                System.out.println("Address:");
                publisher.setAddress(scanner.nextLine());
                System.out.println("Phone:");
                publisher.setPhone(scanner.nextLine());

                {
                    String sql = "insert into PUBLISHERS(\"NAME\", \"address\", \"phone\") values (?, ?, ?)";
                    PreparedStatement preparedStatement = con.prepareStatement(sql);
                    preparedStatement.setString(1, publisher.getName());
                    preparedStatement.setString(2, publisher.getAddress());
                    preparedStatement.setString(3, publisher.getPhone());

                    if (preparedStatement.executeUpdate() == 1) {
                        System.out.println("Added publisher successfully.");
                        System.out.println(String.format("Publisher Name: %s", publisher.getName()));
                    } else {
                        System.out.println("Failed to add publisher.");
                        con.rollback();
                        return;
                    }
                }
            }

            book.setPublisher(publisher);

            // add book
            {
                String sql = "insert into BOOKS(\"BOOK_ID\", \"title\", \"publisher_name\") values(?, ?, ?)";
                PreparedStatement preparedStatement = con.prepareStatement(sql);
                preparedStatement.setString(1, book.getBookId());
                preparedStatement.setString(2, book.getTitle());
                preparedStatement.setString(3, book.getPublisher().getName());

                if (preparedStatement.executeUpdate() == 1) {
                    System.out.println("Added book successfully.");
                    System.out.println(String.format("Book Id: %s", book.getBookId()));
                } else {
                    System.out.println("Failed to add book.");
                    con.rollback();
                    return;
                }
            }

            // add book author
            {
                BookAuthor bookAuthor = new BookAuthor();
                bookAuthor.setBook(book);
                bookAuthor.setAuthorName(author);

                String sql = "insert into BOOK_AUTHORS(\"BOOK_ID\", \"AUTHOR_NAME\") values(?, ?)";
                PreparedStatement preparedStatement = con.prepareStatement(sql);
                preparedStatement.setString(1, bookAuthor.getBook().getBookId());
                preparedStatement.setString(2, bookAuthor.getAuthorName());

                int res = preparedStatement.executeUpdate();
                if (res == 1) {
                    con.commit();
                    System.out.println("Added book author successfully.");
                    System.out.println(String.format("Author Name: %s", bookAuthor.getAuthorName()));
                } else {
                    System.out.println("Failed to add book author.");
                    con.rollback();
                    return;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                con.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                con.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void updateBookHoldings() {
        Scanner scanner = new Scanner(System.in);
        try {
            con.setAutoCommit(false);

            System.out.println("Book Title:");
            String title = scanner.nextLine();
            Book book = null;
            {
                String sql = "select BOOKS.\"BOOK_ID\", BOOKS.\"title\", BOOKS.\"publisher_name\" from BOOKS where BOOKS.\"title\"= ?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, title);

                ResultSet resultSet = ps.executeQuery();
                while (resultSet.next()) {
                    book = new Book();
                    book.setBookId(resultSet.getString(1));
                    book.setTitle(resultSet.getString(2));
                    Publisher publisher = new Publisher();
                    publisher.setName(resultSet.getString(3));
                    book.setPublisher(publisher);
                }
            }

            if (book == null) {
                System.out.println("The book doesn't exist.");
                return;
            }

            System.out.println("Branch Name:");
            String branchName = scanner.nextLine();

            Branch branch = null;
            {
                String sql = "select \"BRANCH_ID\", \"branch_name\", \"address\" from BRANCHES where \"branch_name\" = ?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, branchName);

                ResultSet resultSet = ps.executeQuery();
                while (resultSet.next()) {
                    branch = new Branch();
                    branch.setBranchId(resultSet.getString(1));
                    branch.setBranchName(resultSet.getString(2));
                    branch.setAddress(resultSet.getString(3));
                }
            }

            if (branch == null) {
                System.out.println("This branch doesn't exist, so we'll create a new branch.");
                branch = new Branch();
                branch.setBranchId(Tool.getFixLenthString(11));
                branch.setBranchName(branchName);
                System.out.println("Address:");
                branch.setAddress(scanner.nextLine());

                {
                    String sql = "insert into BRANCHES(\"BRANCH_ID\", \"branch_name\", \"address\") values (?, ?, ?)";
                    PreparedStatement preparedStatement = con.prepareStatement(sql);
                    preparedStatement.setString(1, branch.getBranchId());
                    preparedStatement.setString(2, branch.getBranchName());
                    preparedStatement.setString(3, branch.getAddress());

                    if (preparedStatement.executeUpdate() == 1) {
                        System.out.println("Added branch successfully.");
                        System.out.println(String.format("Branch Id: %s", branch.getBranchId()));
                    } else {
                        System.out.println("Failed to add branch.");
                        con.rollback();
                        return;
                    }
                }
            }

            BookCopy bookCopy = null;
            {
                String sql = "select \"BOOK_ID\", \"BRANCH_ID\", \"no_of_copies\" from BOOK_COPIES where \"BOOK_ID\"=? and \"BRANCH_ID\"=?";
                PreparedStatement preparedStatement = con.prepareStatement(sql);
                preparedStatement.setString(1, book.getBookId());
                preparedStatement.setString(2, branch.getBranchId());

                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    bookCopy = new BookCopy();
                    bookCopy.setBook(book);
                    bookCopy.setBranch(branch);
                    bookCopy.setNoOfCopies(resultSet.getInt(3));
                }
            }

            if (bookCopy == null) {
                bookCopy = new BookCopy();
                bookCopy.setBook(book);
                bookCopy.setBranch(branch);

                System.out.println("Number of copies:");
                bookCopy.setNoOfCopies(scanner.nextInt());

                {
                    String sql = "insert into BOOK_COPIES(\"BOOK_ID\", \"BRANCH_ID\", \"no_of_copies\") values(?, ?, ?)";
                    PreparedStatement preparedStatement = con.prepareStatement(sql);
                    preparedStatement.setString(1, bookCopy.getBook().getBookId());
                    preparedStatement.setString(2, bookCopy.getBranch().getBranchId());
                    preparedStatement.setInt(3, bookCopy.getNoOfCopies());

                    if (preparedStatement.executeUpdate() == 1) {
                        con.commit();
                        System.out.println("Added book copies successfully.");
                        System.out.println(String.format("Number of copies: %d", bookCopy.getNoOfCopies()));
                    } else {
                        System.out.println("Failed to add book copies.");
                        con.rollback();
                        return;
                    }
                }
            } else {
                System.out.println("Number of copies:");
                bookCopy.setNoOfCopies(scanner.nextInt());

                {
                    String sql = "update BOOK_COPIES set \"no_of_copies\"=? where \"BOOK_ID\"=? and \"BRANCH_ID\"=?";
                    PreparedStatement preparedStatement = con.prepareStatement(sql);
                    preparedStatement.setInt(1, bookCopy.getNoOfCopies());
                    preparedStatement.setString(2, bookCopy.getBook().getBookId());
                    preparedStatement.setString(3, bookCopy.getBranch().getBranchId());

                    if (preparedStatement.executeUpdate() == 1) {
                        con.commit();
                        System.out.println("Updated book copies successfully.");
                        System.out.println(String.format("Number of copies: %d", bookCopy.getNoOfCopies()));
                    } else {
                        System.out.println("Failed to update book copies.");
                        con.rollback();
                        return;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                con.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                con.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void searchBook() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Book Title:");
            String title = scanner.nextLine();

            String sql = "select BOOKS.\"BOOK_ID\", BOOKS.\"title\", BOOKS.\"publisher_name\" from BOOKS where \"title\" = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, title);

            ResultSet resultSet = preparedStatement.executeQuery();
            Book book = null;
            while (resultSet.next()) {
                book = new Book();
                book.setBookId(resultSet.getString(1));
                book.setTitle(resultSet.getString(2));
                Publisher publisher = new Publisher();
                publisher.setName(resultSet.getString(3));
                book.setPublisher(publisher);
            }

            if (book != null) {
                System.out.println("BOOK_ID\ttitle\tpublisher");
                System.out.println(String.format("%s\t%s\t%s", book.getBookId(), book.getTitle(), book.getPublisher().getName()));
            } else {
                System.out.println("No such a book.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addPatron() {
        Scanner scanner = new Scanner(System.in);
        try {
            Borrower borrower = new Borrower();
            borrower.setCardNo(Tool.getFixLenthString(11));
            System.out.println("Name:");
            borrower.setName(scanner.nextLine());
            System.out.println("Phone:");
            borrower.setPhone(scanner.nextLine());
            System.out.println("Address:");
            borrower.setAddress(scanner.nextLine());
            borrower.setUnpaidDues(0d);

            String sql = "insert into BORROWERS(\"CARD_NO\", \"name\", \"address\", \"phone\", \"unpaid_dues\") values(?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, borrower.getCardNo());
            preparedStatement.setString(2, borrower.getName());
            preparedStatement.setString(3, borrower.getAddress());
            preparedStatement.setString(4, borrower.getPhone());
            preparedStatement.setDouble(5, borrower.getUnpaidDues());

            int res = preparedStatement.executeUpdate();
            if (res == 1) {
                System.out.println("Added borrower successfully.");
                System.out.println(String.format("Card No: %s", borrower.getCardNo()));
            } else {
                System.out.println("Failed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void printBranchInfo() {
        try {
            String sql = "select \"BRANCH_ID\", \"branch_name\", \"address\" from BRANCHES where 1 = 1";
            PreparedStatement preparedStatement = con.prepareStatement(sql);

            List<Branch> list = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Branch branch = new Branch();
                branch.setBranchId(resultSet.getString(1));
                branch.setBranchName(resultSet.getString(2));
                branch.setAddress(resultSet.getString(3));

                list.add(branch);
            }

            System.out.println("BRANCH_ID\tbranch_name\taddress");
            for (Branch branch : list) {
                System.out.println(String.format("%s\t%s\t%s", branch.getBranchId(), branch.getBranchName(), branch.getAddress()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void printTop10Books() {
        try {
            String sql = "select BOOKS.\"BOOK_ID\", BOOKS.\"title\", BOOKS.\"publisher_name\", count(*) as \"loan_times\" from BOOK_LOANS, BOOKS where BOOK_LOANS.\"BOOK_ID\" = BOOKS.\"BOOK_ID\" and rownum <= 10 group by BOOKS.\"BOOK_ID\", BOOKS.\"title\", BOOKS.\"publisher_name\" order by \"loan_times\" desc";
            PreparedStatement preparedStatement = con.prepareStatement(sql);

            List<Book> list = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Book book = new Book();
                book.setBookId(resultSet.getString(1));
                book.setTitle(resultSet.getString(2));
                Publisher publisher = new Publisher();
                publisher.setName(resultSet.getString(3));
                book.setPublisher(publisher);

                list.add(book);
            }

            System.out.println("BOOK_ID\ttitle\tpublisher");
            for (Book book : list) {
                System.out.println(String.format("%s\t%s\t%s", book.getBookId(), book.getTitle(), book.getPublisher().getName()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
