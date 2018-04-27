package org.amqueue.api;

public class AMQueueException extends RuntimeException {
    public AMQueueException(String msg) {
        super(msg);
    }

    public AMQueueException(String msg, Throwable inner) {
        super(msg, inner);
    }
}
