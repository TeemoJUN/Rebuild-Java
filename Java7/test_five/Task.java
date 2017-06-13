package test_5;

import java.util.List;
import java.util.concurrent.RecursiveAction;



/**
 * 
 * @author lin
 *
 */

public class Task extends RecursiveAction{
	
	private static final long serialVersionUID=1L;//必须的值
		
	private List<Product> products;
	
	private int first;
	private int last;
	private double increment;//增长比率
	
	public Task(List<Product> products,int first,int last,double increment){
		this.first=first;
		this.last=last;
		this.products=products;
		this.increment=increment;
	}
	
	/**
	 * 任务小于10直接更新，不然的话在在切割
	 */
	@Override
	protected void compute() {
		
		if(last-first<10){
			updatePrices();
		}
		else{
			int middle=(last+first)/2;
			
			System.out.printf("Task : Pending tasks: %s\n",getQueuedTaskCount());
			Task t1=new Task(products,first,middle+1,increment);
			Task t2=new Task(products,middle+1,last,increment);
			invokeAll(t1,t2);
		}
	}
	
	/**
	 * 将price更新为12
	 */
	private void updatePrices(){
		for(int i=first;i<last;i++){
			Product product=products.get(i);
			product.setPrice(product.getPrice()*(1+increment));//10*1.2
			
		}
	}
}
