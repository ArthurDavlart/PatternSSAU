package com.company.instruments.customeCollection;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

public class CircularDoublyLinkedList<T> implements ICircularDoublyLinkedList<T>{

    private Node<T> head;
    private Node<T> last;
    private int size = 0;

    public CircularDoublyLinkedList(){

    }

    //region override method


    @Override
    public boolean contains(FindPredicate<T> func) {
        if(this.size == 0){
            return false;
        }

        if (func.find(this.head.getCurrent())){
            return true;
        }

        for (Node<T> x = this.head.getNext(); x != this.head; x = x.getNext()) {
            if (func.find(x.getCurrent())) {
                return true;
            }
        }

        return false;
    }

    @Override
    public T find(FindPredicate<T> func) {
        if(this.size == 0){
            return null;
        }

        if (func.find(this.head.getCurrent())){
            return this.head.getCurrent();
        }

        for (Node<T> x = this.head.getNext(); x != this.head; x = x.getNext()) {
            if (func.find(x.getCurrent())) {
                return x.getCurrent();
            }
        }

        return null;
    }

    @Override
    public Iterator<T> getIterator() {
        return new ListIterator(this.head, this.size);
    }

    // todo need refactor
//    @Override
//    public <T1> T1[] toArray(T1[] array, SelectPredicate<T> predicate) {
//        T1[] result = (T1[]) java.lang.reflect.Array.newInstance(
//        array.getClass().getComponentType(), size);
//        if(this.size == 0){
//            return result;
//        }
//
//        result[0] = (T1) predicate.condition(this.head.getCurrent());
//        int count = 1;
//
//        for (Node<T> x = this.head.getNext(); x != this.head; x = x.getNext()) {
//            result[count] = (T1) predicate.condition(x.getCurrent());
//            count++;
//        }
//
//        return result;
//    }

//    // todo мои жалкие попытки написать селект, попросить совета у того кто знает.
//    @Override
//    public <E> E[] select(SelectPredicate<T, E> predicate) {
//        final E[] a = (E[]) Array.newInstance(Class<E>, s);
//        E[] result = (E[]) java.lang.reflect.Array.newInstance(
//        array.getClass().getComponentType(), size);
//        if(this.size == 0){
//            return null;
//        }
//
//        result.add((E) predicate.condition(this.head.getCurrent()));
//
//        for (Node<T> x = this.head.getNext(); x != this.head; x = x.getNext()) {
//            result.add((E) predicate.condition(this.head.getCurrent()));
//        }
//
//        return result.toArray(array);
//    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return findElement(o) != null;
    }

    @Override
    public Object[] toArray() {
        Object[] result = new Object[this.size];

        if (this.size == 0){
            return result;
        }

        result[0] = this.head.getCurrent();

        int counter = 1;

        for (Node<T> x = this.head.getNext(); x != this.head; x = x.getNext()) {
            result[counter] = x.getCurrent();
            counter++;
        }

        return result;
    }

    @Override
    public boolean add(T t) {
        boolean result = false;

        if (this.size == 0){
            linkFirst(t);
            result = true;
        } else {
            Node<T> newNode = new Node(t);
            newNode.setPrevious(this.last);
            newNode.setNext(this.head);

            this.last.setNext(newNode);
            this.head.setPrevious(newNode);

            this.last = newNode;

            this.size++;
            result = true;
        }

        return result;
    }

    @Override
    public boolean remove(Object o) {
        Node<T> node = findElement(o);

        if (node == null) {
            return false;
        } else {
            unlink(node);
            return true;
        }
    }

    @Override
    public void clear() {
        this.head = null;
        this.last = null;
    }

    @Override
    public T get(int index) {
        checkElementIndex(index);
        return node(index).getCurrent();
    }

    @Override
    public T set(int index, T element) {
        checkElementIndex(index);
        Node<T> x = node(index);
        T oldVal = x.getCurrent();
        x.setCurrent(element);
        return oldVal;
    }

    @Override
    public T remove(int index) {
        checkElementIndex(index);
        return unlink(node(index));
    }

    @Override
    public int indexOf(Object o) {
        int index = 0;

        if (this.head.equalsItems(o)){
            return index;
        }

        index++;

        for (Node<T> x = this.head.getNext(); x != this.head; x = x.getNext()) {
            if (x.equalsItems(o)) {
                return index;
            }
            index++;
        }

        return -1;
    }

    //endregion

    //region private method

    private void linkFirst(T t){
        Node newNode = new Node(t);
        this.head = newNode;
        this.last = newNode;
        this.size++;
    }

    private T unlink(@NotNull Node<T> x){
        final T element = x.getCurrent();
        final Node<T> next = x.getNext();
        final Node<T> prev = x.getPrevious();

        if(this.size == 1){
            T result = this.head.getCurrent();
            this.head = null;
            this.last = null;
            this.size = 0;
            return result;
        } else {
            if (x == this.head){
                this.head = next;
            }

            if (x == this.last){
                this.last = prev;
            }

            prev.setNext(next);
            next.setPrevious(prev);
            this.size--;
            return element;
        }
    }

    private Node<T> findElement(Object o){
        if (this.head.equalsItems(o)){
            return this.head;
        }

        for (Node<T> x = this.head.getNext(); x != this.head; x = x.getNext()) {
            if (x.equalsItems(o)) {
                return x;
            }
        }

        return null;
    }

    /**
     * Tells if the argument is the index of an existing element.
     */
    private boolean isElementIndex(int index) {
        return index >= 0 && index < size;
    }

    private void checkElementIndex(int index) {
        if (!isElementIndex(index))
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    /**
     * Constructs an IndexOutOfBoundsException detail message.
     * Of the many possible refactorings of the error handling code,
     * this "outlining" performs best with both server and client VMs.
     */
    private String outOfBoundsMsg(int index) {
        return "Index: "+index+", Size: "+size;
    }

    /**
     * Returns the (non-null) Node at the specified element index.
     */
    Node<T> node(int index) {
        // assert isElementIndex(index);

        if (index < (size >> 1)) {
            Node<T> x = head;
            for (int i = 0; i < index; i++)
                x = x.getNext();
            return x;
        } else {
            Node<T> x = last;
            for (int i = size - 1; i > index; i--)
                x = x.getPrevious();
            return x;
        }
    }

    public CircularDoublyLinkedList<T> clone(Class c) {
        // todo рефлексией проверить, что Т реализуют интерфейс Clonable
        CircularDoublyLinkedList<T> newList = new CircularDoublyLinkedList<T>();

        if(this.size == 0){
            return null;
        }

        newList.add(this.head.getCurrent());

        if (this.size == 1){
            return newList;
        }

        for (Node<T> x = this.head.getNext(); x != this.head; x = x.getNext()) {
            newList.add(x.getCurrent());
        }

        return newList;
    }

    //endregion

    private class ListIterator implements Iterator<T>{
        private int quantityElements;
        private int currentElement;
        private Node<T> current;


        public ListIterator(Node<T> current, int quantityElements) {
            this.current = current;
            this.quantityElements = quantityElements;
            this.currentElement = 0;
        }

        @Override
        public boolean hasNext() {
            return this.currentElement < this.quantityElements;
        }

        @Override
        public T next() {
            T result = this.current.getCurrent();
            this.current = this.current.getNext();
            this.currentElement++;
            return result;
        }
    }
}
