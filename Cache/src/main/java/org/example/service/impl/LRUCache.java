package org.example.service.impl;

import org.example.service.ICache;

import java.time.LocalDateTime;
import java.util.*;

public class LRUCache<K, V> implements ICache<K, V> {
    public LRUCache(int maxSize) {
        this.maxSize = maxSize;
    }

    private final int maxSize;
    public HashMap<K, V> map = new HashMap<>();
    SortedMap<LocalDateTime, K> temp = new TreeMap<>();

    @Override
    public boolean set(K key, V value) {

        if ( !map.containsKey(key)) {
            if (maxSize <= map.size()) {
                RemoveOldestData();
            }
            map.put(key, value);
            temp.put(LocalDateTime.now(), key);
            return true;
        } else {
            return false;
        }
    }

    public void RemoveOldestData() {
        LocalDateTime oldest = temp.firstKey();
        K key = temp.remove(oldest);
        map.remove(key);
    }

    @Override
    public V get(K key) {
        reCache(key);
        return map.get(key);
    }


    private void reCache(K key) {
        Set<Map.Entry<LocalDateTime, K>> entries = temp.entrySet();
        for (Map.Entry<LocalDateTime, K> entry : entries) {
            if (entry.getValue().equals(key)) {
                temp.remove(entry.getKey());
                temp.put(LocalDateTime.now(), key);
                break;
            }
        }
    }


    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public void clear() {

        temp.clear();
        map.clear();
    }
}
