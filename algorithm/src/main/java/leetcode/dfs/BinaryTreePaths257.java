package leetcode.dfs;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreePaths257 {
	public void binaryTreePaths(TreeNode root, String path, List<String> list) {
		if (root != null) {
			path += ("".equals(path) ? "" : "->") + root.getVal();
			if (root.getLeft() == null && root.getRight() == null) {
				list.add(path);
			}
			if (root.getLeft() != null) {
				binaryTreePaths(root.getLeft(), path, list);
			}
			if (root.getRight() != null) {
				binaryTreePaths(root.getRight(), path, list);
			}
		}
	}

	public List<String> binaryTreePaths(TreeNode root) {
		ArrayList<String> list = new ArrayList<>();
		binaryTreePaths(root, "", list);
		return list;
	}

	public static void main(String[] args) {
		TreeNode node = new TreeNode(1);
		TreeNode node1 = new TreeNode(2);
		TreeNode node2 = new TreeNode(3);
		TreeNode node3 = new TreeNode(5);

		node.setLeft(node1);
		node.setRight(node2);
		node1.setRight(node3);

		BinaryTreePaths257 btp = new BinaryTreePaths257();
		System.out.println(btp.binaryTreePaths(node));

	}
}
