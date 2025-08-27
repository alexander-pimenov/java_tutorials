package ru.arhiser.hash;

import static java.util.Objects.hash;

/**
 * тут посмотреть как работает хэширование
 */
public class Main {

    public static void main(String[] params) {

        //тут посмотреть как работает хэширование
        int partitions = 10;
        int partition1 = hash("some_key_for_test") % partitions;    //4
        int partition2 = hash("good weather") % partitions;         // -2
        int partition3 = hash("have a nice day") % partitions;      // -5

        System.out.printf("hash() = %s -> partition1 = %s\n", hash("some_key_for_test"), partition1);
        System.out.printf("hash() = %s -> partition2 = %s\n", hash("good weather"), partition2);
        System.out.printf("hash() = %s -> partition3 = %s\n", hash("have a nice day"), partition3);

    }
}
