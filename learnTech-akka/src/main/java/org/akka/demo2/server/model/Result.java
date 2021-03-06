package org.akka.demo2.server.model;

import java.io.Serializable;

/**
 * @author DOUBLE
 * @version 1.0
 * @Date 16/1/7 10:33
 */
public class Result implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 5727560172917790458L;
    private String word;            //单词
    private int no_of_instances;        //数量

    public Result(String word, int no_of_instances) {
        this.setWord(word);
        this.setNoOfInstances(no_of_instances);
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getWord() {
        return word;
    }

    public void setNoOfInstances(int no_of_instances) {
        this.no_of_instances = no_of_instances;
    }

    public int getNoOfInstances() {
        return no_of_instances;
    }

    public String toString() {
        return word + ":" + no_of_instances;
    }
}
