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
	 * 
	 * 十进制的0.1，写成二进制是0.1,相当于1 × 2^-1
	 * 十进制的5.0，写成二进制是101.0，相当于1.01×2^2。那么，按照上面V的格式，可以得出s=0，M(尾数)=1.01，E(指数位)=2。
	 */
	@Test
	public void test_03(){
		float a = 0.1f; //
		float b = 5.0f;
		System.out.println(Integer.toBinaryString(Float.floatToIntBits(a)));
		System.out.println(Integer.toBinaryString(Float.floatToIntBits(b)));
		// 00111101 11001100 11001100 11001101
		// 01000000 10100000 00000000 00000000
		
		// 1bit（符号位）  0
		// 8bits（指数位） 001111011
		// 23bits（尾数位） 1001100 11001100 11001101    
		
		double d = 5.0; // 101.0 ==>> 1.01×2^2  
		System.out.println(Long.toBinaryString(Double.doubleToLongBits(d)));
		//01000000 00010100 00000000 00000000 00000000 00000000 00000000 00000000
		
		
		// 12.5
		// 整数部分12，二进制为1100; 小数部分0.5, 二进制是.1，先把他们连起来，从第一个1数起取24位（后面补0）：1100.10000000000000000000
		System.out.println("====================Double.doubleToLongBits(12.5))=================================");
		System.out.println(Long.toBinaryString(Double.doubleToLongBits(12.5)));
		// 100000000101001000000000000000000000000000000000000000000000000
		
		
		/**
		 * 分析107.1597824f
		 * 进制转换工具地址：http://tool.oschina.net/hexconvert/
		 */
		System.out.println("====================Float.floatToIntBits(107.1597824f)=================================");
		System.out.println(Integer.toBinaryString(Float.floatToIntBits(107.1597824f)));
		
		// 1101011.0010100011100111011111111101011001111001111101
		
		// 1101011.00101000111001111
		// 符号位：0
		// 指数位为： 127 + 6 = 133 即十进制133 10000101
		// 尾数位为：10101100101000111001111
		// 01000010 11010110 01010001 11001111
		
		
		float f1 = 423.1594f;
        float f2 = -423.1594f;
        int floatToIntBits1 = Float.floatToIntBits(f1);// 根据IEEE754规则，得到浮点的表示值。
        int floatToIntBits2 = Float.floatToIntBits(f2);
 
        System.out.println("正float===" + Integer.toBinaryString(floatToIntBits1));// 转二进制
        System.out.println("负float===" + Integer.toBinaryString(floatToIntBits2));
        System.out.println("正float===" + Integer.toHexString(floatToIntBits1));// 转十六进制
        System.out.println("负float===" + Integer.toHexString(floatToIntBits2));
        
		
		double d1 = 423453.1597824345;
        double d2 = -423453.1597824345;
        long doubleToLongBits1 = Double.doubleToLongBits(d1);// 根据IEEE754规则，得到浮点的表示值。
        long doubleToLongBits2 = Double.doubleToLongBits(d2);
 
        System.out.println("正double===" + Long.toBinaryString(doubleToLongBits1));// 转二进制
        System.out.println("负double===" + Long.toBinaryString(doubleToLongBits2));
        System.out.println("正double===" + Long.toHexString(doubleToLongBits1));// 转十六进制
        System.out.println("负double===" + Long.toHexString(doubleToLongBits2));
        
  /*    正double===100000100011001110110000111010010100011100111100000000110101011
		负double===1100000100011001110110000111010010100011100111100000000110101011
		正double===4119d874a39e01ab
		负double===c119d874a39e01ab
		
		*/
		
	}
}
