package com.robbin.algorithm.tree;

/**
 * 
 *  ����һ����Դ�������������ݽ�������ȡ��ǰK�����ݣ�����TopK�����ǵ��������ܴ�ʱ��ֻ��Ҫk������������������ܺ�ʱ��Ч�ʲ��ߡ�
 *	
 *	��������ά��һ��K���ȵ�����a[]���ȶ�ȡԴ�����е�ǰK���������飬�Ը�������С��������򡿣������ζ�ȡԴ���ݵ�K���Ժ�����ݣ�
 *       ����������С��Ԫ�أ�a[0]���Ƚϣ����С��a[0]ֱ��pass�����ڵĻ����Ͷ�����С��Ԫ��a[0]��
 *       ���ö��ַ��ҵ���λ�ã�Ȼ���λ��ǰ������Ԫ��������ǰ��λ��ֱ��Դ���ݶ�ȡ������
 *	              ��ȷ���һЧ�ʻ��кܴ����ߣ����ǵ�K��ֵ�ϴ��ʱ�򣬳���ΪK������������λ��Ҳ�Ƿǳ���ʱ�ġ�
 * 
 *       �����������⣬Ч�ʱȽϸߵĽ��������ʹ����С�ѡ�
 * 
 * @author robbin.zhang
 * @see http://blog.csdn.net/xiao__gui/article/details/8687982
 *
 */
public class TopK
{
	public static void main(String[] args)
	{
		// Դ����
		int[] data = {56,275,12,6,45,478,41,1236,456,12,546,45};
		
// ��ȡTop5
		int[] top5 = topK(data, 5);
		
		for(int i=0;i<5;i++)
		{
			System.out.println(top5[i]);
		}
	}
	
	// ��data�����л�ȡ����k����
	private static int[] topK(int[] data,int k)
	{
		// ��ȡK��Ԫ�ط���һ������topk��
		int[] topk = new int[k]; 
		for(int i = 0;i< k;i++)
		{
			topk[i] = data[i];
		}
		
		// ת������С��
		MinHeap heap = new MinHeap(topk);
		
		// ��k��ʼ������data
		for(int i= k;i<data.length;i++)
		{
			int root = heap.getRoot();
			
			// �����ݴ��ڶ�����С���������ڵ㣩ʱ���滻���еĸ��ڵ㣬��ת���ɶ�
			if(data[i] > root)
			{
				heap.setRoot(data[i]);
			}
		}
		
		return topk;
}
}
