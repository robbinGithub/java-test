package robbin.java.collection;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * @author robbin.zhang
 * 
 * 三、fail-fast解决办法
        通过前面的实例、源码分析，我想各位已经基本了解了fail-fast的机制，下面我就产生的原因提出解决方案。这里有两种解决方案：
        
        方案一：在遍历过程中所有涉及到改变modCount值得地方全部加上synchronized或者直接使用Collections.synchronizedList，这样就可以解决。但是不推荐，
                       因为增删造成的同步锁可能会阻塞遍历操作。
                       
        方案二：使用CopyOnWriteArrayList来替换ArrayList。推荐使用该方案。
        CopyOnWriteArrayList为何物？ArrayList 的一个线程安全的变体，其中所有可变操作（add、set 等等）
                       都是通过对底层数组进行一次新的复制来实现的。 该类产生的开销比较大，但是在两种情况下，它非常适合使用。
                       
       1：在不能或不想进行同步遍历，但又需要从并发线程中排除冲突时。
       2：当遍历操作的数量大大超过可变操作的数量时。遇到这两种情况使用CopyOnWriteArrayList来替代ArrayList再适合不过了。
                              那么为什么CopyOnWriterArrayList可以替代ArrayList呢？
	                              
	        第一、CopyOnWriterArrayList的无论是从数据结构、定义都和ArrayList一样。它和ArrayList一样，同样是实现List接口，底层使用数组实现。
	                  在方法上也包含add、remove、clear、iterator等方法。
	        第二、CopyOnWriterArrayList根本就不会产生ConcurrentModificationException异常，也就是它使用迭代器完全不会产生fail-fast机制。请看：
        
 * @see http://blog.csdn.net/chenssy/article/details/37521461
 */
public class IteratorTest {
	
	@Test
	public void test_01(){
		
		List<String> list = new ArrayList<String>();
        for (int i = 0; i < 10; i++) {
            String str = i + "";
            list.add(str);
        }
        java.util.Iterator it = list.iterator();
        for (int i = 0; i < 5; i++) {
            System.out.println((String) it.next());
        } 
        it.remove(); //  void remove()：删除迭代器刚越过的元素
        System.out.println("////////////////////////");
        it = list.iterator();
        while (it.hasNext()) {
            System.out.println((String) it.next());
        }
        
  /*    0
        1
        2
        3
        4
        ////////////////////////
        0
        1
        2
        3
        5
        6
        7
        8
        9*/

        
	}

}
