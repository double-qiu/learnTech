package org.disrupto.demo5;

import java.util.concurrent.Executors;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;

/**
 * http://zhangfengzhe.blog.51cto.com/8855103/1885830
 * <p>Description: </p>
 * @since 2017年3月7日
 * @author lenovo
 * <p>Copyright:Copyright(c)2017</p>
 */
public class Main {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws InterruptedException {

		// 创建订单工厂
		OrderFactory orderFactory = new OrderFactory();

		// ringbuffer的大小
		int RINGBUFFER_SIZE = 1024;

		// 创建disruptor
		Disruptor<Order> disruptor = new Disruptor<Order>(orderFactory,
				RINGBUFFER_SIZE, Executors.defaultThreadFactory());

		// 设置事件处理器 即消费者
		disruptor.handleEventsWith(new OrderHandler());

		disruptor.start();

		RingBuffer<Order> ringBuffer = disruptor.getRingBuffer();

		// -------------生产数据
		for (int i = 0; i < 3; i++) {

			long sequence = ringBuffer.next();
			Order order = ringBuffer.get(sequence);
			order.setId(i);

			ringBuffer.publish(sequence);
			System.out.println(Thread.currentThread().getName() + " 生产者发布一条数据:"
					+ sequence + " 订单ID：" + i);
		}

		Thread.sleep(1000);

		disruptor.shutdown();
	}
}
