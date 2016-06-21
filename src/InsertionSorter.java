import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

/**
 * Created by Alex Korneyko on 08.06.2016.
 */
public class InsertionSorter<T extends Comparable<T>> {


    public long sort(List<T> list, int threadsNumber) throws InterruptedException {

        final Lock lock = new ReentrantLock(true);
        long startTime = System.currentTimeMillis();

        IntStream.range(1, list.size()).forEach(i -> {
            if (list.get(i - 1).compareTo(list.get(i)) > 0) {
                new Thread(() -> {
                    lock.lock();
                    try {
                        for (int j = i; j > 0; j--) {
                            if (list.get(j - 1).compareTo(list.get(j)) > 0) {
                                Utils.swapElements(list, j - 1, j);
                            }
                        }
                    } finally {
                        lock.unlock();
                    }
                }).start();
            }
        });

        Thread.sleep(1000);

        return System.currentTimeMillis() - startTime;
    }
}
