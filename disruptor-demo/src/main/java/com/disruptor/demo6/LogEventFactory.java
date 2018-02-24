package org.disrupto.demo6;

import com.lmax.disruptor.EventFactory;

/**
 * 事件生成工厂（用来初始化预分配事件对象）
 * @author Administrator
 *
 */
public class LogEventFactory implements EventFactory<LogEvent> {

	public LogEvent newInstance() {
		return new LogEvent();
	}

}
