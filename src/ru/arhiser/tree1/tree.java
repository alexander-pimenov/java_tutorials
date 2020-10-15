package ru.arhiser.tree1;

public class tree {
    public static void main(String[] params) {
        /*Создаем дерево, как на картинке.*/
        Tree root =
                new Tree(20,
                        new Tree(7,
                                new Tree(4, null,
                                        new Tree(6)), new Tree(9)),
                        new Tree(35,
                                new Tree(31,
                                        new Tree(28), null),
                                new Tree(40,
                                        new Tree(38),
                                        new Tree(52))));

        System.out.println("Сумма дерева: " + root.sum());
    }

    static class Tree {
        /**
         * Значение, находящееся внутри узла.
         */
        int value;
        /**
         * Левый потомок узла.
         */
        Tree left;
        /**
         * Правый потомок узла.
         */
        Tree right;

        public Tree(int value, Tree left, Tree right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }

        /**
         * Конструтор, для описания узлов у которых нет
         * потомков, т.е. листьев дерева.
         *
         * @param value значение узла.
         */
        public Tree(int value) {
            this.value = value;
        }

        /**
         * Метод вичисляющий сумму чисел во всех узлах.
         * Этот метод имеет название - рекурсивный обход в глубину.
         * Сначала этот алгоритм идет влевую часть дерева, старается
         * углубиться влево, оставляя правую сторону пока не просмотренной.
         * И потом переходит в правую часть дерева, как закончит с
         * левой частью.
         *
         * @return сумма чисел.
         */
        public int sum() {
            int summ = value;

            /*если левый потомок не пустой, то:*/
            if (left != null) {
                summ += left.sum();
            }

            /*если правый потомок не пустой, то:*/
            if (right != null) {
                summ += right.sum();
            }
            return summ;
        }
    }
}
