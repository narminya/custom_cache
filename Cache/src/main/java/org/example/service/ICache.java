package org.example.service;

public interface ICache <K,V>{
    boolean set(K key, V value);
    V get(K key);
    int size();
    boolean isEmpty();
    void clear();
}
