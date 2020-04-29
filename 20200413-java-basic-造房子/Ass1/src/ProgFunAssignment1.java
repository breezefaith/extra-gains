// import whatever packages are needed here

import java.util.Scanner;

public class ProgFunAssignment1
{


    public static void main(String[] args)
    {
        // Create a Scanner object for input
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter the rows");
        int rows = scanner.nextByte();
        while (rows <= 2 || rows > 10)
        {
            System.out.println("Invalid rows! Please enter again");
            rows = scanner.nextByte();
        }

        System.out.println("enter the cols");
        int cols = scanner.nextByte();
        while (cols <= 2 || cols > 10)
        {
            System.out.println("Invalid cols! Please enter again");
            cols = scanner.nextByte();
        }

        // Create an object of class MyBlock using the 'new' operator, calling on the MyBlock constructor.
        MyBlock block = new MyBlock(rows, cols);

        // Build a loop to display the menu, prompt for input and process it as per requirements.
        while (true)
        {
            System.out.println("1. Add a house");
            System.out.println("2. Display the block");
            System.out.println("3. Clear the block");
            System.out.println("4. Quit");

            int choice = scanner.nextInt();
            if (choice == 1)
            {
                System.out.println("enter the x");
                int x = scanner.nextInt();

                System.out.println("enter the y");
                int y = scanner.nextInt();

                System.out.println("enter the row");
                int row = scanner.nextInt();

                System.out.println("enter the col");
                int col = scanner.nextInt();

                if (block.addHouse(x, y, row, col) == false)
                {
                    System.out.println("The house can't be added.");
                }
            }
            else if (choice == 2)
            {
                block.displayBlock();
            }
            else if (choice == 3)
            {
                block.clearBlock();
            }
            else if (choice == 4)
            {
                break;
            }
            else
            {
                System.out.println("Unknown option! Please reenter");
            }
        }

        // Your program should quit gracefullu
        return;
    }

}

// MyBlock class 

// It does NOT need to handle any input at all.  
// All the input should occur in the main method
//
// If you find yourself needing to do input here rethink your solution.

class MyBlock
{
    private int[][] block;
    // you may need to add other variables.
    private boolean vacant;
    private int maxRows;
    private int maxCols;
    private int curHouseNo;

    // Constructor...
    // Assumption - rows and columns has been validated beforehand.
    // Initialise the block so that each cell is set to the character '0'
    // (hint: use one of your methods!).
    // Set the initial value of any other variables
    public MyBlock(int maxRows, int maxColumns)
    {
        this.maxRows = maxRows;
        this.maxCols = maxColumns;
        this.block = new int[maxRows][maxColumns];

        clearBlock();
    }


    // Display the entrie block as a matrix
    public void displayBlock()
    {
        for (int i = 0; i < block.length; i++)
        {
            for (int j = 0; j < block[i].length; j++)
            {
                System.out.print(block[i][j] + " ");
            }
            System.out.println();
        }
    }


    // Clear out the block. This involves setting each cell to be '0'
    public void clearBlock()
    {
        for (int i = 0; i < block.length; i++)
        {
            for (int j = 0; j < block[i].length; j++)
            {
                block[i][j] = 0;
            }
        }

        curHouseNo = 1;
        vacant = true;
    }


    // Build a house on the block

    // Ensure none of the other rules are violated
    // Return if any rule is violated
    // An error message should display when an error occurs
    // If all is ok "build" the house on the block
    // Update relevant variable(s) and call on displayLand().
    public boolean addHouse(int rowPos, int colPos, int rows, int columns)
    {
        if (rowPos < 1 || rows < 1 || rowPos + rows - 1 >=  maxRows - 1)
        {
            return false;
        }

        if (colPos < 1 || columns < 1 || colPos + columns - 1 >= maxCols - 1)
        {
            return false;
        }

        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < columns; j++)
            {
                int x = rowPos + i;
                int y = colPos + j;

                if (block[x][y] != 0 || block[x - 1][y] != 0 || block[x][y - 1] != 0 || block[x + 1][y] != 0 || block[x][y + 1] != 0)
                {
                    return false;
                }
            }
        }
        // You may find the code snippt below useful
        for (int i = rowPos; i < rowPos + rows; i++)
        {
            for (int j = colPos; j < colPos + columns; j++)
            {
                block[i][j] = curHouseNo;
            }
        }
        if (vacant == true)
        {
            vacant = false;
        }
        displayBlock();
        curHouseNo++;
        return true;
    }

}