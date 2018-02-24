package org.disruptor.demo8;

import java.util.ArrayList;
import java.util.List;

import com.lmax.disruptor.EventHandler;

/**
 * 
 * <p>Description: 异步保存日志信息到数据库中</p>
 * @since 2017年3月8日
 * @author lenovo
 * <p>Copyright:Copyright(c)2017</p>
 */
public class SystemLogEventHandler implements EventHandler<SystemLog> {
	
	private static final int MAX_BATCH_SIZE = 200;
	private List<SystemLog> batch = new ArrayList<SystemLog>();

	@Override
	public void onEvent(SystemLog event, long sequence, boolean endOfBatch)
			throws Exception {
		batch.add(event);

		if (endOfBatch || batch.size() >= MAX_BATCH_SIZE) {
			processBatch(batch);
		}
	}

	/**
	 * 
	 * 方法用途: 批量保存数据<br>
	 * 实现步骤: <br>
	 * @param batch
	 */
	private void processBatch(List<SystemLog> batch) {
		System.out.println("insert batch size: " + batch.size());
//		Object[][] args = new Object[batch.size()][4];
//		int i = 0;
//		for (SystemLog log : batch) {
//			args[i][0] = log.getEntityType();
//			args[i][1] = log.getLogType();
//			args[i][2] = log.getOperateMessage();
//			args[i][3] = log.getCreateTime();
//			i++;
//		}
//		daoTemplate.batchSave(SQL, args);
		for (int i = 0; i < 100000; i++) {
			
		}
		batch.clear();
	}
}
