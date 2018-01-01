package robbin.java.collection;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

/**
 * ���ȼ����в���
 * 
 * @author robbin.zhang
 * 
 */
public class QueueTest {
    
	/**
	 * PriorityQueue
	 * ����һ�ֱȽ���������ݽṹ���������������洢Ϊ���飬����������˳�򣬸������ֵ/��Сֵ������/���/ɾ��Ч�ʶ��ܸߣ����Ը�Ч����ܶ����⡣
	 * 
	 * �ѿ��Էǳ���Ч����Ľ���ܶ����⣬����˵��
	 *	�����ȼ����С�������֮ǰ���ܵĶ���ʵ����LinkedList�ǰ����˳���Ŷӵģ�����ʵ�У�������Ҫ�����ȼ�����ÿ�ζ�Ӧ�ô���ǰ���������ȼ���ߵģ������ȼ��ģ���ʹ������ҲӦ�ñ����ȴ���
	 *	����ǰK������Ԫ�ء���Ԫ�ظ�����ȷ�������������ܴܺ�����ԴԴ���ϵ���������Ҫ֪����ĿǰΪֹ������ǰK��Ԫ�ء��������ı����У���ǰK����С��Ԫ�أ����K�����ģ����K����С�ġ�
	 *	������ֵԪ�ء�����ֵ����ƽ��ֵ������������м��Ǹ�Ԫ�ص�ֵ��ͬ�������������ܴܺ�����ԴԴ���ϵ�������
	 *  �������򡿶ѻ�����ʵ�����򣬳�֮Ϊ�����򣬲����б������õ������㷨�����ԣ����ǾͲ��������������е�Ӧ���ˡ�
	 * 
	 */
	@Test
	public void test_01() {
		
//		Queue<Integer> pq = new PriorityQueue<>();
		Queue<Integer> pq = new PriorityQueue<>(11, Collections.reverseOrder());
		
		pq.offer(10);
		pq.add(22);
		pq.addAll(Arrays.asList(new Integer[]{
		    11, 12, 34, 2, 7, 4, 15, 12, 8, 6, 19, 13 }));
		while(pq.peek()!=null){
		    System.out.print(pq.poll() + " ");
		}
		
//		��������������е�ʾ�����룺
		Queue<Task> tasks = new PriorityQueue<Task>(11, taskComparator);
		tasks.offer(new Task(20, "д�ռ�"));
		tasks.offer(new Task(10, "������"));
		tasks.offer(new Task(100, "д����"));
		
		Task task = tasks.poll();
		while(task!=null){
		    System.out.print("��������: "+task.getName()
		            +"�����ȼ�:"+task.getPriority()+"\n");
		    task = tasks.poll();
		}

	}
	
	/**
	 * ��ʱ����
	 */
	@Test
	public void test_02() {
		DelayQueue<DelayEvent> q = new DelayQueue<DelayEvent>();
		DelayEvent e = new DelayEvent(new Date());
		q.put(e);
		try {
			DelayEvent element = q.take();
			System.out.println(element);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}
	
	static class Task {
	    int priority;
	    String name;
	    
	    public Task(int priority, String name) {
	        this.priority = priority;
	        this.name = name;
	    }

	    public int getPriority() {
	        return priority;
	    }
	    
	    public String getName() {
	        return name;
	    }
	}
	
	private static Comparator<Task> taskComparator = new Comparator<Task>() {

	    @Override
	    public int compare(Task o1, Task o2) {
	        if(o1.getPriority()>o2.getPriority()){
	            return -1;
	        }else if(o1.getPriority()<o2.getPriority()){
	            return 1;
	        }
	        return 0;
	    }
	};
	
	static class Task21 {
	    int priority;
	    String name;
	    
	    public Task21(int priority, String name) {
	        this.priority = priority;
	        this.name = name;
	    }

	    public int getPriority() {
	        return priority;
	    }
	    
	    public String getName() {
	        return name;
	    }
	}
	
	public class DelayEvent implements Delayed {
	    private Date startDate;
	    public DelayEvent(Date startDate) {
	        super();
	        this.startDate = startDate;
	    }
	    @Override
	    public int compareTo(Delayed o) {
	        long result = this.getDelay(TimeUnit.NANOSECONDS)
	                - o.getDelay(TimeUnit.NANOSECONDS);
	        if (result < 0) {
	            return -1;
	        } else if (result > 0) {
	            return 1;
	        } else {
	            return 0;
	        }
	    }
	    @Override
	    public long getDelay(TimeUnit unit) {
	        Date now = new Date();
	        long diff = startDate.getTime() - now.getTime();
	        return unit.convert(diff, TimeUnit.MILLISECONDS);
	    }
	}
	
}
