package com.robbin.algorithm.tree;

/**
 * 最小堆
 * 
 * @author robbin.zhang
 * @see http://blog.csdn.net/xiao__gui/article/details/8687982
 * 
 */
public class MinHeap
{
	// 堆的存储结构 - 数组
	private int[] data;
	
	// 将一个数组传入构造方法，并转换成一个小根堆
	public MinHeap(int[] data)
	{
		this.data = data;
		buildHeap();
	}

	/**
	 * 【构建初始堆】 给定一个无序数组，如何使之成为一个最小堆呢？将普通无序数组变为堆的过程我们称之为heapify。
	 * 
	 * 基本思路是，从最后一个非叶子节点开始，一直往前直到根，对每个节点，执行向下调整siftdown。
	 * 换句话说，是自底向上，先使每个【最小子树为堆】，然后每对【左右子树和其父节点合并】
	 * ，调整为更大的堆，因为每个子树已经为堆，所以调整就是对父节点执行siftdown，就这样一直合并调整直到根。
	 * 
	 * 这个算法的伪代码是：
	 *   void heapify()
	 *   { 
	 *       for (int i=size/2; i >= 1; i--) siftdown(i);
	 *   }
	 * 
	 * size表示节点个数, 节点编号从1开始，size/2表示第一个非叶节点的编号。
	 * 
	 * 这个构建的时间效率为O(N)，N为节点个数，具体就不证明了。
	 **/
	// 将数组转换成最小堆(调整堆)
	private void buildHeap()
	{
		// 完全二叉树只有数组下标小于或等于 (data.length) / 2 - 1 的元素有孩子结点，遍历这些结点。
		// *比如上面的图中，数组有10个元素， (data.length) / 2 - 1的值为4，a[4]有孩子结点，但a[5]没有*
        for (int i = (data.length) / 2 - 1; i >= 0; i--)  // 4 3 2 1 0
        {
        	// 对有孩子结点的元素heapify
            heapify(i);
        }
    }
	
	private void heapify(int i)
	{
		// 获取左右结点的数组下标
        int l = left(i);  
        int r = right(i);
        
        // 这是一个临时变量，表示 跟结点、左结点、右结点中最小的值的结点的下标
        int smallest = i;
        
        // 存在左结点，且左结点的值小于根结点的值
        if (l < data.length && data[l] < data[i])  
        	smallest = l;  
        
        // 存在右结点，且右结点的值小于以上比较的较小值
        if (r < data.length && data[r] < data[smallest])  
        	smallest = r;  
        
        // 左右结点的值都大于根节点，直接return，不做任何操作
        if (i == smallest)  
            return;  
        
        // 交换根节点和左右结点中最小的那个值，把根节点的值替换下去
        swap(i, smallest);
        
        // 由于替换后左右子树会被影响，所以要对受影响的子树再进行heapify
        heapify(smallest);
    }
	
	// 获取右结点的数组下标
	private int right(int i)
	{  
        return (i + 1) << 1;  
    }   

	// 获取左结点的数组下标
    private int left(int i) 
    {  
        return ((i + 1) << 1) - 1;  
    }
    
    // 交换元素位置
    private void swap(int i, int j) 
    {  
        int tmp = data[i];  
        data[i] = data[j];  
        data[j] = tmp;  
    }
    
    // 获取对中的最小的元素，根元素
    public int getRoot()
    {
    	    return data[0];
    }

    // 替换根元素，并重新heapify
	public void setRoot(int root)
	{
		data[0] = root;
		heapify(0);
	}
	
	public static void main(String[] args) {
		int[] data = {56,275,12,6,45,478,41,1236,456,12};
		MinHeap minHeap = new MinHeap(data);
		for (int i = 0; i < data.length; i++) {
			System.out.println(data[i]);
		}
	}
}
