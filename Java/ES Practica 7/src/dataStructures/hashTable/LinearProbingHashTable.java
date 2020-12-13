/**
 * ALUMNO:
 * GRUPO:
 * <p>
 * Hash Table with linear probing to resolve collisions
 */

package dataStructures.hashTable;

import java.util.Iterator;
import java.util.NoSuchElementException;

import dataStructures.tuple.Tuple2;

/**
 * Hash tables whose entries are associations of different keys to values
 * implemented using open addressing (linear probing). Note that keys should
 * define {@link java.lang.Object#hashCode} method properly.
 *
 * @param <K> Type of keys.
 * @param <V> Types of values.
 */
public class LinearProbingHashTable<K, V> implements HashTable<K, V> {

    private K keys[];
    private V values[];
    private int size;
    private double maxLoadFactor;

    /**
     * Creates an empty linear probing hash table.
     * <p>Time complexity: O(n)
     *
     * @param numCells   Initial number of cells in table (should be a prime number).
     * @param loadFactor Maximum load factor to tolerate. If exceeded, rehashing is performed automatically.
     */
    @SuppressWarnings("unchecked")
    public LinearProbingHashTable(int numCells, double loadFactor) {
        keys = (K[]) new Object[numCells];
        values = (V[]) new Object[numCells];
        size = 0;
        maxLoadFactor = loadFactor;
    }

    /**
     * True if table stores no associations
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns number of associations stored in table
     */
    public int size() {
        return size;
    }

    /**
     * Returns array index for a key
     */
    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % keys.length;
    }

    /**
     * Returns current load factor
     */
    private double loadFactor() {
        return (double) size / (double) keys.length;
    }


    /**
     * Takes key of association and returns
     * its insertion position in the table.
     * Collisions must be resolved using linear probing algorithm)
     */
    private int searchIdx(K key) {
        // to be completed
        int i = hash(key);
        while (keys[i] != null && !keys[i].equals(key)) {
            i = (i + 1) % keys.length;
        }

        return i;
    }

    /**
     * Returns value associated with key parameter or null if
     * association does not exist
     */
    public V search(K key) {
        return values[searchIdx(key)];
    }

    /**
     * Returns true if key is associated or false if
     * association does not exist
     */
    public boolean isElem(K key) {
        // to be completed
        return search(key) != null;
    }

    /**
     * Inserts a new association in table. If key was already
     * present in table, associated value is modified.
     */
    public void insert(K key, V value) {
        if (loadFactor() > maxLoadFactor)
            rehashing();

        int id = searchIdx(key);

        if (keys[id] == null) {
            keys[id] = key;
            size++;
        }
        values[id] = value;
    }

    /**
     * If key is associated, association is removed from table.
     * If association does not exist, table is not modified.
     * In order to implement this operation, ypu should firstly locate
     * corresponding position (p) in table for key to be deleted,
     * assign null to keys[p] and values[p], and remove from table
     * and reinsert again all elements in same cluster (all elements
     * in table after deleted one until finding an empty cell).
     */
    public void delete(K key) {
        int id = searchIdx(key);

        if (keys[id] != null) {
            keys[id] = null;
            values[id] = null;
            size--;
            // Clave borrada

            id = (id + 1) % keys.length;

            while (keys[id] != null) {
                V nValue = values[id];
                K nKey = keys[id];

                values[id] = null;
                keys[id] = null;

                int nId = searchIdx(nKey);

                keys[nId] = nKey;
                values[nId] = nValue;

                id = (id + 1) % keys.length;
            }
        }
    }


    /**
     * Doubles table capacity and reshashes elements.
     */
    @SuppressWarnings("unchecked")
    private void rehashing() {
        // compute new table size
        int newCapacity = HashPrimes.primeDoubleThan(keys.length);
        //System.out.printf("REHASH %d\n",newCapacity);
        K oldKeys[] = keys;
        V oldValues[] = values;

        keys = (K[]) new Object[newCapacity];
        values = (V[]) new Object[newCapacity];

        // reinsert elements in new table
        for (int i = 0; i < oldKeys.length; i++)
            if (oldKeys[i] != null) {
                int newIdx = searchIdx(oldKeys[i]);
                keys[newIdx] = oldKeys[i];
                values[newIdx] = oldValues[i];
            }
    }


    // Almost an iterator
    private class TableIter {
        private int visited; // number of elements already visited by this iterator
        protected int nextIdx; // index of next element to be visited by this iterator

        public TableIter() {
            visited = 0;
            nextIdx = -1; // so that after first increment it becomes 0
        }

        public boolean hasNext() {
            return visited < size;
        }

        // advance nextIdx to be index of next to be visited element
        public void advance() {
            if (!hasNext()){
                throw new NoSuchElementException();
            }

            do {
                nextIdx = (nextIdx + 1) % keys.length;
                visited++;

            } while (keys[nextIdx] != null);
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

    private class KeysIter extends TableIter implements Iterator<K> {
        public K next() {
            advance();
            return keys[nextIdx];
        }
    }

    private class ValuesIter extends TableIter implements Iterator<V> {
        public V next() {
            advance();
            return values[nextIdx];
        }
    }

    private class KeysValuesIter extends TableIter implements Iterator<Tuple2<K, V>> {
        public Tuple2<K, V> next() {
            advance();
            return new Tuple2<>(keys[nextIdx], values[nextIdx]);
        }
    }

    public Iterable<K> keys() {
        return KeysIter::new;
    }

    public Iterable<V> values() {
        return ValuesIter::new;
    }

    public Iterable<Tuple2<K, V>> keysValues() {
        return KeysValuesIter::new;
    }

    /**
     * Returns representation of hash table as a String.
     */
    public String toString() {
        String className = getClass().getName().substring(getClass().getPackage().getName().length() + 1);
        StringBuilder s = new StringBuilder(className + "(");
        if (!isEmpty()) {
            for (Tuple2<K, V> t : keysValues())
                s.append(t._1()).append("->").append(t._2()).append(",");
            s = new StringBuilder(s.substring(0, s.length() - 1));
        }
        s.append(")");
        return s.toString();
    }

}
