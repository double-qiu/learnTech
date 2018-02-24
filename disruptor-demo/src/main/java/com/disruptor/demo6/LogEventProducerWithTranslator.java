package org.disrupto.demo6;

import java.util.Date;
import java.util.List;
import com.lmax.disruptor.EventTranslatorVararg;
import com.lmax.disruptor.RingBuffer;

/**
 * 使用translator方式到事件生产者发布事件
 * @author Administrator
 *
 */
public class LogEventProducerWithTranslator {
	
	private final static EventTranslatorVararg<List<LogEvent>> translator = new EventTranslatorVararg<List<LogEvent>>() {
		

        @Override
        public void translateTo(List<LogEvent> event, long sequence, Object... args) {
            
        }
	};

	private final RingBuffer<List<LogEvent>> ringBuffer;
	public LogEventProducerWithTranslator(RingBuffer<List<LogEvent>> ringBuffer){
		this.ringBuffer = ringBuffer;
	}
	
	public void onData(List<LogEvent> list){
		this.ringBuffer.publishEvent(translator, list);
	}
}
