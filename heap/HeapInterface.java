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
public interface HeapInterface <T extends Comparable<T>>{
    public void add(T value);
    public T remove();
    public void show();
    public boolean isEmpty();
}
