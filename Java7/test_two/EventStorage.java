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
	 * ����
	 * ������������maxSizeʱ�����߳�
	 * ��������֪ͨ�����߳�����
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
	 * �Ƴ�
	 * ��storageΪ��ʱ���̹߳���
	 * ÿ�ν��������������߳�����
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
 * ����
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
 * �Ƴ�
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



