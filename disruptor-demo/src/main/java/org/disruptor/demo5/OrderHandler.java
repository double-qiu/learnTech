package org.disruptor.demo5;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;

public class OrderHandler implements EventHandler<Order>, WorkHandler<Order> {
	 
    @Override
    public void onEvent(Order order, long sequence, boolean endOfBatch) throws Exception {
 
        System.out.println(Thread.currentThread().getName() + " 消费者处理中:" + sequence);
        order.setInfo("info" + order.getId());
        order.setPrice(Math.random());
    }

	@Override
	public void onEvent(Order order) throws Exception {
		System.out.println(Thread.currentThread().getName() + " 消费者处理中:");
        order.setInfo("info" + order.getId());
        order.setPrice(Math.random());
	}
 
}
