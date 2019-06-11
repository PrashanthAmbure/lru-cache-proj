/**
 * 
 */
package com.pramatiinc.core.ds;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author pambure
 *
 * @param <E>
 */
public class MyDoublyLinkedList<E> {
	private Node head;
	private Node tail;
	private int size;
	
	/**
	 * @author pambure
	 * Node that holds data, pointer to next and previous nodes.
	 */
	private class Node {
		E data;
		Node previous;
		Node next;
		
		public Node(E data) {
			this.data = data;
		}
	}

	/**
	 * @author pambure
	 * An optimized version of AbstractList.Itr 
	 */
	private class DLLIterator implements Iterator<E> {
	    private Node cursor = head;

	    @Override
	    public boolean hasNext() {
	        return cursor != null;
	    }

	    @Override
	    public E next() {
	        if (!hasNext()) throw new NoSuchElementException("No next element.");
	        E element = cursor.data;
	        cursor = cursor.next;
	        return element;
	    }

	    @Override
	    public void remove() {
	        throw new UnsupportedOperationException("Remove not supported.");
	    }
	}
	
	/**
	 * Returns an iterator over the elements in this doubly linked list in proper sequence
	 * @return an iterator over the elements in this doubly linked list in proper sequence
	 */
	
	public Iterator<E> getIterator() {
		return new DLLIterator();
	}
	
	public boolean isEmpty() {
		return head == null;
	}
	
	/**
	 * Adds the node to the end in the double linked list always.
	 * If the double linked list is empty HEAD and TAIL point to the same node.
	 * Else the double linked list is not empty TAIL will be advanced to point to the new node added at the last, HEAD remains as is.
	 * 
	 * @param data the element to be added at the last.
	 */
	
	public void addToLast(E data) {
//		Allocate the node with the data
		Node node = new Node(data);
		
//		New node will be TAIL because we are adding to the last.
		tail = node;
		
//		If the DLL is empty, then mark the new node as HEAD
		if (isEmpty()) {
			head = node;
			size++;
			return;
		} 
		
//		Else find the existing TAIL of the DLL
		Node current = head;
		while(current.next != null) {
			current = current.next;
		}
		
//		Mark the existing TAIL next to newly created node.
		current.next = node;
		
//		Mark the new TAIL previous to existing TAIL.
		node.previous = current;
		
//		As the element is added increment the size counter.
		size++;
	}
	
	/**
	 * Deletes the first node always from the double linked list. 
	 * If the node is empty warns with a message and does nothing.
	 * Else dereferences HEAD and points it to the next node, TAIL remains as is.
	 * 
	 * TODO: Future state to throw an exception when trying to delete a empty double linked list.
	 */
	
	public E deleteFirstNode() {
//		If the DLL is empty there is nothing to delete.
		if (isEmpty()) {
			System.out.println("DLL is empty, nothing to delete!");
			return null;
		}
		
//		Dereference HEAD with it's next.
		E data = head.data;
		head = head.next;
		
//		As the element is deleted decrement the size counter.
		size--;
		
		return data;
	}
	
	
	/**
	 * Iterates through the head to tail and displays the elements with , separated.
	 */
	public void printNodesHeadToTail() {
		System.out.print("Keys in the cache: ");
		Node current = head;
		while(current != null) {
			if (current.next != null)
				System.out.print(current.data+",");
			else
				System.out.print(current.data);
			current = current.next;
		}
		System.out.println();
	}
	
	/**
	 * Moves the node to the last always.
	 * If the node to move is at the last already then does nothing.
	 * Else identify the node and move it to TAIL and update previous and next for the subsequent nodes accordingly.
	 * 
	 * @param data node to shift.
	 */
	
	public void shiftToLast(E data) {
//		Tail node.
		Node lastNode = tail;
		
//		If last node is the key element to shift then do nothing because it is in LRU position already.
		if (lastNode.data == data) {
			return;
		}
		
		Boolean keyElementIsFirst = Boolean.TRUE;
		Node keyNode = head;
		
//		Traverse through HEAD node to identify the key node to be pushed to LRU position.
		while(keyNode.data != data) {
			keyNode = keyNode.next;
			keyElementIsFirst = Boolean.FALSE;
		}
		
		/*
		 * If the keyNode is not at the first position then
		 * keyNode's previous nodes next has to be referenced to keyNodes next
		 * and lastNode next will be keyNode and keyNode next becomes null as it is last node and that will be the TAIL too
		 * and previous becomes lastNode.
		 */
		
		/*
		 * If keyNode is at the first position then
		 * keyNode next element will be HEAD and keyNode will be TAIL
		 * and keyNode next and that nodes previous should be come null as that is the HEAD now
		 * lastNode next will be the keyNode and keyNode next becomes null as it is the last node nd that will be the TAIL too
		 * and previous becomes lastNode.
		 */
		
		if (!keyElementIsFirst) {
			keyNode.previous.next = keyNode.next;
			lastNode.next = keyNode;
			keyNode.previous = lastNode;
			keyNode.next = null;
			tail = keyNode;
		} else {
			head = keyNode.next;
			keyNode.next.previous = null;
			lastNode.next = keyNode;
			keyNode.previous = lastNode;
			keyNode.next = null;
			tail = keyNode;
		}
		
	}
	
	/**
	 * Determines the number of nodes in the doubly linked list.
	 */
	
	public int size() {
		return size;
	}
	
	
	/**
	 * Returns the index of the first occurrence of the specified element 
     * in this list, or -1 if does not contain the element. 
     * More formally, returns the lowest index
     * 
	 * @param data the index to be searched for.
	 * @return index position if
	 */
	public int indexOf(E data) {
		Node current = head;
		for (int i = 1; i <= size; i++) {
			if (current.data == data) {
				return i;
			}
			current = current.next;
		}
		return -1;
	}
	
	/**
	 * Returns <tt>true</tt> if this list contains the specified element. 
	 * 
	 * @param data element whose presence in this list is to be tested 
     * @return <tt>true</tt> if this list contains the specified element 
	 */
	public boolean contains(E data) {
		Node current = head;
		for (int i = 1; i <= size; i++) {
			if (current.data == data) {
				return Boolean.TRUE;
			}
			current = current.next;
		}
		return Boolean.FALSE;
	}
}