package Trees.BinaryTrees;

import java.util.*;

public class TreePrinter {
    public static void printTree(Node root) {
        printTreeHelper(Collections.singletonList(root), 1, getMaxLevel(root));
    }

    private static void printTreeHelper(List<Node> nodes, int level, int maxLevel) {
        if (nodes.isEmpty() || isAllElementsNull(nodes)) {
            return;
        }

        int floor = maxLevel - level;
        int edgeLines = (int) Math.pow(2, Math.max(floor - 1, 0));
        int firstSpaces = (int) Math.pow(2, floor) - 1;
        int betweenSpaces = (int) Math.pow(2, floor + 1) - 1;

        printSpaces(firstSpaces);

        List<Node> newNodes = new ArrayList<>();
        for (Node node : nodes) {
            if (node != null) {
                System.out.print((node.color?"r":"b")+node.data);
                newNodes.add(node.left);
                newNodes.add(node.right);
            } else {
                System.out.print(" ");
                newNodes.add(null);
                newNodes.add(null);
            }
            printSpaces(betweenSpaces);
        }
        System.out.println();

        for (int i = 1; i <= edgeLines; i++) {
            for (Node node : nodes) {
                printSpaces(firstSpaces - i);
                if (node == null) {
                    printSpaces(edgeLines + edgeLines + i + 1);
                    continue;
                }
                if (node.left != null) {
                    System.out.print("/");
                } else {
                    printSpaces(1);
                }
                printSpaces(i + i - 1);
                if (node.right != null) {
                    System.out.print("\\");
                } else {
                    printSpaces(1);
                }
                printSpaces(edgeLines + edgeLines - i);
            }
            System.out.println();
        }

        printTreeHelper(newNodes, level + 1, maxLevel);
    }

    private static void printSpaces(int count) {
        for (int i = 0; i < count; i++) {
            System.out.print(" ");
        }
    }

    private static int getMaxLevel(Node node) {
        if (node == null) {
            return 0;
        }
        return Math.max(getMaxLevel(node.left), getMaxLevel(node.right)) + 1;
    }

    private static boolean isAllElementsNull(List<Node> nodes) {
        for (Node node : nodes) {
            if (node != null) {
                return false;
            }
        }
        return true;
    }
}
