/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caching;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author Ian Gortan
 */
public class Cache {
    final static int CACHE_CAPACITY = 1000;
    
    public static void main(String[] args) throws Exception {
        ConcurrentHashMap<String,String> store=new ConcurrentHashMap<String,String>(CACHE_CAPACITY); 
        EvictionList evict = new EvictionList(store);
        
        ServerSocket m_ServerSocket = new ServerSocket(12032);
        int id = 0; 
        System.out.println("Cache started .....");
        while (true) {
            Socket clientSocket = m_ServerSocket.accept();
            CacheThread server = new CacheThread (clientSocket, store, evict, CACHE_CAPACITY);
            server.start();
     
    }
  }
}
