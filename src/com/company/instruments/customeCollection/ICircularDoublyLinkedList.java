package com.company.instruments.customeCollection;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

// public interface ICircularDoublyLinkedList<T> extends List<T> {
public interface ICircularDoublyLinkedList<T> extends Cloneable {
    boolean add(T t);

    int size();

    boolean isEmpty();

    boolean contains(Object o);

    @NotNull
    Object[] toArray();

    boolean remove(Object o);

    void clear();

    T get(int index);

    T set(int index, T element);

    T remove(int index);

    int indexOf(Object o);

    //todo need writing tests
    boolean contains(FindPredicate<T> func);

    T find(FindPredicate<T> func);

    Iterator<T> getIterator();

    //<T1> T1[] toArray(T1[] array, SelectPredicate<T> predicate);

    //<E> E[] select(SelectPredicate<T, E> predicate);
}

