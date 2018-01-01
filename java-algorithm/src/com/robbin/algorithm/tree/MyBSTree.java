package com.robbin.algorithm.tree;

/**
 * 二叉搜索树
 * 
 * @author robbin.zhang
 * @date 2017/07/17 09:53
 * @see http://www.linuxidc.com/Linux/2016-08/134742.htm
 * 
 * @param <T>
 */
public class MyBSTree<T extends Comparable<T>> {

	private MyBSTNode<T> root; // 根结点
	
	public MyBSTNode<T> search(T key) {
		return search(root, key);
	}

	private MyBSTNode<T> search(MyBSTNode<T> x, T key) {
		if (x == null) {
			return null;
		}
		int cmp = x.key.compareTo(key);
		if (cmp < 0) {
			return search(x.left, key);
		} else if (cmp > 0) {
			return search(x.right, key);
		} else {
			return x;
		}
	}

	private void insert(MyBSTree<T> bst, MyBSTNode<T> z) {
		int cmp;
		MyBSTNode<T> x = root;
		MyBSTNode<T> y = null;
		while (x != null) {
			cmp = x.key.compareTo(z.key);
			y = x;
			if (cmp < 0) {
				x = x.left;
			} else {
				x = x.right;
			}
		}

		z.parent = y;
		if (y == null) {
			bst.root = z;
		} else {
			cmp = y.key.compareTo(z.key);
			if (cmp < 0) {
				y.left = z;
			} else {
				y.right = z;
			}
		}
	}

	public void insert(T key) {
		MyBSTNode<T> z = new MyBSTNode<T>(key, null, null, null);
		if (z != null) {
			insert(this, z);
		}
	}
	
	public T remove(T key) {
		MyBSTNode<T> p = search(key);
		if (p == null)
			return null;
		T oldValue = p.key;
		delete(p);
		return oldValue;
	}
    
	/**
	 * 删除结点(z)，并返回被删除的结点
	 * @param bst 二叉树
	 * @param z 删除的结点
	 * @return
	 * 
	 *  // （1）需要删除的节点下并没有其他子节点。
        // （2）需要删除的节点下有一个子节点（左或右）。
        // （3）需要删除的节点下有两个子节点（既左右节点都存在）。
	 */
	private void delete(MyBSTNode<T> p) {
		
		 // If strictly internal, copy successor's element to p and then make p
        // point to successor.
		if(p.left != null && p.right != null){
			MyBSTNode<T> s = successor(p);
			p.key = s.key;
			p = s;
		}// p has 2 children
		
		// Start fixup at replacement node, if it exists.
		MyBSTNode<T> replacement = (p.left != null ? p.left : p.right);
		if (replacement != null) {
			replacement.parent = p.parent;
			
			// 就是判断这个节点是否是根节点，如果是根节点的话，就直接让根节点指向这个节点的左孩子或右孩子，然后删除这个节点。
			if (p.parent == null)
				root = replacement;
			else if (p == p.parent.left)
				p.parent.left = replacement;
			else
				p.parent.right = replacement;
		} else if (p.parent == null) { // return if we are the only node.
            root = null;
		} else { //  No children. Use self as phantom replacement and unlink.
            
            if (p.parent != null) {
                if (p == p.parent.left)
                    p.parent.left = null;
                else if (p == p.parent.right)
                    p.parent.right = null;
                p.parent = null;
            }
        }
	}
	
	/**
	 * 获取该节点最小的结点
	 * @param bst
	 * @return
	 */
	private MyBSTNode<T> minimum(MyBSTNode<T> tree) {
		
		if(tree == null){
			return null;
		}
		while(tree.left != null){
			tree = tree.left;
		}
		return tree;
	}
	
	/**
	 * 获取树最小值
	 * @param bst
	 * @return
	 */
	public T minimum() {
		MyBSTNode<T> node = minimum(root);
		if(node != null){
			return node.key;
		}
		return null;
	}
	
	/**
	 * 节点的后继：是该节点的右子树中的最小节点。
	 * @param x
	 * @return
	 */
	public MyBSTNode<T> successor(MyBSTNode<T> x) {

		// 如果x存在右孩子，则"x的后继结点"为 "以其右孩子为根的子树的最小结点"。
		if (x.right != null) {
			return minimum(x.right);
		}

		// 如果x没有右孩子。则x有以下两种可能：
		// (01) x是"一个左孩子"，则"x的后继结点"为 "它的父结点"。
		// (02) x是"一个右孩子"，则查找"x的【最低的父结点】，并且该【父结点要具有左孩子】"，找到的这个"最低的父结点"就是"x的后继结点"。
		MyBSTNode<T> y = x.parent;
		while (y != null && (x == y.right)) {
			x = y;
			y = y.parent;
		}
		return y;
	}
	
	/**
	 * 节点的前驱：是该节点的左子树中的最大节点。
	 * @param x
	 * @return
	 */
	public MyBSTNode<T> predecessor(MyBSTNode<T> x) {
		
		// 如果x存在左孩子，则"x的前驱结点"为 "以其左孩子为根的子树的最大结点"。
		if (x.left != null) {
			return maximum(x.left);
		}
		
		// 如果x没有左孩子。则x有以下两种可能：
        // (01) x是"一个右孩子"，则"x的前驱结点"为 "它的父结点"。
        // (01) x是"一个左孩子"，则查找"【x的最低的父结点】，并且该【父结点要具有右孩子】"，找到的这个"最低的父结点"就是"x的前驱结点"。
		// 沿着左树路线向上搜索
		MyBSTNode<T> y = x.parent;
		while (y != null && (x == y.left)) {
			x = y;
			y = y.parent;
		}
		return y;
	}
	
	private MyBSTNode<T> maximum(MyBSTNode<T> tree) {
		if(tree == null){
			return null;
		}
		while(tree.right != null){
			tree = tree.right;
		}
		return tree;
	}

	public class MyBSTNode<T extends Comparable<T>> {
		T key;
		MyBSTNode<T> left;
		MyBSTNode<T> right;
		MyBSTNode<T> parent;

		public MyBSTNode(T key, MyBSTNode<T> left, MyBSTNode<T> right,
				MyBSTNode<T> parent) {
			this.key = key;
			this.left = left;
			this.right = right;
			this.parent = parent;
		}

		public T getKey() {
			return key;
		}

		public void setKey(T key) {
			this.key = key;
		}

		public String toString() {
			return "key:" + key;
		}
	}
}
