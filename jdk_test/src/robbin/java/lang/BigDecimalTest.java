package robbin.java.lang;

import java.math.BigDecimal;

import org.junit.Test;

/**
 * Java��ҵ���㾫������
 * @author robbin.zhang
 * @see http://www.w2bc.com/article/202564
 */
public class BigDecimalTest {
	
	/**
	 * ����double��Ϊ�����Ĺ��캯�����޷���ȷ����һ��BigDecimal������Ҫ�Լ�ָ��һ�������ĵĻ�����Ҳ����ָ����ȷλ��
	 * ������String������Ϊ��������Ĺ��캯���ܾ�ȷ�Ĺ����һ��BigDecimal����
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
	 * �������͵ıȽ�double float�Ƚ�
	 * ���ھ������⣬double/float�Ƚ����Ҳ����ֱ��ʹ��==�����ǱȽϴ�С������<�� >��
	 */
	@Test
	public void test_02(){
		double a = 0.01;
		double b = 0.5;
		System.out.println(a+b);
		System.out.println(a==b);
		
		double d1 = 0.1, d2 = 0.1;  
		if (d1 == d2) { System.out.println("bad");}// ������  
		if (Double.compare(d1, d2) == 0) {}// �ô���  
		if (Double.doubleToLongBits(d1) == Double.doubleToLongBits(d2)) {}// �ô���  
		if (Double.valueOf(d1).equals(d2)) {}// �ô��룬1.5����  
	}
	
	/**
	 * 0.1�Ķ����Ʊ�ʾ
	 * 1/2 = 0.5
	 * 1/4 = 0.25
	 * 1/8 = 0.125
	 * 1/16 = 0.0625
	 * 1/32 = 0.03125
	 * 1/64 = 0.015625
	 * 0.1(ʮ����) = 0.0001100110011001(������)
	 */
	@Test
	public void test_03(){
		float a = 0.1f;
		System.out.println(Integer.toBinaryString(Float.floatToIntBits(a)));
		// 00111101 11001100 11001100 11001101
	}
}
