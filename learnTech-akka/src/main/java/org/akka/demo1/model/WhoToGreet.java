package org.akka.demo1.model;

import java.io.Serializable;

/**
 * 打招呼的人
 * @author DOUBLE
 * @version 1.0
 * @Date 16/1/6 21:41
 */
public class WhoToGreet implements Serializable {
    public final String who;
    public WhoToGreet(String who) {
        this.who = who;
    }
}
