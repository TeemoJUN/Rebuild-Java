package test_3;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;


/*
 * ģ�⿪���¼�
 * 
 * ����Ҫ10���˵���ʱ�ſ�ʼ���ᣬCountDownLatch ������ʱ���߳����󣬵��ﵽ�������߳̿�ʼ��
 */


public class Videoconference implements Runnable{
	
	private final CountDownLatch controller;
	
	
	public Videoconference(int number){
		this.controller=new CountDownLatch(number);
	}
	
	
	
	
	public void arrive(String name){
		System.out.printf("%s has arrived.\n",name);
		
		controller.countDown();//�����ٵ�0ʱ�ỽ���������߳�
		
		System.out.printf("Videoconference:Waiting for %d participants.\n",controller.getCount());
	}
	
	
	@Override
	public void run() {
		System.out.printf("Videoconference: Initialization: %d participants.\n",controller.getCount());
		
		try{
			controller.await();
			
			System.out.println("Videoconference: All the participants have come");
			System.out.println("Videoconference: Let's Start....");
			
		}
		catch(InterruptedException e){
			e.printStackTrace();
		}
	}
	
	
	//����
	public static void main(String[] args){
		Videoconference c=new Videoconference(10);
		
		Thread threadConference=new Thread(c);
		threadConference.start();
		
		for(int i=0;i<10;i++){
			Participant p=new Participant(c,"Participant  "+i);
			Thread t=new Thread(p);
			t.start();
		}
	}
	
}
//���������
class Participant implements Runnable{
	
	private Videoconference conference;
	
	private String name;
	
	public Participant(Videoconference conference,String name){
		this.conference=conference;
		this.name=name;
	}
	
	@Override
	public void run() {
		long duration=new Double(Math.random()*10).longValue();
		
		try{
			TimeUnit.SECONDS.sleep(duration);
		}
		catch(InterruptedException e){
			e.printStackTrace();
		}
		
		conference.arrive(name);
	}
	
}












