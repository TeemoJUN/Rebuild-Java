package test_1;

public class PrimeGenerator extends Thread{
	public void run(){
		long number=1L;
		
		while(true){
			if(isPrime(number)){
				System.out.printf("Number %d is Prime",number);
			}
			if(isInterrupted()){
				System.out.printf("The Prime Generator has been Interruptred");
				return ;
			}
		}
	}
	
	
	private boolean isPrime(long number){
		if(number<=2){
			return true;
		}
		for(int i=1;i<number;i++){
			if((number%i)==0){
				return false;
			}
		}
		return true;
		
	}
	
	public static void main(String[] args){
		Thread task=new PrimeGenerator();
		task.start();
		
		try{
			Thread.sleep(5000);
		}
		catch(InterruptedException e){
			e.printStackTrace();
		}
		task.interrupt();
	}
}
