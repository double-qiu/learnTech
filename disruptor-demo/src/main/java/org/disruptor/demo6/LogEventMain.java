package org.disruptor.demo6;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;

public class LogEventMain {

	public static void main(String[] args) {
//		producer();
		producerWithTranslator();
	}
	
	@SuppressWarnings("unchecked")
	public static void producer(){
		LogEventFactory factory = new LogEventFactory();
		int ringBufferSize = 1024;
		Disruptor<LogEvent> disruptor = new Disruptor<LogEvent>(factory, ringBufferSize, Executors.defaultThreadFactory());
		disruptor.handleEventsWith(new LogEventConsumer());
		disruptor.start();
		RingBuffer<LogEvent> ringBuffer = disruptor.getRingBuffer();
		
		LogEventProducer producer = new LogEventProducer(ringBuffer);
		for(int i = 0; i<5000; i++){
			producer.onData(i, "c" + i, new Date());
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void producerWithTranslator(){
		LogEventFactory factory = new LogEventFactory();
		int ringBufferSize = 1024;
		ThreadFactory threadFactory = new ThreadFactory() {
			
			public Thread newThread(Runnable r) {
				return new Thread(r);
			}
		};
		Disruptor<LogEvent> disruptor = new Disruptor<LogEvent>(factory, ringBufferSize, threadFactory);
		disruptor.handleEventsWith(new LogEventConsumer());
		disruptor.start();
		RingBuffer<LogEvent> ringBuffer = disruptor.getRingBuffer();
//		LogEventProducerWithTranslator producer2 = new LogEventProducerWithTranslator(ringBuffer);
		
		for(int i = 0; i<5000; i++){
//			producer2.onData(i, "c" + i, new Date());
		}
	}

}
