/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caching;

import static caching.Cache.CACHE_CAPACITY;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author Ian Gortan
 */
public class EvictionList {
    private int front = 0;
    private int victim= 0;
    private int numEntries = 0;
    private String[] list = new String [CACHE_CAPACITY];
    ConcurrentHashMap<String, String> cache;
    
    public EvictionList(ConcurrentHashMap<String, String> store) {
        cache = store;
    }
    
    synchronized public void addKey (String key) {
        
        String keyToEvict ; 
        
        
        if (numEntries == CACHE_CAPACITY) {
            //cache is full so we need to evist entry taht has been there for longest
            keyToEvict = list [victim];
            cache.remove(keyToEvict);
            System.out.println("Evicted key " + victim);
            victim = (victim + 1 ) % CACHE_CAPACITY;
        } else {
            // still space in cache, add entry
            list [front] = key;
            front = (front + 1) % CACHE_CAPACITY;
            numEntries++;
        }
    
    }
}
