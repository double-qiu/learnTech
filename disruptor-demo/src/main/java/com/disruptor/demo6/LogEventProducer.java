package org.disrupto.demo6;

import java.util.Date;

import com.lmax.disruptor.RingBuffer;

public class LogEventProducer {

	private final RingBuffer<LogEvent> ringBuffer;
	
	public LogEventProducer(RingBuffer<LogEvent> ringBuffer){
		this.ringBuffer = ringBuffer;
	}
	
	public void onData(long logId, String content, Date date){
		//ringBuffer类似一个队列，next就是下一个槽
		long seq = ringBuffer.next();
		try{
			//用seq索引取出一个空到事件用于填充
			LogEvent logEvent = ringBuffer.get(seq);
			logEvent.setLogId(logId);
			logEvent.setContent(content);
			logEvent.setDate(date);
			
		}
		finally{
			//最终发布事件，很重要
			ringBuffer.publish(seq);
		}
	}
	
}