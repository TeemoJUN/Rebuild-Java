package test_4;

import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 
 * ��ִ��������ʱִ������
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
		
		
		ScheduledThreadPoolExecutor executor=(ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(1);//���̵߳��̳߳�
		
		System.out.printf("Main : Staring at: %s\n",new Date());
		
		for(int i=0;i<5;i++){
			Test task=new Test("Task "+1);
			executor.schedule(task, i, TimeUnit.SECONDS);//���ݣ�����Ҫִ�е���������ִ��ǰҪ�ȴ���ʱ�䣬�ȴ�ʱ��ĵ�λ
		}
		
		executor.shutdown();//�ر��̳߳�
		
		try{
			executor.awaitTermination(1, TimeUnit.DAYS);
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		
		System.out.printf("Main : Ends at: %s\n",new Date());
		
		
	}
}







