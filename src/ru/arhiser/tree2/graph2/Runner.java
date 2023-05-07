package ru.arhiser.tree2.graph2;

public class Runner {
    public static void main(String[] args) {
//############################################
// Имеем такой граф:
//        B[1] - C[2] - D[3]
//       /
//      A[0] - E[4] - F[5]
//       \
//        G[6] - H[7]
//############################################

        Graph graph = new Graph();

        /*Создаем вершины графа*/
        graph.addVertex('A'); //0
        graph.addVertex('B'); //1
        graph.addVertex('C'); //2
        graph.addVertex('D'); //3
        graph.addVertex('E'); //4
        graph.addVertex('F'); //5
        graph.addVertex('G'); //6
        graph.addVertex('H'); //7

        /*Создаем связи между вершинами графа*/
        graph.addEdge(0, 1, 1); //A-B
        graph.addEdge(1, 2, 1); //B-C
        graph.addEdge(2, 3, 1); //C-D
        graph.addEdge(0, 4, 1); //A-E
        graph.addEdge(4, 5, 1); //E-F
        graph.addEdge(0, 6, 1); //A-G
        graph.addEdge(6, 7, 1); //G-H

        /*Обходим граф в глубину с вершины А*/
        graph.passInDeep(0); //Начинаем с вершины А
        System.out.println("---------");

        /*Обходим граф в глубину с вершины Е*/
        graph.passInDeep(4); //Начинаем с вершины E
        System.out.println("---------");

        /*Обходим граф в ширину с вершины А*/
        graph.passInWidth(0);
        System.out.println("---------");

        /*Обходим граф в ширину с вершины E*/
        graph.passInWidth(4);


    }
}
