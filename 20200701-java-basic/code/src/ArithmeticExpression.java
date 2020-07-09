import java.io.*;
import java.util.Random;

/**
 * (A) Design a binary ArithmeticExpression class, which includes:
 */
public class ArithmeticExpression {
    /**
     * (1) Two double private data members named x and y
     */
    private double x;
    private double y;
    /**
     * (2) A double private data member named operator
     * [0,1) +
     * [1,2) -
     * [2,3) *
     * [3,4) /
     */
    private char operator;

    /**
     * (3) A constructor for creating objects based on concrete binary expressions.
     *
     * @param x
     * @param y
     * @param operator
     * @throws IllegalArgumentException
     */
    public ArithmeticExpression(double x, double y, char operator) throws IllegalArgumentException {
        if (operator != '+' && operator != '-' && operator != '*' && operator != '/') {
            throw new IllegalArgumentException();
        }
        this.x = x;
        this.y = y;
        this.operator = operator;
    }

    /**
     * (5) getResult() method to evaluate the expression’s value.
     *
     * @return
     * @throws ArithmeticException
     */
    public double getResult() throws ArithmeticException {
        switch (operator) {
            case '+':
                return x + y;
            case '-':
                return x - y;
            case '*':
                return x * y;
            case '/':
                if (y == 0) {
                    throw new ArithmeticException("/ by zero");
                }
                return x / y;
            default:
                throw new ArithmeticException();
        }
    }

    /**
     * (4) Accessor method of three members
     */
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public char getOperator() {
        return operator;
    }

    /**
     * (B) Write the main program, if the disk file named out.txt does not exist,
     * then create the file, and create 100 binary arithmetic expression objects
     * with random Numbers, and write the expression and operation results into
     * the file; If the file exists, all expressions are read from the file and
     * displayed in line.
     *
     * @param args
     */
    public static void main(String[] args) {
        try {
            File file = new File("out.txt");
            if (file.exists()) {
                //If the file exists
                readFile(file);
            } else {
                // if the disk file named out.txt does not exist,
                System.out.println("out.txt doesn't exist.");
                writeFile(file);
                System.out.println("out.txt is generated successfully.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * all expressions are read from the file and displayed in line
     *
     * @param file
     * @throws IOException
     */
    private static void readFile(File file) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            System.out.println(line);
        }
        bufferedReader.close();
    }

    /**
     * create the file, and create 100 binary arithmetic expression objects with random Numbers, and write the expression and operation results into the file;
     *
     * @param file
     * @throws FileNotFoundException
     */
    private static void writeFile(File file) throws FileNotFoundException {
        PrintWriter printWriter = new PrintWriter(file);
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            //x:[0,1000), y:[0,1000)
            double x = random.nextDouble() * 1000;
            double y = random.nextDouble() * 1000;

            double op = random.nextDouble() * 4;
            char operator = '+';
            //judge operator
            if (op >= 0 && op < 1) {
                operator = '+';
            } else if (op >= 1 && op < 2) {
                operator = '-';
            } else if (op >= 2 && op < 3) {
                operator = '*';
            } else if (op >= 3 && op < 4) {
                operator = '/';
            }

            ArithmeticExpression expression = new ArithmeticExpression(x, y, operator);

            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(expression.getX());
            stringBuffer.append(" ");

            stringBuffer.append(expression.getOperator());
            stringBuffer.append(" ");

            stringBuffer.append(expression.getY());
            stringBuffer.append(" ");

            stringBuffer.append("=");
            stringBuffer.append(" ");

            try {
                stringBuffer.append(expression.getResult());
            } catch (Exception e) {
                stringBuffer.append(e.getMessage());
            }

            printWriter.println(stringBuffer);
        }
        printWriter.close();
    }
}
