/**
 * @author Pepe Gallardo, Data Structures, Grado en Informática. UMA.
 *
 * Interface for lists
 */

package dataStructures.list;

/**
 * Interface for positional lists. Each element is indexed by an integer,
 * corresponding to its position in the list. Index for first element in the
 * list (head) is 0. Index for last element is size-1.
 * 
 * @param <T> Type of elements in list.
 */
public interface List<T> extends Iterable<T> {
	/**
	 * Test for list emptiness.
	 * 
	 * @return {@code true} if list is empty, else {@code false}.
	 */
	boolean isEmpty();

	/**
	 * Retrieves number of elements in list.
	 * @return Number of elements in list.
	 */
	int size();

	/**
	 * Retrieves one element from list.
	 * 
	 * @param i Index of element to retrieve (0 for head of list, size-1 for last
	 *          element).
	 * @return Element in list at index {@code i}.
	 * @throws ListException
	 *           if index is invalid.
	 */
	T get(int i);

	/**
	 * Modifies value of an element in list. Does not insert a new element, just
	 * mutates old element.
	 * 
	 * @param i Index of element to modify (0 for head of list, size-1 for last
	 *          element).
	 * @param x New value for element at index {@code i}.
	 * @throws ListException
	 *           if index is invalid.
	 */
	void set(int i, T x);

	/**
	 * Inserts new element in list. Note that, after insertion, positions for old
	 * element at index {@code i} and successors get increased.
	 * 
	 * @param i Index corresponding to position for new element (0 for head of
	 *          list, size for end of list).
	 * @param x New element to insert.
	 * @throws ListException
	 *           if index is invalid.
	 */
	void insert(int i, T x);

	/**
	 * Inserts new element at end of list.
	 * 
	 * @param x New element to insert.
	 */
	void append(T x);

	/**
	 * Inserts new element at head of list. Note that, after insertion, positions for old
	 * elements get increased.
	 * 
	 * @param x New element to insert.
	 */
	void prepend(T x);

	/**
	 * Removes an element from list.
	 * 
	 * @param i Index of element to remove.
	 * @throws ListException
	 *           if index is invalid.
	 */
	void remove(int i);
}
