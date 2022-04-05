package demo.generic;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class GenericDemo {
    public static void main(String[] args) {
//        List linkedList = new LinkedList();
//        linkedList.add("work");
//        linkedList.add(1);
//        Iterator iterator = linkedList.iterator();
//        while (iterator.hasNext()){
//            System.out.println(iterator.next());

        GenericClassExample<String> example = new GenericClassExample<>("he");
        GenericClassExample<Integer> example1 = new GenericClassExample<>(123);
        System.out.println(example.getMember().getClass());
        System.out.println(example1.getMember().getClass());
        }
    }

