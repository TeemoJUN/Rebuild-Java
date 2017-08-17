package two;

import one.Dequeue;

/*
 * 出列排序 
 * 
 * 
 * 用双向队列模拟的
 * 
 * 假设左侧为牌底，即只能进行pushLeft（）把牌放置牌底
 * 假设右侧为牌堆顶，即只能进行popRight()取出牌
 * */


public class MoveQueue {
	public static void main(String[] args) throws Exception{
		Dequeue<Integer> queue=new Dequeue<Integer>();
		
		//设置牌堆
		queue.pushLeft(1);
		queue.pushLeft(3);
		queue.pushLeft(5);
		queue.pushLeft(2);
		queue.pushLeft(4);
		queue.pushLeft(7);
		queue.pushLeft(6);
		queue.pushLeft(8);
		queue.pushLeft(10);
		queue.pushLeft(6);
		queue.pushLeft(8);
		queue.pushLeft(10);
		
		System.out.println("没有排好序的状态");
		queue.print();//10  8  6  10  8  6  7  4  2  5  3  1  
		int N=queue.size();
		
		for(int i=N;i>0;i--){
			int j=i-1;//这轮需要比较的次数j次（第一次为N-1（即i-1）次）
			int k=N-i;//这轮要放k张已经排好序的牌到牌底去（复原）
			int first=queue.popRight();//先取出一张
			while(j>0){
				//需要比较j次找到最大的排,不是最大的统统放进牌堆
				int second=queue.popRight();
				if(first>second){
					queue.pushLeft(second);
				}
				else{
					queue.pushLeft(first);
					first=second;
				}
				j--;
			}
			while(k>0){
				//复原原本就排好序的牌
				int p=queue.popRight();
				queue.pushLeft(p);
				k--;
			}
			queue.pushLeft(first);//将每轮比较中最大的排放到牌底
		}
		System.out.println("排好序的");
		queue.print();//1  2  3  4  5  6  6  7  8  8  10  10  
	}
	
}
