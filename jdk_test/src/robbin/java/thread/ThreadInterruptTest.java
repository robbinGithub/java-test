package robbin.java.thread;
/**
 * �߳��жϲ���
 * 
 *   ����߳��ڵ��� Object ��� wait()��wait(long) �� wait(long, int) ������
 *   ���߸���� join()��join(long)��join(long, int)��sleep(long) �� sleep(long, int)
 *   �������������裬�����ж�״̬����������������յ�һ�� InterruptedException�� 
 *   
 * Thread.interupt
 * @author robbin.zhang
 * @date 2016/12/08 09:57
 *
 */
public class ThreadInterruptTest {
	
	private InterruptThread t;
	
	public static void main(String[] args) {
		ThreadInterruptTest test = new ThreadInterruptTest();
		test.start();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		test.interupt();
		
	}
	
	public void start(){
		t = new InterruptThread();
		t.start();
	}
	
	public void interupt(){
		t.interrupt();
	}
	
   /**
    * Sleep��Ӧ�ж�
    * @author robbin
    *
    */
   class InterruptThread extends Thread{

		@Override
		public void run() {
			long s = System.currentTimeMillis();
//			while(true){
				
//				if(Thread.currentThread().isInterrupted()){
//					System.out.println("Thread.currentThread().isInterrupted()");
//					break;
//				}
				
			    // �˴�������Ӧ�ж�
//				timeConsumingOperation(10000);
//				System.out.println("timeConsumingOperation end...");
				
				try {
					// sleep��ӦInterruptedException�쳣
					sleep(10000);
				} catch (InterruptedException e) {
					System.out.println("interupted:"+ (System.currentTimeMillis() - s));
					System.out.println("isInterrupted:"+ Thread.currentThread().isInterrupted());
					e.printStackTrace();
				}
			}
			
		}
		
		public void timeConsumingOperation(long millis){
			long endTimeMillis = System.currentTimeMillis() + millis;
			while(System.currentTimeMillis() < endTimeMillis ){
                 System.out.println("time-consuming-operation");	
                 if(Thread.currentThread().isInterrupted()){
 					System.out.println(Thread.currentThread().isInterrupted());
 				}
			}
			
		}
   }