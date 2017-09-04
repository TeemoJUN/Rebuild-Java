package test;

import java.util.ArrayList;

/*
 * 
 * �������´�ӡ����������ÿ���ڵ㣬ͬ��ڵ�������Ҵ�ӡ��
 * */

class TreeNode6 {
    int val = 0;
    TreeNode6 left = null;
    TreeNode6 right = null;

    public TreeNode6(int val) {
        this.val = val;

    }

}

public class Solution16 {
	
	public ArrayList<Integer> PrintFromTopToBottom(TreeNode6 root) {
		
		ArrayList<Integer> result=new ArrayList<Integer>();
		if(root==null){
			return result;
		}
		ArrayList<TreeNode6> nodes=new ArrayList<TreeNode6>();
		nodes.add(root);
		while(nodes.size()>0){
			TreeNode6 node=nodes.remove(0);
			if(node.left!=null){
				nodes.add(node.left);
			}
			if(node.right!=null){
				nodes.add(node.right);
			}
			result.add(node.val);
		}
		return result;
		
	}
	
}
