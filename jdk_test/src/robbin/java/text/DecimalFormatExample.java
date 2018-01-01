package robbin.java.text;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * DecimalFormat usage
 * @author robbin.zhang
 * @date 2016/11/15 16:12
 * @description DecimalFormat 是 NumberFormat 的一个具体子类，用于格式化十进制数字
 * 
 * 符号含义： 

		0 一个数字 
		
		# 一个数字，不包括 0 
		
		. 小数的分隔符的占位符 
		
		, 分组分隔符的占位符 
		
		; 分隔格式。 
		
		- 缺省负数前缀。 
		
		% 乘以 100 和作为百分比显示 
		
		? 乘以 1000 和作为千进制货币符显示；用货币符号代替；如果双写，用 
		
		国际货币符号代替。如果出现在一个模式中，用货币十进制分隔符代 
		
		替十进制分隔符。 
		
		X 前缀或后缀中使用的任何其它字符，用来引用前缀或后缀中的特殊字符。 
		
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
		
		DecimalFormat df5 = new DecimalFormat("#########0.##"); // 保留两位小数12.8 0.8
		
		DecimalFormat df6 = new DecimalFormat("##########.00"); // 保留两位小数 12.8 .80
		
		DecimalFormat df7 = new DecimalFormat("#########0.00"); // 保留两位小数 12.80 0.80 (四舍五入)

		System.out.println(df1.format(12.34)); 

		System.out.println(df2.format(12.34)); 

		System.out.println(df3.format(12.34)); 

		System.out.println(df4.format(12.34)); 
		
		System.out.println(df5.format(2994.8));
		
		System.out.println(df6.format(0.8));
		
		System.out.println(df7.format(12.868));
	}
	
	/**
	 * 四舍五入保留两位小数
	 */
	public void test_02(){
		
		DecimalFormat f = new DecimalFormat("#########0.00 ");  // #0.00# 
		f.setRoundingMode(RoundingMode.HALF_UP);  
		System.out.println(f.format(123563.875)); // 123456789.83
	}
	
	/**
	 * 四舍五入保留一位小数
	 */
	public void test_04(){
		DecimalFormat df = new DecimalFormat("########0.#"); 
		df.setRoundingMode(RoundingMode.HALF_UP);  
		System.out.println(df.format(123456789.83)); // 123456789.83
	}
	
	
	/**
	 * 金额以千位分隔
	 * 
	 * 分组分隔符通常用于千位，但是在某些国家/地区中它用于分隔万位。分组大小是分组字符之间的固定数字位数，
	 * 例如 100,000,000 是 3，而 1,0000,0000 则是 4。
	 * 如果使用具有多个分组字符的模式，则最后一个分隔符和整数结尾之间的间隔才是使用的分组大小。
	 * 所以",###,###"== "#,##,###,###" == "######,###" == "##,####,###"
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
	
	
	/** *转换方法 */

	public String parseMoney(String pattern,BigDecimal bd){
	    DecimalFormat df=new DecimalFormat(pattern);
	    return df.format(bd);
	}
}
	
