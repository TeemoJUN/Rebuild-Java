package test_1;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

class Result{
	private String name;
	public Result(){
		
	}
	
	public void setName(String name){
		this.name=name;
	}
	
	public String getName(){
		return name;
	}
}




public class SearchTask implements Runnable{

	private Result result;
	
	public SearchTask(Result result){
		this.result=result;
	}
	
	@Override
	public void run() {
		
		String name=Thread.currentThread().getName();
		System.out.printf("Thread %s : Start\n",name);
		
		try{
			
			doTask();	
			result.setName(name);
		
		}
		catch(InterruptedException e){
			System.out.printf("Thread %s: Interrupted\n",name);
		}
		
		System.out.printf("Thread %s : End\n",name);
		
	}
	
	private void doTask() throws InterruptedException{
		Random random=new Random((new Date()).getTime());
		
		int value=(int)(random.nextDouble()*100);
		
		System.out.printf("Thead %s: %d\n",Thread.currentThread().getName(),value);
		
		TimeUnit.SECONDS.sleep(value);
	}
	
	
	
	
	
	
	
	
	
	
	

}
