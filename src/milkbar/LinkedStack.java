package milkbar;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Atencjusz
 */
import java.util.*;
public class LinkedStack<T> {

    private class Node{
        public T Data;
        public Node Next;
        public Node(T data, Node next){
            Data=data;
            Next=next;
        }
    }
    
    private Node top= null;
    public void push(T item) {
        top = new Node(item, top);
    }

    public T pop() {
        T item = peek();
        top = top.Next;
        return item;                
    }


    public T peek() {
        if (top == null) return null;
        return top.Data;                   
    }


    public int size() {
        int count=0;
        Node node= top;
        while(node!=null){
            count++;
            node=node.Next;
        }
        return count;
    }

    public boolean isEmpty() {
        return top == null;
    }

}
