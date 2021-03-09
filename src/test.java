public class test {
	public static void main(String[] args) {
		QuickUnion demo = new QuickUnion(8);
		
		demo.union(4, 3);
		demo.union(3, 7);
		demo.union(6, 5);
		demo.union(3, 1);
		
		demo.connected(3, 7);
		
		demo.show();
	}
}