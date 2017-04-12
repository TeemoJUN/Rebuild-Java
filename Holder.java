package Test_15;

public class Holder<T> {
	
	private T value;
	public Holder(){}
	
	public Holder(T val){
		this.value=val;
	}
	
	public void set(T val){
		value=val;
	}
	
	public T get(){
		return value;
	}
	
	public boolean equals(Object obj){
		return value.equals(obj);
	}
	
	public static void main(String[] args){
		Holder<Apple> apple=new Holder<Apple>(new Apple());
		
		Apple d=apple.get();
		
		apple.set(d);
		
		
	}

}
