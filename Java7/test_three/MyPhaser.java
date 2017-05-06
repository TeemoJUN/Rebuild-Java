package test_3;

import java.util.Date;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;


/*
 * 重写onAdvance（）方法
 * 在所有arriveAndAwitAdvance()方法里休眠的线程被唤醒之前，onAdvance（）将会被自动调用 
 * 返回值为false，则继续执行其它阶段。
 * 返回值true时，phaser仍然唤醒等待的线程，但是状态已经改变为终止状态，所哟继续调用phaser的方法立即返回，isTerminated();也将返回true
 */


/*
 * 模拟的是学生做题
 */


public class MyPhaser extends Phaser{
	
	@Override
	protected boolean onAdvance(int phase,int registeredParties){
		switch(phase){
		case 0:
			return studentsArrived();
		case 1:
			return finishFirstExercise();
		case 2:
			return finishSecondExercise();
		case 3:
			return finishExam();
		default:
			return true;
		}
	}
	
	
	//学生是否到啦
	private boolean studentsArrived(){
		System.out.printf("Phaser: The exam are going to start.The students are ready.\n");
		
		System.out.printf("Phaser: We have %d students.\n",getRegisteredParties());
		
		return false;
	}
	
	//第一题是否做完
	private boolean finishFirstExercise(){
		System.out.printf("Phaser: All the students have finished the first exercise.\n");
		System.out.printf("Phaser: It's time for the second one.\n");
		return false;
	}
	
	
	//第二题是否做完
	private boolean finishSecondExercise(){
		System.out.printf("Phaser: All the students\n");
		
		System.out.printf("Phaser: It's time for the third one.\n");
		
		return false;
	}
	
	//第三题，也是最后一题
	private boolean finishExam(){
		System.out.printf("Phaser: All the students have finished the exam.\n");
		System.out.printf("Phaser: Thank you for your time.\n");
		
		return true;
	}
	
	public static void main(String[] args){
		MyPhaser phaser=new MyPhaser();
		
		Student[] students=new Student[5];
		
		for(int i=0;i<students.length;i++){
			students[i]=new Student(phaser);
			phaser.register();
		}
		
		Thread[] threads=new Thread[students.length];
		
		for(int i=0;i<students.length;i++){
			threads[i]=new Thread(students[i],"Student "+i);
			threads[i].start();
		}
		
		
		
		for(int i=0;i<threads.length;i++){
			try{
				threads[i].join();
			}
			catch(InterruptedException e){
				e.printStackTrace();
			}
		}
		
		
		System.out.printf("Main: The phaser has finished: %s.\n",phaser.isTerminated());
	}
	
	
}


class Student implements Runnable{

	private Phaser phaser;
	
	public Student(Phaser phaser){
		this.phaser=phaser;
	}
	
	@Override
	public void run() {
		System.out.printf("%s: Has arrived to do the exam.%s\n",Thread.currentThread().getName(),new Date());
		
		//等待同步	
		phaser.arriveAndAwaitAdvance();
	
		//开始做第一题
		System.out.printf("%s: Is going to do the first exercise.%s\n",Thread.currentThread().getName(),new Date());
		doExercise1();
		System.out.printf("%s: Has done the first exercise.%s\n",Thread.currentThread().getName(),new Date());
		
		phaser.arriveAndAwaitAdvance();
		//第二题
		System.out.printf("%s: Is going to do the second exercise.%s\n",Thread.currentThread().getName(),new Date());
		doExercise2();
		System.out.printf("%s: Has done the second exercise.%s\n",Thread.currentThread().getName(),new Date());
		
		phaser.arriveAndAwaitAdvance();
		//第三题
		System.out.printf("%s: Is going to do the third exercise.%s\n",Thread.currentThread().getName(),new Date());
		doExercise3();
		System.out.printf("%s: Has done the third exercise.%s\n",Thread.currentThread().getName(),new Date());
		
		phaser.arriveAndAwaitAdvance();
	}
	
	//三道题随机需要解答时间
	
	private void doExercise1(){
		try{
			long duration=new Double(Math.random()*10).longValue();
			TimeUnit.SECONDS.sleep(duration);
			
		}
		catch(InterruptedException e){
			e.printStackTrace();
		}
	}
	
	private void doExercise2(){
		try{
			long duration=new Double(Math.random()*10).longValue();
			TimeUnit.SECONDS.sleep(duration);
			
		}
		catch(InterruptedException e){
			e.printStackTrace();
		}
	}
	
	private void doExercise3(){
		try{
			long duration=new Double(Math.random()*10).longValue();
			TimeUnit.SECONDS.sleep(duration);
			
		}
		catch(InterruptedException e){
			e.printStackTrace();
		}
	}
	
}