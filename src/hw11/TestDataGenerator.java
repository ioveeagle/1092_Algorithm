package hw11;

import java.io.*;

public class TestDataGenerator {

	// 每 ctrl+F11 就產生一筆新測資
	public static void main(String[] args) {
		TestDataGenerator tdg = new TestDataGenerator();
		tdg.createData(1000, 2000);
	}

	// 產生新測資
	public void createData(int size, int range) {
		try {
			
			FileWriter fw = new FileWriter(
					"D:\\雲端\\程式檔案\\Eclipse\\file\\1092_Algorithm\\src\\hw11\\11-testData\\testData6.txt");
			BufferedWriter bfw = new BufferedWriter(fw);

			bfw.write(String.valueOf(size));
			bfw.newLine();
			bfw.write("-1");
			bfw.newLine();

			for (int i = 0; i < size; i++) {
				int num1 = (int) (Math.random()*range);	// 0~range-1
				int num2 = (int) (Math.random()*range);	// 0~range-1
				
				bfw.write(String.valueOf(num1)+" "+String.valueOf(num2));
				bfw.newLine();
			}

			bfw.close();
			
		} catch (IOException e) {
			System.out.println(e);
		}
		System.out.println("createData complete!");
		System.out.println("size = " + size + ", range = " + range);
	}

	public int readAns() {
		return ans;
	}

	int ans;

	// 讀取測資
	public String[][] readData() {
		String[][] r = null;

		try {
			FileReader fr = new FileReader(
					"D:\\雲端\\程式檔案\\Eclipse\\file\\1092_Algorithm\\src\\hw11\\11-testData\\testData6.txt");
			BufferedReader bfr = new BufferedReader(fr);

			int lines = Integer.parseInt(bfr.readLine());
			ans = Integer.parseInt(bfr.readLine());
			r = new String[2][lines];

			for (int i = 0; i < lines; i++) {
				String[] ch = bfr.readLine().split(" ");
				r[0][i] = ch[0];
				r[1][i] = ch[1];
			}

			bfr.close();
		} catch (IOException e) {
			System.out.println(e);
		}

		return r;
	}

	// display the array
	private void show(int[] A) {
		for (int i = 0; i < A.length; i++) {
			System.out.print(A[i] + ", ");
		}
		System.out.println();
	}
}