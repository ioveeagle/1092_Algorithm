// case4_hashmap + slope + thread: O(1/2*N^2)
//package hw05;

public class HW05_4108056005_4 extends LLK 
{
	public static void main(String[] args) 
	{
//		HW05_4108056005_4 test = new HW05_4108056005_4();
//		int[][] array = TestDataGenerator.readData();
//		System.out.println("case4:");
//		Stopwatch stopwatch = new Stopwatch();
//		System.out.println(test.checkLLK(array));	
//		double time = stopwatch.elapsedTime();
//		System.out.println("elapsed time " + time);
	}
	
	class Slot  // class for saving slope of the pair as linked list
	{
		final double slope;
		final Slot Next;

		public Slot(final double slope, final Slot Next) 
		{
			this.slope = slope;
			this.Next = Next;
		}
	}

	byte tNum = 32;
	final byte log_tNum = 5; // Binary logarithm of launch thread number
	final Slot[][] HashTable = new Slot[1 << log_tNum][]; // Initialize 2^5=32 hash table
	final Thread[] T = new Thread[HashTable.length - 1]; // Initialize 32-1=31 threads, one for main thread
	volatile boolean result; // the result value. modifier volatile let us not to care synchronous problem

	public boolean checkLLK(int[][] array) 
	{
		result = false; // reset the value, prevent same object-method calling

		final int aEnd = array.length - 1, tLen = T.length, freq = HashTable.length,
				// get the closest power-of-2-number bigger than array.length, BUCKETS = 2^k, k in
				// [0,32]
				bNum = 1 << 32 - Integer.numberOfLeadingZeros(aEnd), B = bNum - 1;

		for (byte ti = 0; ti < tLen; ++ti) { // start up all threads
			final byte t = ti; // the variables pass to anonymous class must be final
			T[t] = new Thread(() -> { // lambda expression for Runnable interface since Java 8
				final int start = array.length * t / tNum;
				final int end = array.length * (t + 1) / tNum;
				for(int i = start; i < end; i++) 
				{
					// (re-)set buckets for each i
					for(int j = i + 1; j < array.length; j++) 
					{
						HashTable[t] = new Slot[bNum];
						
						// calculate the slope of the line construct by point i and j
						final double slope = (array[i][1] - array[j][1]) / (double) (array[i][0] - array[j][0]);

						// modulo for ONLY power-of-2-number: same as (hashcode&0x7fff_ffff)%bNum
						// for keep the hash index in [0,BUCKETMUN]
						int bucket = Double.hashCode(slope) & B & 0x7fff_ffff;

						// view through slots of the bucket, hash collisions occur if Solt have Next.
						for (Slot Pivot = HashTable[t][bucket]; Pivot != null; Pivot = Pivot.Next) 
						{
							// find three points in the same line
							if (Pivot.slope == slope)
								result = true;

							// if other thread(s) or this thread found, aEnd up the thread.
							// the same instruction below is for the same reason.
							if (result)
								return;
						}

						// put slope into the foremost slot of the bucket
						HashTable[t][bucket] = new Slot(slope, HashTable[t][bucket]);

						if (result)
							return;
					}
					if (result)
						return;
				}
				// aEnd of each thread
			});
			T[t].setDaemon(true); // let threads aEnd up while main thread return ture
			T[t].start(); // launch threads
			if (result)
				return true;
		} // aEnd of start up threads

		// start algorithm to main thread
		for (int i = aEnd - tLen, j, bucket; i > -1; i -= freq) 
		{
			for (HashTable[tLen] = new Slot[bNum], j = i - 1; j > -1;) 
			{
				final double slope = (array[i][1] - array[j][1]) / (double) (array[i][0] - array[j--][0]);
				bucket = Double.hashCode(slope) & B & 0x7fff_ffff;
				for (Slot Pivot = HashTable[tLen][bucket]; Pivot != null; Pivot = Pivot.Next)
					if (Pivot.slope == slope || result)
						return true;
				HashTable[tLen][bucket] = new Slot(slope, HashTable[tLen][bucket]);
			}
			if (result)
				return true;
		}
		// aEnd of algorithm in main thread

		try 
		{
			for (final Thread t : T) 
			{ // join all threads
				if (result)
					return true;
				t.join();
			}
		} 
		catch (final InterruptedException e) {}
		return result;
	}
}
