package org.example.collection;


import java.util.*;

public class MyLinkedList<E> implements CustomLinkedList<E>{

    private Node<E> head;
    private Node<E> tail;
    private int size = 0;

    public MyLinkedList() {
    }

    @Override
    public void addFirst(E val) {
        Node<E> temp = new Node<>(val);
        temp.next = head;
        head = temp;
        if (tail == null) {
            tail = temp;
        }
        size++;
    }

    @Override
    public void addLast(E val) {
        if(tail == null) {
            addFirst(val);
            return;
        }
         Node<E> temp = new Node<>(val);
        tail.next = temp;
        tail = temp;
        size++;
    }

    @Override
    public void addAt(int index, E val) {
        if (this.size < index || this.size < 0) throw new IllegalArgumentException();
        Node<E> temp = new Node<>(val);
        Node<E> node = head;
        for (int i = 0; i < index - 1; i++) {
            node = node.next;
        }
        temp.next = node.next;
        node.next = temp;

    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean remove(int index) {
        if (size < index) return false;
        Node<E> node = head;
        for (int i = 0; i < index - 1; i++) {
            node = node.next;
        }
        node.next = node.next.next;
        size--;
        return false;
    }
    @Override
    public E getElement(int index) {
        if (size < index || index < 0) throw new IllegalArgumentException();
        Node<E> node = head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node.value;

    }
    @Override
    public Iterator<E> getIterator() {
        Node<E> node = head;
        List<E> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(node.value);
            node = node.next;
        }
        return list.iterator();
    }
    public Node<E> getHead() {
        return head;
    }

    public Node<E> getTail() {
        return tail;
    }



    static class Node<E> {
        E value;
        Node<E> next;

        public Node(E value) {
            this.value = value;
        }
        public E getValue() {
            return value;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    ", next=" + next +
                    '}';
        }
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyLinkedList<?> that = (MyLinkedList<?>) o;
        return size == that.size && Objects.equals(head, that.head) && Objects.equals(tail, that.tail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(head, tail, size);
    }

    @Override
    public String toString() {
        return "MyLinkedList{" +
                "head=" + head +
                ", tail=" + tail +
                ", size=" + size +
                '}';
    }
}
