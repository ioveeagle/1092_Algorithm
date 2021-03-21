package hw03;

import java.io.*;

public class TestDataGenerator {
	
	// 每 ctrl+F11 就產生一筆新測資
	public static void main(String[] args) {
		TestDataGenerator ra = new TestDataGenerator();
		ra.createData(1000000, 1000000);	// 第一個參數是陣列長度，第二個測資是數值範圍(-n~n)
	}
	
	// 產生新測資
	public void createData(int len,int value) {
		// 產生隨機的陣列
		int[] A = new int[len];
		for(int i=0; i<len; i++)
		{
			A[i] = (int)(Math.random()*(2*value)) - value;
		}
		
		// 從小到大排序
		merge_sort(A);
		
		// 切割
		int[] B = new int[len];
		int cut = (int)(Math.random()*(len-1))+1;
		
		for(int i=0; i<len; i++)
		{
			B[i] = A[(i+cut+1)%len];
		}
		
//		show(A);
//		show(B);
		
		try {
			FileWriter fw = new FileWriter("D:\\雲端\\程式檔案\\Eclipse\\file\\1092_Algorithm\\src\\hw03\\testData.txt");
			BufferedWriter bfw = new BufferedWriter(fw);
			
			bfw.write(String.valueOf(cut));
			bfw.newLine();
			bfw.write(String.valueOf(len));
			bfw.newLine();
			for(int j=0; j<len; j++) {
				bfw.write(String.valueOf(B[j]));
				bfw.newLine();
			}
			
			bfw.close();
		}
		catch(IOException e) {
			System.out.println(e);
		}
		System.out.println("createData complete!");
		System.out.println("len = "+len);
		System.out.println("answer is: "+cut);
	}
	
	// 讀取測資
	public int[] readData() {
		int[] A = null;
		
		try {
			FileReader fr = new FileReader("D:\\雲端\\程式檔案\\Eclipse\\file\\1092_Algorithm\\src\\hw03\\testData.txt");
			BufferedReader bfr = new BufferedReader(fr);
			
			int ans = Integer.parseInt(bfr.readLine());
			System.out.println("answer is: "+ans);
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
	
	// mergeSort
	private void merge_sort(int[] arr){
        int[] orderedArr = new int[arr.length];
        for(int i = 2; i < arr.length << 1; i = i << 1) {
            for(int j = 0; j < (arr.length + i - 1) / i; j++) {
                int left = i * j;
                int mid = (left + (i >> 1)) >= arr.length ? (arr.length - 1) : (left + (i >> 1));
                int right = i * (j + 1) - 1 >= arr.length ? (arr.length - 1) : (i * (j + 1) - 1);
                int start = left, l = left, m = mid;
                while (l < mid && m <= right) {
                    if (arr[l] < arr[m]) {
                        orderedArr[start++] = arr[l++];
                    } else {
                        orderedArr[start++] = arr[m++];
                    }
                }
                while (l < mid)
                    orderedArr[start++] = arr[l++];
                while (m <= right)
                    orderedArr[start++] = arr[m++];
                System.arraycopy(orderedArr, left, arr, left, right - left + 1);
            }
        }
    }
	
	// display the array
	private void show(int[] A) {
		for(int i=0; i<A.length; i++) {
			System.out.print(A[i]+", ");
		}
		System.out.println();
	}
}