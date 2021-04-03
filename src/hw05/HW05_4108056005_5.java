// case5_merge sort + deltaX & deltaY: O(1/2*N^2 + NlogN)
//package hw05;

public class HW05_4108056005_5 
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

    public boolean checkLLK(int[][] array)
    {
        int len = array.length;
        int deltaX, deltaY, gcd;
        
        for(int i=0; i<len-1; i++)
        {
        	int[][] delta = new int[len-i-1][2];
        	
            for(int j=i+1; j<len; j++)
            {
                deltaX = array[i][0] - array[j][0];
                deltaY = array[i][1] - array[j][1];
                gcd = gcd(deltaY, deltaX);

                deltaX = (deltaX / gcd);
                deltaY = (deltaY / gcd);
                if(deltaX < 0)	// deltaX must be positive
                {
                    deltaX = ~deltaX + 1;
                    deltaY = ~deltaY + 1;
                }
                
                delta[j-i-1][0] = deltaX;
                delta[j-i-1][1] = deltaY;
            }
            sort(delta);
//            show(delta);
            
            // if two deltaX and deltaX is equal, than they are in one line.
    		for(int j = 0; j < delta.length-1; j++)
    		{
    			if(delta[j][0] == delta[j+1][0] && delta[j][1] == delta[j+1][1])
    				return true;
    		}
        }

        return false;
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

    public int gcd(int a, int b)
    {
        return b==0 ? a : gcd(b, a%b);
    }
    
    private static void show(int[][] a)
	{
		for(int i = 0; i < a.length; i++)
		{
			System.out.println("["+a[i][0]+", "+a[i][1]+"]");
		}
		System.out.println();
	}
}
