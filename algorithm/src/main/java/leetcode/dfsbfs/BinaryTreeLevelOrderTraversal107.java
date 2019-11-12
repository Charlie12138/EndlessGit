package leetcode.dfsbfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BinaryTreeLevelOrderTraversal107 {

	public List<List<Integer>> levelOrderBottom(TreeNode root) {
		if (root == null) {
			return new LinkedList<>();
		}
		LinkedList<List<Integer>> result = new LinkedList<>();
		LinkedList<TreeNode> queue = new LinkedList<>();
		queue.push(root);
		TreeNode curNode;
		List<Integer> list;
		LinkedList<TreeNode> curLevel = new LinkedList<>();
		while (!queue.isEmpty()) {
			list = new ArrayList<>();
			while(!queue.isEmpty()) {
				curNode = queue.poll();
				curLevel.add(curNode);
				list.add(curNode.getVal());
			}
			result.push(list);
			while (!curLevel.isEmpty()) {
				TreeNode node = curLevel.poll();
				//add方法是添加到队尾，因为是从左到右的顺序
				if (node.getLeft() != null) {
					queue.add(node.getLeft());
				}
				if (node.getRight() != null) {
					queue.add(node.getRight());
				}
			}

		}
		return result;
	}

	public static void main(String[] args) {
		TreeNode node = new TreeNode(3);
		TreeNode node1 = new TreeNode(9);
		TreeNode node2 = new TreeNode(20);
		TreeNode node3 = new TreeNode(15);
		TreeNode node4 = new TreeNode(7);

		node.setLeft(node1);
		node.setRight(node2);
		node2.setLeft(node3);
		node2.setRight(node4);

		BinaryTreeLevelOrderTraversal107 btlot = new BinaryTreeLevelOrderTraversal107();

		System.out.println(btlot.levelOrderBottom(node));

	}
}
