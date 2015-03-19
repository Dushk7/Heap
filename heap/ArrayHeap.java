/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package heap;

/**
 *
 * @author INS
 */
public class ArrayHeap<T extends Comparable<T>> implements HeapInterface { 
    private int default_size = 10; 
    
    private T[] array;
    private int size; 

    public ArrayHeap() { 
	array = (T[]) new Comparable[default_size];
	size  = 0; 
    }
    
    

    boolean isRoot(int index) {	return (index == 0);   }
    int leftChild(int index)  { return 2 * index + 1;  }
    int parent(int index)     { return (int)((index - 1) / 2); }
    int rightChild(int index) { return 2 * index + 2;   }
    T myParent(int index) { return array[parent(index)]; }
    boolean hasLeftChild(int i) { return leftChild(i) < size; } 
    boolean hasRightChild(int i){ return rightChild(i) < size; } 

    private void swap(int a, int b) { 
	T tmp = array[a]; 
	array[a] = array[b]; 
	array[b] = tmp;
    }

    @Override
    public boolean isEmpty() { return (size == 0); } 


    /* adding heap */
    @Override
    public void add(Comparable value) { 
	if(size == default_size) throw new IllegalStateException("Full array");
	array[size++] = (T) value; 
	bubbleUp(); 
    }

    public void bubbleUp() { 
	if(size == 0) throw new IllegalStateException("Shape error");
	int index = size - 1;  
	while(!isRoot(index)) { 
	    if(myParent(index).compareTo(array[index]) <= 0) break; 
	    /* else part */
	    swap(parent(index), index); 
	    index = parent(index);
	}
    }  

    /* removing */

    @Override
    public T remove() {
	if(isEmpty()) return null; 
	T res = array[0]; /* root */
	array[0] = array[size-1]; 
	size --;
	bubbleDown(); 
	return res;
    }

    private void bubbleDown() { 
	/* implmement me */
        int index = 0;
        while(hasLeftChild(index) || hasRightChild(index)){
            int lc = 0, rc = 0;
            
            if(hasLeftChild(index)){
                lc = leftChild(index);
                if(hasRightChild(index)){
                    rc = rightChild(index);
                    if(array[lc].compareTo(array[rc]) <= 0){
                        if(array[index].compareTo(array[lc]) > 0){
                            swap(index, lc);
                            index = lc;
                        }else break;
                    }else{
                        if(array[index].compareTo(array[rc]) > 0){
                            swap(index, rc);
                            index = rc;
                        }else break;
                    }
                }else{
                    if(array[index].compareTo(array[lc]) > 0){
                        swap(index, lc);
                        index = rc;
                    }else break;
                }
            }else{
                if(hasRightChild(index)){
                    if(array[index].compareTo(array[rc]) > 0){
                        swap(index, rc);
                        index = rc;
                    }else break;
                }
            }
        }
        
    }


    @Override
    public void show() {
	for(int i=0; i<size; i++) 
	    System.out.print(array[i] + " "); 
	System.out.println("=======");
    }


    /*public static void main(String [] args) {
	Heap heap = new Heap<Integer>(); 

	for(int i=0; i<10; i++) {
	    heap.add((int) (Math.random() * 10)); 
	    heap.show();
	}


	System.out.println("You should see sorted numbers");
	while(!heap.isEmpty()) 
	    System.out.print(heap.remove());
	
    }*/


}