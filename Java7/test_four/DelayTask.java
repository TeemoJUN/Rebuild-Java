package test_4;

import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 
 * 在执行器中延时执行任务
 * 
 * @author lin
 *
 */


class Test implements Callable<String>{
	
	private String name;
	
	public Test(String name){
		this.name=name;
	}
	
	@Override
	public String call() throws Exception {
		System.out.printf("%s: Starting at : %s\n",name,new Date());
		return "Hello world!";
	}
}

public class DelayTask{
	public static void main(String[] args){
		
		
		ScheduledThreadPoolExecutor executor=(ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(1);//单线程的线程池
		
		System.out.printf("Main : Staring at: %s\n",new Date());
		
		for(int i=0;i<5;i++){
			Test task=new Test("Task "+1);
			executor.schedule(task, i, TimeUnit.SECONDS);//传递：即将要执行的任务，任务执行前要等待的时间，等待时间的单位
		}
		
		executor.shutdown();//关闭线程池
		
		try{
			executor.awaitTermination(1, TimeUnit.DAYS);
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		
		System.out.printf("Main : Ends at: %s\n",new Date());
		
		
	}
}







