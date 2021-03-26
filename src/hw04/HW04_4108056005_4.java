// case4: find from edge + mid: O(N/2)
package hw04;

public class HW04_4108056005_4 extends One_0k_rock 
{
	public static void main(String[] args) 
	{
		System.out.println("case4:");
		HW04_4108056005_4 test = new HW04_4108056005_4();
		TestDataGenerator tsg = new TestDataGenerator();
		String[] str= tsg.readData();
		boolean[] ans = tsg.readAns();
		Stopwatch stopwatch = new Stopwatch();
		boolean[] res = test.one0k(str);		
		double time = stopwatch.elapsedTime();
		System.out.println("elapsed time " + time);
		
		test.checkAns(ans, res);
		test.show(ans);
		test.show(res);
	}
	
	@Override
	public boolean[] one0k(String[] str) 
	{
		boolean[] ans = new boolean[str.length];
		
		for(int i=0; i<str.length; i++)
		{
			String s = str[i];
			if(s.length()%2 == 0)
			{
				int left1 = 0, right1 = s.length()-1;
				int left2 = s.length()/2-1, right2 = s.length()/2;
				ans[i] = true;
				int limit = s.length()%4==0 ? s.length()/4 : s.length()/4+1;
				while(left1 < limit)
				{
//					System.out.println("left = "+left+" "+s.charAt(left));
//					System.out.println("right = "+right+" "+s.charAt(right));
					if(s.charAt(left1) == '1' || s.charAt(right1) == '0' || s.charAt(left2) == '1' || s.charAt(right2) == '0')	// false condition
					{
						ans[i] = false;
						break;
					}
					left1++;
					right1--;
					left2--;
					right2++;
				}
			}
			else
			{
				ans[i] = false;
			}
		}
		
		return ans;
	}
	
	private void show(boolean[] ans)
	{
		for(int i=0; i<ans.length; i++)
		{
			System.out.print(ans[i]+", ");
		}
		System.out.println();
	}
	
	private void checkAns(boolean[] ans, boolean[] res)
	{
		int count = 0;
		for(int i=0; i<ans.length; i++)
		{
			if((ans[i] && res[i]) || (!ans[i] && !res[i])) count++;	// true true or false false
		}
		
		System.out.println("correct rate: "+count+"/"+ans.length);
	}
}
