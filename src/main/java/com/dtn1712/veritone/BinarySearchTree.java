package com.dtn1712.veritone;

import com.dtn1712.veritone.model.DeepestNodesResult;
import com.dtn1712.veritone.model.TreeNode;

public interface BinarySearchTree {

    void insert(Object key) throws Exception;

    void print();

    void delete(Object key) throws Exception;

    TreeNode search(Object key);

    DeepestNodesResult getDeepestNodes();

    TreeNode getRoot();
}
