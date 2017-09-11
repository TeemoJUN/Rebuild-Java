package five;

import java.util.Arrays;

public class Quick3SortString {
	
	
	public static void main(String[] args){
		String[] array={"adaasd","fasffas","fada","fadfjgfadfa","adfasfsdfds"};
		Quick3SortString a=new Quick3SortString();
		a.sort(array);
		System.out.println(Arrays.toString(array));
	}
	
	
	
	public void sort(String[] a){
		sort(a,0,a.length-1,0);
	}
	private void sort(String[] a,int lo,int hi,int d){
		
		if(hi<=lo){
			return ;
		}
		
		int v=charAt(a[lo],d);
		
		int i=lo+1;
		int lt=lo;
		int gi=hi;
		
		while(i<=gi){
			int now=charAt(a[i],d);
			if(now<v){
				exch(a,lt++,i++);
			}
			else if(now>v){
				exch(a,i,gi--);
			}
			else{
				i++;
			}
		}
		sort(a,lo,lt-1,d);
		if(v>0){
			sort(a,lt,gi,d+1);
		}
		sort(a,gi+1,hi,d);
		
		
	}
	
	private int charAt(String a,int d){
		if(a.length()<d){
			return -1;
		}
		else{
			return a.charAt(d);
		}
	}
	private void exch(String[] a,int i,int j){
		String temp=a[i];
		a[i]=a[j];
		a[j]=temp;
	}
}
