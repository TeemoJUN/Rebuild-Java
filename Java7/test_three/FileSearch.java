package test_3;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;





/*
 * Phaser实现三个并发任务， 每一步都会同步
 * 实现在三个文件夹下查找文件以log结尾的，修改时间为1天内的，并打印 
 */


public class FileSearch implements Runnable{
	private String initPath;
	private String end;
	private List<String> result;
	
	Phaser phaser;
	
	public FileSearch(String initPath,String end,Phaser phaser){
		this.initPath=initPath;
		this.end=end;
		this.phaser=phaser;
		result=new ArrayList<String>();
		
	}
	
	
	private void directoryProcess(File file){
		File[] list=file.listFiles();
		
		if(list!=null){
			for(int i=0;i<list.length;i++){
				if(list[i].isDirectory()){
					directoryProcess(list[i]);
				}else{
					fileProcess(list[i]);
				}
			}
		}
	}
	
	
	private void fileProcess(File file){
		if(file.getName().endsWith(end)){
			result.add(file.getAbsolutePath());
		}
	}

	private void filterResults(){
		
		List<String>  newResults=new ArrayList<>();
		
		long actulDate=new Date().getTime();
		
		for(int i=0;i<result.size();i++){
			
			File file=new File(result.get(i));
			
			long fileDate=file.lastModified();
			
			if(actulDate-fileDate<TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS)){
				newResults.add(result.get(i));
			}
		}
		
		result=newResults;
	}
	
	
	
	private boolean checkResults(){
		
		if(result.isEmpty()){
			System.out.printf("%s: Phase %d: 0 results.\n",Thread.currentThread().getName(),phaser.getPhase(),result.size());
			System.out.printf("%s:　Phase %d: End.\n",Thread.currentThread().getName(),phaser.getPhase());
			
			phaser.arriveAndDeregister();
			return false;
		}
		else{
			System.out.printf("%s: Phase %d: 0 results.\n",Thread.currentThread().getName(),phaser.getPhase(),result.size());
			
			phaser.arriveAndAwaitAdvance();
			
			return true;
		}
	}

	
	private void showInfo(){
		
		for(int i=0;i<result.size();i++){
			File file=new File(result.get(i));
			
			System.out.printf("%s: %s\n",Thread.currentThread().getName(),file.getAbsolutePath());
		}
		phaser.arriveAndAwaitAdvance();
	}
	
	@Override
	public void run() {
		
		phaser.arriveAndAwaitAdvance();
		
		System.out.printf("%s: Starting.\n",Thread.currentThread().getName());
		
		File file=new File(initPath);
		
		if(file.isDirectory()){
			directoryProcess(file);
		}
		
		if(!checkResults()){
			return ;
		}

		filterResults();
		
		if(!checkResults()){
			return ;
		}
		
		showInfo();
		
		phaser.arriveAndAwaitAdvance();
		
		System.out.printf("%s :Work completed.\n",Thread.currentThread().getName());
	}
	
	
	
	public static void main(String[] args){
		Phaser phaser=new Phaser(3);
		
		FileSearch system=new FileSearch("C:\\windows","log",phaser);
		FileSearch apps=new FileSearch("C:\\Program Files","log",phaser);
		FileSearch documents=new FileSearch("C:\\Documents And Settings","log",phaser);
		
		
		Thread systemThread=new Thread(system,"System");
		systemThread.start();
		Thread appsThread=new Thread(apps,"System");
		appsThread.start();
		Thread documentsThread=new Thread(documents,"System");
		documentsThread.start();
		
		try{
			systemThread.join();
			appsThread.join();
			documentsThread.join();
		}
		catch(InterruptedException e){
			e.printStackTrace();
		}
		
		System.out.println("Terminated: "+phaser.isTerminated());
		
	}
	
	
	
}
