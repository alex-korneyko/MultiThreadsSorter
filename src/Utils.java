import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex Korneyko on 08.06.2016.
 */
public class Utils {

    public static List<Integer> listIntsRndGenerator(int size, int minValue, int maxValue) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            double rnd = Math.random();
            Integer value = (int) (minValue + rnd * (maxValue - minValue + 1));
            list.add(value);
        }
        return list;
    }

    public static boolean testSortedList(List<Integer> list) {
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i - 1).compareTo(list.get(i)) > 0) {
                return false;
            }
        }
        return true;
    }

    public static <T> void  swapElements(List<T> list, int indexElementA, int indexElementB) {
        T temp = list.get(indexElementA);
        list.set(indexElementA, list.get(indexElementB));
        list.set(indexElementB, temp);
    }

    public static boolean allThreadsStopped(List<Thread> threads){
        for (Thread thread : threads) {
            if(thread.getState()!= Thread.State.TERMINATED)
                return false;
        }
        return true;
    }
}
