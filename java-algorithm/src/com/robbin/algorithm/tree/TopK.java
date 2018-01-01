package com.robbin.algorithm.tree;

/**
 * 
 *  方法一：对源数据中所有数据进行排序，取出前K个数据，就是TopK。但是当数据量很大时，只需要k个最大的数，整体排序很耗时，效率不高。
 *	
 *	方法二：维护一个K长度的数组a[]，先读取源数据中的前K个放入数组，对该数组进行【升序排序】，再依次读取源数据第K个以后的数据，
 *       和数组中最小的元素（a[0]）比较，如果小于a[0]直接pass，大于的话，就丢弃最小的元素a[0]，
 *       利用二分法找到其位置，然后该位置前的数组元素整体向前移位，直到源数据读取结束。
 *	              这比方法一效率会有很大的提高，但是当K的值较大的时候，长度为K的数据整体移位，也是非常耗时的。
 * 
 *       对于这种问题，效率比较高的解决方法是使用最小堆。
 * 
 * @author robbin.zhang
 * @see http://blog.csdn.net/xiao__gui/article/details/8687982
 *
 */
public class TopK
{
	public static void main(String[] args)
	{
		// 源数据
		int[] data = {56,275,12,6,45,478,41,1236,456,12,546,45};
		
// 获取Top5
		int[] top5 = topK(data, 5);
		
		for(int i=0;i<5;i++)
		{
			System.out.println(top5[i]);
		}
	}
	
	// 从data数组中获取最大的k个数
	private static int[] topK(int[] data,int k)
	{
		// 先取K个元素放入一个数组topk中
		int[] topk = new int[k]; 
		for(int i = 0;i< k;i++)
		{
			topk[i] = data[i];
		}
		
		// 转换成最小堆
		MinHeap heap = new MinHeap(topk);
		
		// 从k开始，遍历data
		for(int i= k;i<data.length;i++)
		{
			int root = heap.getRoot();
			
			// 当数据大于堆中最小的数（根节点）时，替换堆中的根节点，再转换成堆
			if(data[i] > root)
			{
				heap.setRoot(data[i]);
			}
		}
		
		return topk;
}
}
