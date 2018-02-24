package org.disruptor.demo5;

import com.lmax.disruptor.EventFactory;

public class OrderFactory implements EventFactory<Order> {

	@Override
	public Order newInstance() {

		System.out.println("OrderFactory.newInstance");
		return new Order();
	}

}
