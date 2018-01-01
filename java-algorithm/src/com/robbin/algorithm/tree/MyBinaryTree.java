package com.robbin.algorithm.tree;

import java.util.Stack;



public class MyBinaryTree {
	
	private MyBinaryTreeNode root=null;
	
	public MyBinaryTree(){
		root=new MyBinaryTreeNode(1,"rootNode(A)");
	}
	
	/**
	 * ����һ�ö�����
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
	 * ǰ�����������
	 * <pre>
	 *           A
	 *     B          C
	 *  D     E            F
	 *  </pre>
	 * @param root
	 * @author WWX
	 */
	 
	//ǰ����� ABDECF
	public void preOrder(MyBinaryTreeNode subTree){
		
		if (subTree != null) {
			
			visted(subTree);
             
			// A B D  E C F
			
			// �ݹ������ǰ���������
			preOrder(subTree.leftChild);

			// �ݹ������ǰ���������
			preOrder(subTree.rightChild);
		}

	}
	
	/**
	 * �������������
	 * <pre>
	 *           A
	 *     B          C
	 *  D     E            F
	 *  </pre>
	 * @param root
	 * @author WWX
	 */
	 
	//������� DBEACF
	public void inOrder(MyBinaryTreeNode subTree){
		if (subTree != null) {
			
			// A B D  
			
			// �ݹ������ǰ���������
			inOrder(subTree.leftChild);
            
			visted(subTree);
			
			// �ݹ������ǰ���������
			inOrder(subTree.rightChild);
		}
	}
	
	/**
	 * ��������������
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
	
	//�������� DEBFCA
	public void postOrder(MyBinaryTreeNode subTree) {
          if (subTree != null) {
			
			// �ݹ������ǰ���������  A B D 
        	postOrder(subTree.leftChild);
            
        	// �ݹ������ǰ��������� 
  			postOrder(subTree.rightChild);
  			
			visted(subTree);
		}
	}
	
	/**
	 *  ����ǰ��������ʵ�˳�����ȷ��ʸ���㣬Ȼ���ٷֱ�������Ӻ��Һ��ӡ�
	 *  
	 *  ��������һ��㣬��ɿ����Ǹ���㣬��˿���ֱ�ӷ��ʣ�
	 *  ������֮���������Ӳ�Ϊ�գ�����ͬ�������������������
	 *  ��������������ʱ���ٷ�������������������䴦��������£�

                    ������һ���P��

     1)���ʽ��P���������P��ջ;

     2)�жϽ��P�������Ƿ�Ϊ�գ���Ϊ�գ���ȡջ����㲢���г�ջ����������ջ�������Һ�����Ϊ��ǰ�Ľ��P��ѭ����1);����Ϊ�գ���P��������Ϊ��ǰ�Ľ��P;

     3)ֱ��PΪNULL����ջΪ�գ������������
     
     * <pre>
	 *           A
	 *     B          C
	 *  D     E            F
	 *  </pre>
	 *  
	 *  Stack <---
	 *  
	 * push  A B D (������) Ȼ����popԪ�أ�ȡ�����������д���
	 * pop   D
	 * push  E        ABE
	 * pop   E
	 * push  C
	 *  
	 * @param p
	 */
	//ǰ������ķǵݹ�ʵ�� ABD ECF
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
	//��������ķǵݹ�ʵ�� DBEACF
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
	//��������ķǵݹ�ʵ�� DEBFCA
	public void noRecPostOrder(MyBinaryTreeNode p){
		
		Stack<MyBinaryTreeNode> s = new Stack<MyBinaryTreeNode>();
		MyBinaryTreeNode node = p;
	    while(node != null || !s.empty())
	    {
	        while(node != null) //��������һֱ����������ֱ������û���������Ľ��
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
		 *            �������
		 * @param data
		 *            ������
		 */
		public MyBinaryTreeNode(int key, String data) {
			this.key = key;
			this.data = data;
			this.leftChild = null;
			this.rightChild = null;
		}
	}
}
