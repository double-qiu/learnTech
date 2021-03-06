package org.akka.demo1.actor;

import org.akka.demo1.model.Greeting;

import akka.actor.UntypedActor;

/**
 * 打印招呼
 * @author DOUBLE
 * @version 1.0
 * @Date 16/1/6 21:45
 */
public class GreetPrinter extends UntypedActor{

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof Greeting)
            System.out.println(((Greeting) message).message);
    }
}
