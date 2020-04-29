import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class T {
    int a;
    int b;
    int c;

    public T(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public String toString() {
        return String.format("(%d, %d, %d)", a, b, c);
    }
}

public class Main {
    public static void main(String[] args) {
        String filepath = null;
        if (args.length >= 1) {
            filepath = args[0];
        } else {
            System.out.print("Please enter the absolute path of input file:");
            Scanner scanner = new Scanner(System.in);
            filepath = scanner.nextLine();
        }
        readFile(filepath);
    }

    private static void readFile(String filepath) {
        int size = 0;
        int count = 0;
        int clause = 0;
        char[] keys = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k'};
        T[] dict = new T[keys.length];
        List<T> list = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filepath));
            String line;
            while ((line = reader.readLine()) != null) {
                size++;
            }
            reader.close();
            clause = size * 4;

            reader = new BufferedReader(new FileReader(filepath));
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                for (int i = 0; i < line.split(" ").length; i++) {
                    clause += 3;
                }
            }
            reader.close();

            for (int i = 0; i < size; i++) {
                T t = new T(count + 1, count + 2, count + 3);
                list.add(t);
                dict[i] = t;
                count += 3;
            }

            int variable = count;

            for (int i = 0; i < size; i++) {
                System.out.println(keys[i] + ": " + list.get(i).toString());
            }

            System.out.println("P cnf " + clause + " " + variable);

            for (int i = 0; i < size; i++) {
                T item = list.get(i);
                System.out.println(item.a + " " + item.b + " " + item.c + " 0");
                clause += 1;
                System.out.println("-" + item.a + " -" + item.b);
                clause += 1;
                System.out.println("-" + item.a + " -" + item.c);
                clause += 1;
                System.out.println("-" + item.b + " -" + item.c);
                clause += 1;
            }

            reader = new BufferedReader(new FileReader(filepath));
            count = 0;
            while ((line = reader.readLine()) != null) {
                int currentNodeIndex = count;
                count++;

                line = line.trim();
                String[] ss = line.split(" ");
                for (int i = 0; i < ss.length; i++) {
                    int connectedNodeIndex = findIndex(keys, ss[i].charAt(0));
                    System.out.println("-" + list.get(currentNodeIndex).a + " -" + list.get(connectedNodeIndex).a);
                    clause += 1;
                    System.out.println("-" + list.get(currentNodeIndex).b + " -" + list.get(connectedNodeIndex).b);
                    clause += 1;
                    System.out.println("-" + list.get(currentNodeIndex).c + " -" + list.get(connectedNodeIndex).c);
                    clause += 1;
                }
            }
            reader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int findIndex(char[] keys, char k) {
        for (int i = 0; i < keys.length; i++) {
            if (keys[i] == k) {
                return i;
            }
        }
        return -1;
    }
}
