// case1_vertor: O(1/3*N^3)
//package hw05;

public class HW05_4108056005_1 extends LLK
{
	public static void main(String[] args) 
	{
//		HW05_4108056005_1 test = new HW05_4108056005_1();
//		int[][] array = TestDataGenerator.readData();
//		System.out.println("case1:");
//		Stopwatch stopwatch = new Stopwatch();
//		System.out.println(test.checkLLK(array));	
//		double time = stopwatch.elapsedTime();
//		System.out.println("elapsed time " + time);
//		
////		test.show(array);
	}
	
	@Override
	public boolean checkLLK(int[][] array) 
	{
		sort(array, 0);	// merge sort by x: O(NlogN)
//		show(array);
		
		// if three x of point is equal, than they are in one line.
		int count = 0;
		for(int i = 0; i < array.length-1; i++)
		{
			if(array[i][0] == array[i+1][0])
				count++;
			else 
				count = 0;
			
			if(count >= 2)	
				return true;
		}
		
		
		sort(array, 1);	// merge sort by y: O(NlogN)
//		show(array);
		
		// if three y of point is equal, than they are in one line.
		count = 0;
		for(int i = 0; i < array.length-1; i++)
		{
			if(array[i][1] == array[i+1][1])
				count++;
			else 
				count = 0;
			
			if(count >= 2)	
				return true;
		}
		
		for(int i = 0; i < array.length; i++)
		{
			for(int j = i+1; j < array.length; j++)
			{
				for(int k = j+1; k < array.length; k++)
				{
					if((array[j][0] - array[i][0])*(array[k][1] - array[i][1]) - (array[k][0] - array[i][0])*(array[j][1] - array[i][1]) == 0)
						return true;
				}
			}
		}
		return false;
	}
	
	private static void mergeX(int[][] a, int[][] aux, int lo, int mid, int hi)
	{
		for(int k = lo; k <= hi; k++) aux[k] = a[k];
		
		int i = lo, j = mid+1;
		for(int k = lo; k <= hi; k++)
		{
			if(i > mid) a[k] = aux[j++];
			else if(j > hi) a[k] = aux[i++];
			else if(lessX(aux[i], aux[j])) a[k] = aux[i++];
			else a[k] = aux[j++];
		}
	}
	
	private static void mergeY(int[][] a, int[][] aux, int lo, int mid, int hi)
	{
		for(int k = lo; k <= hi; k++) aux[k] = a[k];
		
		int i = lo, j = mid+1;
		for(int k = lo; k <= hi; k++)
		{
			if(i > mid) a[k] = aux[j++];
			else if(j > hi) a[k] = aux[i++];
			else if(lessY(aux[i], aux[j])) a[k] = aux[i++];
			else a[k] = aux[j++];
		}
	}
	
	private static void sort(int[][] a, int[][] aux, int lo, int hi, int flag)
	{
		if(hi <= lo) return;
		int mid = lo + (hi-lo)/2;
		sort(a, aux, lo, mid, flag);
		sort(a, aux, mid+1, hi, flag);
		if(flag == 0)
			mergeX(a, aux, lo, mid, hi);
		else
			mergeY(a, aux, lo, mid, hi);
	}
	
	public static void sort(int[][] a, int flag)
	{
		int[][] aux = new int[a.length][2];
		sort(a, aux, 0, a.length-1, flag);
	}
	
	private static boolean lessX(int[] i, int[] j)
	{
		if(i[0] < j[0]) return true;
		else if(i[0] > j[0]) return false;
		else
			if(i[1] < j[1]) return true;
			else if(i[1] > j[1]) return false;
			else return true;
	}
	
	private static boolean lessY(int[] i, int[] j)
	{
		if(i[1] < j[1]) return true;
		else if(i[1] > j[1]) return false;
		else
			if(i[0] < j[0]) return true;
			else if(i[0] > j[0]) return false;
			else return true;
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
