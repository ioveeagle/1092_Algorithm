package hw09;

public class Queue {
	
	private static int qSize = 4;
	private static int queue[] = new int[qSize];
	private int rear = 0, front = 0;
	
	public static void main(String[] args) {
		Queue test = new Queue();
		
		test.dequeue(queue);
		test.enqueue(queue, 1);
		test.enqueue(queue, 2);
		test.enqueue(queue, 3);
		test.enqueue(queue, 4);
		test.dequeue(queue);
		test.dequeue(queue);
		test.dequeue(queue);
		test.dequeue(queue);
		test.dequeue(queue);
	}
	
	private boolean isEmpty(int[] q) {
		if(rear == front) {
			return true;
		}
		return false;
	}
	
	private void enqueue(int[] q, int v) {
		if((rear+1)%qSize == front) {
			System.out.println("queue is full!");
		}
		else {
			rear = (rear+1)%qSize;
			queue[rear] = v;
			System.out.println("enqueue "+queue[rear]);
		}
	}
	
	private int dequeue(int[] q) {
		if(rear == front) {
			System.out.println("queue is empty!");
			return -1;
		}
		else {
			front = (front+1)%qSize;
			System.out.println("dequeue "+queue[front]);
			return queue[front];
		}
	}
}
