package ru.arhiser.tree4;

public class Node {
    Node left;
    Node right;
    Node parent;
    String value;

    public Node(Node parent, String value) {
        this.parent = parent;
        this.value = value;
    }

    public Node(Node parent, String value, Node left, Node right) {
        this.value = value;
        this.parent = parent;
        this.left = left;
        this.right = right;
    }

    public Node(String value) {
        this.value = value;
    }

    public Node(String value, Node left, Node right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public void treatment() {
        System.out.printf("Node Value: %s - Parent: %s do something.%n", this.value, this.parent);
    }

    /**
     * Рекурсивный прямой обход в глубину (префиксный, pre-ordered):
     * вершина – левое поддерево – правое поддерево
     */
    void recursivePreOrder() {
        treatment();
        if (left != null) left.recursivePreOrder();
        if (right != null) right.recursivePreOrder();
    }

    /**
     * Рекурсивный обратный обход в глубину (инфиксный, in-ordered):
     * левое поддерево – вершина – правое поддерево
     */
    void recursiveInOrder() {
        if (left != null) left.recursiveInOrder();
        treatment();
        if (right != null) right.recursiveInOrder();
    }

    /**
     * Рекурсивный концевой обход в глубину (постфиксный, post-ordered):
     * левое поддерево – правое поддерево – вершин
     */
    void recursivePostOrder() {
        if (left != null) left.recursivePostOrder();
        if (right != null) right.recursivePostOrder();
        treatment();
    }
}
