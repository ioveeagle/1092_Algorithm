package hw09;

public class Resizing {
	public static void main(String[] args) {
		Resizing r = new Resizing();
		r.call();
	}
	
	public void call() {
		resizeArray a = new resizeArray();
		a.push(1);
		a.push(2);
		a.push(3);
		a.push(4);
		a.push(5);
		a.push(6);
		a.push(7);
		a.push(8);
		a.push(9);
		
		for(int i = 0; i < a.top; i++) {
			System.out.print(a.get(i)+" ");
		}
		
		System.out.println("top = "+a.top());
	}
	
	class resizeArray {
		private int top = 0;
		private int array[] = new int[1];
		
		public resizeArray() {
			// TODO Auto-generated constructor stub
		}
		
		public void resize(int newSize) {
			int copy[] = new int[newSize];
			System.arraycopy(array, 0, copy, 0, array.length);
			array = copy;
		}	
		public void push(int v) {
			System.out.println("push "+v);
			if(top == array.length) {
				System.out.println("size*2");
				resize(2*array.length);
			}
			array[top] = v;
			top++;
		}	
		public int pop() {
			top--;
			int v = array[top];
			array[top] = 0;
			if(top > 0 && top == array.length/4) {
				System.out.println("size/2");
				resize(array.length/2);
			}
			return v;
		}
		public int get(int i) {
			return array[i];
		}
		public int top() {
			return top;
		}
	}
}
