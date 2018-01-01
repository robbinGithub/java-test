package robbin.java.collection;

import java.util.IdentityHashMap;

/**
 * IdentityHashMap����һ���Ҳ����׻�����ʲô���ϵ��࣬�÷��˽�һ�°ɣ�
 * ����һ�������Mapʵ�֣���Ҫ�� ����key�ϸ����ʱ����Ϊ����key���
 * @author robbin.zhang
 * @date 2016/12/29 14:38
 *
 */
public class IdentityHashMapTest {

	public static void main(String[] args) 
	{
		IdentityHashMap ihm = new IdentityHashMap();
		//�������д�����IdentityHashMap�����������key-value��
		ihm.put(new String("����"),89);
		ihm.put(new String("����"),78);
        //�������д���ֻ����IdentityHashMap�������һ��key-value��
		ihm.put("java",93);
		ihm.put("java",98);
		System.out.println(ihm);
		
//   	 {java=98, ����=78, ����=89}
//	              ǰ����key-value���У�key���´�ʼ���ַ�������ͨ�� == �Ƚϲ���ȣ�
//	              ������key-value���У�key���ַ���ֱ�������������ǵ��ֽ�������ȫ��ͬ��ͨ�� == �ȽϷ���True��IdentityHashMap����Ϊ��ͬһ��KEY
	}
}