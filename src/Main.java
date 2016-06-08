import java.util.List;

/**
 * Created by Alex Korneyko on 07.06.2016.
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {

        BubbleSorter<Integer> integerBubbleSorter = new BubbleSorter<>();
        InsertionSorter<Integer> integerInsertionSorter = new InsertionSorter<>();
        List<Integer> exampleList;

//        for (int i = 1; i < 10; i++) {
//            exampleList = Utils.listIntsRndGenerator(100000, 1, 1000);
//            long time = integerBubbleSorter.sort(exampleList, i);
//            System.out.println("Threads: " + i + " sorting in " + time + "ms. List sorted: " + Utils.testSortedList(exampleList));
//        }

        exampleList = Utils.listIntsRndGenerator(60, 1, 20);
        System.out.println(exampleList);
        long time = integerInsertionSorter.sort(exampleList, 1);
        System.out.println(exampleList);
        System.out.println("Time: " + time + " Sorted: " + Utils.testSortedList(exampleList));
    }
}
