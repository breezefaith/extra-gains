//Thread Example 3
//Creates three threads, runs them concurrently but each thread is paused for a random
//time between 0 and 3 seconds
public class ThreadEx4 extends Thread
{
 public static void main(String[] args)
 {
 ThreadEx4 thread1, thread2, thread3, thread4, thread5;
 thread1 = new ThreadEx4();
 thread2 = new ThreadEx4();
 thread3 = new ThreadEx4();
 thread4 = new ThreadEx4();
 thread5 = new ThreadEx4();
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
   int b=0,a;
   for (int i=0; i<5; i++)
   {
      try{
         if(b==0){
            a=Integer.parseInt(getName().substring(getName().length()-1));
            pause = (int)(Math.random()*3000);
            System.out.println(StarWarChars[a] + ": Throw LightSaber!");
            sleep(pause);
            b=1;
         }
         else{
            a=Integer.parseInt(getName().substring(getName().length()-1));
            pause = (int)(Math.random()*3000);
            System.out.println("\t\t\t\t"+ StarWarChars[a] + ": Catch LightSaber!");
            sleep(pause);
            b=0;
         }
      }
      catch (InterruptedException e)
      {
          System.out.println(e);
      }
   }
 }
}