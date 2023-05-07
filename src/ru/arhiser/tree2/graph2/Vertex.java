package ru.arhiser.tree2.graph2;

/**
 * Класс описывает узел графа (вершину).
 */
public class Vertex {
    /*Для упрощения задачи оставим поля public,
     * чтоб не делать геттеры и сеттеры.*/
    /**
     * Имя узла (вершины) графа.
     */
    public char name;
    /**
     * Переменная показывающая, был ли узел
     * графа посещен или нет.
     * Нужна для обхода в глубину.
     */
    public boolean isVisited;

    /*Инициализируем поля в конструкторе.*/
    public Vertex(char name) {
        this.name = name;
        this.isVisited = false; // по дефолту.
    }
}
