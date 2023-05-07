package ru.arhiser.iterator;

import ru.arhiser.reflection.Field;
import ru.arhiser.reflection.FieldEnumerator;

public class MainFieldEnum {

    static class Contact {
        int id;
        String name;
        String phone;

        public Contact(int id, String name, String phone) {
            this.id = id;
            this.name = name;
            this.phone = phone;
        }
    }

    public static void main(String[] args) {
        Contact contact = new Contact(123, "Вася Пупкин", "+129381832");

        for(Field field: new FieldEnumerator(contact)) {
            System.out.println(field);
        }
    }
}
