package test_1;


class SyncThread implements Runnable {
	   private static int count;

	   public SyncThread() {
	      count = 0;
	   }

	   public  void run() {
	      synchronized(this) {
	         for (int i = 0; i < 5; i++) {
	            try {
	               System.out.println(Thread.currentThread().getName() + ":" + (count++));
	               Thread.sleep(100);
	            } catch (InterruptedException e) {
	               e.printStackTrace();
	            }
	         }
	      }
	   }

	   public int getCount() {
	      return count;
	   }
	}

public class Test {
	public static void main(String[] args){
		SyncThread syncThread = new SyncThread();
		Thread thread1 = new Thread(syncThread, "SyncThread1");
		Thread thread2 = new Thread(syncThread, "SyncThread2");
		thread1.start();
		thread2.start();
		//这是thread1独占线程
		
		
		
		
//		Thread thread3=new Thread(new SyncThread(),"SyncThread3");
//		Thread thread4=new Thread(new SyncThread(),"SyncThread4");
//		thread3.start();
//		thread4.start();
		
		//这个因为count是static的所以是两个对象共享的
		
	}
}
