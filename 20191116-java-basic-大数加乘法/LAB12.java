/*
course: CSC190
project: Lab12
date: 
author: 
purpose: 
*/

import java.util.Scanner;
public class Lab12 {
    static String addL(String n1, String n2) {
        
    }
    
    static String mulL(String n1, String n2) {
        
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        
        String res;
        do {
            System.out.println("a. add decimal strings:");
            System.out.println("m. multiply decimal strings::");
            System.out.println("q. quit:");
            
            System.out.println("select:");
            res = in.next();
            switch (res.toLowerCase().charAt(0)) {
                case 'a':
                    System.out.print("Enter string 1: ");
                    String n1 = in.next();
                    System.out.print("Enter string 2: ");
                    String n2 = in.next();
                    System.out.println(n1+"+"+n2+" = "+addL(n1, n2));
                    break;
                case 'm':
                    System.out.print("Enter string 1: ");
                    n1 = in.next();
                    System.out.print("Enter string 2: ");
                    n2 = in.next();
                    System.out.println(n1+"+"+n2+" = "+mulL(n1, n2));
                    break;
                case 'q':
                    System.out.println("Have a good weekend!");
                    break;
                default:
                    System.out.println("Invalid option!  Try it again.");      
            }
                       
        } while (res.toLowerCase().charAt(0) != 'q'); 
    }   
}