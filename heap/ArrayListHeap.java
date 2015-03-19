/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Heap;

import java.util.ArrayList;

/**
 *
 * @author INS
 */
public class ArrayListHeap <T extends Comparable<T>> implements HeapInterface{
    
    
    //private T[] array;
    //private int size; 
    private ArrayList<T> arrayList;
    public ArrayListHeap() { 
	arrayList = new ArrayList();
	//size  = 0; 
    }

    boolean isRoot(int index) {	return (index == 0);   }
    int leftChild(int index)  { return 2 * index + 1;  }
    int parent(int index)     { return (int)((index - 1) / 2); }
    int rightChild(int index) { return 2 * index + 2;   }
    T myParent(int index) { return (T) arrayList.get(parent(index)); }
    boolean hasLeftChild(int i) { return leftChild(i) < arrayList.size(); } 
    boolean hasRightChild(int i){ return rightChild(i) < arrayList.size(); } 

    private void swap(int a, int b) { 
	T tmp = (T) arrayList.get(a); 
	arrayList.set(a, arrayList.get(b));
	arrayList.set(b, tmp);
    }

    @Override
    public boolean isEmpty() { return arrayList.isEmpty(); } 


    /* adding heap */
    /*public void add(T value) { 
	//if(size == default_size) throw new IllegalStateException("Full array");
	//array[size++] = value; 
        arrayList.add(value);
	bubbleUp(); 
    }*/

    public void bubbleUp() { 
	if(arrayList.size() == 0) throw new IllegalStateException("Shape error");
	int index = arrayList.size() - 1;  
	while(!isRoot(index)) { 
	    if(myParent(index).compareTo(arrayList.get(index)) <= 0) {
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
	T res = arrayList.remove(0); /* root */
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
                    if(arrayList.get(lc).compareTo(arrayList.get(rc)) <= 0){
                        if(arrayList.get(index).compareTo(arrayList.get(lc)) > 0){
                            swap(index, lc);
                            index = lc;
                        }else break;
                    }else{
                        if(arrayList.get(index).compareTo(arrayList.get(rc)) > 0){
                            swap(index, rc);
                            index = rc;
                        }else break;
                    }
                }else{
                    if(arrayList.get(index).compareTo(arrayList.get(lc)) > 0){
                        swap(index, lc);
                        index = rc;
                    }else break;
                }
            }else{
                if(hasRightChild(index)){
                    if(arrayList.get(index).compareTo(arrayList.get(rc)) > 0){
                        swap(index, rc);
                        index = rc;
                    }else break;
                }
            }
        }
        
    }


    public void show() {
	for(int i=0; i<arrayList.size(); i++) 
	    System.out.print(arrayList.get(i) + " "); 
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
        arrayList.add((T) value);
	bubbleUp();
    }
    
    
}
