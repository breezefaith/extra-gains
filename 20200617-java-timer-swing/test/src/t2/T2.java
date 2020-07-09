package t2;

public class T2 {
    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            private int count = 20;

            @Override
            public void run() {
                try {
                    while (count > 0) {
                        System.out.println(count--);
                        Thread.sleep(1000);
                    }
                    System.out.println(count);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
    }
}
