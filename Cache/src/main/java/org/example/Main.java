package org.example;

import org.example.service.impl.LFUCache;
import org.example.service.impl.LRUCache;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

 //    LRUCache<Integer,Integer> cache = new LRUCache<>(3);
       LFUCache<Integer,Integer> cache = new LFUCache<>(3);

        cache.set(1,1);

        cache.set(2,2);

        cache.get(1);
        cache.set(3,3);
        cache.set(4,4);


        cache.get(1);
        cache.get(1);
        cache.get(3);
        cache.get(3);
        cache.get(1);
//        cache.get(1);

        cache.set(5,5);
        cache.set(6,6);

        System.out.println(cache.map);
    }
}