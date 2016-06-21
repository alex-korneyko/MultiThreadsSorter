import java.util.Arrays;
import java.util.List;

/**
 * Created by Alex Korneyko on 07.06.2016.
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {

        BubbleSorter<Integer> integerBubbleSorter = new BubbleSorter<>();
//        InsertionSorter<Integer> integerInsertionSorter = new InsertionSorter<>();

        List<Integer> exampleList;

        for (int i = 10; i >0; i--) {
            exampleList = Utils.listIntsRndGenerator(10000, 1, 100);
            long time = integerBubbleSorter.sort(exampleList, i, new SimpleSortValidator<>());
            System.out.println("Threads: " + i + " sorting in " + time + "ms.");
        }
    }
}
