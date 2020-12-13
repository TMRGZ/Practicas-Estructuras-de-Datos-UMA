package dataStructures.priorityQueue;

import dataStructures.heap.MaxiphobicHeap;


public class MaxiphobicHeapPriorityQueue <T extends Comparable<? super T>> implements PriorityQueue<T> {

	private MaxiphobicHeap<T> heap;	
	
	/**
	 * Creates an empty queue.
	 */	
	public MaxiphobicHeapPriorityQueue() {
		heap = new MaxiphobicHeap<>();
	}
	
	/** 
	 * {@inheritDoc}
	 * <p>Time complexity: O(1)
	 */
	public boolean isEmpty() {
		return heap.isEmpty();
	}

	/** 
	 * {@inheritDoc}
	 * Position of new element in queue depends on its priority.
	 * The less the value of the element, the higher its priority. 
	 * <p>Time complexity: O(log n)
	 */
	public void enqueue(T x) {
		heap.insert(x);
	}

	/** 
	 * {@inheritDoc}
	 * <p>Time complexity: O(1)
	 * @throws <code>EmptyQueueException</code> if queue stores no element.
	 */
	public T first() {
		if(isEmpty())
			throw new EmptyPriorityQueueException("first on empty priority queue");
		else
			return heap.minElem();
	}

	/** 
	 * {@inheritDoc}
	 * Position of new element in queue depends on its priority.
	 * The less the value of the element, the higher its priority. 
	 * <p>Time complexity: O(log n)
	 */
	public void dequeue() {
		if(isEmpty())
			throw new EmptyPriorityQueueException("dequeue on empty priority queue");
		else
			heap.delMin();
	}		
	
	/** 
	 * Returns representation of priority queue as a String.
	 */
	@Override public String toString() {
		MaxiphobicHeap<T> clonedHeap = new MaxiphobicHeap<>(heap);
		String className = getClass().getName().substring(getClass().getPackage().getName().length()+1);  
		String s = className+"(";
		boolean stop = clonedHeap.isEmpty();
		while(!stop) {
			s += clonedHeap.minElem();
			clonedHeap.delMin();
			stop = clonedHeap.isEmpty();
			if(!stop)
				s += ",";
		}
		s += ")";
		return s;			
	}	

}
