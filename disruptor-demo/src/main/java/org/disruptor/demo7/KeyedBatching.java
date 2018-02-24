package org.disruptor.demo7;

import java.util.ArrayList;
import java.util.List;

import com.lmax.disruptor.EventHandler;

public class KeyedBatching implements EventHandler<KeyedBatching.KeyedEvent> {

	private static final int MAX_BATCH_SIZE = 100;
	private List<Object> batch = new ArrayList<Object>();

	@Override
	public void onEvent(KeyedEvent event, long sequence, boolean endOfBatch)
			throws Exception {

		batch.add(event.data);

		if (endOfBatch || batch.size() >= MAX_BATCH_SIZE) {
			processBatch(batch);
		}

	}

	private void processBatch(List<Object> batch) {
		// do work.
		System.out.println("------------------" + batch.size());
		for (int i = 0; i < 1000000; i++) {
			
		}
		batch.clear();
	}

	public static class KeyedEvent {
		Object data;
	}
}
