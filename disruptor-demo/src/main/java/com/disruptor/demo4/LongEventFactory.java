package org.disrupto.demo4;

import com.lmax.disruptor.EventFactory;

public class LongEventFactory implements EventFactory<LongEvent> {

	@Override 
    public LongEvent newInstance() { 
        return new LongEvent(); 
    }
}
