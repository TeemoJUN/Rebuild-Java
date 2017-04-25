package test_2;

import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class FileMock {
	private String[] content;
	private int index;
	
	
	//构造一个参数长度为length，size个content[size];
	public FileMock(int size,int length){
		content=new String[size];
		
		for(int i=0;i<size;i++){
			StringBuilder buffer=new StringBuilder(length);
			
			for(int j=0;j<length;j++){
				int indice=(int) Math.random()*255;
				buffer.append((char)indice);
			}
			content[i]=buffer.toString();
		}
		index=0;
	}
	
	//看行数是否够
	public boolean hasMoreLines(){
		return index<content.length;
	}
	//返回当前行数
	public String getLine(){
		if(this.hasMoreLines()){
			System.out.println("Mock: "+(content.length-index));
			return content[index++];
		}
		return null;
	}
	
	
	
	public static void main(String[] args){
		
		FileMock mock=new FileMock(100,10);
		
		Buffer buffer=new Buffer(20);
		
		Producer producer=new Producer(mock,buffer);
		
		Thread threadProducer=new Thread(producer,"Producer");
		
		Consumer[] consumers=new Consumer[3];
		
		Thread[] threadConsumers=new Thread[3];
		
		for(int i=0;i<3;i++){
			consumers[i]=new Consumer(buffer);
			threadConsumers[i]=new Thread(consumers[i],"Consumer  "+i);
		}
		threadProducer.start();
		for(int i=0;i<3;i++){
			threadConsumers[i].start();
		}
		
	}
	
	
	
	
	
	
	
}

class Buffer{
	
	private LinkedList<String> buffer;
	
	private int maxSize;
	private ReentrantLock lock;
	private Condition lines;
	private Condition space;
	private boolean pendingLines;//缓冲区是否还有数据
	
	
	public Buffer(int maxSize){
		this.maxSize=maxSize;
		this.buffer=new LinkedList<>();
		this.lock=new ReentrantLock();
		this.lines=lock.newCondition();
		this.space=lock.newCondition();
		this.pendingLines=true;
	}
	
	public void insert(String line){
		lock.lock();
		
		try{
			while(buffer.size()==maxSize){
				space.await();
			}
			buffer.offer(line);
			System.out.printf("%s: Inserted Line: %d\n",Thread.currentThread().getName(),buffer.size());
			
			lines.signalAll();
		}
		catch(InterruptedException e){
			e.printStackTrace();
		}
		finally{
			lock.unlock();
		}
	}
	
	public String get(){
		String line=null;
		lock.lock();
		
		try{
			while(buffer.size()==0){
				lines.await();	
			}
			if(hasPendingLines()){
				line=buffer.poll();
				System.out.printf("%s: Line Readed: %d\n", Thread.currentThread().getName(),buffer.size());
				space.signalAll();
			}
			
		}
		catch(InterruptedException e){
			e.printStackTrace();
		}
		finally{
			lock.unlock();
		}
		return line;
	}
	
	
	
	public void setPendingLines(boolean pendingLines){
		this.pendingLines=pendingLines;
	}
	
	
	public boolean hasPendingLines(){
		return pendingLines||buffer.size()>0;
	}
}

//将mock里的content[]全部加载到buffer里
class Producer implements Runnable{
	
	private FileMock mock;
	private Buffer buffer;
	
	public Producer(FileMock mock,Buffer buffer){
		this.mock=mock;
		this.buffer=buffer;
	}
	
	@Override
	public void run() {
		buffer.setPendingLines(true);
		while(mock.hasMoreLines()){
			String line=mock.getLine();
			buffer.insert(line);
		}
		buffer.setPendingLines(false);
	}
	
}

class Consumer implements Runnable{
	
	private Buffer buffer;
	public Consumer(Buffer buffer){
		this.buffer=buffer;
	}
	
	@Override
	public void run() {
		while(buffer.hasPendingLines()){
			String line=buffer.get();
			processLine(line);
		}
		
	}
	
	
	private void processLine(String line){
		try{
			Random random=new Random();
			Thread.sleep(random.nextInt(100));
		}
		catch(InterruptedException e){
			e.printStackTrace();
		}
	}
}























