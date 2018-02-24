package org.disrupto.demo6;

import com.lmax.disruptor.EventHandler;

/**
 * 事件到消费者
 * @author Administrator
 *
 */
public class LogEventConsumer implements EventHandler<LogEvent> {

	public void onEvent(LogEvent logEvent, long seq, boolean bool) throws Exception {
		System.out.println("seq:" + seq + ",bool:" + bool + ",logEvent:" + logEvent.toString());
	}

}
