package test_4;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
/**
 * ��ִ�����п�����������
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
	 * ģ��ȴ�ʱ��
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
	
	
	//����main
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
	
	//���Ƿ����
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
		
		
		ExecutorService executor=(ExecutorService)Executors.newCachedThreadPool();//�����̳߳�
		
		ResultTask[] resultTasks=new ResultTask[5];
		
		
		//��ʼ��ResultTask�����������ÿ��λ�ô���ExecutorTask���󣬴���TesultTask,�ύ���̳߳�
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
			resultTasks[i].cancel(true);//ȡ������
		}
		
		for(int i=0;i<resultTasks.length;i++){
			try{
				if(!resultTasks[i].isCancelled()){
					System.out.printf("%s\n",resultTasks[i].get());//�����û�б�ȡ����������
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










