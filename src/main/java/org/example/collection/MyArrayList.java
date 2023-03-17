package org.example.collection;

import java.util.Arrays;
import java.util.Iterator;

public class MyArrayList<E> implements CustomList<E> {

    private E[] elements;
    private int size;

    public MyArrayList(int initialSize) {
        if (initialSize < 0) {
            throw new IllegalArgumentException("size must be bigger then: " + initialSize);
        }
        this.elements =(E[]) new Object[initialSize];
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public int size() {
        return this.size;
    }

    private void checkCapacity(int requiringCapacity) {
        if (requiringCapacity > elements.length) {
            Object[] oldElements = this.elements;
            int newSize = this.size * 2 + 1;
            this.elements = (E[]) Arrays.copyOf(oldElements, newSize);
        }
    }

    @Override
    public boolean add(E e) {
        checkCapacity(this.size + 1);
        elements[this.size++] = e;
        return true;
    }

    @Override
    public boolean add(int index, E e) {
        if (index < 0 || index > (this.size + 1)) throw new IllegalArgumentException();
        checkCapacity(this.size + 1);
        System.arraycopy(this.elements, index, e, index + 1, size - index);
        elements[index] = e;
        this.size++;
        return true;
    }

    @Override
    public void remove(E e) {
        for (int i = 0; i < this.size; i++) {
            if (e.equals(this.elements[i])) {
                fastRemove(i);
            }
        }

    }

    private void fastRemove(int index) {
        int numberToRemove = this.size - index - 1;
        if (numberToRemove > 0) {
            System.arraycopy(this.elements, index + 1, this.elements, index, numberToRemove);
        }
        this.elements[--this.size] = null;
    }

    @Override
    public E get(int index) {
        return this.elements[index];
    }

    @Override
    public void set(int index, E e) {
        if (index < 0 || index > (this.size + 1)) throw new IllegalArgumentException();
        checkCapacity(this.size + 1);
        this.elements[index] = e;
    }

    @Override
    public boolean contains(E e) {
        for (E curEl : this.elements) {
            if (curEl.equals(e)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void clear() {
        for (int i = 0; i < this.size; i++) {
            this.elements[i] = null;
        }
        this.size = 0;
    }

    @Override
    public E[] toArray() {
        return this.elements;
    }

    @Override
    public Iterator<E> iterator() {
        return new CustomIterator<>();
    }

    public class CustomIterator<E> implements Iterator<E> {
        private int current = 0;
        @Override
        public boolean hasNext() {
            return this.current < size();
        }

        @Override
        public E next() {
            E value = (E) elements[current++];
            return value;
        }
    }


}
