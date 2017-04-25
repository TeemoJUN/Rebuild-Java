package test_1;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class EventStorage {
	private int maxSize;
	
	private List<Date> storage;
	
	public EventStorage(){
		maxSize=10;
		storage=new LinkedList<>();
	}
	
	/*
	 * 产生
	 * 当链表最大等于maxSize时挂起线程
	 * 结束后再通知其它线程启动
	 */
	public synchronized void set(){
		while(storage.size()==maxSize){
			try{
				wait();
			}
			catch(InterruptedException e){
				e.printStackTrace();
			}
		}
		storage.add(new Date());
		
		System.out.printf("Set: %d\n",storage.size());
		notifyAll();
	}
	
	/*
	 * 移除
	 * 当storage为空时让线程挂起
	 * 每次结束后再让其它线程启动
	 */
	
	public synchronized void get(){
		while(storage.size()==0){
			try{
				wait();
			}
			catch(InterruptedException e){
				e.printStackTrace();
			}
		}
		
		System.out.printf("Get: %d: %s\n", storage.size(),((LinkedList<?>) storage).poll());
		
		notifyAll();
	}	
	
	
	public static void main(String[] args){
		EventStorage storage=new EventStorage();
		
		Producer p=new Producer(storage);
		
		Consumer c=new Consumer(storage);
		
		Thread pThread=new Thread(p);
		Thread cThread=new Thread(c);
		
		pThread.start();
		cThread.start();
	}
}


/*
 * 产生
 */


class Producer implements Runnable{
	
	private EventStorage storage;
	
	public Producer(EventStorage storage){
		this.storage=storage;
	}
	
	
	@Override
	public void run() {
		for(int i=0;i<100;i++){
			storage.set();
		}
	}
	
}

/*
 * 移除
 */

class Consumer implements Runnable{

	private EventStorage storage;
	
	public Consumer(EventStorage storage){
		this.storage=storage;
	}
	
	@Override
	public void run() {
		for(int i=0;i<100;i++){
			storage.get();
		}
	}
	
}



