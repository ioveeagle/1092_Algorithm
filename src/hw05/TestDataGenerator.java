package hw05;

import java.io.*;

public class TestDataGenerator {
	
	// 每 ctrl+F11 就產生一筆新測資
	public static void main(String[] args) {
		TestDataGenerator ra = new TestDataGenerator();
		ra.createData(100, 100);	// 第一個參數是陣列長度，第二個測資是數值範圍(-n~n)
	}
	
	// 產生新測資
	public static void createData(int size, int maxlen) 
	{
		
		try 
		{
			FileWriter fw = new FileWriter("D:\\雲端\\程式檔案\\Eclipse\\file\\1092_Algorithm\\src\\hw05\\testData.txt");
			BufferedWriter bfw = new BufferedWriter(fw);
			
			bfw.write(String.valueOf(size));
			bfw.newLine();
			
			for(int i=0; i<size; i++) 
			{
				int x = (int)(Math.random()*maxlen*2)-maxlen;
				int y = (int)(Math.random()*maxlen*2)-maxlen;
				
				String str = x + "," + y;
				
				bfw.write(str);
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
	
	// 讀取測資
	public static int[][] readData() {
		
		int[][] A = null;
		
		try {
			FileReader fr = new FileReader("D:\\雲端\\程式檔案\\Eclipse\\file\\1092_Algorithm\\src\\hw05\\testData.txt");
			BufferedReader bfr = new BufferedReader(fr);
			
			int lines = Integer.parseInt(bfr.readLine());
			A = new int[lines][2];
			for(int i=0; i<lines; i++) {
				String str = bfr.readLine();
				A[i][0] = Integer.parseInt(str.split(",")[0]);	// value of x
				A[i][1] = Integer.parseInt(str.split(",")[1]);	// value of y
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