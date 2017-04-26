package test_3;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;


/*
 * 模拟开会事件
 * 
 * 当需要10个人到齐时才开始开会，CountDownLatch 来拦截时次线程请求，当达到是自身线程开始跑
 */


public class Videoconference implements Runnable{
	
	private final CountDownLatch controller;
	
	
	public Videoconference(int number){
		this.controller=new CountDownLatch(number);
	}
	
	
	
	
	public void arrive(String name){
		System.out.printf("%s has arrived.\n",name);
		
		controller.countDown();//当减少到0时会唤醒阻塞的线程
		
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
	
	
	//测试
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
//会议参与者
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












