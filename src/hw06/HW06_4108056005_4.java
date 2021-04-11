// case4_go back algorithm + thread16 + mod: O(2N)
//package hw06;

public class HW06_4108056005_4 extends Dessert_Desert
{
	static int[][] inputArr;
	static int[] result;
	byte tNum = 16;
	byte logtNum = 4;
	MultiThread[] mt;
	
	public HW06_4108056005_4() 
	{
		mt = new MultiThread[tNum];
		
		for(int tr=0; tr<tNum; tr++) 
		{
			mt[tr] = new MultiThread(tr);
		}
	}
	
	public static void main(String[] args) 
	{
//		HW06_4108056005_4 test = new HW06_4108056005_4();
//		int[][] array = new int[16000][10000];
//		System.out.println("case4:");
//		Stopwatch stopwatch = new Stopwatch();
//		int[] result = test.maxBlocks(array);
//		double time = stopwatch.elapsedTime();
//		System.out.println("elapsed time " + time);
		
//		for(int i = 0; i < result.length; i++)
//		{
//			System.out.print(result[i]+", ");
//		}
//		System.out.println();
	}
	
	@Override
	public int[] maxBlocks(int[][] inputArr) 
	{
		this.inputArr = inputArr;
		result = new int[inputArr.length];
		
		// if array length is larger than the number of threads
		if (inputArr.length > tNum*4) 
		{
			// split array to 8 pieces
			for(int tr=0; tr<tNum; tr++) 
			{
				mt[tr].start();
			}
			
			try
			{
	            for(int tr=0; tr<tNum; tr++) 
	            {
	                mt[tr].join();	// merge all thread and wait end	0.037
//	                mt[tr].sleep(1);
	            }
	        }
			catch(InterruptedException e) {}
		} 
		else 
		{
			for(int i = 0; i < inputArr.length; i++)
			{
				countMax(i);
			}
		}
		
		return result;
	}
	
	private static void countMax(int i)
	{
		int[][] data = new int[inputArr[i].length][2];	// 0 is max, 1 is min
		int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
		for(int j = 0; j < inputArr[i].length; j++)	// each element in array
		{
			if(inputArr[i][j] >= max)	// if next element is bigger than left max
			{
				max = inputArr[i][j];
				min = inputArr[i][j];
			}
			if(inputArr[i][j] < min)	// if next element is smaller than left min
			{
				min = inputArr[i][j];
			}
			data[j][0] = max;	// put max of each element
			data[j][1] = min;	// put min of each element
		}
		// show(i, data);
		
		int rmin = data[inputArr[i].length-1][1], count = 0;	// rmin is the min value from right
		for(int j = inputArr[i].length-2; j >=0; j--)
		{
			/* if rmin is not smaller than the max of this element, it means 
			 * min of right part is larger or equal to the max of left part, 
			 * and when they sorted respectively, all part is sorted, too.
			 * We count plus 1 because this case is the shortest block.
			 */
			if(data[j][0] <= rmin)	// can't merge, so must be one block
			{
				count++;
				rmin = data[j][1];	// new min of the next block
			}
			else
			{
				rmin = Math.min(rmin, data[j][1]);	// merge might get the smaller min.
			}
		}
		count++;	// the last block is included
		
		result[i] = count;
	}
	
	class MultiThread extends Thread 
	{
    	int tr;
    	
    	public MultiThread(int tr) 
    	{
    		this.tr = tr;
    	}
    	
    	public void run() 
    	{
    		for(int i = tr; i < inputArr.length; i += tNum)
    		{
//    			System.out.println("thread="+tr+", i="+i);
    			
    			countMax(i);
    		}
	    }
	}
	
	private static void show(int k, int[][] a)
	{
		System.out.println("data "+k);
		
		// print max
		for(int i = 0; i < a.length; i++)
		{
			System.out.printf("%3d", a[i][0]);
		}
		System.out.println();
		// print min
		for(int i = 0; i < a.length; i++)
		{
			System.out.printf("%3d", a[i][1]);
		}
		System.out.println();
	}
}
