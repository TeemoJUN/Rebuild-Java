package two;

import java.util.Arrays;


/*
 * 中位数的查找
 */
public class FindMedian {
	public static void main(String[] args){
		
		int[] a={2,45,6,2,32,5,21,44,12,434,55,12};
		int[] arr={2,45,6,2,32,5,21,44,12,434,55,12};//arr和a保持一致方便查找
		System.out.println("排序前----");
		System.out.println(Arrays.toString(a));
		
		//用快速排序来排序方便后续对比
		QuickSort q=new QuickSort();
		q.sort(a);
		System.out.println("排序后----");
		System.out.println(Arrays.toString(a));
				
		System.out.println("数组长度:  "+a.length);
		
		//查找中位数
		FindMedian f=new FindMedian();
		double median=f.findMedian(arr);
		System.out.println("中位数      "+median);
		
	}
	
	//判断是否是奇数或偶数
	public double findMedian(int[] arr){
		double median;
		int len=arr.length;
		if(len==1){
			median=arr[0];//数组长度为为一时
		}
		else if(len%2==1){
			System.out.println("奇数中位数的位置在数组中对应的位置:  "+len/2);
			median=findOdd(arr,len/2);
		}
		else{
			System.out.println("偶数中位数的位置在数组中对应的位置:  "+(len/2-1)+"  "+(len/2));
			median=findEven(arr,(len/2-1),(len/2));
		}
		return median;
	}
	
	/*奇数
	 *这里用来找median（即中位数的位置）也是在数组arr中第median+1大的数，
	 *也可以扩展找任何median+1大的数字
	 * */
	public int findOdd(int[] arr,int median){
		int lo=0;
		int hi=arr.length-1;
		int p=partition(arr,lo,hi);//第一个值应该在的位置
		
		while(p!=median){//不是中位数的位置
			if(p<median){
				p=partition(arr,p+1,hi);//大于就在数组右边查找
			}
			else{
				p=partition(arr,0,p-1);//否则在数组左边查找
			}
		}
		return arr[p];
	}
	
	//偶数查找
	public double findEven(int[] arr,int first,int second){
		int a=findOdd(arr,first);
		int b=findOdd(arr,second);
		System.out.println(" 第一个值为 : "+a);
		System.out.println(" 第二个值为 : "+b);
		double nums=(a+b)/2.0;
		return nums;
	}
	
	//切分方法
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


