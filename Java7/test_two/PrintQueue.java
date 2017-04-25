package test_1;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PrintQueue {
	
	private final Lock queueLock=new ReentrantLock();
	
	public void printJob(Object document){
		queueLock.lock();
		
		//Long duration=(Long)(Math.random()*1000);±¨´í
		
		try{
			Long duration=new Double(Math.random()*1000).longValue();
			
			System.out.println(Thread.currentThread().getName()+": PrintQueue: Printing a Job during  "+
			(duration/100)+" seconds");
			
			Thread.sleep(duration);
			
		}
		catch(InterruptedException e){
			e.printStackTrace();
		}
		finally{
			queueLock.unlock();
		}
	}
	
	
	public static void main(String[] args){
		PrintQueue p=new PrintQueue();
		
		Thread[] thread=new Thread[10];
		
		for(int i=0;i<10;i++){
			thread[i]=new Thread(new Job(p),"Thread "+i);
		}
		
		for(int i=0;i<10;i++){
			thread[i].start();
		}
		
	}
	
}


class Job implements Runnable{
	
	private PrintQueue printQueue;
	
	public Job(PrintQueue printQueue){
		this.printQueue=printQueue;
	}
	
	
	@Override
	public void run() {
		System.out.printf("%s: Going to print a doucment\n",Thread.currentThread().getName());
		
		printQueue.printJob(new Object());
		
		System.out.printf("%s: The document has been printed\n",Thread.currentThread().getName());
	}
	
}












