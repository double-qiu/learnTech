package org.disruptor.demo3;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

import org.disruptor.TradeTransaction;

import com.lmax.disruptor.EventTranslator;
import com.lmax.disruptor.dsl.Disruptor;

public class TradeTransactionPublisher implements Runnable{  
    Disruptor<TradeTransaction> disruptor;  
    private CountDownLatch latch;  
    private static int LOOP=1;//模拟一千万次交易的发生  
  
    public TradeTransactionPublisher(CountDownLatch latch,Disruptor<TradeTransaction> disruptor) {  
        this.disruptor=disruptor;  
        this.latch=latch;  
    }  
  
    @Override  
    public void run() {  
        TradeTransactionEventTranslator tradeTransloator=new TradeTransactionEventTranslator();  
        for(int i=0;i<LOOP;i++){  
            disruptor.publishEvent(tradeTransloator);  
        }  
        latch.countDown();  
    }  
      
} 

class TradeTransactionEventTranslator implements EventTranslator<TradeTransaction>{  
    private Random random=new Random();  
    @Override  
    public void translateTo(TradeTransaction event, long sequence) {  
        this.generateTradeTransaction(event);  
    }  
    
    private TradeTransaction generateTradeTransaction(TradeTransaction trade){  
        trade.setPrice(random.nextDouble()*9999);  
        return trade;  
    }  
} 
