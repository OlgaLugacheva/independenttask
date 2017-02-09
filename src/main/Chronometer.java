package main;

/**
 * Created by Olga on 09.02.2017.
 */
public class Chronometer implements Runnable {
    private int count = 0;
    private final int iter;
    public static final Object lock = new Object();

    public Chronometer(int iter) {
        this.iter = iter;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (lock) {
                try {
                    lock.wait();
                    if (++count % iter == 0) {
                        System.out.println(Main.currentTime + " " +Thread.currentThread().getName());
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}