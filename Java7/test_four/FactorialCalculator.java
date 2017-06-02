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
 * ���ʵ������ķ��ؽ��������ִ��������������
 * ʵ��Callable�ӿڷ���Integer
 * ����i��
 * Future������ӿ�������һЩ��������ȡCallable��������Ľ��
 */
public class FactorialCalculator implements Callable<Integer>{

	
	private Integer number;
	
	public FactorialCalculator(Integer number){
		this.number=number;
	}
	
	
	
	//ʵ��call����
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
		ThreadPoolExecutor executor=(ThreadPoolExecutor) Executors.newFixedThreadPool(2);//�̳߳ع̶����߳�
		
		List<Future<Integer>> resultList=new ArrayList<>();
		
		Random ran=new Random();
		
		
		//����ʮ�����񣬲������ύ���̳߳أ�����resultList��������Ľ��
		for(int i=0;i<10;i++){
			Integer number=ran.nextInt(10);//�������0-9����
			FactorialCalculator calculator=new FactorialCalculator(number);
			
			Future<Integer> result=executor.submit(calculator);
			resultList.add(result);
		}
		
		
		//��excutor��ɵ��߳���С��resultListʱһֱִ��
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
		while(executor.getCompletedTaskCount()<resultList.size());	//��excutor��ɵ��߳���С��resultListʱһֱִ��
		
		System.out.printf("Main: Results\n");
		
		
		//ѭ����ӡ���
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
		
		executor.shutdown();//�����̳߳�
	}
	
	
}






