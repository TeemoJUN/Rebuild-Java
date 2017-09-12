package two;

import java.util.Arrays;

public class Insertion {

	
	private static int count=0;//µπ÷√∂‘
	
	public static void sort(int[] a){
		for(int i=0;i<a.length;i++){
			for(int j=i;j>0&&Tools.less(a[j],a[j-1]);j--){
				Tools.exch(a, i, j);
				count++;
			}
		}
	}
	
	public int getCount(){
		return count;
	}
	
	
	
	public static void main(String[] args){
		int[] arr={3,4,57,21,2,3,1,4,2,5,6};
		Insertion a=new Insertion();
		a.sort(arr);
		System.out.println(Arrays.toString(arr));
		
	}
}
