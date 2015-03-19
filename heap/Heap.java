/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package heap;

/**
 *
 * @author INS
 * @param <T>
 */
public class Heap <T extends Comparable<T>>{
    HeapInterface heapStore;
    
    /* default storage is an array. which is in ArrayHeap class */
    public Heap(){
        heapStore = (HeapInterface) new ArrayHeap();
    }
    
    /*If heap should use a LinkedList then object from LinkListHeap 
    should be passed as a parameter*/
    
    public Heap(LinkListHeap l){
        heapStore = (HeapInterface) l;
    }
    
    public Heap(DataBaseHeap d){
        heapStore = (HeapInterface) d;
    }
    
    public void add(T value){
        heapStore.add(value);
    }
            
    public boolean isEmpty(){
        return heapStore.isEmpty();
    }
    
    public T remove(){
        return (T) heapStore.remove();
    }
    
    public void show(){
        heapStore.show();
    }
            
    public static void main(String [] args) {
	//Heap heap = new Heap<Integer>();  // use an array as storage
        Heap heap = new Heap<Integer>(new LinkListHeap());   // this is to use a LinkedList as storage
        //Heap heap = new Heap<Integer>(new DataBaseHeap());   // this is to use database as storage
        
	for(int i=0; i<10; i++) {
	    heap.add((int) (Math.random() * 10)); 
	    heap.show();
	}
        System.out.println("");

	System.out.println("You should see sorted numbers");
	while(!heap.isEmpty()) 
	    System.out.print(heap.remove()+ " ");
	
        System.out.println("");
    }
    
}
