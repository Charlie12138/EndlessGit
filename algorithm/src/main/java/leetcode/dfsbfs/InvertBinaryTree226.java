package leetcode.dfsbfs;

import java.util.LinkedList;

public class InvertBinaryTree226 {
	public static TreeNode invertTree(TreeNode root) {
		LinkedList<TreeNode> queue = new LinkedList<>();
		LinkedList<TreeNode> newQueue = new LinkedList<>();

		//广度遍历二叉树，将二叉树反序放进newQueue
		queue.add(root);
		newQueue.add(root);
		while(!queue.isEmpty()) {
			TreeNode node = queue.poll();
			TreeNode curnode = newQueue.poll();
			if (node != null) {
				TreeNode left = node.getLeft();
				TreeNode right = node.getRight();
				//先右再左
				queue.add(right);
				queue.add(left);
				newQueue.add(right);
				newQueue.add(left);
				if (curnode != null) {
					curnode.setLeft(right);
					curnode.setRight(left);
				}
			}
		}
		return root;
	}

	public static void main(String[] args) {
		TreeNode node = new TreeNode(4);
		TreeNode node9 = new TreeNode(2);
		TreeNode node1 = new TreeNode(7);
		TreeNode node2 = new TreeNode(1);
		TreeNode node3 = new TreeNode(3);
		TreeNode node4 = new TreeNode(6);
		TreeNode node5 = new TreeNode(9);
		node.setLeft(node9);
		node.setRight(node1);
		node9.setLeft(node2);
		node9.setRight(node3);
		node1.setLeft(node4);
		node1.setRight(node5);
		TreeNode newNode = invertTree(node);
		System.out.println();
	}
}
