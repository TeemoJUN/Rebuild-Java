package one;

import java.util.ArrayList;
import java.util.List;

/*
 * 
 * �Ƚ�����������������֮����ȵ���
 * 
 * */

public class Repeat {
	public static void main(String[] args){
		int[] a=new int[50];
		
		for(int i=0;i<a.length;i++){
			a[i]=i;
		}
		
		int[] b=new int[100];
		
		for(int i=0;i<b.length;i++){
			b[i]=2*i;
		}
		
		List<Integer> list=new ArrayList<Integer>();
		eq(a,b,list);
		
		System.out.println(list);//���һλ��¼ѭ������
		
	}
	
	public static void eq(int[] a,int[] b,List<Integer> save){
		int now=0;
		int count=0;
		//�Ƚ�a��b����Ķ�,�����ķ������棬���Լ��ٱȽϴ���
		if(a.length>b.length){
			for(int i=0;i<a.length;i++){
				for(int j=now;j<b.length;j++){
					count++;
					if(a[i]==b[j]){
						now=j;
						save.add(a[i]);
					}
				}
			}
		}else{
			for(int i=0;i<b.length;i++){
				for(int j=now;j<a.length;j++){
					count++;
					if(a[j]==b[i]){
						now=j;
						save.add(a[j]);
					}
				}
			}
		}
		save.add(count);
	}
}