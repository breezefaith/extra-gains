import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.util.Iterator;

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
        // create an EvenIterator for the array
        Iterator it = new EvenIterator(0, numbers.length-1, numbers);
        // run the iterator to completion
        while(it.hasNext()){
            System.out.println(it.next());
        }
        
        // check if NoSuchElementException is thrown on hasNext false
        try {
            it.next();
            System.out.println("Should not get printed");
        } catch (NoSuchElementException e) {
            // quiet
        }
    }
    private static class EvenIterator implements Iterator{

        private int current;
        private final int end;
        private final Integer[] numbers;

        public EvenIterator(int start, int end, Integer[] numbers){
            //Your code here
            this.current = start;
            this.end = end;
            this.numbers = numbers;
        }

        @Override
        public boolean hasNext(){
            //Your code here
            if(current>end){
                return false;
            }
            for(int i=current;i<=end;i++){
                if(numbers[i]%2==0){
                    return true;
                }
            }
            return false;
        }

        @Override
        public Integer next() throws java.util.NoSuchElementException{
            // this line should always go first in your next() implementations
            // it ensures the fourth property of Iterators, that if hasNext is
            // false then calls to next give a NoSuchElementException
            if (!hasNext()) throw new java.util.NoSuchElementException();
            
            // Your code here
            for(int i=current;i<=end;i++){
                if(numbers[i]%2==0){
                    current = i+1;
                    return numbers[i];
                }
            }
            return null;
        }

    }
}