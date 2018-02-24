package org.disruptor.demo4;

import com.lmax.disruptor.EventHandler;

public class LongEventHandler implements EventHandler<LongEvent> { 
	
    @Override 
    public void onEvent(LongEvent longEvent, long sequence, boolean endOfBatch) throws Exception { 
        System.out.println(longEvent.getValue()); 
    } 
}
