package com.dtn1712.veritone.model;

import java.util.List;

public class DeepestNodesResult {

    private List<TreeNode> deepestNodes;
    private int depth;

    public List<TreeNode> getDeepestNodes() {
        return deepestNodes;
    }

    public void setDeepestNodes(List<TreeNode> deepestNodes) {
        this.deepestNodes = deepestNodes;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("deepest=");
        for (int i = 0; i < deepestNodes.size(); i++) {
            sb.append(deepestNodes.get(i).getKey());
            if (i != deepestNodes.size() - 1) {
                sb.append(",");
            }
        }
        sb.append("; depth=");
        sb.append(depth);
        return sb.toString();
    }
}
