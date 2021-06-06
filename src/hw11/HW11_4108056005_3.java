// Case3: kashiwa
//package hw11;

public class HW11_4108056005_3 extends GroupCounting {
	
	final int _cap = 131072;
	MyHashMap m = new MyHashMap(_cap);
	UnionFind uf = new UnionFind(_cap);
	
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
	
	public int count(String[] A, String[] B){
		int len = A.length;
		int index = 0;
		int indexA, indexB;

		for(int i=0; i<len; i++){
			indexA = m.get(A[i]);
			if(indexA == -1){
				indexA = index++;
				m.put(A[i], indexA);
			}

			indexB = m.get(B[i]);
			if(indexB == -1){
				indexB = index++;
				m.put(B[i], indexB);
			}
			
//			System.out.println(A[i]+" = "+indexA+", "+B[i]+" = "+indexB);

			uf.union(indexA, indexB);
		}

		return uf.getAns(index);
	}

	private class UnionFind{
		private int[] parent;
		private int[] weight;
		private int cap;
		private int count;

		UnionFind(int size){
			this.cap = size;
			parent = new int[cap];
			weight = new int[cap];

			for(int i=0; i < cap; i++)
				parent[i] = i;
		}

		final public int find(int i){
			while(parent[i] != i){
				parent[i] = parent[parent[i]];
				i = parent[i];
			}
			return i;
		}

		final public void union(int a, int b){
			a = find(a);
			b = find(b);

			if(a == b) return;
			count++;
			if(weight[a] > weight[b]){
				parent[b] = a;
				weight[a] += weight[b];
			}else{
				parent[a] = b;
				weight[b] += weight[a];
			}
		}

		final public int getAns(int end){
			return end-count;
		}
	}

	private class MyHashMap{
	    class Entry{
	        public String key;
	        public int val;
	        public Entry next;
	    }

	    private int cap;
	    private Entry[] list;

	    MyHashMap(int size){
	        this.cap = size;
	        Entry[] newEntry = new Entry[this.cap];
	        this.list = newEntry;
	    }

		final public void put(String key, int val){
	        int index = (key.hashCode() & 0x7fffffff) & this.cap-1;
	        Entry newEntry = new Entry();
	        newEntry.key = key;
	        newEntry.val = val;
	        newEntry.next = list[index];
	        list[index] = newEntry;
	    }

	    final public int get(String key){
	        for(Entry current = list[(key.hashCode() & 0x7fffffff) & this.cap-1]; current!=null; current=current.next){
	            if(current.key.equals(key)){
	                return current.val;
	            }
	        }
	        return -1;
	    }
	}
}
