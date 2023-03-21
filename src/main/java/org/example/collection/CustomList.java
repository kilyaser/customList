package org.example.collection;

public interface CustomList<E> extends Iterable<E>{
    boolean isEmpty();
    int size();
    boolean add(E e);
    boolean add(int index, E e);
    void remove(E e);
    E get(int index);
    void set(int index, E e);
    boolean contains(E e);
    void clear();
    E[] toArray();
}
