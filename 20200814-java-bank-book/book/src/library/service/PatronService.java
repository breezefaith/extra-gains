package library.service;

import library.entity.*;
import library.tools.Tool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PatronService {
    private Connection con = Tool.getConnection();

    private Borrower borrower;

    public void showMenu() {
        System.out.println("     PATRON MENU");
        System.out.println("(1) Book checkout");
        System.out.println("(2) Book return");
        System.out.println("(3) Pay fine");
        System.out.println("(4) Print loaned books list");
        System.out.println("(5) Quit");
    }

    public void checkoutBook() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Book Id:");
            String bookId = scanner.nextLine();

            Book book = null;
            {
                String sql = "select BOOKS.\"BOOK_ID\", BOOKS.\"title\", BOOKS.\"publisher_name\" from BOOKS where \"BOOK_ID\" = ?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, bookId);

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
                System.out.println("No such a book.");
                return;
            }

            System.out.println("Branch Id:");
            String branchId = scanner.nextLine();

            Branch branch = null;
            {
                String sql = "select \"BRANCH_ID\", \"branch_name\", \"address\" from BRANCHES where \"BRANCH_ID\" = ?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, branchId);

                ResultSet resultSet = ps.executeQuery();
                while (resultSet.next()) {
                    branch = new Branch();
                    branch.setBranchId(resultSet.getString(1));
                    branch.setBranchName(resultSet.getString(2));
                    branch.setAddress(resultSet.getString(3));
                }
            }
            if (branch == null) {
                System.out.println("No such a branch.");
                return;
            }

            BookCopy bookCopy = null;
            {
                String sql = "select \"BOOK_ID\", \"BRANCH_ID\", \"no_of_copies\" from BOOK_COPIES where \"BOOK_ID\"=? and \"BRANCH_ID\"=?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, book.getBookId());
                ps.setString(2, branch.getBranchId());

                ResultSet resultSet = ps.executeQuery();
                while (resultSet.next()) {
                    bookCopy = new BookCopy();
                    bookCopy.setBook(book);
                    bookCopy.setBranch(branch);
                    bookCopy.setNoOfCopies(resultSet.getInt(3));
                }
            }

            if (bookCopy == null || bookCopy.getNoOfCopies() < 1) {
                System.out.println("There is no copy for loaning.");
                return;
            }

            {
                bookCopy.setNoOfCopies(bookCopy.getNoOfCopies() - 1);
                String sql = "update BOOK_COPIES set \"no_of_copies\" = ? where \"BOOK_ID\"=? and \"BRANCH_ID\"=?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, bookCopy.getNoOfCopies());
                ps.setString(2, bookCopy.getBook().getBookId());
                ps.setString(3, bookCopy.getBranch().getBranchId());

                if (ps.executeUpdate() == 1) {
                    System.out.println("Updated book copies successfully.");
                } else {
                    System.out.println("Failed to update book copies.");
                    con.rollback();
                    return;
                }
            }

            System.out.println("Days:");
            Integer days = scanner.nextInt();

            {
                BookLoan bookLoan = new BookLoan();
                bookLoan.setBook(book);
                bookLoan.setBranch(branch);
                bookLoan.setBorrower(getBorrower());
                bookLoan.setDateOut(new Date(System.currentTimeMillis()));
                bookLoan.setDateDue(new Date(bookLoan.getDateOut().getTime() + days * 24 * 3600 * 1000));
                bookLoan.setDateReturned(null);

                String sql = "insert into BOOK_LOANS(\"BOOK_ID\", \"BRANCH_ID\", \"CARD_NO\", \"date_out\", \"date_due\", \"date_returned\") values(?, ?, ?, ?, ?, ?)";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, bookLoan.getBook().getBookId());
                ps.setString(2, bookLoan.getBranch().getBranchId());
                ps.setString(3, bookLoan.getBorrower().getCardNo());
                ps.setDate(4, bookLoan.getDateOut());
                ps.setDate(5, bookLoan.getDateDue());
                ps.setDate(6, bookLoan.getDateReturned());

                if (ps.executeUpdate() == 1) {
                    System.out.println("Checked out successfully.");
                    System.out.println(String.format("Due date: %s", bookLoan.getDateDue().toLocaleString()));
                } else {
                    System.out.println("Failed to checked out.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void returnBook() {
        Scanner scanner = new Scanner(System.in);
        try {
            con.setAutoCommit(false);

            System.out.println("Book Id:");
            String bookId = scanner.nextLine();

            Book book = null;
            {
                String sql = "select BOOKS.\"BOOK_ID\", BOOKS.\"title\", BOOKS.\"publisher_name\" from BOOKS where \"BOOK_ID\" = ?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, bookId);

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
                System.out.println("No such a book.");
                return;
            }

            System.out.println("Branch Id:");
            String branchId = scanner.nextLine();

            Branch branch = null;
            {
                String sql = "select \"BRANCH_ID\", \"branch_name\", \"address\" from BRANCHES where \"BRANCH_ID\" = ?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, branchId);

                ResultSet resultSet = ps.executeQuery();
                while (resultSet.next()) {
                    branch = new Branch();
                    branch.setBranchId(resultSet.getString(1));
                    branch.setBranchName(resultSet.getString(2));
                    branch.setAddress(resultSet.getString(3));
                }
            }
            if (branch == null) {
                System.out.println("No such a branch.");
                return;
            }

            BookCopy bookCopy = null;
            {
                String sql = "select \"BOOK_ID\", \"BRANCH_ID\", \"no_of_copies\" from BOOK_COPIES where \"BOOK_ID\"=? and \"BRANCH_ID\"=?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, book.getBookId());
                ps.setString(2, branch.getBranchId());

                ResultSet resultSet = ps.executeQuery();
                while (resultSet.next()) {
                    bookCopy = new BookCopy();
                    bookCopy.setBook(book);
                    bookCopy.setBranch(branch);
                    bookCopy.setNoOfCopies(resultSet.getInt(3));
                }
            }

            if (bookCopy == null) {
                System.out.println("There is no copy for loaning.");
                return;
            }

            {
                bookCopy.setNoOfCopies(bookCopy.getNoOfCopies() + 1);
                String sql = "update BOOK_COPIES set \"no_of_copies\" = ? where \"BOOK_ID\"=? and \"BRANCH_ID\"=?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, bookCopy.getNoOfCopies());
                ps.setString(2, bookCopy.getBook().getBookId());
                ps.setString(3, bookCopy.getBranch().getBranchId());

                if (ps.executeUpdate() == 1) {
                    System.out.println("Updated book copies successfully.");
                } else {
                    System.out.println("Failed to update book copies.");
                    con.rollback();
                    return;
                }
            }

            BookLoan bookLoan = null;
            {
                String sql = "select \"BOOK_ID\", \"BRANCH_ID\", \"CARD_NO\", \"date_out\", \"date_due\", \"date_returned\" from BOOK_LOANS where \"BOOK_ID\" = ? and \"BRANCH_ID\" = ? and \"CARD_NO\" = ?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, book.getBookId());
                ps.setString(2, branch.getBranchId());
                ps.setString(3, getBorrower().getCardNo());

                ResultSet resultSet = ps.executeQuery();
                while (resultSet.next()) {
                    bookLoan = new BookLoan();
                    bookLoan.setBook(book);
                    bookLoan.setBranch(branch);
                    bookLoan.setBorrower(getBorrower());
                    bookLoan.setDateOut(resultSet.getDate(4));
                    bookLoan.setDateDue(resultSet.getDate(5));
                }
            }

            if (bookLoan == null) {
                System.out.println("No such book loaning record.");
                return;
            }


            bookLoan.setDateReturned(new Date(System.currentTimeMillis()));
            {
                String sql = "update BOOK_LOANS set \"date_returned\" = ? where \"BOOK_ID\" = ? and \"BRANCH_ID\" = ? and \"CARD_NO\" = ?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setDate(1, bookLoan.getDateReturned());
                ps.setString(2, bookLoan.getBook().getBookId());
                ps.setString(3, bookLoan.getBranch().getBranchId());
                ps.setString(4, bookLoan.getBorrower().getCardNo());

                if (ps.executeUpdate() == 1) {
                    System.out.println("Returned successfully.");
                } else {
                    System.out.println("Failed to return.");
                    con.rollback();
                    return;
                }
            }

            long diffDays = (bookLoan.getDateReturned().getTime() - bookLoan.getDateDue().getTime()) / 1000 / 3600 / 24;
            if (diffDays <= 0) {
                con.commit();
                return;
            }
            Borrower borrower = getBorrower();
            Double dues = borrower.getUnpaidDues() + diffDays * Cons.finePerDay;
            {
                String sql = "update BORROWERS set \"unpaid_dues\" = ? where \"CARD_NO\" = ?";
                PreparedStatement preparedStatement = con.prepareStatement(sql);
                preparedStatement.setDouble(1, dues);
                preparedStatement.setString(2, borrower.getCardNo());

                if (preparedStatement.executeUpdate() == 1) {
                    con.commit();
                    borrower.setUnpaidDues(dues);
                    System.out.println(String.format("Successfully. Now your unpaid dues: %.2f ", dues));
                } else {
                    System.out.println("Failed.");
                    con.rollback();
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

    public void payFine() {
        Scanner scanner = new Scanner(System.in);
        try {
            Borrower borrower = getBorrower();
            {
                String sql = "select \"unpaid_dues\" from  \"BORROWERS\" where \"CARD_NO\" = ?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, borrower.getCardNo());
                ResultSet resultSet = ps.executeQuery();
                while (resultSet.next()) {
                    borrower.setUnpaidDues(resultSet.getDouble(1));
                }
            }

            System.out.println(String.format("Unpaid Dues: %.2f", borrower.getUnpaidDues()));
            System.out.println("Your payment:");
            Double payment = scanner.nextDouble();

            Double diff = borrower.getUnpaidDues() - payment;
            if (diff < 0) {
                System.out.println("Your payment is too much.");
                return;
            }

            {
                String sql = "update BORROWERS set \"unpaid_dues\" = ? where \"CARD_NO\" = ?";
                PreparedStatement preparedStatement = con.prepareStatement(sql);
                preparedStatement.setDouble(1, diff);
                preparedStatement.setString(2, borrower.getCardNo());

                if (preparedStatement.executeUpdate() == 1) {
                    borrower.setUnpaidDues(diff);
                    System.out.println(String.format("Successfully. Now your unpaid dues: %.2f ", diff));
                } else {
                    System.out.println("Failed.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void printLoanedBooks() {
        try {
            String sql = "select BOOKS.\"BOOK_ID\", BOOKS.\"title\", BOOKS.\"publisher_name\" from BOOK_LOANS, BOOKS where BOOK_LOANS.\"BOOK_ID\" = BOOKS.\"BOOK_ID\" and BOOK_LOANS.\"CARD_NO\" = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, getBorrower().getCardNo());

            List<Book> list = new ArrayList<>();
            ResultSet resultSet = ps.executeQuery();
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

    public void login() {
        Scanner scanner = new Scanner(System.in);
        try {
            this.borrower = null;
            System.out.println("Card No:");
            String cardNo = scanner.nextLine();

            String sql = "select \"CARD_NO\", \"name\", \"address\", \"phone\", \"unpaid_dues\" from  \"BORROWERS\" where \"CARD_NO\" = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, cardNo);
            ResultSet resultSet = ps.executeQuery();
            Borrower borrower = null;
            while (resultSet.next()) {
                borrower = new Borrower();
                borrower.setCardNo(resultSet.getString(1));
                borrower.setName(resultSet.getString(2));
                borrower.setAddress(resultSet.getString(3));
                borrower.setPhone(resultSet.getString(4));
                borrower.setUnpaidDues(resultSet.getDouble(5));
            }

            if (borrower == null) {
                System.out.println("No such a borrower.");
                return;
            }
            setBorrower(borrower);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean isLogged() {
        return borrower != null;
    }

    public void setBorrower(Borrower borrower) {
        this.borrower = borrower;
    }

    public Borrower getBorrower() {
        return borrower;
    }
}
