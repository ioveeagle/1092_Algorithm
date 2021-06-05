// Case5: tw as int
//package hw11;

public class HW11_4108056005_5 extends GroupCounting {

	final static int[] UNION = new int[10000000];

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

	final class Slot {
		final Slot NEXT;
		final String K;
		final int V;

		Slot(final String k, final int v, final Slot next) {
			this.K = k;
			this.V = v;
			this.NEXT = next;
		}
	}

	public int count(final String[] A, final String[] B) {
		final int LEN = A.length;
		int nodeN = 0, unionN = 0;
		for(int i = 0, a, b, t; i < LEN; i++) {
			a = Integer.parseInt(A[i]) + 1;
			b = Integer.parseInt(B[i]) + 1;
			if(UNION[a] == 0) {
				UNION[a] = -1;
				nodeN++;
			}
			else {
				for(t = a; UNION[t] > -1; t = UNION[t]);
				for(int j, k = a; k != t; k = j) {
					j = UNION[k];
					UNION[k] = t;
				}
				a = t;
			}
			if(UNION[b] == 0) {
				UNION[b] = -1;
				nodeN++;
			}
			else{
				for(t = b; UNION[t] > -1; t = UNION[t]);
				for(int j, k = b; k != t; k = j) {
					j = UNION[k];
					UNION[k] = t;
				}
				b = t;
			}
			if(a == b)
				continue;
			t = UNION[a] + UNION[b];
			if(UNION[a] > UNION[b]) {
				UNION[a] = b;
				UNION[b] = t;
			}
			else {
				UNION[b] = a;
				UNION[a] = t;
			}
			unionN++;
		}
		return nodeN - unionN;
	}
}
