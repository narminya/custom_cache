package org.example.service.impl;

import org.example.service.ICache;

import java.util.HashMap;
import java.util.SortedMap;
import java.util.TreeMap;

public class LFUCache<K, V> implements ICache<K, V> {
    public HashMap<K, V> map = new HashMap<>();
    SortedMap<K, Integer> sorted = new TreeMap<>();
    private final int maxSize;

    public LFUCache(int maxSize) {
        this.maxSize = maxSize;
    }

    @Override
    public boolean set(K key, V value) {

        if (!map.containsKey(key)) {
            if (maxSize <= map.size()) {
                evictLeastUsedData();
            }
            map.put(key, value);
            sorted.put(key, 0);
            return true;
        } else {
            return false;
        }

    }

    public void evictLeastUsedData() {
        K key = sorted.lastKey();
        if (key !=null){
            sorted.remove(key);
            map.remove(key);
        }

    }

    @Override
    public V get(K key) {
        reCache(key);
        return map.get(key);
    }

    public void reCache(K key) {
        try {
            if (sorted.get(key) != null) {
                int current = sorted.get(key);
                sorted.remove(key);
                sorted.put(key, ++current);
            }

        } catch (Exception ex) {
            //todo
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
        sorted.clear();
        map.clear();
    }
}
