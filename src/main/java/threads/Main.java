package threads;

import java.util.Random;

/**
 * @author : Alex
 * @created : 14.03.2021, воскресенье
 **/
public class Main {
    public static void main(String[] args) {
        LockTask queue = new LockTask();

        class Producer implements Runnable{
            final int count;
            public Producer(int count){this.count = count;}
            @Override
            public void run() {
                Random rand = new Random();
                for (int i = 0; i < 10; i++) {
                    try {
                        Thread.sleep(rand.nextInt(500));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    queue.set("Task ("+count+","+i+")");
                    System.out.println("--> "+i+" task from producer "+count);
                }
            }
        }

        class Consumer implements Runnable{
            final int count;
            public Consumer(int count){this.count = count;}
            public void run() {
                Random rand = new Random();
                for (int i = 0; i < 10; i++) {
                    String task = queue.get();
                    try {
                        Thread.sleep(rand.nextInt(500));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("complete "+task+" in  consumer "+count);
                }

            }
        }


        new Thread(new Producer(1)).start();
        new Thread(new Producer(2)).start();

        new Thread(new Consumer(1)).start();
        new Thread(new Consumer(2)).start();

    }
}
