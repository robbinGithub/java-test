package com.robbin.algorithm.tree;

/**
 * ����������
 * 
 * @author robbin.zhang
 * @date 2017/07/17 09:53
 * @see http://www.linuxidc.com/Linux/2016-08/134742.htm
 * 
 * @param <T>
 */
public class MyBSTree<T extends Comparable<T>> {

	private MyBSTNode<T> root; // �����
	
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
	 * ɾ�����(z)�������ر�ɾ���Ľ��
	 * @param bst ������
	 * @param z ɾ���Ľ��
	 * @return
	 * 
	 *  // ��1����Ҫɾ���Ľڵ��²�û�������ӽڵ㡣
        // ��2����Ҫɾ���Ľڵ�����һ���ӽڵ㣨����ң���
        // ��3����Ҫɾ���Ľڵ����������ӽڵ㣨�����ҽڵ㶼���ڣ���
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
			
			// �����ж�����ڵ��Ƿ��Ǹ��ڵ㣬����Ǹ��ڵ�Ļ�����ֱ���ø��ڵ�ָ������ڵ�����ӻ��Һ��ӣ�Ȼ��ɾ������ڵ㡣
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
	 * ��ȡ�ýڵ���С�Ľ��
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
	 * ��ȡ����Сֵ
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
	 * �ڵ�ĺ�̣��Ǹýڵ���������е���С�ڵ㡣
	 * @param x
	 * @return
	 */
	public MyBSTNode<T> successor(MyBSTNode<T> x) {

		// ���x�����Һ��ӣ���"x�ĺ�̽��"Ϊ "�����Һ���Ϊ������������С���"��
		if (x.right != null) {
			return minimum(x.right);
		}

		// ���xû���Һ��ӡ���x���������ֿ��ܣ�
		// (01) x��"һ������"����"x�ĺ�̽��"Ϊ "���ĸ����"��
		// (02) x��"һ���Һ���"�������"x�ġ���͵ĸ���㡿�����Ҹá������Ҫ�������ӡ�"���ҵ������"��͵ĸ����"����"x�ĺ�̽��"��
		MyBSTNode<T> y = x.parent;
		while (y != null && (x == y.right)) {
			x = y;
			y = y.parent;
		}
		return y;
	}
	
	/**
	 * �ڵ��ǰ�����Ǹýڵ���������е����ڵ㡣
	 * @param x
	 * @return
	 */
	public MyBSTNode<T> predecessor(MyBSTNode<T> x) {
		
		// ���x�������ӣ���"x��ǰ�����"Ϊ "��������Ϊ���������������"��
		if (x.left != null) {
			return maximum(x.left);
		}
		
		// ���xû�����ӡ���x���������ֿ��ܣ�
        // (01) x��"һ���Һ���"����"x��ǰ�����"Ϊ "���ĸ����"��
        // (01) x��"һ������"�������"��x����͵ĸ���㡿�����Ҹá������Ҫ�����Һ��ӡ�"���ҵ������"��͵ĸ����"����"x��ǰ�����"��
		// ��������·����������
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
