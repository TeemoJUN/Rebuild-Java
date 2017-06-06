package test_4;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/*
 * ���ж�����񲢴������н��
 */

public class Result {
	private String name;
	private int value;
	
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name=name;
	}
	
	public void setValue(int value){
		this.value=value;
	}
	public int getValue(){
		return value;
	}
	
	public static void main(String[] args){
		Main.main(args);
	}
}


class Task2 implements Callable<Result>{

	private String name;
	
	public Task2(String name){
		this.name=name;
	}
	
	
	
	/*
	 * @see java.util.concurrent.Callable#call()
	 */
	@Override
	public Result call() throws Exception {
		
		System.out.printf("%s: Staring\n",this.name);
		
		try{
			long duration=new Double(Math.random()*10).longValue();
			System.out.printf("%s: Wating %d seconds for results.\n",this.name,duration);
			
			TimeUnit.SECONDS.sleep(duration);
		}
		catch(InterruptedException e){
			e.printStackTrace();
		}
		int value=0;
		for(int i=0;i<5;i++){
			value+=(int)(Math.random()*100);
		}
		
		Result result=new Result();
		result.setName(this.name);
		result.setValue(value);
		
		return result;
	}
	
}





class Main{
	public static void main(String[] args){
		ExecutorService executor=(ExecutorService) Executors.newCachedThreadPool();//�����̳߳�
		
		List<Task2> taskList=new ArrayList<>();//�����������
		
		for(int i=0;i<3;i++){
			Integer j=i;
			Task2 task=new Task2(j.toString());
			taskList.add(task);
		}
		
		List<Future<Result>> resultList=null;
		
		try{
			resultList=executor.invokeAll(taskList);//�ύ���̳߳أ����ȴ�����������ɣ����������resultList
		}
		catch(InterruptedException e){
			e.printStackTrace();
		}
		
		
		executor.shutdown();//�����̳߳�
		
		System.out.println("Main: Printing the results");
		
		//���resultList�е��̷߳��ؽ��
		for(int i=0;i<resultList.size();i++){
			Future<Result> future=resultList.get(i);
			
			try{
				Result result=future.get();
				System.out.println(result.getName()+"  : " +result.getValue());
			}
			catch(InterruptedException e){
				e.printStackTrace();
			}
			catch(ExecutionException e){
				e.printStackTrace();
			}
		}
		
	}
}

























