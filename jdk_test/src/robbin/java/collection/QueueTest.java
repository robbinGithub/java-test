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
 * 优先级队列测试
 * 
 * @author robbin.zhang
 * 
 */
public class QueueTest {
    
	/**
	 * PriorityQueue
	 * 堆是一种比较神奇的数据结构，概念上是树，存储为数组，父子有特殊顺序，根是最大值/最小值，构建/添加/删除效率都很高，可以高效解决很多问题。
	 * 
	 * 堆可以非常高效方便的解决很多问题，比如说：
	 *	【优先级队列】，我们之前介绍的队列实现类LinkedList是按添加顺序排队的，但现实中，经常需要按优先级来，每次都应该处理当前队列中优先级最高的，高优先级的，即使来得晚，也应该被优先处理。
	 *	【求前K个最大的元素】，元素个数不确定，数据量可能很大，甚至源源不断到来，但需要知道到目前为止的最大的前K个元素。这个问题的变体有：求前K个最小的元素，求第K个最大的，求第K个最小的。
	 *	【求中值元素】，中值不是平均值，而是排序后中间那个元素的值，同样，数据量可能很大，甚至源源不断到来。、
	 *  【堆排序】堆还可以实现排序，称之为堆排序，不过有比它更好的排序算法，所以，我们就不介绍其在排序中的应用了。
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
		
//		下面来看任务队列的示例代码：
		Queue<Task> tasks = new PriorityQueue<Task>(11, taskComparator);
		tasks.offer(new Task(20, "写日记"));
		tasks.offer(new Task(10, "看电视"));
		tasks.offer(new Task(100, "写代码"));
		
		Task task = tasks.poll();
		while(task!=null){
		    System.out.print("处理任务: "+task.getName()
		            +"，优先级:"+task.getPriority()+"\n");
		    task = tasks.poll();
		}

	}
	
	/**
	 * 延时队列
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
