package org.disruptor.demo3;


import org.disruptor.TradeTransaction;

import com.lmax.disruptor.EventHandler;

public class TradeTransactionJMSNotifyHandler implements EventHandler<TradeTransaction> {  
	  
    @Override  
    public void onEvent(TradeTransaction event, long sequence,  
            boolean endOfBatch) throws Exception {  
        //do send jms message  
    	System.out.println("TradeTransactionJMSNotifyHandler");
    }  
} 
