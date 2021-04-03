// case4_hashmap + slope + thread: O(1/2*N^2)
package hw05;

public class HW05_4108056005_4 extends LLK {
	
	public static void main(String[] args) 
	{
		HW05_4108056005_4 test = new HW05_4108056005_4();
		int[][] array = TestDataGenerator.readData();
		System.out.println("case4:");
		Stopwatch stopwatch = new Stopwatch();
		System.out.println(test.checkLLK(array));	
		double time = stopwatch.elapsedTime();
		System.out.println("elapsed time " + time);
	}
	
	int[][] array;
	volatile boolean ans;
	int tNum = 8;
	int lgTNum = 5;
	int len;
	int hashCap;
	Thread[] t = new Thread[tNum];

	public boolean checkLLK(int[][] array) {
		ans = false;
		this.array = array;
		len = array.length;
		hashCap = 1 << ((int) Math.ceil(Math.log10((double) len) / 0.3010));

		// if array length is larger than the number of threads
		if (len > tNum) {
			MultiThread[] mt = new MultiThread[tNum];
			
			// split array to 8 pieces
			for(int tr=0; tr<tNum; tr++) {
				mt[tr] = new MultiThread(tr);
				mt[tr].start();
			}
			
			try{
	            for(int tr=0; tr<tNum; tr++) {
	                mt[tr].join();	// merge all thread and wait end
	            }
	        }
			catch(InterruptedException e) {}

			return ans;
		} 
		else {
			int i, j;
			HashMap m = new HashMap(hashCap);
			for (i = 0; i < len; i++) {
				for (j = i + 1; j < len; j++) {
					if (m.containOrPut((double) (array[i][1] - array[j][1]) / (double) (array[i][0] - array[j][0]))) {
						return true;
					}
				}
				m.reset();
			}

			return false;
		}
	}
	
	class MultiThread extends Thread {
    	
    	int tr;
    	
    	public MultiThread(int tr) {
    		this.tr = tr;
    	}
    	
    	public void run() {
    		final int start = (len * tr) >> lgTNum;
			final int end = (len * (tr + 1)) >> lgTNum;
			HashMap m = new HashMap(hashCap);

			for (int i = start; i < end && !ans; i++) {
				for (int j = i + 1; j < len && !ans; j++) {
					if (m.containOrPut(
							(double) (array[i][1] - array[j][1]) / (double) (array[i][0] - array[j][0]))) {
						ans = true;
					}
				}
				m.reset();
			}
    	}
    }

	private class HashMap {
		class Entry {
			public double key;
			public Entry next;
		}

		private int cap;
		private Entry[] list;

		HashMap(int size) {
			this.cap = size;
			Entry[] newEntry = new Entry[this.cap];
			this.list = newEntry;
		}

		final public void reset() {
			Entry[] newEntry = new Entry[this.cap];
			this.list = newEntry;
		}

		final public boolean containOrPut(double key) {
			// & (this.cap - 1) is equivalent to % this.cap when this.cap is power of 2
			int index = (Double.valueOf(key).hashCode() & 0x7fffffff) & (this.cap - 1);

			for (Entry current = list[index]; current != null; current = current.next) {
				if (current.key == key) {
					return true;	// if the value already contained in hashmap
				}
			}
			
			// if not, than add new entry at the head of hashmap
			Entry newEntry = new Entry();
			newEntry.key = key;
			newEntry.next = list[index];
			list[index] = newEntry;
			return false;
		}
	}
}
