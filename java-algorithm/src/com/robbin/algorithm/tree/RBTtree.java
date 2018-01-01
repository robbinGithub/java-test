package com.robbin.algorithm.tree;
/**
 * RBT 红黑树
    
    AVL，SBT，伸展树，TREAP ，红黑树
    
	AVL是严格平衡树，因此在增加或者删除节点的时候，根据不同情况，旋转的次数比红黑树要多；
	红黑是弱平衡的，用非严格的平衡来换取增删节点时候旋转次数的降低；
	所以简单说，【搜索的次数远远大于插入和删除，那么选择AVL树】，如果搜索，插入删除次数几乎差不多，应该选择RB树。
	
	红黑树上每个结点内含五个域，color，key，left，right，p。如果相应的指针域没有，则设为NIL。
	一般的，红黑树，满足以下性质，即只有满足以下全部性质的树，我们才称之为红黑树：
	
	1）每个结点要么是红的，要么是黑的。
	2）根结点是黑的。
	3）每个叶结点，即空结点（NIL）是黑的。
	4）如果一个结点是红的，那么它的俩个儿子都是黑的。
	5）对每个结点，从该结点到其子孙结点的所有路径上包含相同数目的黑结点。
	下图所示，即是一颗红黑树：
	
 * @author robbin
 * @see http://blog.csdn.net/chenssy/article/details/26668941
 * @see http://www.importnew.com/19074.html
 *
 */
public class RBTtree {
	

}
