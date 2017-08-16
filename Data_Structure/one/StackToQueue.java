package one;

public class StackToQueue<Item>{

	private Stack<Item> stack1;
	private Stack<Item> stack2;
	private int N=0;
	public StackToQueue(){
		this.stack1=new Stack<Item>();
		this.stack2=new Stack<Item>();
	}
	
	public void enqueue(Item item){
		stack1.push(item);
		N++;
	}
	
	public Item dequeue() throws Exception{
		if(N==0){
			throw new RuntimeException("ЮЊПе");
		}
		if(stack2.size()==0){
			while(stack1.size()!=0){
				stack2.push(stack1.pop());
			}
		}
		N--;
		return stack2.pop();
	}
	
	public int size(){
		return N;
	}
	public boolean isEmpty(){
		return N==0;
	}
}
