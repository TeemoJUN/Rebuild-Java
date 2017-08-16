package one;

import java.util.Iterator;

public class Queue<Item> implements Iterable<Item> {

	
	private Node first;
	private Node last;
	private int N;
	
	private class Node{
		Item item;
		Node next;
	}
	
	public int count(){
		return N;
	}
	
	public boolean isEmpty(){
		return N==0;
	}
	public void enqueue(Item item){
		Node oldLast=last;
		last=new Node();
		last.item=item;
		last.next=null;
		
		if(isEmpty()){
			first=last;
		}
		else{
			oldLast.next=last;
		}
		N++;
	}
	
	public Item dequeue() throws Exception{
		if(N==0){
			throw new Exception("ЮЊПе");
		}
		Item item=first.item;
		first=first.next;
		if(isEmpty()){
			last=null;
		}
		N--;
		return item;
	}
	
	@Override
	public Iterator<Item> iterator() {
		return new Iterator<Item>(){
			
			private Node current=first;
			@Override
			public boolean hasNext() {
				return first!=null;
			}

			@Override
			public Item next() {
				Item item=current.item;
				current=current.next;
				return item;
			}			
		};
	}
	
}

