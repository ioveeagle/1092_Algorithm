// count in constructor
//package hw01;

public class HW01_4108056005_1 extends ArrayData {
	
	int maximun=-99999999;
	int minimun=99999999;
	
	public HW01_4108056005_1(int[] A) {
		this.A = A;
		for(int i=0; i<A.length; i++) {
			if(A[i]>maximun) {
				maximun = A[i];
			}
		}
		for(int i=0; i<A.length; i++) {
			if(A[i]<minimun) {
				minimun = A[i];
			}
		}
	}
	
	public static void main(String[] args) {
		int[] A = {-100, 5, 2222, 45, 666, 90, 87, -55, 123, -88888};
		HW01_4108056005_1 test = new HW01_4108056005_1(A);
		System.out.println(test.max());
		System.out.println(test.min());
	}
	
	@Override
	public int max() {
		return maximun;
	}
	
	@Override
	public int min() {
		return minimun;
	}
}
