package robbin.java.lang;

import java.math.BigDecimal;

import org.junit.Test;

/**
 * Java商业计算精度问题
 * @author robbin.zhang
 * @see http://www.w2bc.com/article/202564
 */
public class BigDecimalTest {
	
	/**
	 * 利用double作为参数的构造函数，无法精确构造一个BigDecimal对象，需要自己指定一个上下文的环境，也就是指定精确位。
	 * 而利用String对象作为参数传入的构造函数能精确的构造出一个BigDecimal对象。
	 */
	@Test
	public void test_01(){
	
		double a = 1;
		double b = 0.9;
		BigDecimal a1 = new BigDecimal(Double.toString(a));
		BigDecimal b1 = new BigDecimal(Double.toString(b));
		BigDecimal a2 = new BigDecimal(a);
		BigDecimal b2 = new BigDecimal(b);
		double c = a1.subtract(b1).doubleValue();
		double d = a2.subtract(b2).doubleValue();
		System.out.println("c="+c); // c=0.1
		System.out.println("d="+d); // d=0.09999999999999998
	}
	
	/**
	 * 浮点类型的比较double float比较
	 * 由于精度问题，double/float比较相等也不能直接使用==，但是比较大小可以用<、 >号
	 */
	@Test
	public void test_02(){
		double a = 0.01;
		double b = 0.5;
		System.out.println(a+b);
		System.out.println(a==b);
		
		double d1 = 0.1, d2 = 0.1;  
		if (d1 == d2) { System.out.println("bad");}// 坏代码  
		if (Double.compare(d1, d2) == 0) {}// 好代码  
		if (Double.doubleToLongBits(d1) == Double.doubleToLongBits(d2)) {}// 好代码  
		if (Double.valueOf(d1).equals(d2)) {}// 好代码，1.5以上  
	}
	
	/**
	 * 0.1的二进制表示
	 * 1/2 = 0.5
	 * 1/4 = 0.25
	 * 1/8 = 0.125
	 * 1/16 = 0.0625
	 * 1/32 = 0.03125
	 * 1/64 = 0.015625
	 * 0.1(十进制) = 0.0001100110011001(二进制)
	 */
	@Test
	public void test_03(){
		float a = 0.1f;
		System.out.println(Integer.toBinaryString(Float.floatToIntBits(a)));
		// 00111101 11001100 11001100 11001101
	}
}
