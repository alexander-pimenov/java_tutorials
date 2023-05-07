package ru.arhiser.iterator;

import java.util.LinkedList;
import java.util.ListIterator;

public class MainDelete {

    public static void main(String[] args) {
        LinkedList<Integer> list = getRandomList();

        //1 вариант
        //ListIterator - имеет более широкий функционал, чем Iterator
        ListIterator<Integer> iterator = list.listIterator();
        while (iterator.hasNext()) {
            Integer item = iterator.next();
            if (item >= 128) {
                iterator.remove();
            }

        }
        //2 вариант - самый быстрый способ удалить элементы из коллекции
        list.removeIf(item -> item >= 128);

    }

    //заполним список случайными числами
    public static LinkedList<Integer> getRandomList() {
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < 100000; i++) {
            list.add((int) Math.round(Math.random() * 256));
        }
        return list;
    }
}
