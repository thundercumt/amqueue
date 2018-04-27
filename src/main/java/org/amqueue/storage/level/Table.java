package org.amqueue.storage.level;

import java.util.Iterator;

import org.amqueue.storage.db.Column;

public interface Table {
    String name();
    Iterator<Column> columnIterator();
    Row get(Object key);
    void add(Row r);
    void remove(Row r);
}
