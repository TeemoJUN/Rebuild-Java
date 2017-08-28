package three;



public class BinarySerchST<Key extends Comparable<Key>,Value> {
	
	
	
	Key[] keys;
	Value[] vals;
	
	
	int N;
	
	
	@SuppressWarnings("unchecked")
	public BinarySerchST(int n){
		keys=(Key[]) new Comparable[n];
		vals=(Value[]) new Object[n];
	}
	
	public int rank(Key key){
		int lo=0;int hi=N-1;
		while(lo<=hi){
			int mid=lo+(hi-lo)/2;
			int r=key.compareTo(keys[mid]);
			if(r>0){
				lo=mid+1;
			}
			if(r<0){
				hi=mid-1;
			}
			else{
				return mid;
			}
		}
		return lo;
	}
	
	
	public Value get(Key key){
		int index=rank(key);
		
		if(index<N&&key.compareTo(keys[index])==0){
			return vals[index];
		}
		return null;
	}
	
	
	public void put(Key key,Value val){
		int index=rank(key);
		
		if(index<N&&key.compareTo(keys[index])==0){
			vals[index]=val;
		}
		else{
			for(int i=N;i>index;i--){
				keys[i]=keys[i-1];
				vals[i]=vals[i-1];
			}
			keys[index]=key;
			vals[index]=val;
			N++;
		}		
	}
	
	
	public Key min(){
		return keys[0];
	}
	
	public Key max(){
		return keys[N-1];
	}
	
	public int size(){
		return N;
	}
	
	
	
	
	
}
