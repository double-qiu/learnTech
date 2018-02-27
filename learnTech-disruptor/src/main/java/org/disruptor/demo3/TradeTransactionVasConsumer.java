package org.disruptor.demo3;


import org.disruptor.TradeTransaction;

import com.lmax.disruptor.EventHandler;

public class TradeTransactionVasConsumer implements EventHandler<TradeTransaction> {  
	  
    @Override  
    public void onEvent(TradeTransaction event, long sequence,  
            boolean endOfBatch) throws Exception {  
        //do something....  
    		throw new RuntimeException("TradeTransactionVasConsumer");
    }  
}
