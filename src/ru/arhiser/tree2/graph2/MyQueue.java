package ru.arhiser.tree2.graph2;

/**
 * Класс реализующий структуру Очередь.
 * Она нужна для обхода графа в ширину.
 * Реализуем на массиве.
 */
public class MyQueue {
    /**
     * Размер очереди.
     */
    private int size = 10;
    /**
     * Массив, на нем реализуем очередь.
     */
    private int[] array;
    /**
     * Поле хранит указатель на голову очереди.
     */
    private int head;
    /**
     * Поле хранит указатель на хвост очереди.
     */
    private int tail;
    /**
     * Поле хранит количество элементов в очереди.
     */
    private int count;

    /**
     * В конструкторе инициализирум
     * поля.
     */
    public MyQueue() {
        array = new int[size];
        head = 0;
        tail = -1;
        count = 0;
    }

    /**
     * Метод добавления в очередь.
     *
     * @param v вершина (элемент).
     */
    public void insert(int v) {
        /*Проверка, чтоб не превышать лимит размера очереди.
         * Если превысим, то начнем перезаписывать элементы в ячейках,
         * но не будет ArrayIndexOutBoundException*/
        if (tail == size - 1) {
            tail = -1;
        }
        array[++tail] = v;
        count++;
    }

    /**
     * Метод удаляет элемент из очереди из
     * головы очереди.
     *
     * @return удаляемый элемент.
     */
    public int remove() {
        /*Проверка на заполнение очереди.*/
        if (head == size) {
            head = 0;
        }
        count--;
        return array[head++];
    }

    /**
     * Метод, проверяющий пустая очередь или нет.
     *
     * @return boolean result.
     */
    public boolean isEmpty() {
        return count == 0;
    }


}
