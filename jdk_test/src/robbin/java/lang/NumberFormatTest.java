package robbin.java.lang;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Java����doubleֵ�����������룬������λС���ļ��ַ���

	1. ����
	�������е�doubleֵ��ȷ��С�������λ�������������룬Ҳ����ֱ�ӽضϡ�
	
	���磺����12345.6789�����������12345.68Ҳ������12345.67�������Ƿ���Ҫ�������룬����ͨ������������(RoundingMode.UP/RoundingMode.DOWN�Ȳ�������
	
 *  @author robbin.zhang
 *
 */
public class NumberFormatTest {
	
	 /**
	  * 4.015 �о�������( *100)�о�������
	  * 4.015*100=401.49999999999994
	  * 
     * ������λС�������������һ�������ķ���
     * @param d
     * @return
     */
    public static double formatDouble1(double d) {
        return (double)Math.round(d*100)/100;
    }

    
    /**
     * The BigDecimal class provides operations for arithmetic, scale manipulation, rounding, comparison, hashing, and format conversion.
     * @param d
     * @return
     */
    public static double formatDouble2(double d) {
        // �ɷ������Ѿ������Ƽ�ʹ��
//        BigDecimal bg = new BigDecimal(d).setScale(2, BigDecimal.ROUND_HALF_UP);

        
        // �·������������Ҫ�������룬����ʹ��RoundingMode.DOWN
        BigDecimal bg = new BigDecimal(d).setScale(2, RoundingMode.UP);

        
        return bg.doubleValue();
    }

    /**
     * NumberFormat is the abstract base class for all number formats. 
     * This class provides the interface for formatting and parsing numbers.
     * @param d
     * @return
     */
    public static String formatDouble3(double d) {
        NumberFormat nf = NumberFormat.getNumberInstance();
        

        // ������λС��
        nf.setMaximumFractionDigits(2); 

        
        // �������Ҫ�������룬����ʹ��RoundingMode.DOWN
        nf.setRoundingMode(RoundingMode.UP);

        
        return nf.format(d);
    }

    
    /**
     * �������ͦ�򵥵ġ�
     * DecimalFormat is a concrete subclass of NumberFormat that formats decimal numbers. 
     * @param d
     * @return
     */
    public static String formatDouble4(double d) {
        DecimalFormat df = new DecimalFormat("#.00");

        
        return df.format(d);
    }

    
    /**
     * ���ֻ�����ڳ����еĸ�ʽ����ֵȻ���������ô�����������ͦ����ġ�
     * Ӧ��������ʹ�ã�System.out.println(String.format("%.2f", d));
     * @param d
     * @return
     */
    public static String formatDouble5(double d) {
        return String.format("%.2f", d);
    }

    public static void main(String[] args) {
        double d = 12345.67890;
        
        System.out.println(formatDouble1(d));
        System.out.println(formatDouble2(d));
        System.out.println(formatDouble3(d));
        System.out.println(formatDouble4(d));
        System.out.println(formatDouble5(d));
    }


}
