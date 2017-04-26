package test_3;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/*
 * 采用信号量机制 Semaphore
 * 模拟的是有三台打印机工作
 */


public class Print {
	private boolean[] freePrinters;
	
	private final Semaphore semaphore;
	private Lock lockPrinters;
	
	public Print(){
		
		this.semaphore=new Semaphore(3);//信号量为三
		this.lockPrinters=new ReentrantLock();//锁
		this.freePrinters=new boolean[3];//三台打印机
		
		for(int i=0;i<3;i++){
			freePrinters[i]=true;//设置目前均为可以工作状态
		}
	}
	
	public void printJob(Object document){
		try{
			semaphore.acquire();
			
			int assignedPrinter=getPrinter();//获取打印机编号
			long duration=new Double(Math.random()*10).longValue();//打印需要的时间
			
			System.out.printf("%s:　Print : Printing a Job in Printer %d during %d seconds\n",
					Thread.currentThread().getName(),assignedPrinter,duration);
			
			TimeUnit.SECONDS.sleep(duration);
			
			freePrinters[assignedPrinter]=true;
		}
		catch(InterruptedException e){
			e.printStackTrace();
		}finally{
			semaphore.release();
		}
	}
	
	private int getPrinter(){
		int ret=-1;//ret是将采用那个打印机工作标号
		
		try{
			lockPrinters.lock();
			
			for(int i=0;i<freePrinters.length;i++){
				if(freePrinters[i]){
					ret=i;
					freePrinters[i]=false;
					break;
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			lockPrinters.unlock();
		}
		return ret;
	}
	
	public static void main(String[] args){
		Print p=new Print();
		
		Thread[] t=new Thread[10];
		
		for(int i=0;i<10;i++){
			t[i]=new Thread(new Job(p),"Thead+ "+i);
		}
		for(int i=0;i<10;i++){
			t[i].start();
		}
	}
	
}


 class Job implements Runnable{
	 
	 private Print p;
	 
	 public Job(Print p){
		 this.p=p;
	 }
	
	 
	 
	@Override
	public void run() {
		p.printJob(new Object());
	}
	 
 }















