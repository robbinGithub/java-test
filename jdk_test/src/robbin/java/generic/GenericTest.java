package robbin.java.generic;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

/**
 * “?”代表未知类型
 * 
 * extends关键字声明了类型的上界，表示参数化的类型可能是所指定的类型，或者是此类型的子类，不能add
 * 
 * super关键字声明了类型的下界，表示参数化的类型可能是所指定的类型，或者是此类型的父类型，直至Object
 * 
 * 小结一下就是：上界add方法受限，下界 get方法受限。
 * 
 * 
 * @author robbin.zhang
 * @see http://blog.csdn.net/daniel_h1986/article/details/5708605
 * 
 */
public class GenericTest {
	
	/**
	 * extends关键字声明了类型的【上界】
	 */
	@Test
	public void testUpperBound()  
	{  
	    List<Timestamp> list = new ArrayList<Timestamp>();  
	    Date date = new Date();  
	    upperBound(list,date);  
	}
	
	/**
	 * List<? extends Date> list :Date或Date的子类，定义【上界】 add受限，get->一定是Date类型 
	 * @param list
	 * @param date
	 */
	public void upperBound(List<? extends Date> list, Date date)  
	{  
	    Date now = list.get(0);  
	    System.out.println("now==>" + now);  
	    
	    //list.add(date); //这句话无法编译 ，
		/*
		 * 无法编译的原因就在于，实际调用时传入的list可能是java.util.Date的某个子类的参数化类型，如:
		 * 也就是说，upperBound方法中实际的list是List<Timestamp>，向它添加一个基类Date类型，
		 * 由于基类无法转换为子类，
		 * 所以无法添加。相反，读取数据时，不管实际的list是什么类型，但可以知道它至少会返回一个Date类型，所以用foreach
		 * ，get等没有问题。
		 */
	    
	    list.add(null);//这句可以编译，因为null没有类型信息  
	}
	
	/**
	 * super关键字声明了类型的【下界】
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
	 * List<? super Timestamp> list:Timestamp或Timestamp的父类,定义【下界】，get受限
	 * @param list
	 */
	@Test
	public void lowerBound(List<? super Timestamp> list)  
	{  
	    Timestamp now = new Timestamp(System.currentTimeMillis()); 
	    // 集合父类子类都可add
	    list.add(now);  
	    
	    //Timestamp time = list.get(0); //不能编译  
	    
		/*
		 * 在lowerBound方法中的List<? super
		 * Timestamp>表示这个list的参数类型可能是Timestamp或Timestamp的父类
		 * ，如后面的代码里，实际传入的是一个List<Date>类型。
		 * List<Date>中可以添加一个Timestamp对象，但list.get()
		 * 方法返回的对象类型可能是Date甚至是Object，不能安全的向下转换到Timestamp，也就因此无法编译了。
		 */
	}  

}
