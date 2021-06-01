package hw10;

import java.io.*;

public class TestDataGenerator {
	
	// 每 ctrl+F11 就產生一筆新測資
	public static void main(String[] args) {
	}
	
	// 產生新測資
	public void createData(int size, int maxlen) 
	{
		
		try 
		{
			FileWriter fw = new FileWriter("D:\\雲端\\程式檔案\\Eclipse\\file\\1092_Algorithm\\src\\hw12\\testData.txt");
			BufferedWriter bfw = new BufferedWriter(fw);
			
			bfw.write(String.valueOf(size));
			bfw.newLine();
			
			for(int i=0; i<size; i++) 
			{
				String str = "";
				boolean ans = true;
				int ran1 = (int)(Math.random()*3); // 0~2
				if(ran1 == 0)	// true: 1/3
				{
					int len = (int)(Math.random()*(maxlen/2))*2 + 2;	// even length
					for(int j=0; j<len/2; j++)
					{
						str = str +"0";
					}
					for(int j=len/2; j<len; j++)
					{
						str = str +"1";
					}
					ans = true;
				}
				else	// false: 2/3
				{
					int ran2 = (int)(Math.random()*3); // 0~2
					if(ran2 == 0)	// odd length
					{
						int len = (int)(Math.random()*(maxlen/2))*2 + 1;	// odd length
						for(int j=0; j<len/2; j++)
						{
							str = str +"0";
						}
						for(int j=len/2; j<len; j++)
						{
							str = str +"1";
						}
					}
					else if(ran2 == 1)	// wrong digit near edge
					{
						int len = (int)(Math.random()*(maxlen/2))*2 + 2;	// even length
						for(int j=0; j<len/2; j++)
						{
							str = str +"0";
						}
						for(int j=len/2; j<len; j++)
						{
							str = str +"1";
						}
						
						// insert wrong digit
						int ran3 = (int)(Math.random()*3);
						if(ran3 == 0)	// wrong in zero
						{
							int wrong = (int)(Math.random()*(len/4));
							char[] charArray = str.toCharArray();
							charArray[wrong] = '1';
							str = String.valueOf(charArray);
						}
						else if(ran3 == 1)	// wrong in one
						{
							int wrong = (int)(Math.random()*(len/4));
							char[] charArray = str.toCharArray();
							charArray[str.length()-1-wrong] = '0';
							str = String.valueOf(charArray);
						}
						else	// wrong in both
						{
							char[] charArray = str.toCharArray();
							int wrong = (int)(Math.random()*(len/4));
							charArray[wrong] = '1';
							wrong = (int)(Math.random()*(len/4));
							charArray[str.length()-1-wrong] = '0';
							str = String.valueOf(charArray);
						}
					}
					else	// wrong digit near mid
					{
						int len = (int)(Math.random()*(maxlen/2))*2 + 2;	// even length
						for(int j=0; j<len/2; j++)
						{
							str = str +"0";
						}
						for(int j=len/2; j<len; j++)
						{
							str = str +"1";
						}
						
						// insert wrong digit
						int ran3 = (int)(Math.random()*3);
						if(ran3 == 0)	// wrong in zero
						{
							int wrong = (int)(Math.random()*(len/4));
							char[] charArray = str.toCharArray();
							charArray[(str.length()/2-1)-wrong] = '1';
							str = String.valueOf(charArray);
						}
						else if(ran3 == 1)	// wrong in one
						{
							int wrong = (int)(Math.random()*(len/4));
							char[] charArray = str.toCharArray();
							charArray[str.length()/2+wrong] = '0';
							str = String.valueOf(charArray);
						}
						else	// wrong in both
						{
							char[] charArray = str.toCharArray();
							int wrong = (int)(Math.random()*(len/4));
							charArray[(str.length()/2-1)-wrong] = '1';
							wrong = (int)(Math.random()*(len/4));
							charArray[str.length()/2+wrong] = '0';
							str = String.valueOf(charArray);
						}
					}
					ans = false;
				}
				
				bfw.write(str);
				bfw.newLine();
				bfw.write(String.valueOf(ans));
				bfw.newLine();
			}
			
			bfw.close();
		}
		catch(IOException e) {
			System.out.println(e);
		}
		System.out.println("createData complete!");
		System.out.println("size = "+size+", maxlen = "+maxlen);
	}
	
	private boolean[] ans = null;
	
	public boolean[] readAns()
	{
		return ans;
	}
	
	// 讀取測資
	public int[] readData() {
		int[] A = null;
		
		try {
			FileReader fr = new FileReader("D:\\雲端\\NCHU\\大二\\大二下\\演算法\\作業\\testData\\hw10_testData\\testData_1000.txt");
			BufferedReader bfr = new BufferedReader(fr);
			
			int lines = Integer.parseInt(bfr.readLine());
			A = new int[lines];
			for(int i=0; i<lines; i++) {
				A[i] = Integer.parseInt(bfr.readLine());
			}
			
			bfr.close();
		}
		catch(IOException e) {
			System.out.println(e);
		}
		
		return A;
	}
	
	// display the array
	private void show(int[] A) {
		for(int i=0; i<A.length; i++) {
			System.out.print(A[i]+", ");
		}
		System.out.println();
	}
}