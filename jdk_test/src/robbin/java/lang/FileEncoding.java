package robbin.java.lang;

/**
 *  ��һ�����������ñ༭����дjavaԴ�ļ��������ļ��ڱ���ʱ����ò���ϵͳĬ�ϵı����ʽ��һ���������ĵĲ���ϵͳ���õ���GBK�����ʽ���γ�һ��.java�ļ���javaԴ�ļ��ǲ��ò���ϵͳĬ��֧�ֵ�file.encoding�����ʽ����ġ����������Բ鿴ϵͳ��file.encoding����ֵ��
		 System.out.println(System.getProperty("file.encoding"));  
	
	�ڶ�����������ʹ��javac.exe�������ǵ�java�ļ�ʱ��JDK���Ȼ�ȷ�����ı������encoding��ȷ��Դ�����ַ�����
	             ������ǲ�ָ���ñ��������JDK���Ȼ��ȡ����ϵͳĬ�ϵ�file.encoding������
	             Ȼ��JDK�ͻ�����Ǳ�д��javaԴ�����file.encoding�����ʽת��ΪJAVA�ڲ�Ĭ�ϵ�UNICODE��ʽ�����ڴ��С�
	             
	��������JDK���������õ��ұ������ڴ�����Ϣд��class�ļ��У��γ�.class�ļ�����ʱ.class�ļ���Unicode����ģ�
	           Ҳ����˵���ǳ�����.class�ļ��е����������������ַ�����Ӣ���ַ������Ƕ��Ѿ�ת��ΪUnicode�����ʽ�ˡ�
		����һ���жԶ�JSPԴ�ļ��Ĵ���ʽ�е����ͬ��WEB��������JSP��������JSP���������Ȼ�鿴JSP�ļ��Ƿ��������ļ������ʽ��
		���û��������JSP����������õ���JDK����Ĭ�ϵı��뷽ʽ��JSP�ļ�ת��Ϊ��ʱ��servlet�࣬Ȼ���ٱ���Ϊ.class�ļ������ֵ���ʱ�ļ����С�
		
	���Ĳ������б�����ࣺ����������һ�¼������
		1��ֱ����console�����С�
		2��JSP/Servlet�ࡣ
		3��java�������ݿ�֮�䡣
		
	���������ÿ������ķ�ʽ���᲻ͬ��
	
 * @author robbin.zhang
 * @see http://blog.csdn.net/chenssy/article/details/42672219
 * @see http://blog.csdn.net/chenssy/article/details/42913631
 * @see http://blog.csdn.net/chenssy/article/details/42963595
 * @see http://blog.csdn.net/chenssy/article/details/43304091
 *  Ϊʲô��ҪJS�ﳣ��Ҫ������encodeURI(url)
 * @see http://blog.csdn.net/posonrick/article/details/51542991
 *
 */
public class FileEncoding {
	public static void main(String[] args) {
		System.out.println(System.getProperty("file.encoding"));  
	}

}
