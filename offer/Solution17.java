package test;


/*
 * 
 * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。如果是则输出Yes,否则输出No。假设输入的数组的任意两个数字都互不相同。
 * 
 * */
public class Solution17 {
	
	
	public static void main(String[] args){
		int[] a={5,4,3,2,1};
		Solution17 cc=new Solution17();
		boolean r=cc.VerifySquenceOfBST(a);
		System.out.println(r);
	}
	
    public boolean VerifySquenceOfBST(int [] sequence) {
        int hi=sequence.length-1;
        if(hi<0){
        	return false;
        }
        int lo=0;
        
        int index=hi;
        
        boolean r=true;
        
    	while((hi>lo)&&(r==true)){
    		index=find(sequence,lo,hi-1,hi);
    		if(index<hi-1){
    			r=ensure(sequence,hi,index+1,hi-1);
    		
    		}
    		hi=index;
    	}
    	
    	if(r==false){
    		return false;
    	}
    	else{
    		return true;
    	}
    }
    public int find(int[] arr,int start,int end,int root){
    	if(end<=start){
    		return start;
    	}
    	int v=arr[root];
    	int i=start;
    	for(;i<=end;i++){
    		if(arr[i]>v){
    			break;
    		}
    	}
    	return i-1;
    }
    
    public boolean ensure(int[] arr,int root,int start,int end){
    	int v=arr[root];
    	int i=start;
    	for(;i<=end;i++){
    		if(arr[i]<v){
    			return false;
    		}
    	}
    	return true;
    }
}
