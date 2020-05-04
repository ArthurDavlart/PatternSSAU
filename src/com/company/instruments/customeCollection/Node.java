package com.company.instruments.customeCollection;

import java.lang.reflect.ParameterizedType;
import java.util.LinkedList;
import java.util.Objects;

public class Node<T> {
    private Node<T> previous;
    private T current;
    private Node<T> next;

    public Node(T current){
        this.next = this;
        this.current = current;
        this.previous = this;
    }

    public Node<T> getPrevious() {
        return previous;
    }

    public void setPrevious(Node<T> previous) {
        this.previous = previous;
    }

    public T getCurrent() {
        return current;
    }

    public void setCurrent(T current) {
        this.current = current;
    }

    public T getNewItem(Class c){
        T newItem = null;
        try {
            newItem = (T) c.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return newItem;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }

    protected Node(Node<T>  previous, T current, Node<T>  next) {
        this.previous = previous;
        this.current = current;
        this.next = next;
    }

    public boolean equalsItems(Object o) {
        if (current == null && o == null){
            return true;
        }

        if ((current != null && o == null)
            || (current == null && o != null)){
            return false;
        }
         return current.equals(o);
    }
}
