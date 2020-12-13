/******************************************************************************
 * Student's name:
 * Student's group:
 * Data Structures. Grado en Informática. UMA.
******************************************************************************/

package dataStructures.vector;

import java.util.Iterator;

public class SparseVector<T> implements Iterable<T> {

    protected interface Tree<T> {

        T get(int sz, int i);

        Tree<T> set(int sz, int i, T x);
    }

    // Unif Implementation

    protected static class Unif<T> implements Tree<T> {
        private T elem;

        public Unif(T e) {
            elem = e;
        }

        @Override
        public T get(int sz, int i) {	
        	return elem;
        }

        @Override
        public Tree<T> set(int sz, int i, T x) {
            if(sz == 1)     return new Unif<>(x);

            if(i < sz/2)    return new Node<>(set(sz/2, i, x), new Unif<>(this.elem));

            return new Node<>(new Unif<>(this.elem), set(sz/2, i - sz/2, x));
        }

        @Override
        public String toString() {
            return "Unif(" + elem + ")";
        }
    }

    // Node Implementation

    protected static class Node<T> implements Tree<T> {
        private Tree<T> left, right;

        public Node(Tree<T> l, Tree<T> r) {
            left = l;
            right = r;
        }

        @Override
        public T get(int sz, int i) {
            T devolver;

            if(i < sz/2)    devolver = (left instanceof Unif)   ?   left.get(sz, i) : left.get(sz/2, i);

            else            devolver = (right instanceof Unif)  ?   right.get(sz, i-sz/2) : right.get(sz/2, i-sz/2);

            return devolver;
        }
        

        @Override
        public Tree<T> set(int sz, int i, T x) {
            if(i < sz/2)    left = (left instanceof Unif)   ? left.set(sz/2, i, x) : left.set(sz/2, i, x);

            else            right = (right instanceof Unif) ? right.set(sz/2, i-sz/2, x) : right.set(sz/2, i-sz/2, x);

            return new Node<>(left,right);
        }

        public Tree<T> simplify() {
            Tree<T> devolver = this;

            if(left instanceof Unif<?> && right instanceof Unif<?>) {
                Unif<T> leftp = (Unif<T>) left;
                Unif<T> rightp = (Unif<T>) right;

                if(leftp.elem.equals(rightp.elem)) devolver = rightp;
            }
            return devolver;
        }

        @Override
        public String toString() {
            return "Node(" + left + ", " + right + ")";
        }
    }

    // SparseVector Implementation

    private int size;
    private Tree<T> root;

    public SparseVector(int n, T elem) {
        if(n < 0) throw new VectorException("n es negativo");

        size = (int) Math.pow(2, n);
        root = new Unif(elem);
    }

    public int size() {
        return size;
    }

    public T get(int i) {
        if(i<0 || i>=size) throw new VectorException("Indice no bueno");
        return root.get(size, i);
    }

    public void set(int i, T x) {
        if(i<0) throw new VectorException("Indice no bueno");
        root = root.set(this.size, i, x);
    }

    @Override
    public Iterator<T> iterator() {
    	Iterator<T> it = new Iterator<T>() {

            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < size;
            }

            @Override
            public T next() {
                return get(currentIndex++);
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
        return it;
    }

    @Override
    public String toString() {
        return "SparseVector(" + size + "," + root + ")";
    }
}
