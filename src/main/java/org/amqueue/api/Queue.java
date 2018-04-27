package org.amqueue.api;

public interface Queue {
    void name(String name);

    String name();

    void exclusive(boolean b);

    boolean exclusive();

    void durable(boolean b);

    boolean durable();
    
    Message consume();
}
