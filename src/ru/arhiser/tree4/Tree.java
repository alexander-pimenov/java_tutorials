package ru.arhiser.tree4;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * "Обход бинарных деревьев: рекурсия, итерации и указатель на родителя"
 * https://habr.com/ru/post/144850/
 */
public class Tree {
    public static void main(String[] args) {

        Node root = new Node("Fruit",
                new Node("Apple",
                        new Node("Red"),
                        new Node("Yellow")),
                new Node("Plum",
                        new Node("Plum green"),
                        new Node("Plum red")));

//        contInOrder(root);
//        root.recursivePreOrder();
//        root.recursiveInOrder();
        root.recursivePostOrder();
        //Tree root =
        //                new Tree(20,
        //                        new Tree(7,
        //                                new Tree(4, null,
        //                                        new Tree(6)),
        //                                new Tree(9)),
        //                        new Tree(35,
        //                                new Tree(31,
        //                                        new Tree(28), null),
        //                                new Tree(40,
        //                                        new Tree(38),
        //                                        new Tree(52)))
        //                );
    }


    /**
     * Горизонтальный обход дерева.
     * Обрабатываем первый в очереди узел,
     * при наличии дочерних узлов заносим
     * их в конец очереди.
     *
     * @param top вершина дерева.
     */
    static void contLevelOrder(Node top) {
        Queue<Node> queue = new LinkedList<>();
        do {
            top.treatment();
            if (top.left != null) {
                queue.add(top.left);
            }
            if (top.right != null) {
                queue.add(top.right);
            }
            if (!queue.isEmpty()) {
                top = queue.poll();
            }
        } while (!queue.isEmpty());
    }


    /**
     * Вертикальный прямой обход дерева.
     * Обрабатываем текущий узел, при наличии
     * правого поддерева добавляем его в стек
     * для последующей обработки. Переходим к
     * узлу левого поддерева. Если левого узла
     * нет, переходим к верхнему узлу из стека.
     *
     * @param top Вершина дерева.
     */
    static void contPreOrder(Node top) {
        Stack<Node> stack = new Stack<>();
        while (top != null || !stack.empty()) {
            if (!stack.empty()) {
                top = stack.pop();
            }
            while (top != null) {
                top.treatment();
                if (top.right != null) {
                    stack.push(top.right);
                }
                top = top.left;
            }

        }
    }

    /**
     * Вертикальный обратный обход дерева.
     * Из текущего узла «спускаемся» до самого нижнего
     * левого узла, добавляя в стек все посещенные узлы.
     * Обрабатываем верхний узел из стека. Если в текущем
     * узле имеется правое поддерево, начинаем следующую
     * итерацию с правого узла. Если правого узла нет,
     * пропускаем шаг со спуском и переходим к обработке
     * следующего узла из стека.
     *
     * @param top Вершина дерева.
     */
    static void contInOrder(Node top) {
        Stack<Node> stack = new Stack<>();
        while (top != null || !stack.empty()) {
            if (!stack.empty()) {
                top = stack.pop();
                top.treatment();
                if (top.right != null) {
                    top = top.right;
                } else {
                    top = null;
                }
            }
            while (top != null) {
                stack.push(top);
                top = top.left;
            }
        }
    }

    /**
     * Вертикальный концевой обход дерева.
     * Здесь ситуация усложняется – в отличие от обратного обхода,
     * помимо порядка спуска нужно знать обработано ли уже правое
     * поддерево. Одним из вариантов решения является внесение в
     * каждый экземпляр узла флага, который бы хранил соответствующую
     * информацию (не рассматривается). Другим подходом является
     * «кодирование» непосредственно в очередности стека — при
     * спуске, если у очередного узла позже нужно будет обработать
     * еще правое поддерево, в стек вносится последовательность
     * «родитель, правый узел, родитель». Таким образом, при
     * обработке узлов из стека мы сможем определить, нужно ли
     * нам обрабатывать правое поддерево.
     *
     * @param top вершина дерева.
     */
    static void contPostOrder(Node top) {
        Stack<Node> stack = new Stack<>();
        while (top != null || !stack.empty()) {
            if (!stack.empty()) {
                top = stack.pop();
                if (!stack.empty() && top.right == stack.lastElement()) {
                    top = stack.pop();
                } else {
                    top.treatment();
                    top = null;
                }
            }
            while (top != null) {
                stack.push(top);
                if (top.right != null) {
                    stack.push(top.right);
                    stack.push(top);
                }
                top = top.left;
            }
        }
    }

    /**
     * Вертикального концевой обход дерева с использованием
     * родительских указателей.
     * Есть возможность из произвольного узла дерева «дойти»
     * до любого из его узлов.
     * Все, за чем нужно следить при «подъеме» на верхний уровень
     * – пришли ли от правого потомка или от левого.
     *
     * @param top Вершина дерева.
     */
    static void parentPostOrder(Node top) {
        boolean fromright = false;
        Node shuttle = top, holder;
        while (true) {
            while (fromright) {
                shuttle.treatment();
                if (shuttle == top) return;
                holder = shuttle;
                shuttle = shuttle.parent;
                fromright = shuttle.right == holder;
                if (!fromright && shuttle.right != null) shuttle = shuttle.right;
                else fromright = true;
            }
            while (shuttle.left != null) shuttle = shuttle.left;
            if (shuttle.right != null) shuttle = shuttle.right;
            else fromright = true;
        }
    }

    /**
     * Перемещение внутри дерева с использованием родительского
     * указателя.
     * Так, что бы перейти на n-ый по счету узел от текущего узла,
     * без «ориентации в дереве» пришлось бы обходить дерево с
     * самого начала, до известного узла, а потом еще n-узлов.
     * С использованием же родительского указателя при обратном
     * обходе дерева перемещение на steps узлов от текущего узла
     * (start) будет иметь следующий вид.
     * Примечание: В общем случае также требуется предотвратить
     * возможность попытки выхода за пределы дерева (подняться
     * выше корневого узла).
     *
     * @param start текущий узел.
     * @param steps шаг
     * @return посещенный узел
     */
    public static Node walkTheTree(Node start, int steps) {
        boolean fromright = true;
        Node shuttle = start, holder;
        if (shuttle.right != null) {
            shuttle = shuttle.right;
            while (shuttle.left != null) shuttle = shuttle.left;
            fromright = false;
        }
        int counter = 0;
        do {
            while (true) {
                if (!fromright && ++counter == steps) return shuttle;
                if (!fromright && shuttle.right != null) {
                    shuttle = shuttle.right;
                    break;
                }
                holder = shuttle;
                shuttle = shuttle.parent;
                fromright = (holder == shuttle.right);
            }
            while (shuttle.left != null) shuttle = shuttle.left;
        } while (true);
    }


}
