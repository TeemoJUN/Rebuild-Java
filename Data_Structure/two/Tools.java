package two;

public class Tools {
	public static boolean less(int i,int j){
		return i<j;
	}
	
	public static void exch(int[] a,int i,int j){
		int temp=a[i];
		a[i]=a[j];
		a[j]=temp;
	}
}
