package two;

public class Heap {
	public  void sort(int[] arr){
		int N=arr.length;
		
		for(int i=N/2;N>=1;N--){
			sink(arr,i,N);
		}
		
		while(N>0){
			Tools.exch(arr,1,N--);
			sink(arr,1,N);
		}
	}
	
	
	public void sink(int[] arr,int index,int N){
		
		while(2*index<=N){
			int j=index*2;
			if(j<N&&arr[j]<arr[j+1]){
				j++;
			}
			if(arr[j]<arr[index]){
				Tools.exch(arr, j, index);
			}else{
				break;
			}
		}
		
		if(arr instanceof int[]){
			System.out.println("aa");
		}
	}
}
