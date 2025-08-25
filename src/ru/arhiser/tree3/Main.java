package ru.arhiser.tree3;

import ru.arhiser.funcops.inter.Func2;

import java.util.ArrayList;

/**
 * В примере строим дерево из элементов и выводим в консоль строки с именами листовых узлов.
 */
public class Main {

    public static void main(String[] params) {
        ArrayList<Data> items = new ArrayList<>();

        items.add(new Data(1, "Juices", 0));
        items.add(new Data(2, "Mango Juice", 1));
        items.add(new Data(3, "Grape Juice", 1));
        items.add(new Data(4, "Apple Juice", 1));
        items.add(new Data(5, "Gaz Water", 0));
        items.add(new Data(6, "Cola", 5));
        items.add(new Data(7, "Cola 0.5L", 6));
        items.add(new Data(8, "Cola 1.5L", 6));
        items.add(new Data(9, "Mineral Water", 5));
        items.add(new Data(10, "Lemonade", 5));

        DataNode tree = DataNode.makeTree(items, new TreeNode.TypeAdapter<Data, DataNode>() {
            @Override
            public DataNode newInstance() {
                return new DataNode();
            }

            @Override
            public boolean isChildOf(Data parentNodeData, Data childNodeData) {
                return parentNodeData.id == childNodeData.parentId;
            }

            @Override
            public boolean isTopLevelItem(Data data) {
                return data.parentId == 0;
            }
        });

        String names = tree.reduce((node, names1) -> {
            if (node.children == null || node.children.isEmpty()) {
                if (names1.length() > 0) {
                    names1 = names1 + ", ";
                }
                names1 = names1 + node.data.name;
            }
            return names1;
        }, "");

        System.out.println("Names of goods: " + names); //Names of goods: Mango Juice, Grape Juice, Apple Juice, Cola 0.5L, Cola 1.5L, Mineral Water, Lemonade
    }

    static class DataNode extends TreeNode<Data, DataNode> {
        @Override
        public String toString() {
            return data.name;
        }
    }


    public static class Data {
        int id;
        String name;
        int parentId;

        public Data(int id, String name, int parentId) {
            this.id = id;
            this.name = name;
            this.parentId = parentId;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}
