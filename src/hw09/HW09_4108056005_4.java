// case4_BFS from farthest vertex + resizing list
package hw09;

public class HW09_4108056005_4 extends LSD {
	
	int _vtxNum = 1500000;
	resizeArray adjList[] = new resizeArray[_vtxNum];
	resizeArray maxCmp = new resizeArray();
	boolean marked[] = new boolean[_vtxNum];
	
	private int qSize = _vtxNum/2;
	private int queue[][] = new int[qSize][2];
	private int rear = 0, front = 0;
	
	public HW09_4108056005_4() {
		for(int i = 0; i < _vtxNum; i++) {
			adjList[i] = new resizeArray();
			marked[i] = false;
		}
	}
	
	public static void main(String[] args) {
		HW09_4108056005_4 test = new HW09_4108056005_4();
//		int[][] inputArr = { { 0, 1 }, { 0, 2 }, { 0, 4 }, { 1, 3 }, { 1, 4 }, { 2, 5 }, { 6, 7 } };	// 4
//		int[][] inputArr = { { 1, 2 }, { 3, 2 }, { 5, 4 }, { 4, 6 }, { 7, 4 }, { 9, 8 } };	// 2
//		int[][] inputArr = { { 0, 1 }, { 0, 2 }, { 1, 3 }, { 1, 4 }, { 2, 4 }, { 2, 5 }, { 2, 6 }, { 3, 7 }, { 5, 6 }, { 5, 7 }, { 6, 9 }, { 7, 8 }, { 9, 10 } };	// 5
//		int[][] inputArr = { { 0, 1 }, { 1, 2 }, { 2, 3 }, { 3, 4 }};	// 4
//		int[][] inputArr = { { 0, 1 }, { 1, 2 }, { 2, 3 }, { 3, 1 }, { 2, 4 }, { 5, 4 }, { 6, 4 }, { 3, 7 }, { 7, 8 }, { 7, 10 }, { 8, 9 }};	// 6
		int[][] inputArr = new TestDataGenerator().readData();
		
		System.out.println("case4:");
		Stopwatch stopwatch = new Stopwatch();
		int ans = test.Distance(inputArr);
		double time = stopwatch.elapsedTime();
		System.out.println("elapsed time " + time);
		
		System.out.println(ans);
	}
	
	@Override
	public int Distance(int[][] array) {
		int arrLen = array.length;
		
		// create adj list of the graph
		int maxDegree = 0, maxDegreeVtx = 0;
		for(int i = 0; i < arrLen; i++) {
			adjList[array[i][0]].push(array[i][1]);
			adjList[array[i][1]].push(array[i][0]);
			
//			System.out.println("add edge of "+array[i][0]+" and "+array[i][1]);
			
			// find the max degree vertex
			if(maxDegree < adjList[array[i][0]].top()) {
				maxDegree = adjList[array[i][0]].top();
				maxDegreeVtx = array[i][0];
			}
			if(maxDegree < adjList[array[i][1]].top()) {
				maxDegree = adjList[array[i][1]].top();
				maxDegreeVtx = array[i][1];
			}
		}
		
//		show(adjList);
		System.out.println("Max degree at "+maxDegreeVtx+", and its degree "+maxDegree);
//		System.out.println("Max cmp at "+maxVtx+", and its size "+max);
//		show(maxCmp);
		
		// use breadth-first search and queue to find the shortest path from maxDegreeVtx to all the other vertex	
		marked[maxDegreeVtx] = true;
		enqueue(queue, maxDegreeVtx, 0);
		while(!isEmpty(queue)) {	// if queue is empty, means all vertex has been visited
			int[] node = dequeue(queue);
			int nv = node[0], ns = node[1];
			for(int j = 0; j < adjList[nv].top(); j++) {
				// if this vertex has not been visited, then enqueue it
				if(!marked[adjList[nv].get(j)]) {
					enqueue(queue,(int) adjList[nv].get(j), ns+1);
					marked[adjList[nv].get(j)] = true;
				}
			}
		}
//		System.out.println("farthest = "+queue[front][0]+", "+queue[front][1]);
		
		// use breadth-first search and queue to find the shortest path from farthest vertex to all the other vertex	
		int farthestVtx = queue[front][0];	// last element in queue must be the farthest vertex
		marked[farthestVtx] = false;
		enqueue(queue, farthestVtx, 0);
		while(!isEmpty(queue)) {	// if queue is empty, means all vertex has been visited
			int[] node = dequeue(queue);
			int nv = node[0], ns = node[1];
			for(int j = 0; j < adjList[nv].top(); j++) {
				// if this vertex has not been visited, then enqueue it
				if(marked[adjList[nv].get(j)]) {
					enqueue(queue,(int) adjList[nv].get(j), ns+1);
					marked[adjList[nv].get(j)] = false;
				}
			}
		}
		int lsd = queue[front][1];	// last element in queue must be the largest shortest path of this vertex
//		System.out.println("lsd = "+lsd);
		
		return lsd;
	}
	
	private void show(resizeArray[] arr) {
		System.out.println("edges:");
		for(int i = 0; i < arr.length; i++) {
			for(int j = 0; j < arr[i].top(); j++) {
				System.out.println(i+" -> "+arr[i].get(j));
			}
		}
	}
	
	private void show(resizeArray arr) {
		for(int i = 0; i < arr.top(); i++) {
			System.out.print(arr.get(i)+" ");
		}
		System.out.println();
	}
	
	private void show(int[] arr) {
		for(int i = 0; i < arr.length; i++) {
			System.out.print(arr[i]+" ");
		}
		System.out.println();
	}
	
	private boolean isEmpty(int[][] q) {
		if(rear == front) {
			return true;
		}
		return false;
	}
	
	private void enqueue(int[][] q, int v, int s) {
		if((rear+1)%qSize == front) {
			System.out.println("queue is full!");
		}
		else {
			rear = (rear+1)%qSize;
			queue[rear][0] = v;
			queue[rear][1] = s;
//			System.out.println("enqueue "+queue[rear][0]+", "+queue[rear][1]);
		}
	}
	
	private int[] dequeue(int[][] q) {
		if(rear == front) {
			System.out.println("queue is empty!");
			return null;
		}
		else {
			front = (front+1)%qSize;
//			System.out.println("dequeue "+queue[front][0]+", "+queue[front][1]);
			return queue[front];
		}
	}
	
	class resizeArray {
		private int top = 0;
		private int array[] = new int[8];
		
		public resizeArray() {
			// TODO Auto-generated constructor stub
		}
		
		public void resize(int newSize) {
			int copy[] = new int[newSize];
			System.arraycopy(array, 0, copy, 0, array.length);
			array = copy;
		}	
		public void push(int v) {
//			System.out.println("push "+v);
			if(top == array.length) {
//				System.out.println("size*2");
				resize(2*array.length);
			}
			array[top] = v;
			top++;
		}	
		public int pop() {
			top--;
			int v = array[top];
			array[top] = 0;
			if(top > 0 && top == array.length/4) {
				System.out.println("size/2");
				resize(array.length/2);
			}
			return v;
		}
		public int get(int i) {
			return array[i];
		}
		public int top() {
			return top;
		}
	}
}
