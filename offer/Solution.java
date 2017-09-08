package test;

import java.util.Arrays;



//数组的全排序
public class Solution {  
	static int k=0;
	public static void fullSort(int[] array,int lo,int hi){
		if(lo==hi){
			System.out.println(Arrays.toString(array));
			k++;
		}
		else{
			for(int i=lo;i<=hi;i++){
				swap(array,i,lo);
				fullSort(array,lo+1,hi);
				swap(array,i,lo);
			}
		}
	}
	public static void swap(int[] array,int i,int j){
		int temp=array[i];
		array[i]=array[j];
		array[j]=temp;
	}
	public static void main(String[] args){
		int[] array={1,2,3};
		fullSort(array,0,2);
		System.out.println(k);
	}
} 




