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
            if (sz == 1) return new Unif<>(x);

            else if (i < sz / 2) return new Node<>(set(sz / 2, i, x), new Unif<>(this.elem));

            else return new Node<>(new Unif<>(this.elem), set(sz / 2, i - sz / 2, x));
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
            T res;

            if (i < sz / 2) {
                res = left.get(sz / 2, i);
            } else {
                res = right.get(sz / 2, i - sz / 2);
            }

            return res;
        }

        @Override
        public Tree<T> set(int sz, int i, T x) {
            if (i < sz / 2) {
                left = left.set(sz/2, i, x);
            } else {
                right = right.set(sz/2, i-sz/2, x);
            }


            return new Node<>(left, right);
        }

        protected Tree<T> simplify() {
            Tree<T> res = this;

            if (left instanceof Unif<?> && right instanceof Unif<?>) {
                Unif<T> nRight = (Unif<T>) right;
                Unif<T> nLeft = (Unif<T>) left;

                if (nRight.elem.equals(nLeft.elem)) {
                    res = nRight;
                }
            }

            return res;
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
        if (n < 0) throw new VectorException("Numero negativo");

        root = new Unif(elem);
        size = (int) Math.pow(2, n);
    }

    public int size() {
        return size;
    }

    public T get(int i) {
        return root.get(size, i);
    }

    public void set(int i, T x) {
        root = root.set(this.size(), i, x);
    }

    @Override
    public Iterator<T> iterator() {
        Iterator<T> it = new Iterator<>() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < size();
            }

            @Override
            public T next() {
                return get(currentIndex++);
            }
        };

        return it;
    }

    @Override
    public String toString() {
        return "SparseVector(" + size + "," + root + ")";
    }
}
