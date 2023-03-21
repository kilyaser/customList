package org.example.collection;

import java.util.Iterator;

public interface CustomLinkedList<E> {
    void addFirst(E e);
    void addLast(E e);
    void addAt(int index, E e);
    int size();
    Iterator<E> getIterator();
    E getElement(int index);
    boolean remove(int index);

}
