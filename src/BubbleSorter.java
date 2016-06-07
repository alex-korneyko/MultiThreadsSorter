import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex Korneyko on 07.06.2016.
 */
public class BubbleSorter<T extends Comparable> {

    private long lastSortTime = 0;

    public void ascending(List<T> list, int threadsNumber) {

        List<Thread> threads = new ArrayList<>();

        lastSortTime = System.currentTimeMillis();

        for (int i = 0; i < threadsNumber; i++) {
            final Thread thread = new Thread(new Sort(list));
            threads.add(thread);
            thread.start();
        }

        boolean allThreadsStop = false;
        while (!allThreadsStop) {
            allThreadsStop = true;
            for (Thread thread : threads) {
                if (thread.getState() != Thread.State.TERMINATED) {
                    allThreadsStop = false;
                    break;
                }
            }
        }
        lastSortTime = System.currentTimeMillis() - lastSortTime;

    }

    private void sortAlgorithm(List<T> list) {
        boolean listChanged = true;
        while (listChanged) {
            listChanged = false;
            for (int i = 1; i < list.size(); i++) {
                if (list.get(i - 1).compareTo(list.get(i)) > 0) {
                    swapElements(list, i - 1, i);
                    listChanged = true;
                }
            }
        }
    }


    private class Sort implements Runnable {

        List<T> list;

        public Sort(List<T> list) {
            this.list = list;
        }

        @Override
        public void run() {
            sortAlgorithm(list);
        }
    }


    public long getLastSortTime() {
        return lastSortTime;
    }

    private void swapElements(List<T> list, int elementA, int elementB) {
        T temp = list.get(elementA);
        list.set(elementA, list.get(elementB));
        list.set(elementB, temp);
    }
}
