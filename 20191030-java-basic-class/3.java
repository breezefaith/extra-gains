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
        // create a DecreasingIterator for the list
        Iterator<Integer> it = new DecreasingIterator(l);
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
    private static class DecreasingIterator implements Iterator<Integer> {
        // Your instance variables
        private int current;
        private int end;
        private List<Integer> numbers;
        private Integer last;

        public DecreasingIterator(List<Integer> numbers){
            //Your code here
            this.numbers = numbers;
            this.current = 0;
            this.end = this.numbers.size()-1;
            last = null;
        }

        //Your methods
        public boolean hasNext(){
            if(numbers.size() == 0){
                return false;
            }

            if(current == 0){
                return true;
            }

            if(current > end){
                return false;
            }
            for(int i=current;i<=end;++i){
                if(numbers.get(i) < last){
                    return true;
                }
            }
            return false;
        }

        public Integer next() throws java.util.NoSuchElementException{
            if(!hasNext()) throw new java.util.NoSuchElementException();

            if(current == 0){
                last = numbers.get(0);
                current = current + 1;
                return last;
            }

            for(int i=current;i<=end;++i){
                if(numbers.get(i) < last){
                    current = i + 1;
                    last = numbers.get(i);
                    return last;
                }
            }
            return null;
        }
    }// end of DecreasingIterator class