package robbin.java.lang;

import org.junit.Test;

public class ByteConvertor {
	
	 /**
     * All possible chars for representing a number as a String
     */
    final static char[] digits = {
        '0' , '1' , '2' , '3' , '4' , '5' ,
        '6' , '7' , '8' , '9' , 'a' , 'b' ,
        'c' , 'd' , 'e' , 'f' , 'g' , 'h' ,
        'i' , 'j' , 'k' , 'l' , 'm' , 'n' ,
        'o' , 'p' , 'q' , 'r' , 's' , 't' ,
        'u' , 'v' , 'w' , 'x' , 'y' , 'z'
    };
	
	 /**
     * Convert the integer to an unsigned number.
     * 
     * 	���Կ�����ʵ������ͨ������toUnsignedString������ʵ�ֵġ���δ���ľ���֮���������¼��㣺

		1. ����mask�ļ��㡣shift�����������ֲ�ͬ���ƣ���������Ƶ�shift=1��mask=1���˽��Ƶ�shift=3��mask=7��ʮ�����Ƶ�shift=4��mask=15��
	
		2. ����ʹ�õ���>>>������>>��λ�����е����Ʒ�Ϊ�������ƣ�>>�����߼����ƣ�>>>�����ڽ�����������ʱ�����λ������λ�����ڽ����߼�����ʱ�����λ��0���������ʹ�õ��������ƣ���ô������-1�����ĸ��������۽��ж��ٴ����Ʋ����������ܱ��0�����Ի������ѭ����
	
		3. ʹ�õ���do-while������while������һ��������Ҫ��ϸ�ڣ����ʹ�õ���while����ô����i=0�ĳ�����᷵�ؿ��ַ�����
		
		http://www.cnblogs.com/jtree/p/4162321.html
     */
    private static String toUnsignedString(int i, int shift) {
        char[] buf = new char[32];
        int charPos = 32;
        int radix = 1 << shift;
        int mask = radix - 1;
        do {
        	System.out.println(i & mask);
            buf[--charPos] = digits[i & mask];
            i >>>= shift;
        } while (i != 0);

        return new String(buf, charPos, (32 - charPos));
    }
    
    /**��16����ת��Ϊ������
     * @param hexStr
     * @return
     */
    public static byte[] parseHexStr2Byte(String hexStr) {
            if (hexStr.length() < 1)
                    return null;
            byte[] result = new byte[hexStr.length()/2];
            for (int i = 0;i< hexStr.length()/2; i++) {
                    int high = Integer.parseInt(hexStr.substring(i*2, i*2+1), 16);
                    int low = Integer.parseInt(hexStr.substring(i*2+1, i*2+2), 16);
                    result[i] = (byte) (high * 16 + low);
            }
            return result;
    }
    
	
    public static void main(String[] args) {
		System.out.println(toUnsignedString(4, 1));
		System.out.println(parseInt("12", 10));
	}
    
   /**
    * @param      radix   the radix to be used while parsing {@code s}.
    * @return     the integer represented by the string argument in the
    *             specified radix.
    * @exception  NumberFormatException if the {@code String}
    *             does not contain a parsable {@code int}.
    */
   public static int parseInt(String s, int radix)
               throws NumberFormatException
   {
       /*
        * WARNING: This method may be invoked early during VM initialization
        * before IntegerCache is initialized. Care must be taken to not use
        * the valueOf method.
        */

       if (s == null) {
           throw new NumberFormatException("null");
       }

       if (radix < Character.MIN_RADIX) {
           throw new NumberFormatException("radix " + radix +
                                           " less than Character.MIN_RADIX");
       }

       if (radix > Character.MAX_RADIX) {
           throw new NumberFormatException("radix " + radix +
                                           " greater than Character.MAX_RADIX");
       }

       int result = 0;
       boolean negative = false;
       int i = 0, len = s.length();
       int limit = -Integer.MAX_VALUE;
       int multmin;
       int digit;

       if (len > 0) {
           char firstChar = s.charAt(0);
           if (firstChar < '0') { // Possible leading "+" or "-"
               if (firstChar == '-') {
                   negative = true;
                   limit = Integer.MIN_VALUE;
               } else if (firstChar != '+')
                   throw forInputString(s);

               if (len == 1) // Cannot have lone "+" or "-"
                   throw forInputString(s);
               i++;
           }
           multmin = limit / radix;
           while (i < len) {
               // Accumulating negatively avoids surprises near MAX_VALUE
               digit = Character.digit(s.charAt(i++),radix);
               if (digit < 0) {
                   throw forInputString(s);
               }
               if (result < multmin) {
                   throw forInputString(s);
               }
               result *= radix;
               if (result < limit + digit) {
                   throw forInputString(s);
               }
               result -= digit;
           }
       } else {
           throw forInputString(s);
       }
       return negative ? result : -result;
   }
   
   /**
    * Factory method for making a <code>NumberFormatException</code>
    * given the specified input which caused the error.
    *
    * @param   s   the input causing the error
    */
   static NumberFormatException forInputString(String s) {
       return new NumberFormatException("For input string: \"" + s + "\"");
   }
    
	public static void main2(String[] args) {
		
		  // ʹ�����µ����,�Ϳ�������ʹ��&0xff�Ͳ�ʹ�õ�������
          
//		  byte b = (byte) 11010110;
		  byte b = -1;  //   ԭ�룺  10000001    ���룺11111110 + 1 =>  11111111
		  System.out.println(b); // 00111110
		  
		  System.out.println(Integer.toBinaryString(b & 0xff)); // ������:000000000000000000000000 11010110

		  System.out.println(Integer.toBinaryString(b)); // ������:       111111111111111111111111 11010110
		  
		  
		  char c = (char)-1 & 0xFF;

		  char d = (char)-1;

		  System.out.println((int)c); // 255

		  System.out.println((int)d); //65535
		  

	}
	
	
	public static String bytes2HexString(byte[] b) {

	  String ret = "";

	  for (int i = 0; i < b.length; i++) {

	   String hex = Integer.toHexString(b[ i ] & 0xFF);

	   if (hex.length() == 1) {

	    hex = '0' + hex;

	   }

	   ret += hex.toUpperCase();
	  }
	  return ret;
	}
		  
	
	@Test
	public void test_Integer_toUnsignedString(){
		
		byte b = 10;
		Integer.toHexString(b & 0xFF);  // 1111 1111 Ϊ����0xff����������
	}

}
