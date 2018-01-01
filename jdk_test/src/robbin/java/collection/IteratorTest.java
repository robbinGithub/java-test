package robbin.java.collection;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * @author robbin.zhang
 * 
 * ����fail-fast����취
        ͨ��ǰ���ʵ����Դ������������λ�Ѿ������˽���fail-fast�Ļ��ƣ������ҾͲ�����ԭ�����������������������ֽ��������
        
        ����һ���ڱ��������������漰���ı�modCountֵ�õط�ȫ������synchronized����ֱ��ʹ��Collections.synchronizedList�������Ϳ��Խ�������ǲ��Ƽ���
                       ��Ϊ��ɾ��ɵ�ͬ�������ܻ���������������
                       
        ��������ʹ��CopyOnWriteArrayList���滻ArrayList���Ƽ�ʹ�ø÷�����
        CopyOnWriteArrayListΪ���ArrayList ��һ���̰߳�ȫ�ı��壬�������пɱ������add��set �ȵȣ�
                       ����ͨ���Եײ��������һ���µĸ�����ʵ�ֵġ� ��������Ŀ����Ƚϴ󣬵�������������£����ǳ��ʺ�ʹ�á�
                       
       1���ڲ��ܻ������ͬ��������������Ҫ�Ӳ����߳����ų���ͻʱ��
       2��������������������󳬹��ɱ����������ʱ���������������ʹ��CopyOnWriteArrayList�����ArrayList���ʺϲ����ˡ�
                              ��ôΪʲôCopyOnWriterArrayList�������ArrayList�أ�
	                              
	        ��һ��CopyOnWriterArrayList�������Ǵ����ݽṹ�����嶼��ArrayListһ��������ArrayListһ����ͬ����ʵ��List�ӿڣ��ײ�ʹ������ʵ�֡�
	                  �ڷ�����Ҳ����add��remove��clear��iterator�ȷ�����
	        �ڶ���CopyOnWriterArrayList�����Ͳ������ConcurrentModificationException�쳣��Ҳ������ʹ�õ�������ȫ�������fail-fast���ơ��뿴��
        
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
        it.remove(); //  void remove()��ɾ����������Խ����Ԫ��
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
