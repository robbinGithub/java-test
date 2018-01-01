package robbin.java.generic;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

/**
 * ��?������δ֪����
 * 
 * extends�ؼ������������͵��Ͻ磬��ʾ�����������Ϳ�������ָ�������ͣ������Ǵ����͵����࣬����add
 * 
 * super�ؼ������������͵��½磬��ʾ�����������Ϳ�������ָ�������ͣ������Ǵ����͵ĸ����ͣ�ֱ��Object
 * 
 * С��һ�¾��ǣ��Ͻ�add�������ޣ��½� get�������ޡ�
 * 
 * 
 * @author robbin.zhang
 * @see http://blog.csdn.net/daniel_h1986/article/details/5708605
 * 
 */
public class GenericTest {
	
	/**
	 * extends�ؼ������������͵ġ��Ͻ硿
	 */
	@Test
	public void testUpperBound()  
	{  
	    List<Timestamp> list = new ArrayList<Timestamp>();  
	    Date date = new Date();  
	    upperBound(list,date);  
	}
	
	/**
	 * List<? extends Date> list :Date��Date�����࣬���塾�Ͻ硿 add���ޣ�get->һ����Date���� 
	 * @param list
	 * @param date
	 */
	public void upperBound(List<? extends Date> list, Date date)  
	{  
	    Date now = list.get(0);  
	    System.out.println("now==>" + now);  
	    
	    //list.add(date); //��仰�޷����� ��
		/*
		 * �޷������ԭ������ڣ�ʵ�ʵ���ʱ�����list������java.util.Date��ĳ������Ĳ��������ͣ���:
		 * Ҳ����˵��upperBound������ʵ�ʵ�list��List<Timestamp>���������һ������Date���ͣ�
		 * ���ڻ����޷�ת��Ϊ���࣬
		 * �����޷���ӡ��෴����ȡ����ʱ������ʵ�ʵ�list��ʲô���ͣ�������֪�������ٻ᷵��һ��Date���ͣ�������foreach
		 * ��get��û�����⡣
		 */
	    
	    list.add(null);//�����Ա��룬��Ϊnullû��������Ϣ  
	}
	
	/**
	 * super�ؼ������������͵ġ��½硿
	 * @param list
	 */
	@Test
	public void testLowerBound()  
	{  
	    List<Date> list = new ArrayList<Date>();  
	    list.add(new Date());  
	    lowerBound(list);  
	} 
	
	/**
	 * List<? super Timestamp> list:Timestamp��Timestamp�ĸ���,���塾�½硿��get����
	 * @param list
	 */
	@Test
	public void lowerBound(List<? super Timestamp> list)  
	{  
	    Timestamp now = new Timestamp(System.currentTimeMillis()); 
	    // ���ϸ������඼��add
	    list.add(now);  
	    
	    //Timestamp time = list.get(0); //���ܱ���  
	    
		/*
		 * ��lowerBound�����е�List<? super
		 * Timestamp>��ʾ���list�Ĳ������Ϳ�����Timestamp��Timestamp�ĸ���
		 * �������Ĵ����ʵ�ʴ������һ��List<Date>���͡�
		 * List<Date>�п������һ��Timestamp���󣬵�list.get()
		 * �������صĶ������Ϳ�����Date������Object�����ܰ�ȫ������ת����Timestamp��Ҳ������޷������ˡ�
		 */
	}  

}
