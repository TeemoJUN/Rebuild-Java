package one;

import java.util.Iterator;

public class Link<Item> implements Iterable<Item>{
	private Node first;
	private int N=0;
	
	
	private class Node{
		Item item;
		Node next;
	}
	
	public void addNode(Item item){
		Node oldFirst=first;
		first=new Node();
		first.item=item;
		first.next=oldFirst;
		N++;
	}
	
	//返回链表个数N
	public int size(){
		return N;
	}
	
	//是否为空
	public boolean isEmpty(){
		return N==0;
	}
	
	//删除节点等于item的节点
	public void deleteNode(Item item){
		if(first.item.equals(item)){
			first=first.next;
			return;
		}
		Node current=first;
		for(;current.next!=null;current=current.next){
			if(current.next.item.equals(item)){
				if(current.next.next!=null){
					current=current.next.next;
				}
				else{
					current.next=null;
				}
			}
		}
	}

	@Override
	public Iterator<Item> iterator() {
		return new LinkIterator<Item>();
	}
	
	@SuppressWarnings("hiding")
	private class LinkIterator<Item> implements Iterator<Item>{
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
	//查找和item想节点
	public boolean find(Item item){
		if(first.item.equals(item)){
			return true;
		}
		Node current=first;
		for(;current.next!=null;current=current.next){
			if(current.next.item.equals(item)){
				return true;
			}
		}
		return false;
	}
	
	//反转
	public  Link<Item> reverseLink(Link<Item> link){
		Link<Item> rLink=new Link<Item>();
		for(Item i:link){
			rLink.addNode(i);
		}
		return rLink;
	}
	
	public static void main(String[] args){
		Link<String> link=new Link<String>();
		link.addNode("A");
		link.addNode("B");
		link.addNode("C");
		link.addNode("d");
		link.addNode("h");
		link.addNode("U");
		link.addNode("T");
		
		for(String i:link){
			System.out.println(i);
		}
		System.out.println(link.find("A"));//true
		
		System.out.println(link.find("T"));//true
		
		System.out.println(link.find("B"));//true
		
		System.out.println(link.find("f"));//false
		
		System.out.println(link.find("D"));//false
		
		System.out.println(link.find("d"));//true
		System.out.println(link.find("t"));//false
		
		Link<String> reverse=link.reverseLink(link);
		
		for(String i:reverse){
			System.out.println(i);
		}
		
	}
	
	
	
}

