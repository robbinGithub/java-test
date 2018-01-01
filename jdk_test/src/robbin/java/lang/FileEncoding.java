package robbin.java.lang;

/**
 *  第一步：当我们用编辑器编写java源文件，程序文件在保存时会采用操作系统默认的编码格式（一般我们中文的操作系统采用的是GBK编码格式）形成一个.java文件。java源文件是采用操作系统默认支持的file.encoding编码格式保存的。下面代码可以查看系统的file.encoding参数值。
		 System.out.println(System.getProperty("file.encoding"));  
	
	第二步：当我们使用javac.exe编译我们的java文件时，JDK首先会确认它的编译参数encoding来确定源代码字符集，
	             如果我们不指定该编译参数，JDK首先会获取操作系统默认的file.encoding参数，
	             然后JDK就会把我们编写的java源程序从file.encoding编码格式转化为JAVA内部默认的UNICODE格式放入内存中。
	             
	第三步：JDK将上面编译好的且保存在内存中信息写入class文件中，形成.class文件。此时.class文件是Unicode编码的，
	           也就是说我们常见的.class文件中的内容无论是中文字符还是英文字符，他们都已经转换为Unicode编码格式了。
		在这一步中对对JSP源文件的处理方式有点儿不同：WEB容器调用JSP编译器，JSP编译器首先会查看JSP文件是否设置了文件编码格式，
		如果没有设置则JSP编译器会调用调用JDK采用默认的编码方式将JSP文件转化为临时的servlet类，然后再编译为.class文件并保持到临时文件夹中。
		
	第四步：运行编译的类：在这里会存在一下几种情况
		1、直接在console上运行。
		2、JSP/Servlet类。
		3、java类与数据库之间。
		
	这三种情况每种情况的方式都会不同，
	
 * @author robbin.zhang
 * @see http://blog.csdn.net/chenssy/article/details/42672219
 * @see http://blog.csdn.net/chenssy/article/details/42913631
 * @see http://blog.csdn.net/chenssy/article/details/42963595
 * @see http://blog.csdn.net/chenssy/article/details/43304091
 *  为什么需要JS里常需要做两次encodeURI(url)
 * @see http://blog.csdn.net/posonrick/article/details/51542991
 *
 */
public class FileEncoding {
	public static void main(String[] args) {
		System.out.println(System.getProperty("file.encoding"));  
	}

}
