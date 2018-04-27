package org.amqueue.api;

public interface API {
    Queue declareQueue(String name, boolean durable, boolean exclusive, boolean autoCreate) throws AMQueueException;

    Exchange declareExchange(String name, boolean durable, boolean exclusive, boolean autoCreate)
            throws AMQueueException;
}
