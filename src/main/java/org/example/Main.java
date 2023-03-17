package org.example;

import org.example.collection.CustomLinkedList;
import org.example.collection.MyLinkedList;

import java.util.Iterator;

public class Main {
    public static void main(String[] args) {

//        Testing customLinkedList

        CustomLinkedList<String> customLinkedList = new MyLinkedList<>();
        customLinkedList.addLast("First");
        customLinkedList.addLast("Second");
        customLinkedList.addLast("Third");

        Iterator<String> iterator = customLinkedList.getIterator();

        System.out.printf("size: %d\n", customLinkedList.size());
        iterator.forEachRemaining(System.out::println);

//      Remove element

        customLinkedList.remove(2);
        System.out.printf("size: %d\n", customLinkedList.size());
        Iterator<String> iteratorAfterRemoving = customLinkedList.getIterator();
        iteratorAfterRemoving.forEachRemaining(System.out::println);


    }
}