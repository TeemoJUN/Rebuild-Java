package test;


/*
 * 
 * 
 * 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。要求不能创建任何新的结点，只能调整树中结点指针的指向。
 * 
 * */


public class Solution18 {
	
	public class TreeNode {
	    int val = 0;
	    TreeNode left = null;
	    TreeNode right = null;

	    public TreeNode(int val) {
	        this.val = val;
	    }

	}
	TreeNode head=null;
	TreeNode realHead=null;
		public TreeNode Convert(TreeNode pRootOfTree) {
			
			convertLink(pRootOfTree);
	    	return realHead;
	    }
	    
		
		private void convertLink(TreeNode pRootOfTree){
			
			if(pRootOfTree==null){
				return ;
			}
			convertLink(pRootOfTree.left);
			
			if(realHead==null){
				head=pRootOfTree;
				realHead=head;
			}else{
				head.right=pRootOfTree;
				pRootOfTree.left=head;
				head=pRootOfTree;
			}
			convertLink(pRootOfTree.right);
		}
	    
	    
	    
	    
	    
	    

}
