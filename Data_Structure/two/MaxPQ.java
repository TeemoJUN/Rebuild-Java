package two;

import java.util.NoSuchElementException;

public class MaxPQ {
	private int N;
	private int[] id;
	public MaxPQ(int max){
		id=new int[max+1];
		this.N=0;
	}
	
	public int size(){
		return N;
	}
	
	public void add(int E){
		id[++N]=E;
		swim(N);
	}
	
	private void swim(int index){
		while(index>1&&less(index/2,index)){
			exch(index/2,index);
			index=index/2;
		}
	}
	
	private boolean less(int i,int j){
		return id[i]<id[j];
	}
	
	private void exch(int i,int j){
		int temp=id[i];
		id[i]=id[j];
		id[j]=temp;
	}
	
	public int deleteMax(){
		if(N==0){
			throw new NoSuchElementException();
		}
		int result= id[1];
		id[0]=id[N];
		sink(1);
		return result;
	}
	
	private void sink(int index){
		while(2*index<=N){
			int j=index*2;
			if(j<N&&less(j,j+1)){
				j++;
			}
			if(less(index,j)){
				exch(index,j);
				index=j;
			}else{
				break;
			}
		}	
	}
	
	
	
	public static void main(String[] args){
		MaxPQ pq=new MaxPQ(12);
		pq.add(5);
		pq.add(4);
		pq.add(7);
		pq.add(1);
		pq.add(10);
		pq.add(23);
		int a=pq.deleteMax();
		System.out.println(a);
	}
	
	
	
	
	
}
