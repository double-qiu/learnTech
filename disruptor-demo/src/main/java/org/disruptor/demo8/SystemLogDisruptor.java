package org.disruptor.demo8;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.lmax.disruptor.BatchEventProcessor;
import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.SleepingWaitStrategy;

/**
 * 
 * <p>Description: 异步处理日志消息数据</p>
 * @since 2017年3月8日
 * @author lenovo
 * <p>Copyright:Copyright(c)2017</p>
 */
public class SystemLogDisruptor {

	public final static int BUFFER_SIZE = 1024 * 4;
	private final static RingBuffer<SystemLog> ringBuffer;
	private final static EventTranslatorOneArg<SystemLog, SystemLog> translator;

	static {
		//消息缓冲区(如果使用createSingleProducer，会导致ringBuffer分配的seq重复，部分数据放到同一个内存空间了)
		ringBuffer = RingBuffer.createMultiProducer(new EventFactory<SystemLog>() {  
            @Override  
            public SystemLog newInstance() {  
                return new SystemLog();  
            }  
        }, BUFFER_SIZE, new SleepingWaitStrategy());  
		
        //创建消息处理器  
        BatchEventProcessor<SystemLog> logProcessor = new BatchEventProcessor<SystemLog>(  
                ringBuffer, ringBuffer.newBarrier(), new SystemLogEventHandler());  
        
        //消息转换器(把生产者产生的数据，设置到缓冲区中)
        translator = new EventTranslatorOneArg<SystemLog, SystemLog>() {
    		public void translateTo(SystemLog bufferLog, long seq, SystemLog producerLog) {
    			bufferLog.setCreateTime(producerLog.getCreateTime());
    			bufferLog.setEntityType(producerLog.getEntityType());
    			bufferLog.setLogType(producerLog.getLogType());
    			bufferLog.setEntityId(producerLog.getEntityId());
    			bufferLog.setErrorMessage(producerLog.getErrorMessage());
    			bufferLog.setOperateMessage(producerLog.getOperateMessage());
    			bufferLog.setNewData(producerLog.getNewData());
    			bufferLog.setOrigData(producerLog.getOrigData());
    		}
    	};
    	
    	//创建线程池(启动一个线程，处理接收到的消息)  
        ExecutorService executors = Executors.newSingleThreadExecutor();
        executors.submit(logProcessor);
	}
	
	/**
	 * 
	 * 方法用途: 异常处理消息<br>
	 * 实现步骤: <br>
	 * @param log
	 */
	public static void processLog(SystemLog log) {
		ringBuffer.publishEvent(translator, log);
	}
}
