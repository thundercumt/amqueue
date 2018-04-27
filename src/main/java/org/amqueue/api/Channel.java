package org.amqueue.api;

/**
 * Multiple channels may reuse a single connection
 * @author thundercumt
 *
 */
public interface Channel {
    Connection getConnection();
}
