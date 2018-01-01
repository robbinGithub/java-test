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
		 * 最根本的原因，有些小数无法用二进制精确表示，比如0.1用二进制表示时，产生了循环，由于内存有限，只能近似存储0.1，所以内存中的0.1是0.1的近似值。
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
	 * 0.1、0.2、0.5在内存中是多少

		这里利用BigDecimal(double)方法来查看0.1，0.2，0.5在内存中的值，可以看到由于0.1,0.2无法用二进制精确表示，
		在内存中都是近似值，而0.5是1/2，可以用二进制精确表示所以存储的是精确值。
		
		BigDecimal(double val) 
		将 double 转换为 BigDecimal，后者是 double 的二进制浮点值准确的十进制表示形式。
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
	 * 错误的精确运算
	 * 为什么Double.toString(0.1)是0.1？
	 * Double.toString(0.1)只是【保留了部分小数位】，恰巧0.1后边很多小数位都是0，所以打印出来是0.1。
	 * 并且Double.toString(double d)方法规范中没有明确指出保留小数位多少，
	 * 所以，虽然在sun jdk中Double.toString(0.1)是0.1，也许换一个JDK(比如IBM的jdk)也许就是0.10000000000000000555111512…了。 
	 * 所以，涉及到精确运算的一律用BigDecimal，不能用double！
	 *        
	 * 【总结】
	 *	涉及到精确计算的地方，比如金额，都要用BigDecimal；在rest接口中，用BigDecimal接收数值。
	 *	某些科学计算，如刀片厚0.1毫米，100个刀片厚多少，这种使用浮点数，因为实际情况中一个刀片是0.1毫米，100个刀片的确不一定等于100*0.1，所以这里使用浮点数是合理的。
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
	 * 四舍五入误区
	 * 
	 * 
	 * 如果你有9.999999999999元，你的计算机是不会认为你可以购买10元的商品的。
		在有的编程语言中提供了专门的货币类型来处理这种情况，但是Java没有。现在让我们看看如何解决这个问题。
		
		四舍五入
		我们的第一个反应是做四舍五入。Math类中的round方法不能设置保留几位小数，我们只能象这样（保留两位）：
		public double round(double value){
		    return Math.round(value*100)/100.0;
		}
		非常不幸，上面的代码并不能正常工作，给这个方法传入4.015它将返回4.01而不是4.02，如我们在上面看到的
		4.015*100=401.49999999999994
	 */
	@Test
	public void test_04() { 
		// 十进制   4.015*100=401.5  round(401.5)/100 = 402/100 =  4.02
		// 二进制   4.015*100=401.49999999999994 round(401.49999999999994)/100 = 401/100 = 4.01
		System.out.println(round(4.015)); 
	}

	public double round(double value) {
		return Math.round(value * 100) / 100.0;
	}
	
	
	/**
	 * JAVA保留两位小数
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
		
		// 方式一
		double f = 111231.5585;
		BigDecimal b = new BigDecimal(f);
		// 旧方法，已经不再推荐使用
		double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		// 新方法，如果不需要四舍五入，可以使用RoundingMode.DOWN
		BigDecimal f2 = b.setScale(2, RoundingMode.UP);
		System.out.println("方式一：" + f2);

		// 方式二

		/*
		 * java.text.DecimalFormat df =new java.text.DecimalFormat("#.00");
		 * df.format(你要格式化的数字)
		 */

		String format = new java.text.DecimalFormat("#.00").format(3.1455926);
		System.out.println("方式二：" + format);
		// #.00 表示两位小数 #.0000四位小数 以此类推...

		// 方式三
		double d = 3.1455926;
		String result = String.format("%.2f", d);
		System.out.println("方式三：" + result);
		// %.2f %. 表示 小数点前任意位数 2 表示两位小数 格式后的结果为f 表示浮点型

		// 方式四
		double x = 23.5455;
		NumberFormat ddf1 = NumberFormat.getNumberInstance();
		ddf1.setMaximumFractionDigits(2);
		String s = ddf1.format(x);
		System.out.println("方式四：" + s);

		// 方式一：111231.56
		// 方式二：3.15
		// 方式三：3.15
		// 方式四：23.55

	}

}
