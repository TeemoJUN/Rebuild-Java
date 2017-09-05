package one;

import java.util.Iterator;

public class Bag<Item> implements Iterable<Item> {

	private Node first;
	private class Node{
		Item item;
		Node next;
	}
	
	public void add(Item item){
		Node oldFirst=first;
		first=new Node();
		first.item=item;
		first.next=oldFirst;
	}
	
	
	
	@Override
	public Iterator<Item> iterator() {
		return new BagIterator<Item>();
	}
	
	private class BagIterator<Item> implements Iterator<Item>{
		Node head=first;
		@Override
		public boolean hasNext() {
			return head!=null;
		}
		@Override
		public Item next() {
			Item item=(Item) head.item;
			head=head.next;
			return item;
		}
		
	}
	
}
