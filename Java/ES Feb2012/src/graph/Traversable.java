/**
 * @author Pepe Gallardo, Data Structures, Grado en Inform�tica. UMA.
 *
 * Interface for a container defining a successors method
 */

package graph;

import set.Set;

public interface Traversable<T> {
	Set<T> successors(T x);
}
