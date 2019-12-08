package leetcode.tree;

import java.util.LinkedList;

public class MinimumDepth111 {

	public int minDepthOfBFS(TreeNode root) {
		if (root == null) {
			return 0;
		}
		LinkedList<TreeNode> queue = new LinkedList<>();
		queue.push(root);
		int result = 1;
		TreeNode node = null;
		while (!queue.isEmpty()) {
			int flag = queue.size();
			for (int j = 0; j < flag; j++) {
				node = queue.poll();
				if (node != null) {
					if (node.getLeft() == null && node.getRight() == null) {
						break;
					}
					if (node.getLeft() != null) {
						queue.add(node.getLeft());
					}
					if (node.getRight() != null) {
						queue.add(node.getRight());
					}
				}
			}
			if (node != null && node.getLeft() == null && node.getRight() == null) {
				break;
			}
			result++;
		}
		return result;
	}

	public int minDepth(TreeNode root) {
		if (root == null) {
			return 0;
		}
		if (root.getLeft() == null && root.getRight() == null) {
			return 1;
		}
		int minDepth = Integer.MAX_VALUE;
		if (root.getLeft() != null) {
			minDepth = Math.min(minDepth(root.getLeft()), minDepth);
		}
		if (root.getRight() != null) {
			minDepth = Math.min(minDepth(root.getRight()), minDepth);
		}
		return minDepth + 1;
	}

	public static void main(String[] args) {
		TreeNode node = new TreeNode(1);
		TreeNode node1 = new TreeNode(2);
		TreeNode node2 = new TreeNode(3);
		TreeNode node3 = new TreeNode(4);
		TreeNode node4 = new TreeNode(5);
		TreeNode node5 = new TreeNode(6);
		TreeNode node6 = new TreeNode(7);

		node.setLeft(node1);
		node.setRight(node2);
		node2.setRight(node3);
//		node1.setLeft(node4);

		MinimumDepth111 minimumDepth111 = new MinimumDepth111();
		System.out.println(minimumDepth111.minDepthOfBFS(node));
	}
}
