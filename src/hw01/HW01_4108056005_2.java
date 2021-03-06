// count max and min together
package hw01;

public class HW01_4108056005_2 extends ArrayData {
	
	boolean isFirst = true;
	int maxValue = 0;
	int minValue = 0;

	public HW01_4108056005_2(int[] A) {
		this.A = A;
	}
	
	public static void main(String[] args) {
		int[] A = {-100, 5, 2222, 45, 666, 90, 87, -55, 123, -88888};
		HW01_4108056005_1 test = new HW01_4108056005_1(A);
		System.out.println(test.max());
		System.out.println(test.min());
	}
	
	@Override
	public int max() {
		if(isFirst) {
			maxAndMin();
			isFirst = false;
		}
		return maxValue;
	}
	
	@Override
	public int min() {
		if(isFirst) {
			maxAndMin();
			isFirst = false;
		}
		return minValue;
	}
	
	public void maxAndMin() {
		int maximun=-99999999;
		int minimun=99999999;
		for(int i=0; i<A.length; i++) {
			if(A[i]>maximun) {
				maximun = A[i];
			}
			if(A[i]<minimun) {
				minimun = A[i];
			}
		}
		maxValue = maximun;
		minValue = minimun;
	}
}
