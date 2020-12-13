/**
 * Estructuras de Datos. Grado en Informática, IS e IC. UMA.
 * Examen de Febrero 2015.
 * <p>
 * Implementación del TAD Deque
 * <p>
 * Apellidos:
 * Nombre:
 * Grado en Ingeniería ...
 * Grupo:
 * Número de PC:
 */

package dataStructures.doubleEndedQueue;

public class LinkedDoubleEndedQueue<T> implements DoubleEndedQueue<T> {

    private static class Node<E> {
        private E elem;
        private Node<E> next;
        private Node<E> prev;

        public Node(E x, Node<E> nxt, Node<E> prv) {
            elem = x;
            next = nxt;
            prev = prv;
        }
    }

    private Node<T> first, last;

    /**
     *  Invariants:
     *  if queue is empty then both first and last are null
     *  if queue is non-empty:
     *      * first is a reference to first node and last is ref to last node
     *      * first.prev is null
     *      * last.next is null
     *      * rest of nodes are doubly linked
     */

    /**
     * Complexity: 1
     */
    public LinkedDoubleEndedQueue() {
        // TODO Auto-generated method stub
    }

    /**
     * Complexity: 1
     */
    @Override
    public boolean isEmpty() {
        // TODO Auto-generated method stub
        return first == null && last == null;
    }

    /**
     * Complexity:
     */
    @Override
    public void addFirst(T x) {
        // TODO Auto-generated method stub
        Node<T> nuevoFirst = new Node<>(x, null, null);

        if (isEmpty()){
            first = new Node<>(null, null, null);
            last = new Node<>(null, null, null);

            first.next = nuevoFirst;
            first.next.prev = first;

            last.prev = nuevoFirst;
            last.prev.next = last;
        } else {
            nuevoFirst.next = first.next;
            first.next.prev = nuevoFirst;

            nuevoFirst.prev = first;
            first.next = nuevoFirst;
        }


    }

    /**
     * Complexity:
     */
    @Override
    public void addLast(T x) {
        // TODO Auto-generated method stub
        Node<T> nuevoLast = new Node<>(x,null , null);

        if (isEmpty()){
            first = new Node<>(null, null, null);
            last = new Node<>(null, null, null);

            first.next = nuevoLast;
            first.next.prev = first;

            last.prev = nuevoLast;
            last.prev.next = last;
        } else {
            nuevoLast.prev = last.prev;
            last.prev.next = nuevoLast;

            nuevoLast.next = last;
            last.prev = nuevoLast;
        }


    }

    /**
     * Complexity: 1
     */
    @Override
    public T first() {
        // TODO Auto-generated method stub
        return first.next.elem;
    }

    /**
     * Complexity: 1
     */
    @Override
    public T last() {
        // TODO Auto-generated method stub
        return last.prev.elem;
    }

    /**
     * Complexity:
     */
    @Override
    public void deleteFirst() {
        // TODO Auto-generated method stub
       if (this.first.next.elem.equals(this.last.prev.elem)){
           first = null;
           last = null;
       }else{
           first.next.next.prev = first;
           first.next = first.next.next;
       }

    }

    /**
     * Complexity:
     */
    @Override
    public void deleteLast() {
        // TODO Auto-generated method stub
        if(this.first.next.equals(this.last.prev)){
            first = null;
            last = null;

        }else{
            last.prev.prev.next = last;
            last.prev = last.prev.prev;



        }

    }

    /**
     * Returns representation of queue as a String.
     */
    @Override
    public String toString() {
        String className = getClass().getName().substring(getClass().getPackage().getName().length() + 1);
        StringBuilder s = new StringBuilder(className + "(");
        for (Node<T> node = first; node != null; node = node.next)
            s.append(node.elem).append(node.next != null ? "," : "");
        s.append(")");
        return s.toString();
    }
}
