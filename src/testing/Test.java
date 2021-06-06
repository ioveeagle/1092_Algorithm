package testing;

public class Test {
	public static void main(String[] args) {
		int len = 15;
		int cap = 1 << (int) Math.ceil(Math.log(len)/Math.log(2));
		System.out.println(cap);
	}
}
