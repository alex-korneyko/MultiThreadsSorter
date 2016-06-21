import java.util.List;

/**
 * Interface for validating of collection
 * @param <T>
 */
public interface SortValidator<T> {

    /**
     * Validating
     * @param valuesList Collection for validating
     * @param sortDirect Direction of sorting. True - from larger to smaller, False - on the contrary
     * @return true - collection is sorted, false - not sorted
     */
    public boolean isValid(List<? extends T> valuesList, boolean sortDirect);
}
