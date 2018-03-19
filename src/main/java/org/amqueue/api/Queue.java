package org.amqueue.api;

public interface Queue {
    void declare(Session session, String name, boolean autoDelete, boolean exclusive);
}
