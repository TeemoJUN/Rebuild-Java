package five;

import java.util.Arrays;


/**
 * 
 * �ȳ��ַ�����λ���ȷ�
 * 
 * */


public class LSD {
	/**
	 * 
	 * @param a Ҫ������ַ���
	 * @param w λ��
	 */
	public static void sort(String[] a,int w){
		int N=a.length;
		int R=256;
		String[] aux=new String[N];
		
		for(int d=w-1;d>=0;d--){
			int[] count=new int[R+1];
			
			for(int i=0;i<N;i++){
				count[a[i].charAt(d)+1]++;
			}
			
			for(int i=0;i<R;i++){
				count[i+1]=count[i]+count[i+1];
			}
			
			for(int i=0;i<N;i++){
				aux[count[a[i].charAt(d)]++]=a[i];
			}
			
			for(int i=0;i<N;i++){
				a[i]=aux[i];
			}			
		}
	}
	public static void main(String[] args){
		String[] array={"abdc","adad","1233","1234","1212","fads"};
		sort(array,4);
		
		System.out.println(Arrays.toString(array));
	}
}
