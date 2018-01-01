package com.robbin.algorithm.tree;

import java.util.Stack;



public class MyBinaryTree {
	
	private MyBinaryTreeNode root=null;
	
	public MyBinaryTree(){
		root=new MyBinaryTreeNode(1,"rootNode(A)");
	}
	
	/**
	 * 创建一棵二叉树
	 * <pre>
	 *           A
	 *     B          C
	 *  D     E            F
	 *  </pre>
	 * @param root
	 * @author WWX
	 */
	public void createBinTree(MyBinaryTreeNode root){
		MyBinaryTreeNode newNodeB = new MyBinaryTreeNode(2,"B");
		MyBinaryTreeNode newNodeC = new MyBinaryTreeNode(3,"C");
		MyBinaryTreeNode newNodeD = new MyBinaryTreeNode(4,"D");
		MyBinaryTreeNode newNodeE = new MyBinaryTreeNode(5,"E");
		MyBinaryTreeNode newNodeF = new MyBinaryTreeNode(6,"F");
        root.leftChild=newNodeB;
        root.rightChild=newNodeC;
        root.leftChild.leftChild=newNodeD;
        root.leftChild.rightChild=newNodeE;
        root.rightChild.rightChild=newNodeF;
	}
	
	public static void main(String[] args) {
		MyBinaryTree tree = new MyBinaryTree();
		tree.createBinTree(tree.root);
		tree.nonRecInOrder(tree.root);
	}
	
	/**
	 * 前序遍历二叉树
	 * <pre>
	 *           A
	 *     B          C
	 *  D     E            F
	 *  </pre>
	 * @param root
	 * @author WWX
	 */
	 
	//前序遍历 ABDECF
	public void preOrder(MyBinaryTreeNode subTree){
		
		if (subTree != null) {
			
			visted(subTree);
             
			// A B D  E C F
			
			// 递归遍历当前结点左子数
			preOrder(subTree.leftChild);

			// 递归遍历当前结点右子数
			preOrder(subTree.rightChild);
		}

	}
	
	/**
	 * 中序遍历二叉树
	 * <pre>
	 *           A
	 *     B          C
	 *  D     E            F
	 *  </pre>
	 * @param root
	 * @author WWX
	 */
	 
	//中序遍历 DBEACF
	public void inOrder(MyBinaryTreeNode subTree){
		if (subTree != null) {
			
			// A B D  
			
			// 递归遍历当前结点左子数
			inOrder(subTree.leftChild);
            
			visted(subTree);
			
			// 递归遍历当前结点右子数
			inOrder(subTree.rightChild);
		}
	}
	
	/**
	 * 后续遍历二叉树
	 * <pre>
	 *           A
	 *     B          C
	 *  D     E            F
	 *  </pre>
	 * @param root
	 * @author WWX
	 * 
	 * postOrder(A) - > postOrder(B)- > postOrder(D)- > postOrder(null) return
	 *                              - > postOrder(D->rightChild)  null return   
	 *              - > postOrder(B->rightChild) postOrder(E)  null return
	 *              - > postOrder(A->rightChild) postOrder(C)
	 */
	
	//后续遍历 DEBFCA
	public void postOrder(MyBinaryTreeNode subTree) {
          if (subTree != null) {
			
			// 递归遍历当前结点左子数  A B D 
        	postOrder(subTree.leftChild);
            
        	// 递归遍历当前结点右子数 
  			postOrder(subTree.rightChild);
  			
			visted(subTree);
		}
	}
	
	/**
	 *  根据前序遍历访问的顺序，优先访问根结点，然后再分别访问左孩子和右孩子。
	 *  
	 *  即对于任一结点，其可看做是根结点，因此可以直接访问，
	 *  访问完之后，若其左孩子不为空，按相同规则访问它的左子树；
	 *  当访问其左子树时，再访问它的右子树。因此其处理过程如下：

                    对于任一结点P：

     1)访问结点P，并将结点P入栈;

     2)判断结点P的左孩子是否为空，若为空，则取栈顶结点并进行出栈操作，并将栈顶结点的右孩子置为当前的结点P，循环至1);若不为空，则将P的左孩子置为当前的结点P;

     3)直到P为NULL并且栈为空，则遍历结束。
     
     * <pre>
	 *           A
	 *     B          C
	 *  D     E            F
	 *  </pre>
	 *  
	 *  Stack <---
	 *  
	 * push  A B D (左子数) 然后再pop元素，取其右子数进行处理
	 * pop   D
	 * push  E        ABE
	 * pop   E
	 * push  C
	 *  
	 * @param p
	 */
	//前序遍历的非递归实现 ABD ECF
	public void nonRecPreOrder(MyBinaryTreeNode p){
		Stack<MyBinaryTreeNode> stack = new Stack<MyBinaryTreeNode>();
		MyBinaryTreeNode node = p;
		
		while(node != null || stack.size() > 0){
			
			while (node != null) {
				visted(node);
				stack.push(node);
				node = node.leftChild;
			}

			if (stack.size() > 0) {
				node = stack.pop();
				node = node.rightChild;
			}
		}
		
	}
	
	/**
	 * <pre>
	 *           A
	 *     B          C
	 *  D     E            F
	 *  </pre>
	 *  
	 *  
	 *  
	 *  D        E
	 *  B  B     B
	 *  A  A  A  A
	 * @param p
	 */
	//中序遍历的非递归实现 DBEACF
	public void nonRecInOrder(MyBinaryTreeNode p){
		Stack<MyBinaryTreeNode> stack = new Stack<MyBinaryTreeNode>();
		MyBinaryTreeNode node = p;
		
		while(node != null || stack.size() > 0){
			
			while (node != null) {
				stack.push(node);
				node = node.leftChild;
			}

			if (stack.size() > 0) {
				node = stack.pop();
				visted(node);
				node = node.rightChild;
			}
		}
	}
	
	/**
	 * <pre>
	 *           A
	 *     B          C
	 *  D     E            F
	 *  </pre>
	 *  
	 * @param p
	 */
	//后序遍历的非递归实现 DEBFCA
	public void noRecPostOrder(MyBinaryTreeNode p){
		
		Stack<MyBinaryTreeNode> s = new Stack<MyBinaryTreeNode>();
		MyBinaryTreeNode node = p;
	    while(node != null || !s.empty())
	    {
	        while(node != null) //沿左子树一直往下搜索，直至出现没有左子树的结点
	        {
	         
	        }
	        if(!s.empty())
	        {
	            
	        }
	    }
	}
	
	public void visted(MyBinaryTreeNode subTree){
		subTree.isVisted=true;
		System.out.println("key:"+subTree.key+"--name:"+subTree.data);;
	}

	private class MyBinaryTreeNode {
		private int key = 0;
		private String data = null;
		private boolean isVisted = false;
		private MyBinaryTreeNode leftChild = null;
		private MyBinaryTreeNode rightChild = null;

		public MyBinaryTreeNode() {
		}

		/**
		 * @param key
		 *            层序编码
		 * @param data
		 *            数据域
		 */
		public MyBinaryTreeNode(int key, String data) {
			this.key = key;
			this.data = data;
			this.leftChild = null;
			this.rightChild = null;
		}
	}
}
