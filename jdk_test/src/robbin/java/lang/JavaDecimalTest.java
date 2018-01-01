package robbin.java.lang;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import org.junit.Test;

/**
 * 
 * @author robbin.zhang
 * 
 * @see http://blog.csdn.net/wangjun5159/article/details/51362588
 * 
 */
public class JavaDecimalTest {
  
	@Test
	public void test_01(){
		
		double d2 = 0.125;

		System.out.println("============Double.toHexString:" + d2 + "============");
		System.out.println(Double.toHexString(d2));
		
		/**
		 * �������ԭ����ЩС���޷��ö����ƾ�ȷ��ʾ������0.1�ö����Ʊ�ʾʱ��������ѭ���������ڴ����ޣ�ֻ�ܽ��ƴ洢0.1�������ڴ��е�0.1��0.1�Ľ���ֵ��
		 */
		System.out.println("============0.1 + 0.2)============");
		System.out.println(0.1 + 0.2); // 0.30000000000000004
		
		System.out.println("============1.0 - 0.66)============");
		System.out.println(1.0 - 0.66); // 0.33999999999999997

		System.out.println(0.06 + 0.01);
		System.out.println(1.0 - 0.42);
		System.out.println(4.015 * 100);
		System.out.println(303.1 / 1000);
	}
	
	/**
	 * 0.1��0.2��0.5���ڴ����Ƕ���

		��������BigDecimal(double)�������鿴0.1��0.2��0.5���ڴ��е�ֵ�����Կ�������0.1,0.2�޷��ö����ƾ�ȷ��ʾ��
		���ڴ��ж��ǽ���ֵ����0.5��1/2�������ö����ƾ�ȷ��ʾ���Դ洢���Ǿ�ȷֵ��
		
		BigDecimal(double val) 
		�� double ת��Ϊ BigDecimal�������� double �Ķ����Ƹ���ֵ׼ȷ��ʮ���Ʊ�ʾ��ʽ��
	 */
	@Test
	public void test_02(){

		System.out.println(new BigDecimal(0.1));
		System.out.println(new BigDecimal(0.2));
		System.out.println(new BigDecimal(0.2).add(new BigDecimal(0.1)));
		System.out.println(new BigDecimal(0.5));
		
		/*
		 0.1000000000000000055511151231257827021181583404541015625
		 0.200000000000000011102230246251565404236316680908203125
		 0.3000000000000000166533453693773481063544750213623046875
		 0.5
		*/
	}
	
	/**
	 * ����ľ�ȷ����
	 * ΪʲôDouble.toString(0.1)��0.1��
	 * Double.toString(0.1)ֻ�ǡ������˲���С��λ����ǡ��0.1��ߺܶ�С��λ����0�����Դ�ӡ������0.1��
	 * ����Double.toString(double d)�����淶��û����ȷָ������С��λ���٣�
	 * ���ԣ���Ȼ��sun jdk��Double.toString(0.1)��0.1��Ҳ��һ��JDK(����IBM��jdk)Ҳ�����0.10000000000000000555111512���ˡ� 
	 * ���ԣ��漰����ȷ�����һ����BigDecimal��������double��
	 *        
	 * ���ܽ᡿
	 *	�漰����ȷ����ĵط����������Ҫ��BigDecimal����rest�ӿ��У���BigDecimal������ֵ��
	 *	ĳЩ��ѧ���㣬�絶Ƭ��0.1���ף�100����Ƭ����٣�����ʹ�ø���������Ϊʵ�������һ����Ƭ��0.1���ף�100����Ƭ��ȷ��һ������100*0.1����������ʹ�ø������Ǻ���ġ�
	 */
	@Test
	public void test_03(){
		
		// Wrong 
//		BigDecimal b1 = new BigDecimal(0.1);  
//		BigDecimal b2 = new BigDecimal(Double.toString(0.1));
		
		System.out.println(new BigDecimal("2.0").subtract(new BigDecimal("1.10")));// 0.9
	
	}
	
	/**
	 * 
	 * ������������
	 * 
	 * 
	 * �������9.999999999999Ԫ����ļ�����ǲ�����Ϊ����Թ���10Ԫ����Ʒ�ġ�
		���еı���������ṩ��ר�ŵĻ��������������������������Javaû�С����������ǿ�����ν��������⡣
		
		��������
		���ǵĵ�һ����Ӧ�����������롣Math���е�round�����������ñ�����λС��������ֻ����������������λ����
		public double round(double value){
		    return Math.round(value*100)/100.0;
		}
		�ǳ����ң�����Ĵ��벢���������������������������4.015��������4.01������4.02�������������濴����
		4.015*100=401.49999999999994
	 */
	@Test
	public void test_04() { 
		// ʮ����   4.015*100=401.5  round(401.5)/100 = 402/100 =  4.02
		// ������   4.015*100=401.49999999999994 round(401.49999999999994)/100 = 401/100 = 4.01
		System.out.println(round(4.015)); 
	}

	public double round(double value) {
		return Math.round(value * 100) / 100.0;
	}
	
	
	/**
	 * JAVA������λС��
	 */
	@Test
	public void test_05() { 
		
		DecimalFormat df = new DecimalFormat("######0.00");
		double d1 = 3.23456;
		double d2 = 0.0;
		double d3 = 2.0;
		df.format(d1);
		df.format(d2);
		df.format(d3);
		
		// ��ʽһ
		double f = 111231.5585;
		BigDecimal b = new BigDecimal(f);
		// �ɷ������Ѿ������Ƽ�ʹ��
		double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		// �·������������Ҫ�������룬����ʹ��RoundingMode.DOWN
		BigDecimal f2 = b.setScale(2, RoundingMode.UP);
		System.out.println("��ʽһ��" + f2);

		// ��ʽ��

		/*
		 * java.text.DecimalFormat df =new java.text.DecimalFormat("#.00");
		 * df.format(��Ҫ��ʽ��������)
		 */

		String format = new java.text.DecimalFormat("#.00").format(3.1455926);
		System.out.println("��ʽ����" + format);
		// #.00 ��ʾ��λС�� #.0000��λС�� �Դ�����...

		// ��ʽ��
		double d = 3.1455926;
		String result = String.format("%.2f", d);
		System.out.println("��ʽ����" + result);
		// %.2f %. ��ʾ С����ǰ����λ�� 2 ��ʾ��λС�� ��ʽ��Ľ��Ϊf ��ʾ������

		// ��ʽ��
		double x = 23.5455;
		NumberFormat ddf1 = NumberFormat.getNumberInstance();
		ddf1.setMaximumFractionDigits(2);
		String s = ddf1.format(x);
		System.out.println("��ʽ�ģ�" + s);

		// ��ʽһ��111231.56
		// ��ʽ����3.15
		// ��ʽ����3.15
		// ��ʽ�ģ�23.55

	}

}
