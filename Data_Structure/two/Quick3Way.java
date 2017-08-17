package two;

import java.util.Arrays;

public class Quick3Way {
	public static void main(String[] args){
		Quick3Way quick=new Quick3Way();
		
		int[] a={2,1,3,4,52,5,3,2,3,5,6,7,8,9,0,10};
		quick.sort(a);
		
		System.out.println(Arrays.toString(a));
		
		
	}
	
	public void sort(int[] arr){
		sort(arr,0,arr.length-1);
	}
	
	public void sort(int[] arr,int lo,int hi){
		if(hi<=lo){
			return ;
		}
		int lt=lo;int i=lo+1;int gi=hi;
		int v=arr[lo];
		
		while(i<=gi){
			if(arr[i]==v){
				i++;
			}
			else if(arr[i]<v){
				Tools.exch(arr, lt++, i++);
			}
			else{
				Tools.exch(arr, gi--, i);
			}
		}
		sort(arr,0,lt-1);
		sort(arr,i,hi);
	}
}
