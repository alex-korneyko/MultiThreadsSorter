import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.*;

/**
 * Created by Alex Korneyko on 07.06.2016.
 */
public class BubbleSorter<T extends Comparable<T>> {

    public long sort(List<T> list, int threadsNumber, SortValidator<? super T> sortValidator) throws InterruptedException {

        List<BobbleSortThread> threads = new ArrayList<>();

        long sortTime = System.currentTimeMillis();
        boolean listChanged = true;

        while (listChanged) {
            while (threads.size() >= threadsNumber) {
                threads.removeIf(t -> t.getState() == State.TERMINATED && t.listChanged);
            }

            threads.add(new BobbleSortThread(list));
            threads.get(threads.size() - 1).start();
            threads.get(threads.size() - 1).join();

            for (BobbleSortThread thread : threads) {
                if (thread.getState() == State.TERMINATED && !thread.listChanged) {
                    listChanged = false;
                }
            }
        }

        sortTime = System.currentTimeMillis() - sortTime;
        return sortValidator.isValid(list, true) ? sortTime : -1;

    }

    class BobbleSortThread extends Thread {

        List<T> list;
        int position = 0;
        volatile boolean listChanged = false;

        public BobbleSortThread(List<T> list) {
            this.list = list;
        }

        @Override
        public void run() {
            for (int i = 1; i < list.size(); i++) {
                if (list.get(i - 1).compareTo(list.get(i)) > 0) {
                    Utils.swapElements(list, i - 1, i);
                    listChanged = true;
                }
            }
        }
    }

}
