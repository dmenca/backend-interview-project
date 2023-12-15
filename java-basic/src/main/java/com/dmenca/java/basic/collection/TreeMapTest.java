package com.dmenca.java.basic.collection;

import java.util.Comparator;
import java.util.TreeMap;

public class TreeMapTest {
    public static class Person {
        private Integer age;

        public Person(Integer age) {
            this.age = age;
        }

        public Integer getAge() {
            return age;
        }

    }
    public static void main(String[] args) {
        //相比于`HashMap`来说 `TreeMap` 主要多了对集合中的元素根据键排序的能力以及对集合内元素的搜索的能力
        TreeMap<Person, String> treeMap = new TreeMap<>(new Comparator<Person>() {
            @Override
            public int compare(Person person1, Person person2) {
                int num = person1.getAge() - person2.getAge();
                return Integer.compare(num, 0);
            }
        });
//        TreeMap<Person, String> treeMap = new TreeMap<>((person1, person2) -> {
//            int num = person1.getAge() - person2.getAge();
//            return Integer.compare(num, 0);
//        });
        treeMap.put(new Person(3), "person1");
        treeMap.put(new Person(18), "person2");
        treeMap.put(new Person(35), "person3");
        treeMap.put(new Person(16), "person4");
        treeMap.entrySet().stream().forEach(personStringEntry -> {
            System.out.println(personStringEntry.getValue());
        });
        treeMap.lastEntry();
    }
}
