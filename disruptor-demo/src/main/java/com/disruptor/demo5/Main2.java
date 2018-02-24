package org.disrupto.demo5;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.lmax.disruptor.IgnoreExceptionHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.WorkerPool;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.ProducerType;

/**
 * http://zhangfengzhe.blog.51cto.com/8855103/1885830
 * <p>Description: </p>
 * @since 2017年3月7日
 * @author lenovo
 * <p>Copyright:Copyright(c)2017</p>
 */
public class Main2 {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception {
		ExecutorService executor = Executors.newFixedThreadPool(3);
		RingBuffer<Order> ringBuffer = RingBuffer.create(ProducerType.SINGLE,
				new OrderFactory(), 1024, new YieldingWaitStrategy());
		WorkerPool<Order> workerPool = new WorkerPool<Order>(ringBuffer, ringBuffer.newBarrier(), new IgnoreExceptionHandler(),
				new OrderHandler());

		workerPool.start(executor);

		// -------------生产数据
		for (int i = 0; i < 30; i++) {

			long sequence = ringBuffer.next();

			Order order = ringBuffer.get(sequence);
			order.setId(i);

			ringBuffer.publish(sequence);

			System.out.println(Thread.currentThread().getName() + " 生产者发布一条数据:"
					+ sequence + " 订单ID：" + i);
		}

		Thread.sleep(1000);

		workerPool.halt();
		executor.shutdown();
	}

}
