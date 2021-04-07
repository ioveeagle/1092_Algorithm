// case5_tw solution: slope + hashmap + thread
//package hw05;

public class HW05_4108056005_5 extends LLK 
{
	public static void main(String[] args) 
	{
//		HW05_4108056005_5 test = new HW05_4108056005_5();
//		int[][] array = TestDataGenerator.readData();
//		System.out.println("case5:");
//		Stopwatch stopwatch = new Stopwatch();
//		System.out.println(test.checkLLK(array));	
//		double time = stopwatch.elapsedTime();
//		System.out.println("elapsed time " + time);
	}
	
	class Entry  // class for hashmap entry
	{
		final double slope;
		final Entry next;

		public Entry(final double slope, final Entry next) 
		{
			this.slope = slope;
			this.next = next;
		}
	}

	final byte log_tNum = 5;
	final Entry[][] HashTable = new Entry[1 << log_tNum][];
	final Thread[] T = new Thread[HashTable.length - 1];
	volatile boolean result;

	public boolean checkLLK(int[][] array) 
	{
		result = false;	// init result to be false

		final int end = array.length - 1, tLen = T.length, freq = HashTable.length, bNum = 1 << 32 - Integer.numberOfLeadingZeros(end), B = bNum - 1;

		 // start up all threads
		for (byte ti = 0; ti < tLen; ++ti) 
		{
			final byte t = ti;	// must be final
			T[t] = new Thread(() -> {
				// start of each thread
				for (int i = end - t, j, bucket; i > -1; i -= freq) 
				{
					for (HashTable[t] = new Entry[bNum], j = i - 1; j > -1;) 
					{
						// calculate the slope between point i and j
						final double slope = (array[i][1] - array[j][1]) / (double) (array[i][0] - array[j--][0]);

						bucket = Double.hashCode(slope) & B & 0x7fff_ffff;

						for (Entry Pivot = HashTable[t][bucket]; Pivot != null; Pivot = Pivot.next) 
						{
							// same slope means three point is in one line
							if (Pivot.slope == slope)
								result = true;

							// if other thread(s) or this thread found, end up the thread
							if (result)
								return;
						}

						// put slope into the hashmap
						HashTable[t][bucket] = new Entry(slope, HashTable[t][bucket]);

						if (result)
							return;
					}
					if (result)
						return;
				}
				// end of each thread
			});
			T[t].setDaemon(true);
			T[t].start();
			if (result)
				return true;
		} // end of start up threads

		// start algorithm to main thread
		for (int i = end - tLen, j, bucket; i > -1; i -= freq) 
		{
			for (HashTable[tLen] = new Entry[bNum], j = i - 1; j > -1;) 
			{
				final double slope = (array[i][1] - array[j][1]) / (double) (array[i][0] - array[j--][0]);
				bucket = Double.hashCode(slope) & B & 0x7fff_ffff;
				for (Entry Pivot = HashTable[tLen][bucket]; Pivot != null; Pivot = Pivot.next)
					if (Pivot.slope == slope || result)
						return true;
				HashTable[tLen][bucket] = new Entry(slope, HashTable[tLen][bucket]);
			}
			if (result)
				return true;
		}
		// end of algorithm in main thread

		try 
		{
			// join all threads, and wait program end
			for (final Thread t : T) 
			{
				if (result)
					return true;
				t.join();
			}
		} 
		catch (final InterruptedException e) {}
		return result;
	}
}