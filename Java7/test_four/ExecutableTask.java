package test_4;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
/**
 * 在执行其中控制任务的完成
 * @author lin
 *
 */
public class ExecutableTask implements Callable<String>{
	
	
	private String name;
	
	public String getName(){
		return name;
	}
	
	
	public ExecutableTask(String name){
		this.name=name;
	}
	
	/*
	 * 模拟等待时间
	 * @see java.util.concurrent.Callable#call()
	 */
	@Override
	public String call() throws Exception {
		
		try{
			
			long duration=new Double(Math.random()*10).longValue();
			System.out.printf("%s: Waiting %d seconds for results.\n",this.name,duration);
			TimeUnit.SECONDS.sleep(duration);
			
		}
		catch(InterruptedException e){
			e.printStackTrace();
		}
		
		return "Hello, world.I am "+name;
	}
	
	
	//调用main
	public static void main(String[] args){
		Test_two.main(args);
	}
}



class ResultTask extends FutureTask<String>{

	
	private String name;
	
	
	public ResultTask(Callable<String> callable) {
		super(callable);
		this.name=((ExecutableTask)callable).getName();
	}
	
	//看是否结束
	@Override
	protected void done(){
		if(isCancelled()){
			System.out.printf("%s: Has been canceled\n",name);
		}
		else{
			System.out.printf("%s: has finished\n",name);
		}
	}	
}





class Test_two{
	public static void main(String[] args){
		
		
		ExecutorService executor=(ExecutorService)Executors.newCachedThreadPool();//创建线程池
		
		ResultTask[] resultTasks=new ResultTask[5];
		
		
		//初始化ResultTask对象，在数组的每个位置创建ExecutorTask对象，穿给TesultTask,提交给线程池
		for(int i=0;i<5;i++){	
			ExecutableTask executableTask=new ExecutableTask("Task "+i);
			resultTasks[i]=new ResultTask(executableTask);
			executor.submit(resultTasks[i]);
		}
		
		try{
			TimeUnit.SECONDS.sleep(5);
		}
		catch(InterruptedException e){
			e.printStackTrace();
		}
		
		for(int i=0;i<resultTasks.length;i++){
			resultTasks[i].cancel(true);//取消任务
		}
		
		for(int i=0;i<resultTasks.length;i++){
			try{
				if(!resultTasks[i].isCancelled()){
					System.out.printf("%s\n",resultTasks[i].get());//输出还没有被取消的任务结果
				}
			}
			catch(InterruptedException e){
				e.printStackTrace();
			}
			catch(ExecutionException e){
				e.printStackTrace();
			}
		}
		
		executor.shutdown();
		
	}
}










