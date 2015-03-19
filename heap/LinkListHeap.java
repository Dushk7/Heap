/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package heap;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author INS
 */
public class LinkListHeap <T extends Comparable<T>> implements HeapInterface{
    
    
    //private T[] array;
    //private int size; 
    private LinkedList<T> linkList;
    public LinkListHeap() { 
	linkList = new LinkedList();
	//size  = 0; 
    }

    boolean isRoot(int index) {	return (index == 0);   }
    int leftChild(int index)  { return 2 * index + 1;  }
    int parent(int index)     { return (int)((index - 1) / 2); }
    int rightChild(int index) { return 2 * index + 2;   }
    T myParent(int index) { return (T) linkList.get(parent(index)); }
    boolean hasLeftChild(int i) { return leftChild(i) < linkList.size(); } 
    boolean hasRightChild(int i){ return rightChild(i) < linkList.size(); } 

    private void swap(int a, int b) { 
	T tmp = (T) linkList.get(a); 
	linkList.set(a, linkList.get(b));
	linkList.set(b, tmp);
    }

    @Override
    public boolean isEmpty() { return linkList.isEmpty(); } 


    /* adding heap */
    /*public void add(T value) { 
	//if(size == default_size) throw new IllegalStateException("Full array");
	//array[size++] = value; 
        arrayList.add(value);
	bubbleUp(); 
    }*/

    public void bubbleUp() { 
	if(linkList.size() == 0) throw new IllegalStateException("Shape error");
	int index = 
                linkList.size() - 1;  
	while(!isRoot(index)) { 
	    if(myParent(index).compareTo(linkList.get(index)) <= 0) {
                break;
            } else { 
            } 
	    /* else part */
	    swap(parent(index), index); 
	    index = parent(index);
	}
    }  

    /* removing */

    @Override
    public T remove() {
	if(isEmpty()) return null; 
	T res = linkList.remove(0); /* root */
	//array[0] = array[size-1]; 
	//size --;
	bubbleDown();
	return res;
    }

    private void bubbleDown() { 
	/* implmement me */
        int index = 0;
        while(hasLeftChild(index) || hasRightChild(index)){
            int lc = 0,rc = 0;
            
            if(hasLeftChild(index)){
                lc = leftChild(index);
                if(hasRightChild(index)){
                    rc = rightChild(index);
                    if(linkList.get(lc).compareTo(linkList.get(rc)) <= 0){
                        if(linkList.get(index).compareTo(linkList.get(lc)) > 0){
                            swap(index, lc);
                            index = lc;
                        }else break;
                    }else{
                        if(linkList.get(index).compareTo(linkList.get(rc)) > 0){
                            swap(index, rc);
                            index = rc;
                        }else break;
                    }
                }else{
                    if(linkList.get(index).compareTo(linkList.get(lc)) > 0){
                        swap(index, lc);
                        index = rc;
                    }else break;
                }
            }else{
                if(hasRightChild(index)){
                    if(linkList.get(index).compareTo(linkList.get(rc)) > 0){
                        swap(index, rc);
                        index = rc;
                    }else break;
                }
            }
        }
        
    }


    public void show() {
	for(int i=0; i<linkList.size(); i++) 
	    System.out.print(linkList.get(i) + " "); 
	System.out.println("=======");
    }

    
    
    /*public static void main(String [] args) {
	ArrayListStorage heap = new ArrayListStorage<Integer>(); 

	for(int i=0; i<10; i++) {
	    heap.add((int) (Math.random() * 10)); 
	    heap.show();
	}


	System.out.println("You should see sorted numbers");
	while(!heap.isEmpty()) 
	    System.out.print(heap.remove());
	
    }*/

    @Override
    public void add(Comparable value) {
        linkList.add((T) value);
	bubbleUp();
    }
    
    
}
