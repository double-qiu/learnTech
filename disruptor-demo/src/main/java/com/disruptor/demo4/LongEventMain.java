package org.disrupto.demo4;

import java.nio.ByteBuffer;
import java.util.concurrent.Executors;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;

/**
 * http://ifeve.com/disruptor-getting-started/
 * Disruptor入门
 * <p>Description: </p>
 * @since 2017年3月7日
 * @author lenovo
 * <p>Copyright:Copyright(c)2017</p>
 */
public class LongEventMain {
	@SuppressWarnings({ "unchecked" })
	public static void main(String[] args) throws InterruptedException {
		// Executor that will be used to construct new threads for consumers
		// The factory for the event
		LongEventFactory factory = new LongEventFactory();
		// Specify the size of the ring buffer, must be power of 2.
		int bufferSize = 1024;
		// Construct the Disruptor
		Disruptor<LongEvent> disruptor = new Disruptor<LongEvent>(factory,
				bufferSize, Executors.defaultThreadFactory());
		// Connect the handler
		disruptor.handleEventsWith(new LongEventHandler());
		// Start the Disruptor, starts all threads running
		disruptor.start();
		// Get the ring buffer from the Disruptor to be used for publishing.
		RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();

		LongEventProducer producer = new LongEventProducer(ringBuffer);

		ByteBuffer bb = ByteBuffer.allocate(8);
		for (long l = 0; true; l++) {
			bb.putLong(0, l);
			producer.onData(bb);
			Thread.sleep(1000);
		}
	}
}
