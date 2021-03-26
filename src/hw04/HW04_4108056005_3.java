// case3: find from mid+thread: O(N/2)
package hw04;

public class HW04_4108056005_3 extends One_0k_rock 
{
	static int threadNum = 8;
	String[] str;
	boolean[] ans;
	
	public static void main(String[] args) 
	{
		System.out.println("case3:");
		HW04_4108056005_3 test = new HW04_4108056005_3();
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
		this.str = str;
		ans = new boolean[str.length];
		
		if(str.length >= threadNum) 
		{
			MultiThread[] mt = new MultiThread[threadNum];
			
			// split str to 8 slice, each run 1/8 str.
			for(int tr=0; tr<threadNum; tr++) 
			{
				mt[tr] = new MultiThread(tr);
				mt[tr].start();
			}
			
			try
			{
	            for(int tr=0; tr<threadNum; tr++) mt[tr].join();	// join all of thread, and wait to end
	        }
			catch(InterruptedException e) {}
		}
		else 
		{
			// no thread
			for(int i=0; i<str.length; i++)
			{
				String s = str[i];
				if(s.length()%2 == 0)
				{
					int left = s.length()/2-1, right = s.length()/2;
					ans[i] = true;
					while(left > -1)
					{
						if(s.charAt(left) == '1' || s.charAt(right) == '0')	// false condition
						{
							ans[i] = false;
							break;
						}
						left--;
						right++;
					}
				}
				else
				{
					ans[i] = false;
				}
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
	
	class MultiThread extends Thread 
	{
    	int tr;
    	
    	public MultiThread(int tr) 
    	{
    		this.tr = tr;
    	}
    	
    	public void run() 
    	{
    		int i_end = tr!=7 ? str.length/threadNum*(tr+1) : str.length;
    		for(int i = str.length/(threadNum)*tr; i < i_end; i++) 
    		{
    			String s = str[i];
    			if(s.length()%2 == 0)
    			{
    				int left = s.length()/2-1, right = s.length()/2;
    				ans[i] = true;
    				while(left > -1)
    				{
    					if(s.charAt(left) == '1' || s.charAt(right) == '0')	// false condition
    					{
    						ans[i] = false;
    						break;
    					}
    					left--;
    					right++;
    				}
    			}
    			else
    			{
    				ans[i] = false;
    			}
    		}
	    }
	}
}