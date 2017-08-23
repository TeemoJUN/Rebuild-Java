package test;

import java.util.Arrays;



/*
 * 
 * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有的奇数位于数组的前半部分，所有的偶数位于位于数组的后半部分，并保证奇数和奇数，偶数和偶数之间的相对位置不变。
 * 
 * 
 * */

public class Solution13 {
	
	
	public static void main(String[] args){
		int[] a={12,32,12,2,31,12,1,4,5};
		
		Solution13 s=new Solution13();
		
		
		s.reOrderArray(a);
		System.out.println(Arrays.toString(a));
	}
	
	
	
	public void reOrderArray(int [] array) {
	 
		
		if(array.length==1){
			return ;
		}
		 
		int len=array.length;
		int[] a=new int[len];
		
		int count=0;
		
		for(int i=0;i<len;i++){
			if((array[i]&1)==1){
				a[count++]=array[i];
			}
		}
		for(int i=0;i<len;i++){
			if(!((array[i]&1)==1)){
				a[count++]=array[i];
			}
		}
		 
		for(int i=0;i<len;i++){
			array[i]=a[i];
		}
	 }
}
