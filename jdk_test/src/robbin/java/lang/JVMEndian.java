package robbin.java.lang;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

import org.junit.Test;

/**
 * big-endian和little-endian
 * 
 *  1、什么是大端和小端

	1) Little-Endian就是低位字节排放在内存的低地址端，高位字节排放在内存的高地址端。
	2) Big-Endian就是高位字节排放在内存的低地址端，低位字节排放在内存的高地址端。
	
	举例说明：例如数字0x12345678在内存中表现形式为：
	
	1）大端模式：
	
	低地址 -----------------> 高地址
	0x12  |  0x34  |  0x56  |  0x78
	
	2）小端模式：
	
	低地址 ------------------> 高地址
	0x78  |  0x56  |  0x34  |  0x1

2、Java 大端互转代码

 * 
 * @author robbin.zhang
 * 
 */
public class JVMEndian {
	
	public static void main(String[] args) {
		
	}
	
	/**
	 * java测试大端小端
	 */
	@Test
	public void test_01(){
		
		// 测试环境大端小端
		if (ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN)
			System.out.println("big endian");
		else
			System.out.println("little endian"); // little endian
		
		
		// ByteBuffer存储字节次序默认为大端模式
		int x = 0x01020304;
         
        ByteBuffer bb = ByteBuffer.wrap(new byte[4]);
        bb.asIntBuffer().put(x);
        String ss_before = Arrays.toString(bb.array());
         
        System.out.println("默认字节序 " +  bb.order().toString() +  ","  +  " 内存数据 " +  ss_before);
         
        bb.order(ByteOrder.LITTLE_ENDIAN);
        bb.asIntBuffer().put(x);
        String ss_after = Arrays.toString(bb.array());
         
        System.out.println("修改字节序 " + bb.order().toString() +  ","  +  " 内存数据 " +  ss_after);
        
	}
	
	/**
	 * java大端小端互转
	 * 
	 * @param a
	 * @return
	 */
	public static int toLittleEndian(int a) {  
        return (((a & 0xFF) << 24) | (((a >> 8) & 0xFF) << 16) | (((a >> 16) & 0xFF) << 8) | ((a >> 24) & 0xFF));  
    }  
    
    /**  
     * 将int数值转换为占四个字节的byte数组，本方法适用于(低位在前，高位在后)的顺序。 和bytesToInt（）配套使用 
     * @param value  
     *            要转换的int值 
     * @return byte数组 
     */    
	 public static byte[] intToBytes( int value )   
	 {   
	     byte[] src = new byte[4];  
	     src[3] =  (byte) ((value>>24) & 0xFF);  // 取最高位
	     src[2] =  (byte) ((value>>16) & 0xFF);  
	     src[1] =  (byte) ((value>>8) & 0xFF);    
	     src[0] =  (byte) (value & 0xFF);     // 取最低位             
	     return src;   
	 }  

	/**
	 * 将int数值转换为占四个字节的byte数组，本方法适用于(高位在前，低位在后)的顺序。 和bytesToInt2（）配套使用
	 */  
	 public static byte[] intToBytes2(int value)   
	 {   
	     byte[] src = new byte[4];  
	     src[0] = (byte) ((value>>24) & 0xFF);  
	     src[1] = (byte) ((value>>16)& 0xFF);  
	     src[2] = (byte) ((value>>8)&0xFF);    
	     src[3] = (byte) (value & 0xFF);       
	     return src;  
	 }  
	 
  /**  
    * byte数组中取int数值，本方法适用于(低位在前，高位在后)的顺序，和和intToBytes（）配套使用 
    *   
    * @param src  
    *            byte数组  
    * @param offset  
    *            从数组的第offset位开始  
    * @return int数值  
    */    
	public static int bytesToInt(byte[] src, int offset) {  
	    int value;    
	    value = (int) ((src[offset] & 0xFF)   
	            | ((src[offset+1] & 0xFF)<<8)   
	            | ((src[offset+2] & 0xFF)<<16)   
	            | ((src[offset+3] & 0xFF)<<24));  
	    return value;  
	}  
	  
	/**
	 * byte数组中取int数值，本方法适用于(低位在后，高位在前)的顺序。和intToBytes2（）配套使用
	 */ 
	public static int bytesToInt2(byte[] src, int offset) {  
	    int value;    
	    value = (int) ( ((src[offset] & 0xFF)<<24)  
	            |((src[offset+1] & 0xFF)<<16)  
	            |((src[offset+2] & 0xFF)<<8)  
	            |(src[offset+3] & 0xFF));  
	    return value;  
	}
	
	
	/*
	
	static ByteOrder byteOrder() {
        if (byteOrder == null)
            throw new Error("Unknown byte order");
        return byteOrder;
    }

    static {
        long a = unsafe.allocateMemory(8);
        try {
            unsafe.putLong(a, 0x0102030405060708L);
            byte b = unsafe.getByte(a);
            switch (b) {
            case 0x01: byteOrder = ByteOrder.BIG_ENDIAN;     break;
            case 0x08: byteOrder = ByteOrder.LITTLE_ENDIAN;  break;
            default:
                assert false;
                byteOrder = null;
            }
        } finally {
            unsafe.freeMemory(a);
        }
    }*/

}
