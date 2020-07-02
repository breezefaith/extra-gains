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
    private double operator;

    /**
     * (3) A constructor for creating objects based on concrete binary expressions.
     * @param x
     * @param y
     * @param operator
     * @throws IllegalArgumentException
     */
    public ArithmeticExpression(double x, double y, double operator) throws IllegalArgumentException {
        if (operator < 0 || operator >= 4) {
            throw new IllegalArgumentException();
        }
        this.x = x;
        this.y = y;
        this.operator = operator;
    }

    /**
     * (5) getResult() method to evaluate the expression’s value.
     * @return
     * @throws Exception
     */
    public double getResult() throws Exception {
        if (operator >= 0 && operator < 1) {
            return x + y;
        } else if (operator >= 1 && operator < 2) {
            return x - y;
        } else if (operator >= 2 && operator < 3) {
            return x * y;
        } else if (operator >= 3 && operator < 4) {
            if (y == 0) {
                throw new ArithmeticException("/ by zero");
            }
            return x / y;
        } else {
            throw new Exception();
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

    public double getOperator() {
        return operator;
    }

    /**
     * (B) Write the main program, if the disk file named out.txt does not exist,
     * then create the file, and create 100 binary arithmetic expression objects
     * with random Numbers, and write the expression and operation results into
     * the file; If the file exists, all expressions are read from the file and
     * displayed in line.
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
     * @param file
     * @throws FileNotFoundException
     */
    private static void writeFile(File file) throws FileNotFoundException {
        PrintWriter printWriter = new PrintWriter(file);
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            //x:[0,1000), y:[0,1000), operator: [0,4)
            ArithmeticExpression expression = new ArithmeticExpression(random.nextDouble() * 1000, random.nextDouble() * 1000, random.nextDouble() * 4);

            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(expression.getX());
            stringBuffer.append(" ");

            //judge operator
            if (expression.getOperator() >= 0 && expression.getOperator() < 1) {
                stringBuffer.append("+");
            } else if (expression.getOperator() >= 1 && expression.getOperator() < 2) {
                stringBuffer.append("-");
            } else if (expression.getOperator() >= 2 && expression.getOperator() < 3) {
                stringBuffer.append("*");
            } else if (expression.getOperator() >= 3 && expression.getOperator() < 4) {
                stringBuffer.append("/");
            }
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
