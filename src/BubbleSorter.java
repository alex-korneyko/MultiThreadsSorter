import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex Korneyko on 07.06.2016.
 */
public class BubbleSorter<T extends Comparable> {

    public long sort(List<T> list, int threadsNumber) throws InterruptedException {

        List<Thread> threads = new ArrayList<>();

        long lastSortTime = System.currentTimeMillis();

        for (int i = 0; i < threadsNumber; i++) {
            final Thread thread = new Thread(new SortThread(list));
            threads.add(thread);
            thread.start();
        }

        while (!Utils.allThreadsStopped(threads)) {
            Thread.sleep(100);
        }

        return System.currentTimeMillis() - lastSortTime;
    }

    private void sortAlgorithm(List<T> list) {
        boolean listChanged = true;
        while (listChanged) {
            listChanged = false;
            for (int i = 1; i < list.size(); i++) {
                if (list.get(i - 1).compareTo(list.get(i)) > 0) {
                    Utils.swapElements(list, i - 1, i);
                    listChanged = true;
                }
            }
        }
    }


    private class SortThread implements Runnable {

        List<T> list;
        public SortThread(List<T> list) {
            this.list = list;
        }

        @Override
        public void run() {
            sortAlgorithm(list);
        }
    }
}
