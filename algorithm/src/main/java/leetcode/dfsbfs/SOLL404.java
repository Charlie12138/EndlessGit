package leetcode.dfsbfs;

import java.util.LinkedList;

public class SOLL404 {
	public int sumOfLeftLeaves(TreeNode root) {
		if (root == null) {
			return 0;
		}
		LinkedList<TreeNode> queue = new LinkedList<>();
		queue.add(root);
		int result = 0;
		while(!queue.isEmpty()) {
			TreeNode node = queue.poll();
			if (node.getLeft() != null) {
				queue.add(node.getLeft());
				if (node.getLeft().getLeft() == null && node.getLeft().getRight() == null) {
					result += node.getLeft().getVal();
				}
			}
			if (node.getRight() != null) {
				queue.add(node.getRight());
			}
		}
		return result;
	}
	public static void main(String[] args) {
		TreeNode node = new TreeNode(6);
		TreeNode node1 = new TreeNode(2);
		TreeNode node2 = new TreeNode(8);
		TreeNode node3 = new TreeNode(0);
		TreeNode node4 = new TreeNode(4);
		TreeNode node5 = new TreeNode(7);
		TreeNode node6 = new TreeNode(9);
		TreeNode node7 = new TreeNode(3);
		TreeNode node8 = new TreeNode(5);

		TreeNode node9 = new TreeNode(2);
		TreeNode node10 = new TreeNode(1);

		node.setLeft(node1);
		node.setRight(node2);

		node1.setLeft(node3);
		node1.setRight(node4);

		node2.setLeft(node5);
		node2.setRight(node6);

		node4.setRight(node8);
		node4.setLeft(node7);

		SOLL404 soll = new SOLL404();
		System.out.println(soll.sumOfLeftLeaves(node));
	}
}
