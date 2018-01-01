package com.robbin.algorithm.sort;
/**
 * 
 * @author robbin
 * @see http://www.jb51.net/article/86163.htm
 *
 */
public class HeapSort {
	   
	  /** 
	   * ����ΪС���ѣ��������Ϊ�Ӵ�С��
	   * 
	   * @param array�Ǵ������Ķ����� 
	   * @param s�Ǵ�����������Ԫ�ص�λ��
	   * @param length������ĳ���
	   * 
	   */
	  public static void heapAdjustS(int[] array, int s, int length) {
	    int tmp = array[s];
	    int child = 2 * s + 1;// ���ӽ���λ��
	    System.out.println("���������Ϊ��array[" + s + "] = " + tmp);
	    while (child < length) {
	      // child + 1 �ǵ�ǰ���������Һ���
	      // ������Һ�����С�����ӣ�ʹ���Һ���������бȽϣ�����ʹ������
	      if (child + 1 < length && array[child] > array[child + 1]) {
	        child++;
	      }
	      System.out.println("�����Ӻ��� array[" + child + "] = " + array[child] + " ���бȽ�");
	      // �����С���Ӻ��ӱȴ˽��С
	      if (array[s] > array[child]) {
	        System.out.println("�Ӻ��ӱ���С������λ��");
	        array[s] = array[child];// �ѽ�С���Ӻ��������ƶ����滻��ǰ���������
	        s = child;// ����������ƶ�����С�Ӻ���ԭ����λ��
	        array[child] = tmp;
	        child = 2 * s + 1;// �����жϴ���������Ƿ���Ҫ��������
	         
	        if (child >= length) {
	          System.out.println("û���Ӻ����ˣ���������");
	        } else {
	          System.out.println("�������µ��Ӻ��ӽ��бȽ�");
	        }
	        // continue;
	      } else {
	        System.out.println("�Ӻ��Ӿ�����󣬵�������");
	        break;// ��ǰ���������С���������Һ��ӣ����������ֱ���˳�
	      }
	    }
	  }
	   
	  /** 
	   * ����Ϊ�󶥶ѣ��������Ϊ��С����
	   * 
	   * @param array�Ǵ������Ķ����� 
	   * @param s�Ǵ�����������Ԫ�ص�λ��
	   * @param length������ĳ���
	   * 
	   */
	  public static void heapAdjustB(int[] array, int s, int length) {
	    int tmp = array[s];
	    int child = 2 * s + 1;// ���ӽ���λ��
	    System.out.println("���������Ϊ��array[" + s + "] = " + tmp);
	    while (child < length) {
	      // child + 1 �ǵ�ǰ���������Һ���
	      // ������Һ����Ҵ������ӣ�ʹ���Һ���������бȽϣ�����ʹ������
	      if (child + 1 < length && array[child] < array[child + 1]) {
	        child++;
	      }
	      System.out.println("�����Ӻ��� array[" + child + "] = " + array[child] + " ���бȽ�");
	      // ����ϴ���Ӻ��ӱȴ˽���
	      if (array[s] < array[child]) {
	        System.out.println("�Ӻ��ӱ���󣬽���λ��");
	        array[s] = array[child];// �ѽϴ���Ӻ��������ƶ����滻��ǰ���������
	        s = child;// ����������ƶ����ϴ��Ӻ���ԭ����λ��
	        array[child] = tmp;
	        child = 2 * s + 1;// �����жϴ���������Ƿ���Ҫ��������
	         
	        if (child >= length) {
	          System.out.println("û���Ӻ����ˣ���������");
	        } else {
	          System.out.println("�������µ��Ӻ��ӽ��бȽ�");
	        }
	        // continue;
	      } else {
	        System.out.println("�Ӻ��Ӿ�����С����������");
	        break;// ��ǰ�������������������Һ��ӣ����������ֱ���˳�
	      }
	    }
	  }
	    
	  /**
	   * �������㷨
	   * 
	   * @param array
	   * @param inverse true Ϊ�������У�false Ϊ��������
	   */
	  public static void heapSort(int[] array, boolean inverse) {
	    // ��ʼ��
	    // ���һ���к��ӵĽ��λ�� i = (length - 1) / 2, �Դ����ϵ��������ʹ����϶�
	    System.out.println("��ʼ�ѿ�ʼ");
	    // (9-1)/2 = 4
	    // 4  3  2 1 0 
	    // 
	    for (int i = (array.length - 1) / 2; i >= 0; i--) {
	      if (inverse) {
	        heapAdjustS(array, i, array.length);
	      } else {
	        heapAdjustB(array, i, array.length);
	      }
	    }
	    System.out.println("��ʼ�ѽ���");
	    for (int i = array.length - 1; i > 0; i--) {
	      // �����Ѷ�Ԫ��H[0]�Ͷ������һ��Ԫ��
	      int tmp = array[i];
	      array[i] = array[0];
	      array[0] = tmp;
	      // ÿ�ν����Ѷ�Ԫ�غͶ������һ��Ԫ��֮�󣬶�Ҫ�Զѽ��е���
	      if (inverse) {
	        heapAdjustS(array, 0, i);
	      } else {
	        heapAdjustB(array, 0, i);
	      }
	    }
	  }
	 
	  public static void main(String[] args) {
	    int[] array = { 49, 38, 65, 97, 76, 13, 27, 49 };
	    heapSort(array, false);
	    for (int i : array) {
	      System.out.print(i + " ");
	    }
	  }
	 
	}