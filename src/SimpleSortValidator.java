import java.util.List;

/**
 * Created by Alex Korneyko on 21.06.2016.
 */
public class SimpleSortValidator<T extends Comparable<T>> implements SortValidator<T> {

    @Override
    public boolean isValid(List<? extends T> valuesList, boolean sortDirect) {

        for (int i = 1; i < valuesList.size(); i++) {
            if (valuesList.get(i - 1).compareTo(valuesList.get(i)) > 0 && sortDirect)
                return false;

            if (valuesList.get(i - 1).compareTo(valuesList.get(i)) < 0 && !sortDirect)
                return false;
        }

        return true;

    }
}
