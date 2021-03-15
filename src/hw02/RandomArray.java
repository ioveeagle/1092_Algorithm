package hw02;

import java.io.*;
import java.util.stream.IntStream;

public class RandomArray {
	
	// 每 ctrl+F11 就產生一筆新測資
	public static void main(String[] args) {
		RandomArray ra = new RandomArray();
		ra.createData(10000, 50000);	// 第一個參數是陣列長度，第二個測資是數值範圍(-n~n)
	}
	
	// 產生新測資
	public void createData(int len,int value) {
		int[] A = new int[len];
		int i=0;
		while(i<len) {
			int ran = (int)(Math.random()*(2*value)) - value;
			if(!IntStream.of(A).anyMatch(x -> x == ran)) {
				A[i] = ran;
				i++;
			}
		}
		
		try {
			FileWriter fw = new FileWriter("D:\\雲端\\程式檔案\\Eclipse\\file\\1092_Algorithm\\src\\hw02\\testData.txt");
			BufferedWriter bfw = new BufferedWriter(fw);
			
			bfw.write(String.valueOf(len));
			bfw.newLine();
			for(int j=0; j<len; j++) {
				bfw.write(String.valueOf(A[j]));
				bfw.newLine();
			}
			
			bfw.close();
		}
		catch(IOException e) {
			System.out.println(e);
		}
		System.out.println("createData complete!");
		System.out.println("len = "+len);
	}
	
	// 讀取測資
	public int[] readData() {
		int[] A = null;
		
		try {
			FileReader fr = new FileReader("D:\\雲端\\程式檔案\\Eclipse\\file\\1092_Algorithm\\src\\hw02\\testData.txt");
			BufferedReader bfr = new BufferedReader(fr);
			
			int lines = Integer.parseInt(bfr.readLine());
			A = new int [lines];
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
}