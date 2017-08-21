package test;

public class Fibonacci {
	public static void main(String[] args){
		Fibonacci f=new Fibonacci();
		System.out.println(f.fibonacci(6));
		
	}
	public int fibonacci(int n){
		if(n<=2){
			return 1;
		}
		else{
			return fibonacci(n-2)+fibonacci(n-1);
		}
	}
}
