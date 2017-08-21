package test;

import java.util.Stack;

//������ 22��ջ��ѹ�롢��������

/*
 * ��Ŀ�����������������У���һ�����б�ʾջ��ѹ��˳�����жϵڶ��������� ��Ϊ��ջ�ĵ������С�
 * ����ѹ��ջ���������־�����ȡ�����ѹջ����Ϊ 1��2�� 3��4��5.���� 4��5��3��2��1 ��ѹջ���ж�Ӧ��һ���������У�
 * �� 4��3��5��1�� 2 ȴ����
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
