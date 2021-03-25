package threads;

/**
 * @author : Alex
 * @created : 14.03.2021, воскресенье
 **/
public class Test {
    public static void main(String[] args) {
        LockTask task = new LockTask();

        task.set("Test1");
        System.out.println("task 1 added");


        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    try {
                        Thread.sleep(1000);
                        System.out.println(i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                String t = task.get();
                System.out.println("get:"+t);

            }
        }).start();

        task.set("Test2");
        System.out.println("task 2 added");
    }
}
