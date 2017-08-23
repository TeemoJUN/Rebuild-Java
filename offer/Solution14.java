package test;


/*
 * 
 * 输入两个单调递增的链表，输出两个链表合成后的链表，当然我们需要合成后的链表满足单调不减规则。
 * 
 * */
class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}
public class Solution14 {
	public ListNode Merge(ListNode list1,ListNode list2) {
        if(list1==null){
    		return list2;
    	}
    	if(list2==null){
    		return list1;
    	}
    	
    	ListNode head=null;
    	ListNode current=null;
    	while(list1!=null&&list2!=null){
    		if(list1.val<=list2.val){
    			if(head==null){
    				head=current=list1;
    			}
    			else{
    				current.next=list1;
    				current=current.next;
    			}
    			list1=list1.next;
    		}
    		else{
    			if(head==null){
    				head=current=list2;
    			}
    			else{
    				current.next=list2;
    				current=current.next;
    			}
    			list2=list2.next;
    		}
    	}
    	if(list1 == null){
		current.next = list2;
		}else{
			current.next = list1;
			}
    	return head;
    }
}
