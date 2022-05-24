package com.dtn1712.veritone;

import com.dtn1712.veritone.impl.BinarySearchTreeIntegerImpl;
import com.dtn1712.veritone.model.DeepestNodesResult;
import com.dtn1712.veritone.model.TreeNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BinarySearchTreeIntegerTest {

    @Test
    public void testConstructTree() {
        BinarySearchTree binarySearchTree = new BinarySearchTreeIntegerImpl(new int[] {12,11,90,82,7,9});

        TreeNode root = binarySearchTree.getRoot();
        assertEquals(12, root.getKey());
        assertEquals(11, root.getLeft().getKey());
        assertEquals(90, root.getRight().getKey());
        assertEquals(7, root.getLeft().getLeft().getKey());
        assertEquals(9, root.getLeft().getLeft().getRight().getKey());
        assertEquals(82, root.getRight().getLeft().getKey());
    }

    @Test
    public void testOneDeepestNode() {
        BinarySearchTree binarySearchTree = new BinarySearchTreeIntegerImpl(new int[] {12,11,90,82,7,9});
        DeepestNodesResult result = binarySearchTree.getDeepestNodes();

        assertEquals("deepest=9; depth=3", result.toString());
    }

    @Test
    public void testMultipleDeepestNodes() {
        BinarySearchTree binarySearchTree = new BinarySearchTreeIntegerImpl(new int[] {26, 82, 16, 92, 33});
        DeepestNodesResult result = binarySearchTree.getDeepestNodes();

        assertTrue(result.toString().equals("deepest=33,92; depth=2") || result.toString().equals("deepest=92,33; depth=2"));
    }

    @Test
    public void testDeleteNode() throws Exception {
        BinarySearchTree binarySearchTree = new BinarySearchTreeIntegerImpl(new int[] {26, 82, 16, 92, 33});
        assertNotNull(binarySearchTree.search(26));

        binarySearchTree.delete(26);
        assertNull(binarySearchTree.search(26));
    }

    @Test
    public void testInvalidKeyWhenDeleteNode() {
        BinarySearchTree binarySearchTree = new BinarySearchTreeIntegerImpl(new int[] {26, 82, 16, 92, 33});
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            binarySearchTree.delete(1.1);
        });

        assertTrue(exception.getMessage().contains("Key need to be integer value"));
    }

    @Test
    public void testSearchNode() {
        BinarySearchTree binarySearchTree = new BinarySearchTreeIntegerImpl(new int[] {26, 82, 16, 92, 33});

        TreeNode foundNode = binarySearchTree.search(82);
        assertEquals(82, foundNode.getKey());
        assertEquals(33, foundNode.getLeft().getKey());
        assertEquals(92, foundNode.getRight().getKey());
    }

    @Test
    public void testInvalidKeyWhenSearchNode() {
        BinarySearchTree binarySearchTree = new BinarySearchTreeIntegerImpl(new int[] {26, 82, 16, 92, 33});
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            binarySearchTree.search(1.1);
        });

        assertTrue(exception.getMessage().contains("Key need to be integer value"));
    }

    @Test
    public void testInsertNode() throws Exception {
        BinarySearchTree binarySearchTree = new BinarySearchTreeIntegerImpl(new int[] {26, 82, 16, 92, 33});
        assertNull(binarySearchTree.search(17));

        binarySearchTree.insert(17);
        assertNotNull(binarySearchTree.search(17));
    }

    @Test
    public void testInvalidKeyWhenInsertNode() {
        BinarySearchTree binarySearchTree = new BinarySearchTreeIntegerImpl(new int[] {26, 82, 16, 92, 33});
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            binarySearchTree.insert(1.1);
        });

        assertTrue(exception.getMessage().contains("Key need to be integer value"));
    }
}
