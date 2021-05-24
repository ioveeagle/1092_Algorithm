package hw09;
import java.util.ArrayList;
import java.util.Collections;
public class HW09_4108056025_1 extends LSD {
	public static void main(String[] args) {
		int[][] input =  { { 0, 1 }, { 1, 2 }, { 2, 3 }, { 3, 1 }, { 2, 4 }, { 5, 4 }, { 6, 4 }, { 3, 7 }, { 7, 8 }, { 7, 10 }, { 8, 9 }};
		HW09_4108056025_1 test = new HW09_4108056025_1();
		int ans = test.Distance(input);
		
		System.out.println(ans);
	}
	public int Distance(int[][] array) {
	    Short_path_4108056025 path=new Short_path_4108056025(array);
	    int ans=path.go_back();
	    return ans;
	}
}
class Short_path_4108056025{
	private ArrayList<Integer> connect=new ArrayList<Integer>();
	private ArrayList<Integer> input=new ArrayList<Integer>();
	private int LSD=0;
	private int index_2[][];
	Short_path_4108056025(int array[][]){
	    if(array[0][0]==array[0][1]) {
	    	input.add(array[0][0]);
	    	index++;
	    }else {
	    	input.add(array[0][0]);
	    	input.add(array[0][1]);
	    	index+=2;
	    }
	    int add=1;
	    for(int a=1;a<array.length;a++) {
	        for(int b=0;b<index;b++) {
	    		add=1;
	        	if(array[a][0]==input.get(b)) {
	    			add=0;
	    			break;
	    		}
	    	}
	        if(add==1) {
	        	input.add(array[a][0]);
	        	index++;
	        }
	        for(int b=0;b<index;b++) {
	    		add=1;
	        	if(array[a][1]==input.get(b)) {
	    			add=0;
	    			break;
	    		}
	    	}
	        if(add==1) {
	        	input.add(array[a][1]);
	        	index++;
	        }
	    }
	    Collections.sort(input);
	    int index_1[][] = new int [index][2]; 
	    int count;
	    ArrayList<Integer> temp=new ArrayList<Integer>();
	    for(int c=0;c<index;c++) {
	    	count=0;
	    	for(int d=0;d<array.length;d++) {
	    		if(array[d][0]==array[d][1]) {
	    			
	    		}else if(input.get(c)==array[d][0]) {
	    			add=1;
	    			for(int e=0;e<count;e++) {
	    				if(temp.get(e)==array[d][1]) {
	    					add=0;
	    					break;
	    				}
	    			}
	    			if(add==1) {
	    				temp.add(array[d][1]);
	    				count++;
	    			}
	    		}else if(input.get(c)==array[d][1]) {
	    			add=1;
	    			for(int e=0;e<count;e++) {
	    				if(temp.get(e)==array[d][0]) {
	    					add=0;
	    					break;
	    				}
	    			}
	    			if(add==1) {
	    				temp.add(array[d][0]);
	    				count++;
	    			}
	    		}
	    	}
	    	index_1[c][0]=count;
	    	connect.addAll(temp);
	    	temp.clear();
	    }
	    for(int f=1;f<index;f++) {
	    	index_1[f][1]=index_1[f-1][1]+index_1[f-1][0];
	    }
	    set(index_1,index);
	}
	
	public int go_back() {
		for(int f=0;f<index;f++) {
	    	for(int g=f+1;g<index;g++) {
	    		if(index_2[f][0]!=0) {
	    			short_path_by_4108056025(g,f);
	    		}else {
	    			break;
	    		}
	    	}
	    }
		return LSD;
	}
	private int index=0;
	void set(int data[][],int index) {
		index_2=data;
		index=this.index;
	}
	private int finished;
	private int SD;
	private int found;
	private ArrayList<Integer> path=new ArrayList<Integer>();
	private void short_path_by_4108056025(int finish,int now) {
	  	finished=input.get(finish);
	  	SD=index_2.length-1;
	  	found=0;
	  	path.clear();
	  	path.add(input.get(now));
	  	short_path_start_4108056025(now);
	  	if(LSD<SD&&found==1) {
	  		LSD=SD;
	  	}
	}
	private void short_path_start_4108056025(int now) {
		if(path.size()-1>SD) {
			
		}else if(index_2[now][0]==0) {
			
		}else {
			int reserve[]=new int [path.size()];
			for(int a=0;a<path.size();a++) {
				reserve[a]=path.get(a);
			}
			
			for(int b=0;b<index_2[now][0];b++) {
				if(connect.get(index_2[now][1]+b)==finished) {
			        found=1;
					if(path.size()<SD) {
			        	SD=path.size();
			        }
			        return;
				}else {
					int add=1;
					for(int c=0;c<path.size();c++){
						if(path.get(c)==connect.get(index_2[now][1]+b)) {
							add=0;
							break;
						}
					}
					if(add==1) {
						path.add(connect.get(index_2[now][1]+b));
						short_path_start_4108056025(input.indexOf(connect.get(index_2[now][1]+b)));
						path.clear();
						for(int d=0;d<reserve.length;d++) {
							path.add(reserve[d]);
						}
					}
				}
			}
		}
	}
}

