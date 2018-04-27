package org.amqueue.storage.level;

import java.util.TreeMap;

import org.github.jamm.MemoryMeter;

public class MemTable {

    static MemoryMeter meter = new MemoryMeter();
    TreeMap<Object, Row> tab;

    Row insert(Object key, Row row) {
        return tab.put(key, row);
    }

    Row get(Object key) {
        return tab.get(key);
    }

    SSTable dump() {
        SSTable sst = new SSTable();
        return sst;
    }
}
