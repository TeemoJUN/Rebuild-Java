package test;

import java.util.Arrays;



/*
 * 
 * ����һ���������飬ʵ��һ�����������������������ֵ�˳��ʹ�����е�����λ�������ǰ�벿�֣����е�ż��λ��λ������ĺ�벿�֣�����֤������������ż����ż��֮������λ�ò��䡣
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
