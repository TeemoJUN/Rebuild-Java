package test;



/*
 * 
 * �������ö�����A��B���ж�B�ǲ���A���ӽṹ����ps������Լ��������������һ�������ӽṹ��
 * */


class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;

    public TreeNode(int val) {
        this.val = val;
    }
}

public class Solution15 {
    public boolean HasSubtree(TreeNode root1,TreeNode root2) {
    	boolean result=false;
    	//�ȼ���Ƿ����
    	if(root1==null||root2==null){
    		return false;
    	}
    	
    	if(root1.val==root2.val){
    		result=isDo(root1,root2);
    	}
    	if(!result){
    		result=isDo(root1.left,root2);
    	}
    	if(!result){
    		result=isDo(root1.right,root2);
    	}
        return result;
    }
    
    
    
    public boolean isDo(TreeNode root1,TreeNode root2){
    	if(root1==null||root2==null){
    		return false;
    	}
    	if(root1.val!=root2.val){
    		return false;
    	}
    	return HasSubtree(root1.left,root2.left)&&HasSubtree(root1.right,root2.right);
    }
}



















