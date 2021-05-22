// case3_shrink + guess: O(V+C), where C is the shrink component number
//package hw09;

import java.util.ArrayList;

public class HW09_4108056005_3 extends LSD {
	
	final int _vtxNum = 20000;
	ArrayList<Integer> adjList[] = new ArrayList[_vtxNum];
	boolean marked[] = new boolean[_vtxNum];
	boolean shrink[] = new boolean[_vtxNum];
	int[] info = new int[_vtxNum];
	static int lsd = 0, deepest = 0, deepestVtx = 0;
	
	private int qSize = _vtxNum/5;
	private int queue[][] = new int[qSize][2];
	private int rear = 0, front = 0;
	
	public HW09_4108056005_3() {
		for(int i = 0; i < _vtxNum; i++) {
			adjList[i] = new ArrayList<Integer>();
			marked[i] = false;
			shrink[i] = true;
		}
	}
	
//	public static void main(String[] args) {
//		HW09_4108056005_3 test = new HW09_4108056005_3();
//		int[][] inputArr = { { 0, 1 }, { 0, 2 }, { 0, 4 }, { 1, 3 }, { 1, 4 }, { 2, 5 }, { 6, 7 } };	// 4
////		int[][] inputArr = { { 1, 2 }, { 3, 2 }, { 5, 4 }, { 4, 6 }, { 7, 4 }, { 9, 8 } };	// 2
////		int[][] inputArr = { { 0, 1 }, { 0, 2 }, { 1, 3 }, { 1, 4 }, { 2, 4 }, { 2, 5 }, { 2, 6 }, { 3, 7 }, { 5, 6 }, { 5, 7 }, { 6, 9 }, { 7, 8 }, { 9, 10 } };	// 5
////		int[][] inputArr = { { 0, 1 }, { 1, 2 }, { 2, 3 }, { 3, 4 }};	// 4
////		int[][] inputArr = { { 0, 1 }, { 1, 2 }, { 2, 3 }, { 3, 1 }, { 2, 4 }, { 5, 4 }, { 6, 4 }, { 3, 7 }, { 7, 8 }, { 7, 10 }, { 8, 9 }};	// 6
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
			int cmp = DFS(adjList, i);	// use deep-first search
			if(cmp > max) {
				max = cmp;
				maxVtx = i;
			}
		}
		clear(marked);
		
		cDFS(adjList, maxVtx, -1);	// DFS again to pick the vertex in the largest component, and shrink together
		
//		System.out.println("Max cmp at "+maxVtx+", and its size "+max);
//		System.out.println("lsd = "+lsd);
//		show(info);
		
		// use breadth-first search and queue to find the shortest path to all the other vertex
		shrink[deepestVtx] = true; 
		
		enqueue(queue, deepestVtx, deepest);
		while(!isEmpty(queue)) {	// if queue is empty, means all vertex has been visited
			int[] node = dequeue(queue);
			int nv = node[0], ns = node[1];
			
			if(lsd < ns+info[nv]) {
				lsd = ns+info[nv];	// max path into this tree
			}
			for(int j = 0; j < adjList[nv].size(); j++) {
				// if this vertex has not been visited, then enqueue it
				if(!shrink[(int) adjList[nv].get(j)]) {
					enqueue(queue,(int) adjList[nv].get(j), ns+1);
					shrink[(int) adjList[nv].get(j)] = true;
				}
			}
		}
//		System.out.println("v = "+v+", maxStep = "+queue[front][1]+", lsd = "+lsd);
//		show(shortest);
		if(lsd < queue[front][1]) {		// last element in queue must be the largest shortest path of this vertex
			lsd = queue[front][1];
		}
//		System.out.println("lsd = "+lsd);
		
		return lsd;
	}
	
	private void clear(boolean[] arr) {
		for(int i = 0; i < arr.length; i++) {
			arr[i] = false;
		}
	}
	
	private int DFS(ArrayList<Integer>[] arr, int v) {
		if(marked[v]) return 0;
		marked[v] = true;
		
		int degree = 0;
		for(int i = 0; i < arr[v].size(); i++) {
			degree += DFS(adjList, (int) arr[v].get(i));
		}
		
		return degree + 1;
	}
	
	private int cDFS(ArrayList<Integer>[] arr, int v, int pre) {
		if(marked[v]) return 0;	// cycle
		marked[v] = true;
		
		// no cycle
		boolean isCycle = false;
		int max1Child = 0, max2Child = 0;
		for(int i = 0; i < arr[v].size(); i++) {
			int next = (int) arr[v].get(i);
			if(next != pre) {
				int child = cDFS(adjList, next, v);
				if(child == 0) {
					isCycle = true;
				}
				else if(child > max1Child) {
					max2Child = max1Child;
					max1Child = child;
				}
				else if(child > max2Child) {
					max2Child = child;
				}
				else {}
			}
		}
		
//		System.out.println("v = "+v+", max1Child = "+max1Child+", max2Child = "+max2Child+", isCycle = "+isCycle+", arr[v].size() = "+arr[v].size());
		
		if((max1Child+max2Child) > lsd) {	// if sum of two subtrees is larger than lsd
			lsd = (max1Child+max2Child);
		}
		if(isCycle) {
			shrink[v] = false;	// remain shrink component
			info[v] = max1Child;
			if(deepest < max1Child) {
				deepest = max1Child;
				deepestVtx = v;
			}
			return 0;
		}
		else {
			return Math.max(max1Child, max2Child)+1;
		}
		
	}
	
	private void show(ArrayList<Integer>[] arr) {
		System.out.println("edges:");
		for(int i = 0; i < arr.length; i++) {
			for(int j = 0; j < arr[i].size(); j++) {
				System.out.println(i+" -> "+arr[i].get(j));
			}
		}
	}
	
	private void show(ArrayList<Integer> arr) {
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
