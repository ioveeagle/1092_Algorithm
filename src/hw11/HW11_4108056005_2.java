// Case2: Quick-union with hashMap + union
//package hw11;

public class HW11_4108056005_2 extends GroupCounting {
	
	public static MyMap<String, Data> hashMap = new MyMap<>();
	
//	public static void main(String[] args) {
//		
//		System.out.println("case2:");
//		HW11_4108056005_2 test = new HW11_4108056005_2();
//		testDataHere td = new testDataHere();
//		
//		for(int i = 0; i < td.testDataNum; i++) {
//			hashMap = new MyMap<>();
//			String[][] input = td.readData(i);
//			String[] A = input[0];
//			String[] B = input[1];
//			
//			
//			Stopwatch stopwatch = new Stopwatch();
//			int ans = test.count(A, B);
//			double time = stopwatch.elapsedTime();
//			System.out.println("testData #"+(i+1)+": "+ans);
//			System.out.println("elapsed time " + time);
//		}
//	}
	
	@Override
	public int count(String[] A, String[] B) {
		
		int len = A.length;
		
		int cnt = 0;
		for(int i = 0; i < len; i++) {
			
			Data A_data = hashMap.get(A[i]);
			Data B_data = hashMap.get(B[i]);
			
			if(A_data == null && B_data == null) {	// if neither A nor B belong to any group
				hashMap.put(A[i], new Data(A[i], 1));
				hashMap.put(B[i], new Data(B[i], 1));
				union(A[i], B[i]);
				cnt++;
			}
			else if(A_data == null) {	// if B belongs to a group, but A does not
				hashMap.put(A[i], new Data(A[i], 1));
				union(A[i], B[i]);
			}
			else if(B_data == null) {	// if A belongs to a group, but B does not
				hashMap.put(B[i], new Data(B[i], 1));
				union(A[i], B[i]);
			}
			else if(!connected(A[i], B[i])) {	// if A and B belong to the different group
				union(A[i], B[i]);
				cnt -= 1;
			}
			else {	// if A and B belong to the same group
				union(A[i], B[i]);
			}
		}
//		show();
		
		return cnt;
	}
	
	public String find(String s) {	// find the root of s
		while (s != hashMap.get(s).parent) {
			s = hashMap.get(s).parent;
		}
		return s;
	}

	public void union(String p, String q) {		// weighted quick-union
		String proot = find(p);
		String qroot = find(q);
		Data pdata = hashMap.get(proot);
		Data qdata = hashMap.get(qroot);
		
		if(hashMap.get(proot).size < hashMap.get(qroot).size) {		// small size point to big size
			hashMap.put(qroot, new Data(qroot, pdata.size + qdata.size));
			hashMap.put(proot, new Data(qroot, pdata.size));
		}
		else {
			hashMap.put(proot, new Data(proot, pdata.size + qdata.size));
			hashMap.put(qroot, new Data(proot, qdata.size));
		}
	}
	
	public boolean connected(String p, String q) {	// Are p and q in same group?
		if(find(p) == find(q)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	private void show() {
		int n = hashMap.getBucketSize();
		System.out.println("n = "+n);
		for(int i = 0; i < n; i++) {
			System.out.println("i = "+i);
			for(MyMap<String, Data>.Entry<String, Data> e = hashMap.buckets[i]; e != null; e = e.next) {
				System.out.println(e.key+" -> "+e.value.parent+", size="+e.value.size);
			}
		}
	}
	
	static public class Data {
		
		String parent;
		int size;
		
		public Data(String parent, int size) {
			this.parent = parent;
			this.size = size;
		}
	}
	
	static public class MyMap<K, V> {
	    private Entry<K, V>[] buckets;
	    private int capacity; // 16

	    private int size = 0;

	    private double lf = 0.75;

	    public MyMap() {
	        this(16);
	    }

	    public MyMap(int capacity) {
	        this.capacity = capacity;
	        this.buckets = new Entry[this.capacity];
	    }

	    public void put(K key, V value) {
	        if (size == lf * capacity) {
	            // rehash
	            Entry<K, V>[] old = buckets;

	            capacity *= 2;
	            size = 0;
	            buckets = new Entry[capacity];

	            for (Entry<K,V> e: old) {
	                while (e != null) {
	                    put(e.key, e.value);
	                    e = e.next;
	                }
	            }
	        }
	        Entry<K, V> entry = new Entry<>(key, value, null);

	        int bucket = getHash(key) % getBucketSize();

	        Entry<K, V> existing = buckets[bucket];
	        if (existing == null) {
	            buckets[bucket] = entry;
	            size++;
	        } else {
	            // compare the keys see if key already exists
	            while (existing.next != null) {
	                if (existing.key.equals(key)) {
	                    existing.value = value;
	                    return;
	                }
	                existing = existing.next;
	            }

	            if (existing.key.equals(key)) {
	                existing.value = value;
	            } else {
	                existing.next = entry;
	                size++;
	            }
	        }
	    }

	    public V get(K key) {
	        Entry<K, V> bucket = buckets[getHash(key) % getBucketSize()];

	        while (bucket != null) {
	            if (key == bucket.key) {
	                return bucket.value;
	            }
	            bucket = bucket.next;
	        }
	        return null;
	    }

	    public int size() {
	        return size;
	    }

	    private int getBucketSize() {
	        return buckets.length;
	    }

	    private int getHash(K key) {
	        return key == null ? 0 : Math.abs(key.hashCode());
	    }

	    class Entry<K, V> {
	        final K key;
	        V value;
	        Entry<K, V> next;

	        public Entry(K key, V value, Entry<K, V> next) {
	            this.key = key;
	            this.value = value;
	            this.next = next;
	        }

	        public K getKey() {
	            return key;
	        }

	        public V getValue() {
	            return value;
	        }

	        public Entry<K, V> getNext() {
	            return next;
	        }

	        @Override
	        public boolean equals(Object obj) {
	            if (obj == this) return true;

	            if (obj instanceof Entry) {
	                Entry entry = (Entry) obj;

	                return key.equals(entry.getKey()) &&
	                        value.equals(entry.getValue());
	            }
	            return false;
	        }

	        @Override
	        public int hashCode() {
	            int hash = 13;
	            hash = 17 * hash + ((key == null) ? 0 : key.hashCode());
	            hash = 17 * hash + ((value == null) ? 0 : value.hashCode());
	            return hash;
	        }
	    }
	}
}
