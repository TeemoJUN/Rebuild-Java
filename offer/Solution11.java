package test;


/*
 * 
 * ����һ��������������������Ʊ�ʾ��1�ĸ��������и����ò����ʾ��
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
