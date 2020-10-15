package ru.arhiser.tree5;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * "Реализация бинарного дерева в Java"
 * https://www.codeflow.site/ru/article/java-binary-tree
 */
public class BinaryTree {
    static Node root;

    public static void main(String[] args) {
        BinaryTree bt = new BinaryTree();

        final BinaryTree binaryTree = bt.createBinaryTree();

//        А также можно дерево заполнить из main метода
//        bt.add(6);
//        bt.add(4);
//        bt.add(8);
//        bt.add(3);
//        bt.add(5);
//        bt.add(7);
//        bt.add(9);

        System.out.println(binaryTree.containsNode(7));
        System.out.println(binaryTree.containsNode(1));
        binaryTree.printTree();
        System.out.println("=============");
        binaryTree.delete(8);

        binaryTree.printTree();
        System.out.println("=============");

        binaryTree.traverseInOrder(root);
        System.out.println("\n=============");
        binaryTree.traversePreOrder(root);
        System.out.println("\n=============");
        binaryTree.traversePostOrder(root);
        System.out.println("\n=============");
        binaryTree.traverseLevelOrder();
    }

    /**
     * Метод для создание бинарного дерева.
     * Хотя этот заполнение можно сделать в main методе.
     *
     * @return объект BinaryTree
     */
    private BinaryTree createBinaryTree() {
        BinaryTree bt = new BinaryTree();

        bt.add(6);
        bt.add(4);
        bt.add(8);
        bt.add(3);
        bt.add(5);
        bt.add(7);
        bt.add(9);

        return bt;
    }

    /**
     * Метод запускает рекурсию с узла root.
     *
     * @param value значение узла.
     */
    public void add(int value) {
        root = addRecursive(root, value);
    }

    /**
     * Рекурсивный метод для вставки элементов.
     *
     * @param current текущий узел.
     * @param value   значение.
     * @return текущий узел.
     */
    private Node addRecursive(Node current, int value) {
        if (current == null) {
            return new Node(value);
        }
        if (value < current.value) {
            current.left = addRecursive(current.left, value);
        } else if (value > current.value) {
            current.right = addRecursive(current.right, value);
        } else {
            //value already exists
            return current;
        }
        return current;
    }

    /**
     * Публичный метод, который начинает проверку с root.
     *
     * @param value значение узла.
     * @return boolean result.
     */
    public boolean containsNode(int value) {
        return containsNodeRecursive(root, value);
    }

    /**
     * Рекурсивный метод, который перебирает дерево.
     *
     * @param current текущий узел.
     * @param value   значение узла.
     * @return boolean result.
     */
    private boolean containsNodeRecursive(Node current, int value) {

        if (current == null) {
            return false;
        }
        if (value == current.value) {
            return true;
        }
        return value < current.value
                ? containsNodeRecursive(current.left, value)
                : containsNodeRecursive(current.right, value);
    }


    /**
     * Метод запускает удаление из root.
     *
     * @param value значение.
     */
    public void delete(int value) {
        root = deleteRecursive(root, value);
    }

    /**
     * Метод удаление узла из дерева.
     *
     * @param current текущий узел.
     * @param value   значение узла.
     * @return текущий узел.
     */
    private Node deleteRecursive(Node current, int value) {
        if (current == null) {
            return null;
        }
        if (value == current.value) {
            //Node to delete found
            //... code to delete the node will go here

            /*когда узел является листовым узлом*/
            if (current.left == null && current.right == null) {
                return null;
            }
            /*когда у узла есть один дочерний элемент*/
            if (current.right == null) {
                return current.left;
            }

            if (current.left == null) {
                return current.right;
            }
            /*мы назначаем наименьшее значение узлу для удаления
            и после этого удаляем его из правого поддерева:*/
            int smallestValue = findSmallestValue(current.right);
            current.value = smallestValue;
            current.right = deleteRecursive(current.right, smallestValue);
            return current;
        }
        if (value < current.value) {
            current.left = deleteRecursive(current.left, value);
            return current;
        }
        current.right = deleteRecursive(current.right, value);
        return current;
    }

    /**
     * Находит самый маленький узел узла, который нужно удалить,
     * в правом поддереве.
     * Нам нужно найти узел, который заменит удаленный узел.
     *
     * @param root вершина дерева.
     * @return значение узла.
     */
    private int findSmallestValue(Node root) {
        return root.left == null ? root.value : findSmallestValue(root.left);
    }

    /**
     * Поиск в глубину - это тип обхода, который максимально углубляется
     * в каждого ребенка, прежде чем исследовать следующего брата или сестру.
     * <p>
     * Порядок обхода состоит в том, чтобы сначала посетить левое поддерево,
     * затем корневой узел и, наконец, правое поддерево
     *
     * @param node корень дерева.
     */
    public void traverseInOrder(Node node) {
        if (node != null) {
            traverseInOrder(node.left);
            System.out.print(" " + node.value);
            traverseInOrder(node.right);
//            System.out.println();
        }
    }


    /**
     * Обход предварительного заказа сначала посещает корневой узел,
     * затем левое поддерево и, наконец, правое поддерево.
     *
     * @param node вершина дерева.
     */
    public void traversePreOrder(Node node) {
        if (node != null) {
            System.out.print(" " + node.value);
            traversePreOrder(node.left);
            traversePreOrder(node.right);
        }
    }

    /**
     * Обход после заказа посещает левое поддерево,
     * правое поддерево и корневой узел в конце.
     *
     * @param node корень дерева.
     */
    public void traversePostOrder(Node node) {
        if (node != null) {
            traversePostOrder(node.left);
            traversePostOrder(node.right);
            System.out.print(" " + node.value);
        }
    }

    /**
     * Поиск в ширину.
     * Это еще один распространенный тип обхода, который посещает
     * все узлы уровня перед переходом на следующий уровень.
     * Этот вид обхода также называется уровнем порядка и охватывает
     * все уровни дерева, начиная с корня и слева направо.
     * <p>
     * Для реализации мы будем использовать Queue, чтобы держать
     * узлы каждого уровня в порядке. Мы извлечем каждый узел из
     * списка, напечатаем его значения, а затем добавим его дочерние
     * элементы в очередь.
     */
    public void traverseLevelOrder() {
        if (root == null) {
            return;
        }
        Queue<Node> nodes = new LinkedList<>();
        nodes.add(root);
        while (!nodes.isEmpty()) {
            Node node = nodes.remove(); //remove() выкидывает NoSuchElementException if this queue is empty
            System.out.print(" " + node.value);
            if (node.left != null) {
                nodes.add(node.left);
            }
            if (node.right != null) {
                nodes.add(node.right);
            }
        }
    }

    /**
     * На печать выводим узлы дерева, обходя его
     * в ширину.
     */
    public void printTree() {
        if (root == null) {
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            System.out.println(node.value);
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
    }
}
