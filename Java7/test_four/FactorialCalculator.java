package test_4;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;



/*
 * 如何实现任务的返回结果，并在执行器中运行任务
 * 实现Callable接口返回Integer
 * 计算i！
 * Future：这个接口声明了一些方法来获取Callable对象产生的结果
 */
public class FactorialCalculator implements Callable<Integer>{

	
	private Integer number;
	
	public FactorialCalculator(Integer number){
		this.number=number;
	}
	
	
	
	//实现call方法
	@Override
	public Integer call() throws Exception {
		
		int result=1;
		
		if(number==0||number==1){
			result=1;
		}
		else{
			for(int i=2;i<number;i++){
				result*=i;
				TimeUnit.MILLISECONDS.sleep(20);
			}
		}
		
		
		return result;
	}
	
	
	
	public static void main(String[] args){
		ThreadPoolExecutor executor=(ThreadPoolExecutor) Executors.newFixedThreadPool(2);//线程池固定两线程
		
		List<Future<Integer>> resultList=new ArrayList<>();
		
		Random ran=new Random();
		
		
		//创建十个任务，并将其提交给线程池，并用resultList来存放它的结果
		for(int i=0;i<10;i++){
			Integer number=ran.nextInt(10);//随机传入0-9的数
			FactorialCalculator calculator=new FactorialCalculator(number);
			
			Future<Integer> result=executor.submit(calculator);
			resultList.add(result);
		}
		
		
		//当excutor完成的线程数小于resultList时一直执行
		do{
			System.out.printf("Main: Number of Completed Task: %d",executor.getCompletedTaskCount());
			for(int i=0;i<resultList.size();i++){
				Future<Integer> result=resultList.get(i);
				System.out.printf("Main: Task %d : %s\n",i,result.isDone());
			}
			try{
				TimeUnit.MILLISECONDS.sleep(50);
				
			}
			catch(InterruptedException e){
				e.printStackTrace();
			}
		}
		while(executor.getCompletedTaskCount()<resultList.size());	//当excutor完成的线程数小于resultList时一直执行
		
		System.out.printf("Main: Results\n");
		
		
		//循环打印结果
		for(int i=0;i<resultList.size();i++){
			Future<Integer> result=resultList.get(i);
			Integer number=null;
			try{
				number=result.get();
			}catch(InterruptedException e){
				
				e.printStackTrace();
			}
			catch(ExecutionException e){
				e.printStackTrace();
			}
			System.out.printf("Main : Task %d : %d\n",i,number);
		}
		
		executor.shutdown();//结束线程池
	}
	
	
}






