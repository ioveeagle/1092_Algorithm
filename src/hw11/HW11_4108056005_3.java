// Case3: faster Quick-union
//package hw11;

public class HW11_4108056005_3 extends GroupCounting {
	
	final int _cap = (int)Math.pow(2, 20);
	int[] parent = new int[_cap];
	int[] weight = new int[_cap];
	int count = 0;
	MyHashMap m = new MyHashMap(_cap);
	
	public HW11_4108056005_3() {
		for(int i=0; i < _cap; i++) {
			parent[i] = i;
			weight[i] = 0;
		}
	}
	
//	public static void main(String[] args) {
//
//		System.out.println("case3:");
//		HW11_4108056005_3 test = new HW11_4108056005_3();
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
		int index = 0;
		int indexA, indexB;

		for(int i=0; i<len; i++) {
			indexA = m.get(A[i]);
			if(indexA == -1){
				indexA = index++;
				m.put(A[i], indexA);
			}

			indexB = m.get(B[i]);
			if(indexB == -1) {
				indexB = index++;
				m.put(B[i], indexB);
			}
			union(indexA, indexB);
			
//			System.out.println(A[i]+" = "+indexA+", "+B[i]+" = "+indexB);
		}

		return index-count;
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

	private class MyHashMap {
	    class Entry {
	        public String key;
	        public int val;
	        public Entry next;
	    }

	    private int cap;
	    private Entry[] list;

	    MyHashMap(int size) {
	        this.cap = size;
	        Entry[] newEntry = new Entry[this.cap];
	        this.list = newEntry;
	    }

		final public void put(String key, int val) {
	        int index = (key.hashCode() & 0x7fffffff) & this.cap-1;
	        Entry newEntry = new Entry();
	        newEntry.key = key;
	        newEntry.val = val;
	        newEntry.next = list[index];
	        list[index] = newEntry;
	    }

	    final public int get(String key) {
	        for(Entry current = list[(key.hashCode() & 0x7fffffff) & this.cap-1]; current!=null; current=current.next) {
	            if(current.key.equals(key)) {
	                return current.val;
	            }
	        }
	        return -1;
	    }
	}
}
