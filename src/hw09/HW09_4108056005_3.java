// case3_BFS from farthest vertex: O(V^2^)
//package hw09;

import java.util.ArrayList;

public class HW09_4108056005_3 extends LSD {
	
	int _vtxNum = 2000000;
	ArrayList<Integer> adjList[] = new ArrayList[_vtxNum];
	ArrayList<Integer> maxCmp = new ArrayList<Integer>();
	boolean marked[] = new boolean[_vtxNum];
	int maxDegree = 0, maxDegreeVtx = 0;
	
	private int qSize = _vtxNum;
	private int queue[][] = new int[qSize][2];
	private int rear = 0, front = 0;
	
	public HW09_4108056005_3() {
		for(int i = 0; i < _vtxNum; i++) {
			adjList[i] = new ArrayList<Integer>();
			marked[i] = false;
		}
	}
	
//	public static void main(String[] args) {
//		HW09_4108056005_3 test = new HW09_4108056005_3();
////		int[][] inputArr = { { 0, 1 }, { 0, 2 }, { 0, 4 }, { 1, 3 }, { 1, 4 }, { 2, 5 }, { 6, 7 } };	// 4
////		int[][] inputArr = { { 1, 2 }, { 3, 2 }, { 5, 4 }, { 4, 6 }, { 7, 4 }, { 9, 8 } };	// 2
////		int[][] inputArr = { { 0, 1 }, { 0, 2 }, { 1, 3 }, { 1, 4 }, { 2, 4 }, { 2, 5 }, { 2, 6 }, { 3, 7 }, { 5, 6 }, { 5, 7 }, { 6, 9 }, { 7, 8 }, { 9, 10 } };	// 5
////		int[][] inputArr = { { 0, 1 }, { 1, 2 }, { 2, 3 }, { 3, 4 }};	// 4
////		int[][] inputArr = { { 0, 1 }, { 1, 2 }, { 2, 3 }, { 3, 1 }, { 2, 4 }, { 5, 4 }, { 6, 4 }, { 3, 7 }, { 7, 8 }, { 7, 10 }, { 8, 9 }};	// 6
//		int[][] inputArr = new TestDataGenerator().readData();
//		
//		System.out.println("case3:");
//		Stopwatch stopwatch = new Stopwatch();
//		int ans = test.Distance(inputArr);
//		double time = stopwatch.elapsedTime();
//		System.out.println("elapsed time " + time);
//		
//		System.out.println(ans);
//	}
	
	@Override
	public int Distance(int[][] array) {
		int arrLen = array.length;
		
		// create adj list of the graph
		for(int i = 0; i < arrLen; i++) {
			adjList[array[i][0]].add(array[i][1]);
			adjList[array[i][1]].add(array[i][0]);
			
//			System.out.println("add edge of "+array[i][0]+" and "+array[i][1]);
		}
		
//		show(adjList);
		
		// find the largest component
		int max = 0, maxVtx = 0;
		for(int i = 0; i < arrLen; i++) {
			int cmp = DFS(adjList, array[i][0]);	// use deep-first search in first vertex
			if(cmp > max) {
				max = cmp;
				maxVtx = array[i][0];
			}
			cmp = DFS(adjList, array[i][1]);	// use deep-first search in second vertex
			if(cmp > max) {
				max = cmp;
				maxVtx = array[i][1];
			}
		}
		clear(marked);
		
		cDFS(maxCmp, adjList, maxVtx);	// DFS again to pick the vertex in the largest component	
		clearMaxCmp(marked);
		
//		System.out.println("Max degree at "+maxDegreeVtx+", and its degree "+maxDegree);
//		System.out.println("Max cmp at "+maxVtx+", and its size "+max);
//		show(maxCmp);
		
		
		// use breadth-first search and queue to find the shortest path from maxDegreeVtx to all the other vertex	
		marked[maxDegreeVtx] = true;
		enqueue(queue, maxDegreeVtx, 0);
		while(!isEmpty(queue)) {	// if queue is empty, means all vertex has been visited
			int[] node = dequeue(queue);
			int nv = node[0], ns = node[1];
			for(int j = 0; j < adjList[nv].size(); j++) {
				// if this vertex has not been visited, then enqueue it
				if(!marked[adjList[nv].get(j)]) {
					enqueue(queue,(int) adjList[nv].get(j), ns+1);
					marked[adjList[nv].get(j)] = true;
				}
			}
		}
//		System.out.println("farthest = "+queue[front][0]+", "+queue[front][1]);
		clearMaxCmp(marked);
		
		int farthestVtx = queue[front][0];	// last element in queue must be the farthest vertex
		// use breadth-first search and queue to find the shortest path from farthest vertex to all the other vertex	
		marked[farthestVtx] = true;
		enqueue(queue, farthestVtx, 0);
		while(!isEmpty(queue)) {	// if queue is empty, means all vertex has been visited
			int[] node = dequeue(queue);
			int nv = node[0], ns = node[1];
			for(int j = 0; j < adjList[nv].size(); j++) {
				// if this vertex has not been visited, then enqueue it
				if(!marked[adjList[nv].get(j)]) {
					enqueue(queue,(int) adjList[nv].get(j), ns+1);
					marked[adjList[nv].get(j)] = true;
				}
			}
		}
		int lsd = queue[front][1];	// last element in queue must be the largest shortest path of this vertex
//		System.out.println("lsd = "+lsd);
		
		return lsd;
	}
	
	private void clear(boolean[] arr) {
		for(int i = 0; i < arr.length; i++) {
			arr[i] = false;
		}
	}
	
	private void clearMaxCmp(boolean[] arr) {
		for(int i = 0; i < maxCmp.size(); i++) {
			arr[maxCmp.get(i)] = false;	// only set shrink component to false
		}
	}
	
	private int DFS(ArrayList[] arr, int v) {
		if(marked[v]) return 0;
		marked[v] = true;
		
		int degree = 0;
		for(int i = 0; i < arr[v].size(); i++) {
			degree += DFS(adjList, (int) arr[v].get(i));
		}
		
		return degree + 1;
	}
	
	private void cDFS(ArrayList<Integer> temp, ArrayList[] arr, int v) {
		if(marked[v]) return;
		marked[v] = true;
		temp.add(Integer.valueOf(v));
		
		if(maxDegree < arr[v].size()) {
			maxDegree = arr[v].size();
			maxDegreeVtx = v;
		}
		for(int i = 0; i < arr[v].size(); i++) {
			cDFS(temp, adjList, (int) arr[v].get(i));
		}
	}
	
	private void show(ArrayList[] arr) {
		System.out.println("edges:");
		for(int i = 0; i < arr.length; i++) {
			for(int j = 0; j < arr[i].size(); j++) {
				System.out.println(i+" -> "+arr[i].get(j));
			}
		}
	}
	
	private void show(ArrayList arr) {
		for(int i = 0; i < arr.size(); i++) {
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
}
