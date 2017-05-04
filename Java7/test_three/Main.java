package test_3;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;





/*
 * 在集合点的同步
 * 
 * CyclicBrarrier
 * 当线程到达指定点后，它将调用await方法进行等待，CyclicBarrier类将阻塞这个线程直到其他线程到达，当最后一个线程到达await（）是将唤醒
 * 所有等待的线程，然后这些线程将继续执行
 * 
 */





class MatrixMock{
	
	private int data[][];
	
	public MatrixMock(int size,int length,int number){
		int counter=0;
		data=new int[size][length];
		Random random=new Random();
		
		for(int i=0;i<size;i++){
			for(int j=0;j<length;j++){
				data[i][j]=random.nextInt(10);
				if(data[i][j]==number){
					counter++;
				}
			}
		}
		System.out.printf("Mock: There are %d ocurrences of number %d in generated data.\n",counter,number);
		
	}
	
	public int[] getRow(int row){
		if((row>=0)&&(row<data.length)){
			return data[row];
		}
		return null;
	}
}


class Result{
	
	private int data[];
	
	public Result(int size){
		data=new int[size];
	}
	
	public void setData(int position,int value){
		data[position]=value;
	}
	
	public int[] getData(){
		return data;
	}
	
}

class Searcher implements Runnable{

	private int firstRow;
	private int lastRow;
	
	private MatrixMock mock;
	private Result results;
	private int number;
	
	private final CyclicBarrier barrier;
	
	
	public Searcher(int firstRow,int lastRow,MatrixMock mock,Result results,int number,CyclicBarrier barrier){
		this.firstRow=firstRow;
		this.lastRow=lastRow;
		this.mock=mock;
		this.barrier=barrier;
		this.results=results;
		this.number=number;
	}
	
	@Override
	public void run() {
		int counter;
		
		System.out.printf("%s : Processing lines from %d to %d.\n",Thread.currentThread().getName(),firstRow,lastRow);
		
		for(int i=firstRow;i<lastRow;i++){
			int row[]=mock.getRow(i);
			
			counter=0;
			
			for(int j=0;j<row.length;j++){
				if(row[j]==number){
					counter++;
				}
			}
			
			results.setData(i, counter);
		}
		
		System.out.printf("%s:　Line processed.\n",Thread.currentThread().getName());
		
		try{
			barrier.await();
		}
		catch(InterruptedException e){
			e.printStackTrace();
		}
		catch(BrokenBarrierException e){
			e.printStackTrace();
		}
	}
	
}



class Grouper implements Runnable{
	
	Result results;
	
	public Grouper(Result results){
		this.results=results;
	}
	
	@Override
	public void run() {
		int finalResult=0;
		System.out.printf("Grouper: Processing results...\n");
		
		int data[]=results.getData();
		for(int number:data){
			finalResult+=number;
		}
		
		System.out.printf("Grouper: Total result :%d.\n",finalResult);
	}
	
}



public class Main {
	public static void main(String[] args){
		final int ROWS=10000;
		final int NUMBERS=1000;
		final int SEARCH=5;
		final int PARTICIPANTS=5;
		final int LINE_RARTICTPANT=2000;
		
		
		
		MatrixMock mock=new MatrixMock(ROWS,NUMBERS,SEARCH);
		
		Result results=new Result(ROWS);
		
		Grouper grouper=new Grouper(results);
		
		CyclicBarrier barrier=new CyclicBarrier(PARTICIPANTS,grouper);
		
		
		Searcher[] searchers=new Searcher[PARTICIPANTS];
		
		for(int i=0;i<PARTICIPANTS;i++){
			searchers[i]=new Searcher(i*LINE_RARTICTPANT,(i*LINE_RARTICTPANT)+LINE_RARTICTPANT,mock,results,5,barrier);
			
			Thread t=new Thread(searchers[i]);
			t.start();
		}
		System.out.printf("Main:the main thread has finished.\n");
	}
}




















