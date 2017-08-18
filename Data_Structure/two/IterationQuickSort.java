package two;

import java.util.Arrays;

import one.Stack;
/*
 * @author wanzailin
 * 用堆栈模拟递归法来进行快速排序
 */



public class IterationQuickSort {
	public static void main(String[] args) throws Exception{
		int[] a={6,3,4,12,34,56,77,33,21,33,12,32,12,21,45,67,12,433,12,343,32,123};
		System.out.println("排序前----");
		System.out.println(Arrays.toString(a));
		IterationQuickSort q=new IterationQuickSort();
		q.sort(a);
		System.out.println("排序完----");
		System.out.println(Arrays.toString(a));
	}
	
	
	public void sort(int[] arr) throws Exception{
		sort(arr,0,arr.length-1);
	}
	
	public void sort(int[] arr,int lo,int hi) throws Exception{
		Stack<Integer> stack=new Stack<Integer>();
		stack.push(lo);//将头尾的两个位置进栈
		stack.push(hi);
		while(stack.size()>0){//栈不为空时
			hi=stack.pop();
			lo=stack.pop();
			int mid=partition(arr,lo,hi);
			if(lo<mid-1){
				stack.push(lo);
				stack.push(mid-1);
			}
			if(hi>mid+1){
				stack.push(mid+1);
				stack.push(hi);
			}
		}
	}
	
	public int partition(int[] arr,int lo,int hi){
		int i=lo;
		int j=hi+1;
		int first=arr[lo];
		
		while(true){
			while(Tools.less(first, arr[--j])){
				if(j==lo){
					break;
				}
			}
			while(Tools.less(arr[++i],first)){
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
