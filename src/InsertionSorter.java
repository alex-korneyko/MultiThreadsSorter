import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Created by Alex Korneyko on 08.06.2016.
 */
public class InsertionSorter<T extends Comparable> {

    public long sort(List<T> list, int threadsNumber) throws InterruptedException {
        List<Thread> threads = new ArrayList<>();
        long startTime = System.currentTimeMillis();

        for (int i = 1; i < list.size(); i++) {
            if (list.get(i - 1).compareTo(list.get(i)) > 0) {

                threads.add(new Thread(new BackThread(list, i)));
                threads.get(threads.size()-1).start();

                while (threads.size() > 10) {
                    threads.removeIf(t -> t.getState() == Thread.State.TERMINATED);
                }
//                Thread.sleep(10);
            }
        }

        while (threads.size() > 0) {
            threads.removeIf(thread -> thread.getState() == Thread.State.TERMINATED);
        }

        return System.currentTimeMillis() - startTime;
    }


    private void backwardMotion(List<T> list, int fromIndex) {
        for (int j = fromIndex; j > 0; j--) {
            if (list.get(j - 1).compareTo(list.get(j)) > 0) {
                Utils.swapElements(list, j, j - 1);
            } else {
                break;
            }
        }
    }

    private class BackThread implements Runnable {

        List<T> list;
        int index;

        BackThread(List<T> list, int index) {
            this.list = list;
            this.index = index;
        }

        @Override
        public void run() {
            backwardMotion(list, index);
        }
    }
}
