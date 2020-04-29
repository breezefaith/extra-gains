//Thread Example 3
//Creates three threads, runs them concurrently but each thread is paused for a random
//time between 0 and 3 seconds
public class ThreadEx3 extends Thread
{
 public static void main(String[] args)
 {
 ThreadEx3 thread1, thread2, thread3, thread4, thread5;
 thread1 = new ThreadEx3();
 thread2 = new ThreadEx3();
 thread3 = new ThreadEx3();
 thread4 = new ThreadEx3();
 thread5 = new ThreadEx3();
 thread1.start();
 thread2.start();
 thread3.start();
 thread4.start();
 thread5.start();
 }
 public void run()
 {
   int pause;
   String[] StarWarChars={"Han Solo", "Darth Vader", "Luke Skywalker", "Chewbacca", "BB-8"};
   for (int i=0; i<5; i++)
   {
      
      try{
         int a=Integer.parseInt(getName().substring(getName().length()-1));
         System.out.println(StarWarChars[a] + " awake!");
         pause = (int)(Math.random()*3000);
         System.out.println("Sleep time for "+ StarWarChars[a] + ":" +
         pause + " millisecs");
         sleep(pause);
      }
      catch (InterruptedException e)
      {
          System.out.println(e);
      }
   }
 }
}