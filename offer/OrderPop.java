package test;

import java.util.Stack;

//面试题 22：栈的压入、弹出序列

/*
 * 题目：输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是 否为该栈的弹出序列。
 * 假设压入栈的所有数字均不相等。例如压栈序列为 1、2、 3、4、5.序列 4、5、3、2、1 是压栈序列对应的一个弹出序列，
 * 但 4、3、5、1、 2 却不是
 * */
public class OrderPop {
	public static void main(String[] args){
		int[] push={1,2,3,4,5};
		int[] pop={4,5,3,2,1};
		boolean result=isPopOrder(push,pop);
		
		System.out.println(result);
	}
	public static boolean isPopOrder(int[] push,int[] pop){
		if(push.length!=pop.length){
			return false;
		}
		
		Stack<Integer> s=new Stack<Integer>();
		int index=0;
		for(int i=0;i<push.length;i++){
			s.push(push[i]);
			while(!s.isEmpty()&&s.peek().equals(pop[index])){
				s.pop();
				index++;
			}
			
		}
		
		if(s.isEmpty()){
			return true;
		}
		return false;
	}
}
