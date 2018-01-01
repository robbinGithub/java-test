package robbin.java.thread;
/**
 * 线程中断测试
 * 
 *   如果线程在调用 Object 类的 wait()、wait(long) 或 wait(long, int) 方法，
 *   或者该类的 join()、join(long)、join(long, int)、sleep(long) 或 sleep(long, int)
 *   方法过程中受阻，则其中断状态将被清除，它还将收到一个 InterruptedException。 
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
    * Sleep响应中断
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
				
			    // 此处不会响应中断
//				timeConsumingOperation(10000);
//				System.out.println("timeConsumingOperation end...");
				
				try {
					// sleep响应InterruptedException异常
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