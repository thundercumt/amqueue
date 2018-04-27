package org.amqueue.storage.level;

import java.util.Map;
import java.util.TreeMap;

public class Row {
    TreeMap<Object, Object> cells;

    public Row(Map<Object, Object> cells) {
        this.cells = new TreeMap<>(cells);
    }

    public Object cell(Object key) {
        return cells.get(key);
    }
    
    public Object cell(Object key, Object value) {
        return cells.put(key, value);
    }
}
