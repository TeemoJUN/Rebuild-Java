package test;


/*
 * 
 * 输入一个整数，输出该数二进制表示中1的个数。其中负数用补码表示。
 * 
 * */
public class Solution11 {
	public static void main(String[] args){
		
		
		
	 	int num=3;
	int n=NumberOf1(num);
	System.out.println(n);
	}
	
	public static int NumberOf1(int n) {
		int count=0;
		
		while(n!=0){
			n=n&(n-1);
			count++;
		}
		return count;
	}
}
