package org.amqueue.api;

public interface Exchange {
    void bind(Queue queue, Condition cond);

    void publish(String routingKey);

    void delete();
}
