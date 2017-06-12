package test_5;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

public class Main {
	public static void main(String[] args){
		
		ProductListGenerator generator=new ProductListGenerator();
		
		List<Product> products=generator.generate(10000);//产生10000个
		
		Task task=new Task(products,0,products.size(),0.20);//创建任务
		
		ForkJoinPool pool=new ForkJoinPool();//线程池
		
		pool.execute(task);//放入任务
		
		do{
			System.out.printf("Main : Thread Count : %d\n",pool.getActiveThreadCount());
			
			System.out.printf("Main : Thread Steal : %d\n",pool.getParallelism());
			
			try{
				TimeUnit.MILLISECONDS.sleep(5);
				
			}catch(InterruptedException e){
				e.printStackTrace();
			}
			
		}while(!task.isDone());//看是否结束
		
		pool.shutdown();//关闭线程池
		
		if(task.isCompletedNormally()){
			System.out.printf("Main： The process has completed normally.\n");
		}
		
		//当不是12时另外输出
		for(int i=0;i<products.size();i++){
			Product product=products.get(i);
			
			if(product.getPrice()!=12){
				System.out.printf("Product %s: %f\n",product.getName(),product.getPrice());
			}
		}
		
		System.out.println("Main: End of the program");
		
	}
}
