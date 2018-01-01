package robbin.java;

class IntegerReverse{
	
	public static void main(String[] args) {
       
		int x = 0x12345678;
//		System.out.println(Integer.toHexString(x));
//		int r = reverseBytes(x);
//		System.out.println(Integer.toHexString(r));
		
		System.out.println(Integer.toBinaryString(x));
		System.out.println(Integer.toBinaryString(x >>> 24));
		
		
//		00010010 00110100 01010110 01111000
		
//		第一个字节->第四个字节    有符号位移
//		00010010 00110100 01010110 01111000   >>> 24
//		00010010
		
//		第二个字节->第三个字节		
//		00000000 00010010 00110100 01010110  >>   8
//		00000000 00000000 11111111 00000000  &   0xFF00
		
//		第三个字节 ->第二个字节	
//		00110100 01010110 01111000 00000000  ((i <<   8)
//		00000000 11111111 00000000 00000000  & 0xFF0000) 
	
//      最后一个字节-->第一个字节
//		01111000 00000000 00000000 00000000   i << 24
		
//		12345678
//		78563412
		
		
//		int a = 0x12345678;
//		System.out.println(Integer.toBinaryString(a));
//
//		int r = Integer.reverse(a);
//		System.out.println(Integer.toBinaryString(r));
//
//		int rb = Integer.reverseBytes(a);
//		System.out.println(Integer.toHexString(rb));
		
//		10010001101000101011001111000
//		11110011010100010110001001000
//		78563412
		
	}
	
	public static int reverseBytes(int i) {
	    return ((i >>> 24)           ) |
	           ((i >>   8) &   0xFF00) |
	           ((i <<   8) & 0xFF0000) |
	           ((i << 24));
	}
	
	
}
