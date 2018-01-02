package robbin.java.lang;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

import org.junit.Test;

/**
 * big-endian��little-endian
 * 
 *  1��ʲô�Ǵ�˺�С��

	1) Little-Endian���ǵ�λ�ֽ��ŷ����ڴ�ĵ͵�ַ�ˣ���λ�ֽ��ŷ����ڴ�ĸߵ�ַ�ˡ�
	2) Big-Endian���Ǹ�λ�ֽ��ŷ����ڴ�ĵ͵�ַ�ˣ���λ�ֽ��ŷ����ڴ�ĸߵ�ַ�ˡ�
	
	����˵������������0x12345678���ڴ��б�����ʽΪ��
	
	1�����ģʽ��
	
	�͵�ַ -----------------> �ߵ�ַ
	0x12  |  0x34  |  0x56  |  0x78
	
	2��С��ģʽ��
	
	�͵�ַ ------------------> �ߵ�ַ
	0x78  |  0x56  |  0x34  |  0x1

2��Java ��˻�ת����

 * 
 * @author robbin.zhang
 * 
 */
public class JVMEndian {
	
	public static void main(String[] args) {
		
	}
	
	/**
	 * java���Դ��С��
	 */
	@Test
	public void test_01(){
		
		// ���Ի������С��
		if (ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN)
			System.out.println("big endian");
		else
			System.out.println("little endian"); // little endian
		
		
		// ByteBuffer�洢�ֽڴ���Ĭ��Ϊ���ģʽ
		int x = 0x01020304;
         
        ByteBuffer bb = ByteBuffer.wrap(new byte[4]);
        bb.asIntBuffer().put(x);
        String ss_before = Arrays.toString(bb.array());
         
        System.out.println("Ĭ���ֽ��� " +  bb.order().toString() +  ","  +  " �ڴ����� " +  ss_before);
         
        bb.order(ByteOrder.LITTLE_ENDIAN);
        bb.asIntBuffer().put(x);
        String ss_after = Arrays.toString(bb.array());
         
        System.out.println("�޸��ֽ��� " + bb.order().toString() +  ","  +  " �ڴ����� " +  ss_after);
        
	}
	
	/**
	 * java���С�˻�ת
	 * 
	 * @param a
	 * @return
	 */
	public static int toLittleEndian(int a) {  
        return (((a & 0xFF) << 24) | (((a >> 8) & 0xFF) << 16) | (((a >> 16) & 0xFF) << 8) | ((a >> 24) & 0xFF));  
    }  
    
    /**  
     * ��int��ֵת��Ϊռ�ĸ��ֽڵ�byte���飬������������(��λ��ǰ����λ�ں�)��˳�� ��bytesToInt��������ʹ�� 
     * @param value  
     *            Ҫת����intֵ 
     * @return byte���� 
     */    
	 public static byte[] intToBytes( int value )   
	 {   
	     byte[] src = new byte[4];  
	     src[3] =  (byte) ((value>>24) & 0xFF);  // ȡ���λ
	     src[2] =  (byte) ((value>>16) & 0xFF);  
	     src[1] =  (byte) ((value>>8) & 0xFF);    
	     src[0] =  (byte) (value & 0xFF);     // ȡ���λ             
	     return src;   
	 }  

	/**
	 * ��int��ֵת��Ϊռ�ĸ��ֽڵ�byte���飬������������(��λ��ǰ����λ�ں�)��˳�� ��bytesToInt2��������ʹ��
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
    * byte������ȡint��ֵ��������������(��λ��ǰ����λ�ں�)��˳�򣬺ͺ�intToBytes��������ʹ�� 
    *   
    * @param src  
    *            byte����  
    * @param offset  
    *            ������ĵ�offsetλ��ʼ  
    * @return int��ֵ  
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
	 * byte������ȡint��ֵ��������������(��λ�ں󣬸�λ��ǰ)��˳�򡣺�intToBytes2��������ʹ��
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
