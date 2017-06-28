
![这里写图片描述](http://img.blog.csdn.net/20170628175734281?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvVGVtbW9fTGlu/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

### 简短的说下：因为二叉树用中序遍历就一个顺序排列好了的，所以只要将其链接起来就可以成功啦；我有三种方案。

 1. 创建一个队列，在中序遍历时将其添加进去，最终将其链接起来；就是一个双链表啦。
 2. 这种方法呢有点慢，就是每回查找root的右节点的最大值，链接到root上，后查找左结点的最小值链接到root上，这种反复的查找就可以啦
 3. 最后一种就是我做的这一种，先申明一个head头节点，赋值为Integer.MAX_VALUE。在中序遍历是将其链接上来。
```
/**
 * Definition of TreeNode:
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left, right;
 *     public TreeNode(int val) {
 *         this.val = val;
 *         this.left = this.right = null;
 *     }
 * }
 * Definition for Doubly-ListNode.
 * public class DoublyListNode {
 *     int val;
 *     DoublyListNode next, prev;
 *     DoublyListNode(int val) {
 *         this.val = val;
 *         this.next = this.prev = null;
 *     }
 * }
 */ 
public class Solution {
    /**
     * @param root: The root of tree
     * @return: the head of doubly list node
     */
    DoublyListNode head=new DoublyListNode(Integer.MAX_VALUE);//定义头节点
    public DoublyListNode bstToDoublyList(TreeNode root) {  
        // Write your code here
        print(root);//中序遍历
        
        /*当二叉树为空时，只有头节点了*/
        
        while(head.val==Integer.MAX_VALUE){
            return null;
        }
        /*删除头节点*/
        while(head.prev.val!=Integer.MAX_VALUE){
            head=head.prev;
        }
        head.prev.next=null;
        head.prev=null;
        
        return head;
    }
    
    private void print(TreeNode root){
     if(root==null){
            return;
        }
        print(root.left);
        //连接
        DoublyListNode b=new DoublyListNode(root.val);
        head.next=b;
        b.prev=head;
        head=b;
        
        print(root.right);
    }
}
```
*其实在删除头节点时有一个很好的改进，因为仔细观察发现双链表在链接完所有的二叉树的节点后，它的指向是双链表的末尾（head指向的是5），所以整个双链表是倒叙的，因此只要删除一个节点就可以啦。*
删除前
Integer.MAX_VALUE<-->1<-->2<-->3<-->4<-->5<--head
head指向的5；
删除后：
head-->1<-->2<-->3<-->4<-->5
head指向回1。
