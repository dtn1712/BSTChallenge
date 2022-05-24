package com.dtn1712.veritone.impl;

import com.dtn1712.veritone.BinarySearchTree;
import com.dtn1712.veritone.model.DeepestNodesResult;
import com.dtn1712.veritone.model.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Binary Search Tree implementation for Integer
 */
public class BinarySearchTreeIntegerImpl implements BinarySearchTree {

    private TreeNode root;

    public BinarySearchTreeIntegerImpl(int[] nodes) {
        constructTree(nodes);
    }

    private void constructTree(int[] nodes) {
        for (int node : nodes) {
            insert(node);
        }
    }

    /**
     * Insert new node into the tree
     *
     * @param key the node key which should be an integer
     * @throws IllegalArgumentException throw when parameter key is not integer
     */
    public void insert(Object key) throws IllegalArgumentException {
        if (!(key instanceof Integer)) {
            throw new IllegalArgumentException("Key need to be integer value");
        }

        int keyIntValue = (int) key;
        TreeNode curr = root;
        TreeNode parent = null;

        if (root == null) {
            root = new TreeNode(keyIntValue);
            return;
        }

        while (curr != null) {
            parent = curr;
            if (Integer.compare(keyIntValue, (int) curr.getKey()) == -1) {
                curr = curr.getLeft();
            } else {
                curr = curr.getRight();
            }
        }

        if (Integer.compare(keyIntValue, (int) parent.getKey()) == -1) {
            parent.setLeft(new TreeNode(keyIntValue));
        } else {
            parent.setRight(new TreeNode(keyIntValue));
        }
    }

    public void print() {
        printHelper("", root, false, true);
    }

    private void printHelper(String prefix, TreeNode node, boolean isLeft, boolean isRoot) {
        if (node != null) {
            System.out.println(prefix + (isRoot ? "root: " : isLeft ? "left: " : "right: ") + node.getKey());
            printHelper(prefix + "\t", node.getLeft(), true, false);
            printHelper(prefix + "\t", node.getRight(), false, false);
        }
    }

    /**
     * Delete the existing node in the tree
     *
     * @param key the node key to be deleted which should be an integer
     * @throws IllegalArgumentException throw when parameter key is not integer
     */
    public void delete(Object key) throws IllegalArgumentException {
        if (!(key instanceof Integer)) {
            throw new IllegalArgumentException("Key need to be integer value");
        }

        int keyIntValue = (int) key;

        TreeNode current = root;
        TreeNode parent = root;
        boolean isLeftChild = false;
        while ((int) current.getKey() != keyIntValue) {
            parent = current;
            if (Integer.compare(keyIntValue, (int) current.getKey()) == -1) {
                current = current.getLeft();
                isLeftChild = true;
            } else {
                current = current.getRight();
                isLeftChild = false;
            }

            if (current == null) {
                return;
            }
        }

        if (current.getLeft() == null && current.getRight() == null) {
            handleLeafNodeDeletion(current, parent, isLeftChild);
        } else if (current.getLeft() == null) {
            handleNodeWithRightChildDeletion(current, parent, isLeftChild);
        } else if (current.getRight() == null) {
            handleNodeWithLeftChildDeletion(current, parent, isLeftChild);
        } else {
            handleNodeWithBothChildrenDeletion(current, parent, isLeftChild);
        }
    }

    @Override
    public TreeNode search(Object key) {
        if (!(key instanceof Integer)) {
            throw new IllegalArgumentException("Key need to be integer value");
        }

        int keyIntValue = (int) key;
        return searchHelper(root, keyIntValue);
    }

    private TreeNode searchHelper(TreeNode node, Integer key) {
        while (node != null && key != (int) node.getKey()) {
            if (Integer.compare(key, (int) node.getKey()) == -1) {
                node = node.getLeft();
            } else {
                node = node.getRight();
            }
        }

        return node;
    }

    /**
     * This function is to delete the node that have both children
     */
    private void handleNodeWithBothChildrenDeletion(TreeNode current, TreeNode parent, boolean isLeftChild) {
        TreeNode successor = findSuccessor(current);
        if (current == root) {
            root = successor;
        } else if (isLeftChild) {
            parent.setLeft(successor);
        } else {
            parent.setRight(successor);
        }
        successor.setLeft(current.getLeft());
    }

    /**
     * This function is to delete the node that have only left child
     */
    private void handleNodeWithLeftChildDeletion(TreeNode current, TreeNode parent, boolean isLeftChild) {
        if (current == root) {
            root = current.getLeft();
        } else if (isLeftChild) {
            parent.setLeft(current.getLeft());
        } else {
            parent.setRight(current.getLeft());
        }
    }

    /**
     * This function is to delete the node that have only right child
     */
    private void handleNodeWithRightChildDeletion(TreeNode current, TreeNode parent, boolean isLeftChild) {
        if (current == root) {
            root = current.getRight();
        } else if (isLeftChild) {
            parent.setLeft(current.getRight());
        } else {
            parent.setRight(current.getRight());
        }
    }

    /**
     * This function is to delete the leaf node
     */
    private void handleLeafNodeDeletion(TreeNode current, TreeNode parent, boolean isLeftChild) {
        if (current == root) {
            root = null;
        } else if (isLeftChild) {
            parent.setLeft(null);
        } else {
            parent.setRight(null);
        }
    }

    /**
     * This function to find the in-order successor of the deleted node
     */
    private TreeNode findSuccessor(TreeNode node) {
        TreeNode successor = node;
        TreeNode successorParent = node;
        TreeNode current = node.getRight();

        while (current != null) {
            successorParent = successor;
            successor = current;
            current = current.getLeft();
        }
        if (successor != node.getRight()) {
            successorParent.setLeft(successor.getRight());
            successor.setRight(node.getRight());
        }
        return successor;
    }

    /**
     * This function is to find and return the deepest nodes of the tree
     *
     * @return DeepestNodesResult object which contain a list of deepest nodes and its depth
     */
    public DeepestNodesResult getDeepestNodes() {
        DeepestNodesResult result = new DeepestNodesResult();

        List<List<TreeNode>> nodeDepthList = new ArrayList<>();

        if (root == null) return result;

        TreeNode curr = root;

        Queue<TreeNode> q = new LinkedList<>();

        q.offer(curr);

        while (!q.isEmpty()) {
            int qSize = q.size();
            List<TreeNode> subList = new ArrayList<>();
            for (int i = 0; i < qSize; i++) {
                curr = q.poll();

                if (curr != null) {
                    subList.add(curr);
                    if (curr.getLeft() != null) {
                        q.offer(curr.getLeft());
                    }
                    if (curr.getRight() != null) {
                        q.offer(curr.getRight());
                    }
                }
            }
            nodeDepthList.add(subList);
        }

        result.setDepth(nodeDepthList.size() - 1);
        result.setDeepestNodes(nodeDepthList.get(nodeDepthList.size() - 1));
        return result;
    }

    @Override
    public TreeNode getRoot() {
        return root;
    }
}
