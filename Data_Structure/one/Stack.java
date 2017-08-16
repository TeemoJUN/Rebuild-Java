package one;

import java.util.Iterator;


public class Stack<Item> implements Iterable<Item>{
	private Node first;
	private int N;
	
	private class Node{
		Item item;
		Node next;
	}
	//�Ƿ�Ϊ��
	public boolean isEmpty(){
		return N==0;
	}
	//ջ��Ԫ�ظ���
	public int size(){
		return N;
	}
	
	//��ջ
	public void push(Item item){
		Node oldFirst=first;
		first=new Node();
		first.item=item;
		first.next=oldFirst;
		N++;
	}
	
	//��ջ
	public Item pop()throws Exception{
		if(isEmpty()){
			throw new RuntimeException("ջΪ��");
		}
		Item item=first.item;
		first=first.next;
		N--;
		return item;
	}

	@Override
	public Iterator<Item> iterator() {
		return new stackIterator<Item>();
	}
	
	@SuppressWarnings("hiding")
	private class stackIterator<Item> implements Iterator<Item>{
		private Node current=first;
		@Override
		public boolean hasNext() {
			return current!=null;
		}
		@Override
		public Item next() {
			@SuppressWarnings("unchecked")
			Item item=(Item) current.item;
			current=current.next;
			return item;
		}
	}
}
