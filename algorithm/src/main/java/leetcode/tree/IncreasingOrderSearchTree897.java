package leetcode.tree;


import java.util.ArrayList;
import java.util.List;

public class IncreasingOrderSearchTree897 {
	public List<Integer> inorder(TreeNode root, List<Integer> vals) {
		inorder(root.getLeft(), vals);
		vals.add(root.getVal());
		inorder(root.getRight(), vals);
		return vals;
	}

	public TreeNode increasingBST(TreeNode root) {
		if (root == null) {
			return null;
		}
		List<Integer> vals = new ArrayList<>();
		vals = inorder(root, vals);
		TreeNode node = new TreeNode(0), ans = node;
		for (Integer val : vals) {
			ans.setRight(new TreeNode(val));
			ans = ans.getRight();
		}
		return node.getRight();
	}

	public static void main(String[] args) {
		TreeNode node = new TreeNode(2);
		TreeNode node1 = new TreeNode(1);
		TreeNode node2 = new TreeNode(3);
		TreeNode node3 = new TreeNode(4);
		TreeNode node4 = new TreeNode(5);

		node.setLeft(node1);
		node.setRight(node2);
		node2.setLeft(node3);
		node2.setRight(node4);

		IncreasingOrderSearchTree897 iost = new IncreasingOrderSearchTree897();
		TreeNode treeNode = iost.increasingBST(node);
		System.out.println();
	}
}
