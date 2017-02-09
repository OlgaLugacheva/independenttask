package main;
import static main.Chronometer.lock;

/**
 * Created by Olga on 09.02.2017.
 */
public class Main {

    public static long startTime = System.currentTimeMillis();
    public static long currentTime = System.currentTimeMillis();

    public static void main(String[] args) {

        Thread thread1 = new Thread(() -> {
            while (true) {
                currentTime = System.currentTimeMillis() - startTime;
                synchronized (lock) {
                    lock.notifyAll();
                }
                try {

                    Thread.sleep(1000);

                    System.out.println(Main.currentTime + " " + Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread1.setName("поток 1");
        Thread thread2 = new Thread(new Chronometer(5));
        thread2.setName("поток 2");
        Thread thread3 = new Thread(new Chronometer(7));
        thread3.setName("поток 3");

        thread2.start();
        thread3.start();
        thread1.start();
    }
}
