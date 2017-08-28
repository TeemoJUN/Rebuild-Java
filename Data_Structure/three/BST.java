package three;

import java.util.Scanner;

public class BST <Key extends Comparable<Key>,Value>{
	
	private Node root;
	
	private class Node{
		private Node left;
		private Node right;
		private Value val;
		private Key key;
		private int N;
		
		public Node(Key key,Value val,int N){
			this.key=key;
			this.val=val;
			this.N=N;
		}
		
	}
	
	public void add(Key key,Value val){
		root=add(root,key,val);	
	}
	
	private Node add(Node r,Key key,Value val){
		if(r==null){
			return new Node(key,val,1);
		}
		int c=key.compareTo(r.key);
		if(c==0){
			r.val=val;
		}
		else if(c<0){
			r.left=add(r.left,key,val);
		}else{
			r.right=add(r.right,key,val);
		}
		
		r.N=size(r.left)+size(r.right)+1;
		return r;
	}
	/**
	 * 
	 * @param key
	 * @return
	 */
	public Value get(Key key){
		if(root==null){
			return null;
		}
		return get(root,key).val;
	}
	
	public Node get(Node r,Key key){
		
		if(r==null){
			return null;
		}
		
		int c=key.compareTo(r.key);
		if(c==0){
			return r;
		}
		else if(c<0){
			return get(r.left,key);
		}
		else{
			return get(r.right,key);
		}
	}
	
	
	public Value floor(Key key){
		if(root==null){
			return null;
		}
		Node x=floor(root,key);
		if(x==null){
			return null;
		}
		return x.val;
	}
	
	public Node floor(Node r,Key k){
		if(r==null){
			return null;
		}
		
		int c=k.compareTo(r.key);
		
		if(c==0){
			return r;
		}
		else if(c<0){
			return floor(r.left,k);
		}
		Node t=floor(r.right,k);
		if(t!=null){
			return t;
		}
		else{
			return r;
		}
	}
	
	public Node minNode(){
		if(root==null){
			return null;
		}
		return minNode(root);
	}
	
	
	public Node minNode(Node r){
		Node x=r;
		while(x.left!=null){
			x=x.left;
		}
		return x;
	}
	
	public void deleteMin(){
		root=deleteMin(root);
	}
	
	
	public Node deleteMin(Node r){
		if(r.left==null){
			return r.right;
		}
		r.left=deleteMin(r.left);
		r.N=size(r.left)+size(r.right)+1;
		return r;
	}
	
	public int size(){
		return size(root);
	}
	
	public int size(Node r){
		if(r==null){
			return 0;
		}
		else{
			return r.N;
		}
	}
	
	public void delete(Key key){
		root=delete(root,key);
	}
	
	private Node delete(Node r,Key k){
		if(r==null){
			return null;
		}
		int c=k.compareTo(r.key);
		
		if(c<0){
			r.left=delete(r.left,k);
		}
		else if(c>0){
			r.right=delete(r.right,k);
		}
		else{
			Node t=r;
			r=minNode(t.right);
			r.right=deleteMin(t.right);
			r.left=t.left;
		}
		
		r.N=size(r.left)+size(r.right)+1;
		return r;
	}
	
	public void print(){
		print(root);
	}
	
	
	public void print(Node r){
		if(r==null){
			return;
		}
		print(r.left);
		System.out.print("{"+r.key+" "+r.val+"} ");
		print(r.right);
		
	}
	//W H¡¡J K T D
	
	public static void main(String[] args){
		
		
		BST<String,Integer> tree=new BST<String,Integer>();
		Scanner in=new Scanner(System.in);
		
		String line=in.nextLine();
		in.close();
		String[] arr=line.split("\\ ");
		for(int i=0;i<arr.length;i++){
			System.out.println(arr[i]);
			tree.add(arr[i],i);
		}
		
		tree.print();
	}
	
	
	
	
}







