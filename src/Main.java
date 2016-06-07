import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex Korneyko on 07.06.2016.
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {

        BubbleSorter<Integer> integerBubbleSorter = new BubbleSorter<>();
        List<Integer> exampleList = listRndGenerator(100000, 1, 1000);

        integerBubbleSorter.ascending(exampleList, 2);
        System.out.println(integerBubbleSorter.getLastSortTime()
                + "ms. List sorted: " + testSortedList(exampleList));

    }

    public static List<Integer> listRndGenerator(int size, int minValue, int maxValue) {
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
}
