package ru.arhiser.reflection;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

public class FieldEnumerator implements Iterable<Field> {

    private Object object;
    private ArrayList<java.lang.reflect.Field> fields = new ArrayList<>();

    public static List<Field> getFields(Object object) {
        ArrayList<Field> fields = new ArrayList<>();
        java.lang.reflect.Field[] fieldArray = object.getClass().getDeclaredFields();
        for (java.lang.reflect.Field field : fieldArray) {
            if (Modifier.isStatic(field.getModifiers())) {
                field.setAccessible(true);
                try {
                    Field fil = new Field(field.getName(), field.get(object));
                    fields.add(fil);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return fields;
    }

    public FieldEnumerator(Object object) {
        this.object = object;
        java.lang.reflect.Field[] fieldArray = object.getClass().getDeclaredFields();
        for (java.lang.reflect.Field field : fieldArray) {
            if (Modifier.isStatic(field.getModifiers())) {
                fields.add(field);
                field.setAccessible(true);
            }
        }
    }

    public void setObject(Object object) {
        this.object = object;
    }

    @Override
    public Spliterator<Field> spliterator() {
        return null;
    }

    @Override
    public Iterator<Field> iterator() {
        return new FieldIterator();
    }

    public class FieldIterator implements Iterator<Field> {
        private int position = 0;

        public FieldIterator() {
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Remove not supported");
        }

        @Override
        public void forEachRemaining(Consumer<? super Field> action) {
            Iterator.super.forEachRemaining(action);
        }

        @Override
        public boolean hasNext() {
            return position < fields.size();
        }

        @Override
        public Field next() {
            java.lang.reflect.Field field = fields.get(position);
            field.setAccessible(true);
            position++;
            try {
                return new Field(field.getName(), field.get(object));
            } catch (IllegalAccessException e) {
                //e.printStackTrace();
                return null;
            }
        }
    }
}
