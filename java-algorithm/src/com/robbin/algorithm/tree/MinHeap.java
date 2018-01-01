package com.robbin.algorithm.tree;

/**
 * ��С��
 * 
 * @author robbin.zhang
 * @see http://blog.csdn.net/xiao__gui/article/details/8687982
 * 
 */
public class MinHeap
{
	// �ѵĴ洢�ṹ - ����
	private int[] data;
	
	// ��һ�����鴫�빹�췽������ת����һ��С����
	public MinHeap(int[] data)
	{
		this.data = data;
		buildHeap();
	}

	/**
	 * ��������ʼ�ѡ� ����һ���������飬���ʹ֮��Ϊһ����С���أ�����ͨ���������Ϊ�ѵĹ������ǳ�֮Ϊheapify��
	 * 
	 * ����˼·�ǣ������һ����Ҷ�ӽڵ㿪ʼ��һֱ��ǰֱ��������ÿ���ڵ㣬ִ�����µ���siftdown��
	 * ���仰˵�����Ե����ϣ���ʹÿ������С����Ϊ�ѡ���Ȼ��ÿ�ԡ������������丸�ڵ�ϲ���
	 * ������Ϊ����Ķѣ���Ϊÿ�������Ѿ�Ϊ�ѣ����Ե������ǶԸ��ڵ�ִ��siftdown��������һֱ�ϲ�����ֱ������
	 * 
	 * ����㷨��α�����ǣ�
	 *   void heapify()
	 *   { 
	 *       for (int i=size/2; i >= 1; i--) siftdown(i);
	 *   }
	 * 
	 * size��ʾ�ڵ����, �ڵ��Ŵ�1��ʼ��size/2��ʾ��һ����Ҷ�ڵ�ı�š�
	 * 
	 * ���������ʱ��Ч��ΪO(N)��NΪ�ڵ����������Ͳ�֤���ˡ�
	 **/
	// ������ת������С��(������)
	private void buildHeap()
	{
		// ��ȫ������ֻ�������±�С�ڻ���� (data.length) / 2 - 1 ��Ԫ���к��ӽ�㣬������Щ��㡣
		// *���������ͼ�У�������10��Ԫ�أ� (data.length) / 2 - 1��ֵΪ4��a[4]�к��ӽ�㣬��a[5]û��*
        for (int i = (data.length) / 2 - 1; i >= 0; i--)  // 4 3 2 1 0
        {
        	// ���к��ӽ���Ԫ��heapify
            heapify(i);
        }
    }
	
	private void heapify(int i)
	{
		// ��ȡ���ҽ��������±�
        int l = left(i);  
        int r = right(i);
        
        // ����һ����ʱ��������ʾ ����㡢���㡢�ҽ������С��ֵ�Ľ����±�
        int smallest = i;
        
        // �������㣬�������ֵС�ڸ�����ֵ
        if (l < data.length && data[l] < data[i])  
        	smallest = l;  
        
        // �����ҽ�㣬���ҽ���ֵС�����ϱȽϵĽ�Сֵ
        if (r < data.length && data[r] < data[smallest])  
        	smallest = r;  
        
        // ���ҽ���ֵ�����ڸ��ڵ㣬ֱ��return�������κβ���
        if (i == smallest)  
            return;  
        
        // �������ڵ�����ҽ������С���Ǹ�ֵ���Ѹ��ڵ��ֵ�滻��ȥ
        swap(i, smallest);
        
        // �����滻�����������ᱻӰ�죬����Ҫ����Ӱ��������ٽ���heapify
        heapify(smallest);
    }
	
	// ��ȡ�ҽ��������±�
	private int right(int i)
	{  
        return (i + 1) << 1;  
    }   

	// ��ȡ����������±�
    private int left(int i) 
    {  
        return ((i + 1) << 1) - 1;  
    }
    
    // ����Ԫ��λ��
    private void swap(int i, int j) 
    {  
        int tmp = data[i];  
        data[i] = data[j];  
        data[j] = tmp;  
    }
    
    // ��ȡ���е���С��Ԫ�أ���Ԫ��
    public int getRoot()
    {
    	    return data[0];
    }

    // �滻��Ԫ�أ�������heapify
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
