// case1_go back algorithm: O(2N)
package hw06;

public class HW06_4108056005_1 extends Dessert_Desert
{
	public static void main(String[] args) 
	{
		HW06_4108056005_1 test = new HW06_4108056005_1();
		int[][] array = new int[10000][10000];
		System.out.println("case1:");
		Stopwatch stopwatch = new Stopwatch();
		int[] result = test.maxBlocks(array);
		double time = stopwatch.elapsedTime();
		System.out.println("elapsed time " + time);
		
		for(int i = 0; i < result.length; i++)
		{
			System.out.print(result[i]+", ");
		}
		System.out.println();
	}
	
	static int len, arr_len;
	static int[] min = new int[100000];
	static int[] max = new int[100000];
	static int[] ans;
	static int[] arr;
	
	@Override
	public int[] maxBlocks(int[][] inputArr) 
	{
		arr_len = inputArr.length;
		ans = new int[arr_len];
		
		for(int i = 0; i < arr_len; i++)	// each integers array
		{
			arr = inputArr[i];
			len = arr.length;
			int top = 0;		// top of max and min stack
			min[0] = max[0] = arr[0];
			
			for(int j = 1; j < len; j++)	// each element in array
			{
				if(arr[j] >= max[top])	// if next element is bigger than left max
				{
					top++;
					max[top] = min[top] = arr[j];
				}
				else if(arr[j] < min[top])	// if next element is smaller than left min
				{
					min[top] = arr[j];
				}
			}
//			show(i, data);
			
			int rmin = min[top], count = 1;	// rmin is the min value from right
			for(top--; top >= 0; top--)
			{
				/* if rmin is not smaller than the max of this element, it means 
				 * min of right part is larger or equal to the max of left part, 
				 * and when they sorted respectively, all part is sorted, too.
				 * We count plus 1 because this case is the shortest block.
				 */
				if(max[top] <= rmin)	// can't merge, so must be one block
				{
					count++;
					rmin = min[top];	// new min of the next block
				}
				else if(min[top] < rmin)
				{
					rmin = min[top];	// merge might get the smaller min.
				}
			}
			
			ans[i] = count;
		}
		
		return ans;
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
