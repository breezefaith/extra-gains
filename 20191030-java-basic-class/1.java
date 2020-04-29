import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.util.Scanner;
public class Solution {
    public static void main(String args[] ) throws Exception {
        Scanner scan = new Scanner(System.in);
        List<Integer> l = new LinkedList<>();
        // read the inputs and put them into the List
        while (scan.hasNextInt()) {
            l.add(scan.nextInt());
        }
        // convert List to array
        Integer[] numbers = new Integer[l.size()];
        numbers = l.toArray(numbers);
        // create iterator
        EveryOtherTimesTwo it = new EveryOtherTimesTwo(0,numbers.length-1,numbers);
        // run the iterator to completion
        while(it.hasNext()){
            System.out.println(it.next());
        }
    }
    private static class EveryOtherTimesTwo implements Iterator{
        private int current;
        private final int end;
        private final Integer[] numbers;
        
        public EveryOtherTimesTwo(int start, int end, Integer[] numbers){
            this.current = start;
            this.end = end;
            this.numbers = numbers;
        }

        @Override
        public boolean hasNext(){
            return this.current <= end;
        }

        @Override
        public Integer next(){
            Integer temp = numbers[current]*2;
            current+=2;
            return temp;
        }

    }
}