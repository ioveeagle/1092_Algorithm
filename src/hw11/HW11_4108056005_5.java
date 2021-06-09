// Case5: faster Quick-union as int
//package hw11;

public class HW11_4108056005_5 extends GroupCounting {
	
	final int _cap = (int)Math.pow(2, 20);	// 1048576
	int[] parent = new int[_cap];
	int[] weight = new int[_cap];
	int count = 0;
	
	public HW11_4108056005_5() {
		for(int i = 0; i < _cap; i++) {
			parent[i] = i;
			weight[i] = 0;
		}
	}
	
//	public static void main(String[] args) {
//		
//		System.out.println("case5:");
//		HW11_4108056005_5 test = new HW11_4108056005_5();
//		TestDataGenerator tdg = new TestDataGenerator();
//		String[][] input = tdg.readData();
//
//		String[] A = input[0];
//		String[] B = input[1];
//
//		Stopwatch stopwatch = new Stopwatch();
//		int ans = test.count(A, B);
//		double time = stopwatch.elapsedTime();
//		System.out.println("MyAns = "+ans);
//		System.out.println("Ans = "+tdg.readAns());
//		System.out.println("elapsed time " + time);
//	}
	
	public int count(String[] A, String[] B) {
		int len = A.length;
		int total = 0;

		for(int i = 0; i < len; i++) {
			int indexA = Integer.parseInt(A[i]);
			if(weight[indexA] == 0) {
				parent[indexA] = indexA;
				weight[indexA] = 1;
				total++;
			}
			int indexB = Integer.parseInt(B[i]);
			if(weight[indexB] == 0) {
				parent[indexB] = indexB;
				weight[indexB] = 1;
				total++;
			}
			union(indexA, indexB);
			
//			System.out.println(A[i]+" = "+indexA+", "+B[i]+" = "+indexB);
		}
		
		return total-count;
	}

	public int find(int i) {
		while(parent[i] != i){
			parent[i] = parent[parent[i]];
			i = parent[i];
		}
		return i;
	}

	public void union(int a, int b) {
		a = find(a);
		b = find(b);

		if(a == b) return;
		count++;
		if(weight[a] > weight[b]) {
			parent[b] = a;
			weight[a] += weight[b] + 1;
		}
		else {
			parent[a] = b;
			weight[b] += weight[a] + 1;
		}
	}
}
