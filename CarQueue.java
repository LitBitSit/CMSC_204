import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class CarQueue {

    private Queue<Integer> queue;
    private Random rand;

    public CarQueue() {
        queue = new LinkedList<>();
        rand = new Random();

        // Preload 5–6 random directions
        for (int i = 0; i < 6; i++) {
            queue.add(rand.nextInt(4));  // 0–3
        }
    }

    /**
     * Adds random directions (0–3) to queue continuously in a thread.
     */
    public void addToQueue() {

        Runnable r = new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        synchronized (queue) {
                            queue.add(rand.nextInt(4));
                        }
                        Thread.sleep(200);  // adjust speed if needed
                    }
                } catch (InterruptedException e) {
                    System.out.println("Queue thread interrupted");
                }
            }
        };

        Thread t = new Thread(r);
        t.start();
    }

    /**
     * Removes and returns the next direction in queue (or null).
     */
    public Integer deleteQueue() {
        synchronized (queue) {
            return queue.poll();
        }
    }
}
