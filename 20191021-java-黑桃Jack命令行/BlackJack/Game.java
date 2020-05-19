import java.util.*;
import java.io.*;
//import java.io.FileReader;
/**
 * Write a description of class Game here.
 *
 * @author Shaoxuan Wei
 * @version v0.1
 */
public class Game
{  
    private Player player1;
    private Player player2;
    private int round; 
    public void start(){
      System.out.println("##########################");
      System.out.println("Welcome to 21 ponits game!");
      System.out.println("##########################");
      System.out.println("");
      System.out.println("Please select from the follwing options");
      System.out.println("Press 1 to register a player");
      System.out.println("Press 2 to start a new game");
      System.out.println("Press 3 to view a help menu");
      System.out.println("Press 4 to exit");
      Scanner input = new Scanner(System.in);
      int a = input.nextInt();
      switch(a){
         case 1:
           // use Op1 method
           // pan duan you mei you shu ru yong hu ming
           while(!init()){
             System.out.println("Please input correct username!");
             System.out.println(" ");
            }
           start();
           break;
         case 2:
           // Only case 1 done go case 2, use try to processed.
           //kai shi you xi xuan xiang
            try{
              player1.getName().equals("");
            }catch(Exception e){
              System.out.println("Please register username first!");
              start();
            }
            System.out.println("Please enter the enter key to decide who begin first.");
            Scanner in = new Scanner(System.in);
            in.nextLine();
            //jue ding shei xian kai shi
            int i = roll(player1) - roll(player2);
            while(i==0){
              System.out.println("Please enter the enter key to decide who begin first.");
              in.nextLine();
              i = roll(player1) - roll(player2);
            }
            if(i>0){
              System.out.println("Please decide how mant round.");
              int round = in.nextInt();
              player1.setRoundWon(round);
              System.out.println("Game have benn set to finished in"+""+round+""+"round");
            }else{
              int num = (int)(Math.random()*5);
              if(num%2!=0){
                 System.out.println("Game have benn set to finished in"+""+num+""+"round");
                 player2.setRoundWon(num);
                }
                
            }
            for(int r = 0; i < round; i++){
              player1.initTile();
              player2.initTile();
              
              player1.setScore(0);
              player2.setScore(0);
              
              player1.initLast();
              player2.initLast();
              System.out.println("Both players have 5 tiles {1,2,3,5,7}");
              System.out.println("Please choice number.");
              in.nextLine();
              if(i>0){
                  ;
                  turn1(player2);
                }else{
                  turn1(player2);
                  turn(player1);
                }
            }
            break;
         case 3:
           // Help menu;
           //du qu bang zhu wen jian
           try{
             String name = "help.txt";
             BufferedReader file = new BufferedReader(new FileReader(name));
             String line = null;
             while((line = file.readLine())!=null){
                 System.out.println(line);
                }
             file.close();
           }catch(Exception e){
             System.out.println("The help menu is empty");
            }
           start();
           break;
         case '4':
           //exit;
           System.out.println("Have a good day!");
           break;
        }
    }
    //shu ru username
    public boolean init(){
      Scanner in = new Scanner(System.in);
      System.out.println("Please register your username:");
      String name = in.nextLine();
      if(name.length() < 3 || name.length()>10){
          System.out.println("The username length must between 3 and 10!");
          System.out.println(" ");
          return false;
        }
      if(name.startsWith(" ") || name.endsWith(" ")){
          System.out.println("The username must not begin with blank or end with!");
          System.out.println(" ");
          return false;
        }
      player1 = new Player(name);
      player2 = new Player("Computer");
      //display game info
      return true;
    }
    //jia fen
    public void increment(Player player, int score){
      player.setScore(player.getScore()+score);
    }
    //dian nao sui ji xuan pai
    public void turn1(Player player2){
      int num[] = new int[]{1,2,3,5,7}; 
      //Random random = new Random();
      int temp = (int)(Math.random()*num.length);
      int te = num[temp];
      System.out.println(te);
      switch(te){
          case 1:
            increment(player2,5);
            break;
          case 2:
            increment(player2,4);
            break;
          case 3:
            increment(player2,3);
            break;
          case 5:
            increment(player2,2);
            break;
          case 7:
            increment(player2,1);
            break;
        }
    }
    //wan jia xuan pai
    public void turn(Player player1){
      //xian shi shu zu
      int num[] = new int[]{1,2,3,5,7}; 
      System.out.println("shu zu");
      System.out.println("xuan ze");
      Scanner in = new Scanner(System.in);
      int num1 = in.nextInt();
      
      switch(num1){
          case 1:
            increment(player1,5);
            break;
          case 2:
            increment(player1,4);
            break;
          case 3:
            increment(player1,3);
            break;
          case 5:
            increment(player1,2);
            break;
          case 7:
            increment(player1,1);
            break;
        }
    }
    //reng sai jue ding shei kai shi
    public int roll(Player player){
      RNG roll = new RNG(6,1);
      int i = roll.roll();
      System.out.println(player.getName()+"rolls:"+""+i);
      return i;
    }
    
    /*public boolean finish(Player player){
      
      Tile num1 = player1.getLast();
      Tile num2 = player2.getLast();
      //if(num1 + num2 >= 21){
         
        //}
      return false;
    }*/
    /*
    public void a(Player player)
    {
        for(int i = 0; i < player.getTiles().length;i++)
        {
            
        }
    }
    public boolean isFinish(Player player)
    {
        
    }
    public void sum(Player player1,Player player2)
    {
        int total = player1.getTiles();
    }
 
    public void playOne(Player player, int sum)
    {
        for(int i = 0; i < player.getTiles().length; i++)
        {
            System.out.print(player.getTiles()[i].getValue() + ", ");
        }
        Scanner in = new Scanner(System.in);
        int sc = in.nextInt();
        boolean a = true;
        while(a)
        {
            
            for(int i = 0; i < player.getTiles().length; i++)
            {
                if(sc == player.getTiles()[i].getValue())
                {
                    a = false;
                }
            }
        }
        sum = + sum;
        if(sum <= 21)
        {
            for(int i = 0; i < player.getTiles().length; i++)
            {
                if(player.getTiles()[i].getValue() == sc)
                {
                    int newSc = player.getScore() + player.getTiles()[i].getScore();
                    player.setScore(newSc);
                    player.getTiles()[i] = null;
                }
            }
        }
        
    }*/
    //while loop
    /*public int roundNum()
    {
        return 3
    }*/
}
