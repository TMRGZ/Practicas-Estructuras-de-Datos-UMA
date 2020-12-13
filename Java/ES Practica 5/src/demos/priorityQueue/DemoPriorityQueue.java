package demos.priorityQueue;

import java.util.Random;

import dataStructures.priorityQueue.PriorityQueue;
import dataStructures.priorityQueue.WBLeftistHeapPriorityQueue;

public class DemoPriorityQueue {

	public static void main(String[] args) {
		PriorityQueue<Integer> pq = new WBLeftistHeapPriorityQueue<>();
		Random random = new Random();
		for (int i = 0; i < 100; i++) {
			int n = random.nextInt(10);
			pq.enqueue(n);
			System.out.println("Se encola " + n);
		}
		while (!pq.isEmpty()) {
			int n = pq.first();
			pq.dequeue();
			System.out.println(" Se desencola " + n);
		}
	}
}
