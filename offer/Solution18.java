package test;


/*
 * 
 * 
 * ����һ�ö��������������ö���������ת����һ�������˫������Ҫ���ܴ����κ��µĽ�㣬ֻ�ܵ������н��ָ���ָ��
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
