package two;

import java.util.Arrays;

public class QuickSort {
	public void sort(int[] arr){
		sort(arr,0,arr.length-1);
	}
	public void sort(int[] arr,int lo,int hi){
		
		if(hi<=lo){
			return ;
		}
		int index=findIndex(arr,lo,hi);
		
		sort(arr,lo,index-1);
		sort(arr,index+1,hi);
	}
	
	public int findIndex(int[] arr,int lo,int hi){
		int i=lo;
		int j=hi+1;
		int first=arr[lo];
		
		while(true){
			while(Tools.less(first, arr[--j])){
				if(j==lo){
					break;
				}
			}
			while(Tools.less(arr[++i],first)){
				if(i==hi){
					break;
				}
			}
			
			if(i>=j){
				break;
			}
			Tools.exch(arr, i, j);
		}
		Tools.exch(arr, lo, j);
		return j;
	}
	
	
	
	public static void main(String[] args){
		int[] arr={3,4,2,1,7,5,7,8,3,70,71,32,43,22};
		QuickSort q=new QuickSort();
		
		q.sort(arr);
		System.out.println(Arrays.toString(arr));
		
	}
		
}





















