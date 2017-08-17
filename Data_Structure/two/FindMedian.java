package two;

import java.util.Arrays;


/*
 * ��λ���Ĳ���
 */
public class FindMedian {
	public static void main(String[] args){
		
		int[] a={2,45,6,2,32,5,21,44,12,434,55,12};
		int[] arr={2,45,6,2,32,5,21,44,12,434,55,12};//arr��a����һ�·������
		System.out.println("����ǰ----");
		System.out.println(Arrays.toString(a));
		
		//�ÿ������������򷽱�����Ա�
		QuickSort q=new QuickSort();
		q.sort(a);
		System.out.println("�����----");
		System.out.println(Arrays.toString(a));
				
		System.out.println("���鳤��:  "+a.length);
		
		//������λ��
		FindMedian f=new FindMedian();
		double median=f.findMedian(arr);
		System.out.println("��λ��      "+median);
		
	}
	
	//�ж��Ƿ���������ż��
	public double findMedian(int[] arr){
		double median;
		int len=arr.length;
		if(len==1){
			median=arr[0];//���鳤��ΪΪһʱ
		}
		else if(len%2==1){
			System.out.println("������λ����λ���������ж�Ӧ��λ��:  "+len/2);
			median=findOdd(arr,len/2);
		}
		else{
			System.out.println("ż����λ����λ���������ж�Ӧ��λ��:  "+(len/2-1)+"  "+(len/2));
			median=findEven(arr,(len/2-1),(len/2));
		}
		return median;
	}
	
	/*����
	 *����������median������λ����λ�ã�Ҳ��������arr�е�median+1�������
	 *Ҳ������չ���κ�median+1�������
	 * */
	public int findOdd(int[] arr,int median){
		int lo=0;
		int hi=arr.length-1;
		int p=partition(arr,lo,hi);//��һ��ֵӦ���ڵ�λ��
		
		while(p!=median){//������λ����λ��
			if(p<median){
				p=partition(arr,p+1,hi);//���ھ��������ұ߲���
			}
			else{
				p=partition(arr,0,p-1);//������������߲���
			}
		}
		return arr[p];
	}
	
	//ż������
	public double findEven(int[] arr,int first,int second){
		int a=findOdd(arr,first);
		int b=findOdd(arr,second);
		System.out.println(" ��һ��ֵΪ : "+a);
		System.out.println(" �ڶ���ֵΪ : "+b);
		double nums=(a+b)/2.0;
		return nums;
	}
	
	//�зַ���
	public static int partition(int[] arr,int lo,int hi){
		
		int i=lo;int j=hi+1;
		int v=arr[lo];
		
		while(true){	
			while(arr[--j]>v){
				if(j==lo){
					break;
				}
			}
			while(arr[++i]<v){
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
}


