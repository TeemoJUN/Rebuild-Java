package test;

import java.util.ArrayList;


/*
 * 
 * 输入n个整数，找出其中最小的K个数。例如输入4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4,。
 * 
 * */

public class Solution19 {
	 public ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
	       sort(input);
	        ArrayList<Integer> r=new ArrayList<Integer>();
	        if(k<input.length){
	        	return r;
	        }
	        for(int i=0;i<k;i++){
	        	r.add(input[i]);
	        }
	        return r;
	   }
	 
	 
	 public static void sort(int[] array){
		 sort(array,0,array.length-1);
	 }
	 
	 public static void sort(int[] array,int lo,int hi){
		 if(hi<=lo){
			 return ;
		 }
		 
		 
		 
		 int index=partation(array,lo,hi);
		 sort(array,lo,index-1);
		 sort(array,index+1,hi);
	 }
	 
	 private static int partation(int[] array,int lo,int hi){
		 
		 int i=lo;
		 int j=hi+1;
		 int v=array[lo];
		 
		 while(true){
			 
			 while(less(v,array[--j])){
				 if(j==lo){
					 break;
				 }
			 }
			 while(less(array[++i],v)){
				 if(i==hi){
					 break;
				 }
			 }
			 if(i>=j){
				 break;
			 }
			 
			 exch(array,i,j);
		 }
		 exch(array,lo,j);
		 return j;
	 }
	 
	 
	 
	 
	 
	 
	 
	 
	 private static void exch(int[] array,int i,int j){
		 int temp=array[i];
		 array[i]=array[j];
		 array[j]=temp;
	 }
	 
	 
	 
	 
	 private static boolean less(int i,int j){
		 return i<j;
	 }
}
