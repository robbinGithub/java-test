package robbin.java.collection;

import java.util.IdentityHashMap;

/**
 * IdentityHashMap又是一个我不明白会用在什么场合的类，用法了解一下吧，
 * 它是一个特殊的Map实现，它要求 两个key严格相等时才认为两个key相等
 * @author robbin.zhang
 * @date 2016/12/29 14:38
 *
 */
public class IdentityHashMapTest {

	public static void main(String[] args) 
	{
		IdentityHashMap ihm = new IdentityHashMap();
		//下面两行代码向IdentityHashMap对象添加两个key-value对
		ihm.put(new String("语文"),89);
		ihm.put(new String("语文"),78);
        //下面两行代码只会向IdentityHashMap对象添加一个key-value对
		ihm.put("java",93);
		ihm.put("java",98);
		System.out.println(ihm);
		
//   	 {java=98, 语文=78, 语文=89}
//	              前两个key-value对中，key是新创始的字符串对象，通过 == 比较不相等，
//	              后两个key-value对中，key是字符串直接量，而且它们的字节序列完全相同，通过 == 比较返回True，IdentityHashMap会认为它同一个KEY
	}
}