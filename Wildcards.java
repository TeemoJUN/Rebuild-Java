package Test_15;

public class Wildcards {
	static void rawArgs(Holder holder,Object obj){
		holder.set(obj);//Warning
		
		holder.set(new Wildcards());//Warning
		
	}
	
	static void unboundedArg(Holder<?> holder,Object arg){
		//holder.set(val);//Error
		
		//holder.set(new Wildcards());//Error
		
		Object obj=holder.get();
		
	}
	
	static <T> T exact1(Holder<T> holder){
		T t=holder.get();
		
		return t;
	}
	
	static <T> T exact2(Holder<T> holder,T arg){
		holder.set(arg);
		T t=holder.get();
		return t;
	}
	
	static <T> T wildSubtype(Holder<? extends T> holder,T arg){
		//  holder.set(val);//Error
		
		T t=holder.get();
		return t;
	}
	
	static <T> void wildSupertype(Holder<? super T> holder,T arg){
		holder.set(arg);
		
		//T t=holder.get();//Error
		
		//OK ,but type information has been lost;
		Object obj=holder.get();
		
	}
	
	public static void main(String[] args){
		Holder raw=new Holder<Long>();
		//Or: 
		raw=new Holder();
		
		Holder<Long> qualified=new Holder<Long>();
		Holder<?> unbounded=new Holder<Long>();
		Holder<? extends Long> bounded=new Holder<Long>();
		
		Long lng=1L;
		
		rawArgs(raw,lng);
		rawArgs(qualified,lng);
		rawArgs(unbounded,lng);
		rawArgs(bounded,lng);
		
		
		unboundedArg(raw,lng);
		unboundedArg(qualified,lng);
		unboundedArg(unbounded,lng);
		unboundedArg(bounded,lng);
		
		//Object r1=exact1(raw);
		//Unchecked conversion from Holder to Holder<T>
		// Unchecked method invocation:exact1(Holder<T>) id applied to (Holder)
		Long r2=exact1(qualified);
		Object r3=exact1(unbounded);//must return Object
		Long r4=exact1(bounded);
		
		//Long r5=exact2(raw,lng)//Warning:
		//Unchecked conversion from Holder to Holder<Long>
		//Unchecked method invocation: exact2(Holder<T>,T)
		// is applied to (HOlder,Long)
		
		Long r6=exact2(qualified,lng);
		
		//¿‡–Õ≤ª≈‰
		//Long r7=exact2(unbounded,lng);//Error
		//Long r8=exact2(bounded,lng);//Error
		
		
		//Long r9=wildSubtype(raw,lng);//Warning
		
		
		
		
		
		Long r10=wildSubtype(raw,lng);
		Object r11=wildSubtype(unbounded,lng);
		Long r12=wildSubtype(bounded,lng);
		
		
		
		
		
		
		
		
	}
	
}
