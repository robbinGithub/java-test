package robbin.java.text;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * DecimalFormat usage
 * @author robbin.zhang
 * @date 2016/11/15 16:12
 * @description DecimalFormat �� NumberFormat ��һ���������࣬���ڸ�ʽ��ʮ��������
 * 
 * ���ź��壺 

		0 һ������ 
		
		# һ�����֣������� 0 
		
		. С���ķָ�����ռλ�� 
		
		, ����ָ�����ռλ�� 
		
		; �ָ���ʽ�� 
		
		- ȱʡ����ǰ׺�� 
		
		% ���� 100 ����Ϊ�ٷֱ���ʾ 
		
		? ���� 1000 ����Ϊǧ���ƻ��ҷ���ʾ���û��ҷ��Ŵ��棻���˫д���� 
		
		���ʻ��ҷ��Ŵ��档���������һ��ģʽ�У��û���ʮ���Ʒָ����� 
		
		��ʮ���Ʒָ����� 
		
		X ǰ׺���׺��ʹ�õ��κ������ַ�����������ǰ׺���׺�е������ַ��� 
		
		http://www.cnblogs.com/lsun/archive/2011/06/22/2087116.html
 */
public class DecimalFormatExample {
	
	public static void main(String[] args) {
		
		DecimalFormatExample e = new DecimalFormatExample();
//		e.test_01();
//		e.test_02();
//		e.test_03();
		e.test_04();
	}
	
	public void test_01(){
		
		DecimalFormat df1 = new DecimalFormat("0.0"); 

		DecimalFormat df2 = new DecimalFormat("#.#"); 

		DecimalFormat df3 = new DecimalFormat("000.000"); 

		DecimalFormat df4 = new DecimalFormat("###.###"); 
		
		DecimalFormat df5 = new DecimalFormat("#########0.##"); // ������λС��12.8 0.8
		
		DecimalFormat df6 = new DecimalFormat("##########.00"); // ������λС�� 12.8 .80
		
		DecimalFormat df7 = new DecimalFormat("#########0.00"); // ������λС�� 12.80 0.80 (��������)

		System.out.println(df1.format(12.34)); 

		System.out.println(df2.format(12.34)); 

		System.out.println(df3.format(12.34)); 

		System.out.println(df4.format(12.34)); 
		
		System.out.println(df5.format(2994.8));
		
		System.out.println(df6.format(0.8));
		
		System.out.println(df7.format(12.868));
	}
	
	/**
	 * �������뱣����λС��
	 */
	public void test_02(){
		
		DecimalFormat f = new DecimalFormat("#########0.00 ");  // #0.00# 
		f.setRoundingMode(RoundingMode.HALF_UP);  
		System.out.println(f.format(123563.875)); // 123456789.83
	}
	
	/**
	 * �������뱣��һλС��
	 */
	public void test_04(){
		DecimalFormat df = new DecimalFormat("########0.#"); 
		df.setRoundingMode(RoundingMode.HALF_UP);  
		System.out.println(df.format(123456789.83)); // 123456789.83
	}
	
	
	/**
	 * �����ǧλ�ָ�
	 * 
	 * ����ָ���ͨ������ǧλ��������ĳЩ����/�����������ڷָ���λ�������С�Ƿ����ַ�֮��Ĺ̶�����λ����
	 * ���� 100,000,000 �� 3���� 1,0000,0000 ���� 4��
	 * ���ʹ�þ��ж�������ַ���ģʽ�������һ���ָ�����������β֮��ļ������ʹ�õķ����С��
	 * ����",###,###"== "#,##,###,###" == "######,###" == "##,####,###"
	 * 
	 */
	public void test_03(){
		
		 DecimalFormatExample mf = new DecimalFormatExample();
		
		 BigDecimal bd = new BigDecimal(123456789);
		 
		 System.out.println(mf.parseMoney("##,####,##",bd)); //out: 123,456,789

	     System.out.println(mf.parseMoney("######,###",bd)); //out: 123,456,789

	     System.out.println(mf.parseMoney("#,##,###,###",bd)); //out: 123,456,789

	     System.out.println(mf.parseMoney(",###,###.00",bd)); //out: 123,456,789.00

	     System.out.println(mf.parseMoney(",###,##0.00",bd)); //out: 123,456,789.00
	     

	     BigDecimal bd2=new BigDecimal(0);
	     
	     System.out.println(mf.parseMoney(",###,###",bd2)); //out: 0

	     System.out.println(mf.parseMoney(",###,###.00",bd2)); //out: .00

	     System.out.println(mf.parseMoney(",###,##0.00",bd2)); //out: 0.00
	}
	
	// pattern="#0.00#"
	
	
	/** *ת������ */

	public String parseMoney(String pattern,BigDecimal bd){
	    DecimalFormat df=new DecimalFormat(pattern);
	    return df.format(bd);
	}
}
	
