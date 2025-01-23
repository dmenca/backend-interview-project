package com.dmenca.java.basic.generics;

public class ArrayList<E> implements List<E>{
    private  Object[] elementData;
    private int size;

    public ArrayList() {
        this.elementData = new Object[]{};
    }

    @Override
    public void add(E element) {
        elementData[size++] = element;
    }

    @Override
    public E get(int index) {
        return null;
    }
}
