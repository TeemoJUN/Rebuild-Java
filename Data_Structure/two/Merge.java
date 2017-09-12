package two;

public class Merge {
	private int[] aux;
	
	public void sort(int[] a){
		aux=new int[a.length];
		sort(a,0,a.length-1);
	}
	
	public void sort(int[] a,int low,int hi){
		if(hi<=low){
			return ;
		}
		
		int mid=(low+hi)/2;
		
		sort(a,low,mid);
		sort(a,mid+1,hi);
	}
	
	public void merge(int[] a,int lo,int mid,int hi){
		int i=lo;
		int j=mid+1;
		
		for(int k=lo;k<=hi;k++){
			aux[k]=a[k];
		}
		
		for(int k=0;k<=hi;k++){
			if(i>mid){
				a[k]=aux[j++];
			}
			else if(j>hi){
				a[k]=aux[i++];
			}
			else if(Tools.less(a[j],a[i])){
				a[k]=aux[j++];
			}
			else{
				a[k]=aux[i++];
			}
		}		
	}

}
