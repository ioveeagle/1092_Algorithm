package hw11;

public class testDataHere {
	
	public int testDataNum = 5;
	
	public static String[][] readData(int num) {
		
		String[] A = null;
		String[] B = null;
		
		if(num == 0) {
			String[] a = {"A", "B", "B", "C", "B", "D", "F", "G"};
			String[] b = {"E", "E", "C", "D", "D", "E", "H", "H"};
			A = a;
			B = b;
			System.out.println("Answer is 2");
		}
		if(num == 1) {
			String[] a = {"A", "B", "D", "G", "E", "I", "E"};
			String[] b = {"B", "C", "E", "E", "F", "H", "J"};
			A = a;
			B = b;
			System.out.println("Answer is 3");
		}
		if(num == 2) {
			String[] a = {"A", "HA", "HA", "WOW", "WOW", "BB", "Hello", "cc", "WOW", "123", "d", "A"};
			String[] b = {"BB", "Andy", "123", "Hello", "World", "HA", "Andy", "123", "cc", "d", "BB", "123"};
			A = a;
			B = b;
			System.out.println("Answer is 1");
		}
		if(num == 3) {
			String[] a = {"A", "C", "D", "E", "Egg", "Apple", "B", "F", "G", "Gold", "Good"};
			String[] b = {"Apple", "Cat", "Dog", "Egg", "Email", "Ant", "Ball", "Flag", "Gold", "Good", "Git"};
			A = a;
			B = b;
			System.out.println("Answer is 7");
		}
		if(num == 4) {
			String[] a = {"B", "B", "A", "A", "F", "F", "I", "F", "B"};
			String[] b = {"A", "C", "D", "E", "G", "H", "J", "I", "F"};
			A = a;
			B = b;
			System.out.println("Answer is 1");
		}
		
		String[][] ret = {A, B};
		shuffle(ret);
		return ret;
	}
	
	public static void shuffle(String[][] a)
	{
		int N = a[0].length;
		for(int i = 0; i < N; i++)
		{
			int r = (int)(Math.random()*(i));
			exch(a[0], i, r);
			exch(a[1], i, r);
		}
	}
	
	private static void exch(String[] a, int i, int j)
	{
		String temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
}
