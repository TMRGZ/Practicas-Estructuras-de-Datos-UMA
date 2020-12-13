package dataStructures.bag;

/**
 * Interface for the bag ADT.
 * 
 * @param <T>
 *            Type of elements in bag. Note that {@code T} must be
 *            {@code Comparable}
 */
public interface Bag<T extends Comparable<? super T>> {
	/**
	 * Test the bag for emptiness.
	 * 
	 * @return {@code true} if bag is empty, else {@code false}.
	 */
	boolean isEmpty();

	/**
	 * Inserts a new occurrence in the bag.
	 * 
	 * @param item
	 *            the element to insert.
	 */
	void insert(T item);

	/**
	 * Returns the number of occurrences of {@code item} in the bag
	 * 
	 * @param item
	 *            the item to be counted
	 * @return number of occurrences of {@code item}
	 */
	int occurrences(T item);

	/**
	 * Removes an occurrence of {@code item} from the bag
	 * 
	 * @param item
	 *            the item to remove
	 */
	void delete(T item);
}
