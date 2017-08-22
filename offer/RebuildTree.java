package test;

import java.util.Arrays;




public class RebuildTree {
	
	class Node{
		int value;
		Node left;
		Node right;
	}	
	
	public static void main(String[] args) throws Exception{
		int[] preSort={1,2,4,7,3,5,6,8};
		int[] inSort={4,7,2,1,5,3,8,6};
		
		
		RebuildTree r=new RebuildTree();
		Node root=r.rebuildTree(preSort, inSort);
		
	}
	
	public Node rebuildTree(int[] pre,int[] in) throws Exception{
		if(pre==null||in==null){
			return null;
		}
		if(pre.length!=in.length){
			 throw new Exception("不合理的输入");
		}
		Node root=new Node();
		//int nodeValue=pre[0];//放开了就报错,因为pre[0]可能不存在
		//root.value=nodeValue;
		for(int i=0;i<in.length;i++){
			if(in[i]==pre[0]){
				root.value=pre[0];
				
				int[] preFirst=Arrays.copyOfRange(pre, 1, i+1);
				int[] preScond=Arrays.copyOfRange(pre,i+1,pre.length);
				int[] inFirst=Arrays.copyOfRange(in, 0, i);
				int[] inSecond=Arrays.copyOfRange(in, i+1, in.length);
				root.left=rebuildTree(preFirst,inFirst);
				root.right=rebuildTree(preScond,inSecond);
			
			}
		}
		return root;
	}
	
	
}




















