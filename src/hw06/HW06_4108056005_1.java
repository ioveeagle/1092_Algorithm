// case1_go back algorithm: O(2N)
//package hw06;

public class HW06_4108056005_1 extends Dessert_Desert
{
	public static void main(String[] args) 
	{
//		HW06_4108056005_1 test = new HW06_4108056005_1();
//		int[][] array = {{3, 5, 2, 9, 4}};
//		System.out.println("case1:");
//		Stopwatch stopwatch = new Stopwatch();
//		int[] result = test.maxBlocks(array);
//		double time = stopwatch.elapsedTime();
//		System.out.println("elapsed time " + time);
//		
//		for(int i = 0; i < result.length; i++)
//		{
//			System.out.print(result[i]+", ");
//		}
//		System.out.println();
	}
	
	@Override
	public int[] maxBlocks(int[][] inputArr) 
	{
		int[] result = new int[inputArr.length];
		
		for(int i = 0; i < inputArr.length; i++)	// each integers array
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
//			show(i, data);
			
			int rmin = data[inputArr[i].length-1][1], count = 0;	// rmin is the min value from right
			for(int j = inputArr[i].length-2; j >= 0; j--)
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
		
		return result;
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
