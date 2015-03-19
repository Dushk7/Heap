/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package heap;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author INS
 */
public class DataBaseHeap <T extends Comparable<T>> implements HeapInterface{
        static final String DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost/e11488"; //replace e11500 with your registration number

	static final String USERNAME = "root"; // replace e11500 with your registration number
	static final String PASSWORD = "wtf1486837";
        
        private Connection conn = null;
        private Statement stmt = null;
        private ResultSet rs = null;
        
        
        private int size = 0;
        public DataBaseHeap(){
            
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Connecting to a selected database...");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/e11488new", USERNAME, PASSWORD);
			if(conn != null){
                            System.out.println("Connected database successfully...");
                            stmt = conn.createStatement();
                        }
			

		}
		catch(SQLException e)
		{   
                    try {
                        System.out.println("No such database. Creating new...!");
                        conn = DriverManager.getConnection("jdbc:mysql://localhost/", USERNAME, PASSWORD);
                        stmt = conn.createStatement();
                        String sql = "CREATE Database e11488new;";
                        stmt.executeUpdate(sql);
                        System.out.println("Database created\nCreating table...!");
                        stmt.close();
                        conn.close();
                        
                        conn = DriverManager.getConnection("jdbc:mysql://localhost/e11488new", USERNAME, PASSWORD);
                        stmt = conn.createStatement();
                        sql = "CREATE TABLE heap (indexVal integer, dataVal varchar(20));";
                        stmt.executeUpdate(sql);
                        System.out.println("Table created...!");
                    } catch (SQLException ex) {
                        Logger.getLogger(DataBaseHeap.class.getName()).log(Level.SEVERE, null, ex);
                    }
		} catch (ClassNotFoundException ex) {
                Logger.getLogger(DataBaseHeap.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    private T getData(int i){
        String val = null;
        try {
            String sql = "SELECT dataVal FROM heap where indexVal = "+i+"";
            rs = stmt.executeQuery(sql);
            rs.next();
            val = rs.getString(1);
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseHeap.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (T) val;
    }
    
    private boolean hasIndex(int index){
        try {
            String sql = "SELECT * FROM heap where indexVal = "+index+"";
            rs = stmt.executeQuery(sql);
            return rs.next();
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseHeap.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public void add(Comparable value) {
            try {
                String sql = "INSERT INTO heap VALUES ("+size+", "+value+") ";
                stmt.executeUpdate(sql);
                size++;
            } catch (SQLException ex) {
                Logger.getLogger(DataBaseHeap.class.getName()).log(Level.SEVERE, null, ex);
            }
            bubbleUp();
    }

    @Override
    public Comparable remove() {
        T val = null;
        try {
            val = getData(0);
            String sql = "delete from heap where indexVal = 0;";
            stmt.executeUpdate(sql);
            sql = "update heap set indexVal = 0 where indexVal = "+(--size)+" ";
            //System.out.println("size is : " + size);
            stmt.executeUpdate(sql);
            
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseHeap.class.getName()).log(Level.SEVERE, null, ex);
        }
        bubbleDown();
        return val;
    }

    @Override
    public void show() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        for(int i = 0; i < size; i++) System.out.print(getData(i)+" ");
        System.out.println("=======");
    }

    @Override
    public boolean isEmpty() {
        rs = null;
        try {
            String sql = "SELECT * FROM heap";
            rs = stmt.executeQuery(sql);
            return !rs.next();
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseHeap.class.getName()).log(Level.SEVERE, null, ex);
        }
            //if(rs != null) System.out.println("rs is not null now");
        return true;
    }
    
    public void bubbleUp() { 
	if(size == 0) throw new IllegalStateException("Shape error");
	int index = size - 1;  
	while(!isRoot(index)) { 
	    if(myParent(index).compareTo(getData(index)) <= 0) break; 
	    /* else part */
            //System.out.println("compare value is : " + myParent(index).compareTo(getData(index)));
	    swap(parent(index), index); 
	    index = parent(index);
	}
    }
    
    boolean isRoot(int index) {	return (index == 0);   }
    int leftChild(int index)  { return 2 * index + 1;  }
    int parent(int index)     { return (int)((index - 1) / 2); }
    int rightChild(int index) { return 2 * index + 2;   }
    T myParent(int index) { return (T) getData(parent(index)); }
    boolean hasLeftChild(int i) { return hasIndex(2*i+1); } 
    boolean hasRightChild(int i){ return hasIndex(2*i+2); }
    
    
    
    private void swap(int a, int b){
            try {
                String sql = "update heap set indexVal = -1 where indexVal = "+a+" ";
                stmt.executeUpdate(sql);
                sql = "update heap set indexVal = "+a+" where indexVal = "+b+" ";
                stmt.executeUpdate(sql);
                sql = "update heap set indexVal = "+b+" where indexVal = -1 ";
                stmt.executeUpdate(sql);
            } catch (SQLException ex) {
                Logger.getLogger(DataBaseHeap.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    
    private void bubbleDown() { 
	/* implmement me */
        int index = 0;
        //System.out.println("size is : "+size);
        while(hasLeftChild(index) || hasRightChild(index)){
            int lc = 0,rc = 0;
            
            if(hasLeftChild(index)){
                lc = leftChild(index);
                //System.out.println("lc is : "+ lc);
                if(hasRightChild(index)){
                    rc = rightChild(index);
                    //System.out.println("rc is : "+rc);
                    if(getData(lc).compareTo(getData(rc)) <= 0){
                        if(getData(index).compareTo(getData(lc)) > 0){
                            swap(index, lc);
                            index = lc;
                        }else break;
                    }else{
                        if(getData(index).compareTo(getData(rc)) > 0){
                            swap(index, rc);
                            index = rc;
                        }else break;
                    }
                }else{
                    if(getData(index).compareTo(getData(lc)) > 0){
                        swap(index, lc);
                        index = rc;
                    }else break;
                }
            }else{
                if(hasRightChild(index)){
                    if(getData(index).compareTo(getData(rc)) > 0){
                        swap(index, rc);
                        index = rc;
                    }else break;
                }
            }
            if(index >= size) break;
            
        }
        
    }
   
    
    
}
