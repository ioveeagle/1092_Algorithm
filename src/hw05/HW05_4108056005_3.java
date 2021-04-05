// case3_hashmap + deltaX & deltaY: O(1/2*N^2)
//package hw05;

public class HW05_4108056005_3 {
	
	public static void main(String[] args) 
	{
//		HW05_4108056005_3 test = new HW05_4108056005_3();
//		int[][] array = TestDataGenerator.readData();
//		System.out.println("case3:");
//		Stopwatch stopwatch = new Stopwatch();
//		System.out.println(test.checkLLK(array));	
//		double time = stopwatch.elapsedTime();
//		System.out.println("elapsed time " + time);
	}

    public boolean checkLLK(int[][] array)
    {
        int len = array.length;
        int lnLen = (int)Math.ceil(Math.log10((double)len) / 0.3010);
        HashMap m = new HashMap(1 << lnLen);

        sort(array);
        
        int deltaX, deltaY,gcd;
        for(int i=0; i<len; i++)
        {
//        	System.out.println("point "+i);
            for(int j=i+1; j<len; j++)
            {
                deltaX = array[i][0] - array[j][0];
                deltaY = array[i][1] - array[j][1];

                gcd = gcd(deltaY, deltaX);

                deltaX = (deltaX / gcd);
                deltaY = (deltaY / gcd);
                if(deltaX < 0)	// we prefer that x is positive
                {
                    deltaX = ~deltaX + 1;
                    deltaY = ~deltaY + 1;
                }

                if(m.containOrPut(deltaX, deltaY))	// if it is already store in hashmap, return true.
                {
                    return true;
                }
            }
            m.reset();
        }

        return false;
    }

    private class HashMap
    {
        class Entry
        {
            public int key1;
            public int key2;
            public Entry next;
        }

        private int cap;
        private Entry[] list;

        HashMap(int size)
        {
            this.cap = size;
            Entry[] newEntry = new Entry[this.cap];
            this.list = newEntry;
        }

        final public void reset()
        {
            Entry[] newEntry = new Entry[this.cap];
            this.list = newEntry;
        }

        final public boolean containOrPut(int key1, int key2)
        {
            // & (this.cap - 1) is equivalent to % this.cap when base of this.cap is 2
            int index = ((key1 + key2) & 0x7fffffff) & (this.cap -1);
            for(Entry current = list[index]; current!=null; current=current.next)
            {
                if(current.key1 == key1 && current.key2 == key2)
                {
                    return true;
                }
            }
            Entry newEntry = new Entry();
            newEntry.key1  = key1;
            newEntry.key2  = key2;
            newEntry.next = list[index];
            list[index] = newEntry;
            return false;
        }
    }
    
    public int gcd(int a, int b)
    {
        return b==0 ? a : gcd(b, a%b);
    }
    
    public static void sort(int[][] a)
	{
		int[][] aux = new int[a.length][2];
		sort(a, aux, 0, a.length-1);
	}
    
    private static void sort(int[][] a, int[][] aux, int lo, int hi)
	{
		if(hi <= lo) return;
		int mid = lo + (hi-lo)/2;
		sort(a, aux, lo, mid);
		sort(a, aux, mid+1, hi);
		merge(a, aux, lo, mid, hi);
	}
    
    private static void merge(int[][] a, int[][] aux, int lo, int mid, int hi)
	{
		for(int k = lo; k <= hi; k++) aux[k] = a[k];
		
		int i = lo, j = mid+1;
		for(int k = lo; k <= hi; k++)
		{
			if(i > mid) a[k] = aux[j++];
			else if(j > hi) a[k] = aux[i++];
			else if(less(aux[i], aux[j])) a[k] = aux[i++];
			else a[k] = aux[j++];
		}
	}
    
    private static boolean less(int[] i, int[] j)
	{
		if(i[0] < j[0]) return true;
		else if(i[0] > j[0]) return false;
		else
			if(i[1] < j[1]) return true;
			else if(i[1] > j[1]) return false;
			else return true;
	}
}
