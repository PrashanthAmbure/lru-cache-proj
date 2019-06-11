/**
 * 
 */
package com.pramatiinc.client;

import java.util.Iterator;

import com.pramatiinc.core.ds.MyDoublyLinkedList;

/**
 * @author pambure
 *
 */
public class LRUCache {

	// store keys of cache 
    static MyDoublyLinkedList<Integer> myDLL; 
    
    //maximum capacity of cache  
    static int csize; 
    
    LRUCache(int size) { 
    	myDLL = new MyDoublyLinkedList<Integer>();
        csize = size; 
    }
    
    /* Access key x with in the LRU cache */
    public void access(int x) {
    	int originalIndex = myDLL.indexOf(x);
    	if(myDLL.contains(x)) {
    		myDLL.shiftToLast(x);
    		System.out.println("Key found at position "+originalIndex+", moved to position "+myDLL.indexOf(x));
    	} else {
    		if (myDLL.size() == csize) {
    			int deletedNode = myDLL.deleteFirstNode();
    			myDLL.addToLast(x);
    			System.out.println("Deleted old key "+deletedNode+", inserted new key at position "+myDLL.indexOf(x));
    		} else {
    			myDLL.addToLast(x);
    			System.out.println("Key not found in the cache, inserted at position "+myDLL.indexOf(x));
    		}
    	}
    }
    
 // display contents of cache  
    public void cacheState() { 
    	System.out.print("Keys in the cache: ");
    	Iterator<Integer> i = myDLL.getIterator();
    	StringBuilder sb = new StringBuilder();
		while(i.hasNext()) {
			sb=sb.append(i.next()+",");
		}
		System.out.print(sb.substring(0, sb.length()-1));
		System.out.println();
    } 
    
    
	public static void main(String[] args) {
		LRUCache ca = new LRUCache(3);
		ca.access(2);
		ca.access(10);
		ca.access(2);
		ca.cacheState();
		ca.access(9);
		ca.access(2);
		ca.access(10);
		ca.access(20);
		ca.cacheState();
	}
}
