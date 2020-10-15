package ru.arhiser.tree2;

import ru.arhiser.stack.SimpleQueue;
import ru.arhiser.stack.SimpleStack;

public class Tree2 {
    public static void main(String[] params) {
        Tree root =
                new Tree(20,
                        new Tree(7,
                                new Tree(4, null,
                                        new Tree(6)),
                                new Tree(9)),
                        new Tree(35,
                                new Tree(31,
                                        new Tree(28), null),
                                new Tree(40,
                                        new Tree(38),
                                        new Tree(52)))
                );

        System.out.println("Сумма дерева: " + sumWide(root));
        System.out.println("===============================");
        System.out.println("Сумма дерева: " + sumDeep(root));
    }

    static class Tree {
        int value;
        Tree left;
        Tree right;

        public Tree(int value, Tree left, Tree right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }

        public Tree(int value) {
            this.value = value;
        }
    }

    /**
     * Рекурсивный обход дерева в глубину.
     * Метод вичисляющий сумму чисел во всех узлах.
     * Сначала этот алгоритм идет влевую часть дерева, старается
     * углубиться влево, оставляя правую сторону пока не просмотренной.
     * И потом переходит в правую часть дерева, как закончит с
     * левой частью.
     *
     * @return сумма чисел.
     */
    public static int sumRecursive(Tree root) {
        int summ = root.value;

        if (root.left != null) {
            summ += sumRecursive(root.left);
        }

        if (root.right != null) {
            summ += sumRecursive(root.right);
        }
        return summ;
    }

    /**
     * Итеративный обход дерева в глубину.
     * Используется структура Стек.
     * FILO
     *
     * @param root корень дерева.
     * @return сумма узлов.
     */
    public static int sumDeep(Tree root) {
        /*Используем свой созданный ранее стек.*/
        SimpleStack<Tree> stack = new SimpleStack<>();
        /*Кладем в стек корневой узел, с которого мы начинаем
         * проход по дереву.*/
        stack.push(root);

        int summ = 0;

        /*И пока стек не пустой:*/
        while (!stack.isEmpty()) {
            /*достаем узел из стека*/
            Tree node = stack.pop();

            /*Это чтобы видеть в каком порядке алгоритм обошел
             * узлы дерева.*/
            System.out.println(node.value);

            /*прибавляем к сумме число, которое находится внутри узла.*/
            summ = summ + node.value;

            /*проверяем на наличие у узла правого потомка, и если он
             * есть, то кладем в стек.*/
            if (node.right != null) {
                stack.push(node.right);
            }

            /*проверяем на наличие у узла левого потомка, и если он
             * есть, то кладем тоже в стек.*/
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        /*Возвращаем вычисленую сумму.*/
        return summ;
    }

    /**
     * Итеративный обход дерева в ширину.
     * Используется структура Очередь.
     * FIFO
     * Дерево обходится по уровням и слева направо.
     *
     * @param root корень дерева.
     * @return сумма узлов.
     */
    public static int sumWide(Tree root) {
        SimpleQueue<Tree> queue = new SimpleQueue<>();
        queue.add(root);

        int summ = 0;

        while (!queue.isEmpty()) {
            Tree node = queue.remove();

            System.out.println(node.value);

            summ = summ + node.value;

            if (node.left != null) {
                queue.add(node.left);
            }

            if (node.right != null) {
                queue.add(node.right);
            }
        }
        return summ;
    }
}
