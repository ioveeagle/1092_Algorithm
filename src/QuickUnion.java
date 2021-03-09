
public class QuickUnion {
	
	private int[] id;

	public QuickUnion(int N) {
		id = new int[N];
		for (int i = 0; i < N; i++)
			id[i] = i;
	}

	public int find(int i) {
		while (i != id[i])
			i = id[i];
		return i;
	}

	public void union(int p, int q) {
//		int proot = find(p);
//		int qroot = find(q);
//		id[proot] = qroot;
		id[p] = q;
	}
	
	public void connected(int p, int q) {
		if(find(p) == find(q)) {
			System.out.println("connected");
		}
		else {
			System.out.println("not connected");
		}
	}
	
	public void show() {
		for(int i = 0; i < id.length; i++) {
			System.out.print(id[i]+" ");
		}
	}
}
