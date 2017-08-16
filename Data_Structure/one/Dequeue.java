package one;

import java.util.Iterator;

/*
 * 
 * 双向队列，链表版
 * 
 * */

public class Dequeue<Item> implements Iterable<Item> {

	
	private Node first;//最左边的头节点
	private Node last;//最右边的尾节点
	private int N;//记录当前有多少的节点被存贮
	
	public Dequeue(){
		
	}
	
	private class Node{
		private Node pre;
		private Node next;
		private Item item;
	}
	
	//返回存贮节点个数
	public int size(){
		return N;
	}
	//是否为空
	private boolean isEmpty(){
		return N==0;
	}
	//左进
	public void pushLeft(Item item){
		if(isEmpty()){
			first=new Node();
			first.item=item;
			last=first;
		}
		else{
			Node oldFirst=first;
			first=new Node();
			first.item=item;
			first.next=oldFirst;
			oldFirst.pre=first;
		}
		N++;
	}
	//右进
	public void pushRight(Item item){
		if(isEmpty()){
			last=new Node();
			last.item=item;
			first=last;
		}else{
			Node oldLast=last;
			last=new Node();
			last.item=item;
			last.pre=oldLast;
			oldLast.next=last;
		}
		N++;
	}
	//左出
	public Item popLeft() throws Exception{
		//当为空时报错
		if(isEmpty()){
			throw new Exception("没有了");
		}
		Item item=first.item;
		first=first.next;
		if(N==1){
			first=last=null;//当只有一个节点时也准备出的时候，last和first同时置null
		}
		else{
			first.pre=null;//丢弃前一个节点
		}
		N--;
		return item;
	}
	//右出，同理
	public Item popRight() throws Exception{
		if(isEmpty()){
			throw new Exception("没有啦");
		}
		Item item=last.item;
		last=last.pre;
		if(N==1){
			first=last=null;
		}
		else{
			last.next=null;
		}
		N--;
		return item;
	}
	
	public void print(){
		Node current=first;
		while(current!=null){
			System.out.print(current.item+"  ");
			current=current.next;
		}
		System.out.println();
	}

	
	//正向迭代器
	@Override
	public Iterator<Item> iterator() {
		return new Iterator<Item>(){
			Node current=first;
			@Override
			public boolean hasNext() {
				return current!=null;
			}

			@Override
			public Item next() {
				Item item=current.item;
				current=current.next;
				return item;
			}
		};
	}
	
	//反向迭代器
	public Iterable<Item> reverseIterator(){
		return new Iterable<Item>(){
			@Override
			public Iterator<Item> iterator() {
				return new Iterator<Item>(){
					Node current=last;
					@Override
					public boolean hasNext() {
						return current!=null;
					}

					@Override
					public Item next() {
						Item item=current.item;
						current=current.pre;
						return item;
					}
				};
			}
			
		};
		
	}
	
	
	
	
	public static void main(String[] args) throws Exception{
		Dequeue<String> q=new Dequeue<String>();
		
		q.pushLeft("a");
		q.pushLeft("b");
		q.pushLeft("c");
		q.pushRight("c");
		q.pushRight("b");
		q.pushRight("a");
		System.out.println("左出");
		q.print();//c  b  a  c  b  a  
		
		q.popLeft();
		q.print();//b  a  c  b  a 
		q.popLeft();
		q.print();//a  c  b  a  
		q.popLeft();
		q.print();//c  b  a  
		q.popLeft();
		q.print();//b  a 
		q.popLeft();
		q.print();//a
		q.popLeft();
		q.print();//空
		//在来一遍
		
		q.pushLeft("a");
		q.pushLeft("b");
		q.pushLeft("c");
		q.pushRight("c");
		q.pushRight("b");
		q.pushRight("a");
		System.out.println("右出");
		q.print();//c  b  a  c  b  a
		
		q.popRight();
		q.print();//c  b  a  c  b 
		q.popRight();
		q.print();//c  b  a  c
		q.popRight();
		q.print();//c  b  a 
		q.popRight();
		q.print();//c  b
		q.popRight();
		q.print();//c
		q.popRight();
		q.print();
		
		
		System.out.println("---------------");
		q.pushLeft("a");
		q.pushLeft("b");
		q.pushLeft("c");
		q.pushLeft("d");
		q.pushLeft("e");
		q.pushLeft("f");
		
		
		System.out.println("正向排序");
		
		for(String a:q){
			System.out.print(a+"  ");
		}
		System.out.println();
		System.out.println("反向排序");
		for(String a:q.reverseIterator()){
			System.out.print(a+"  ");
		}
	}
}

