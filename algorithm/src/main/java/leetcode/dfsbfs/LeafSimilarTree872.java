package leetcode.dfsbfs;

import java.util.ArrayList;
import java.util.List;

public class LeafSimilarTree872 {

	public List<Integer> getLeaves(TreeNode root, List<Integer> leaves) {
		if (root == null) {
			return leaves;
		}
		if (root.getLeft() == null && root.getRight() == null) {
			leaves.add(root.getVal());
		}
		getLeaves(root.getLeft(), leaves);
		getLeaves(root.getRight(), leaves);
		return leaves;
	}

	public boolean leafSimilar(TreeNode root1, TreeNode root2) {
		List<Integer> leaves1 = getLeaves(root1, new ArrayList<>());
		List<Integer> leaves2 = getLeaves(root2, new ArrayList<>());
		System.out.println(leaves1);
		System.out.println(leaves2);
		if (leaves1.size() != leaves2.size()) {
			return false;
		}
		for (int i = 0; i < leaves1.size(); i++) {
			if (!leaves1.get(i).equals(leaves2.get(i))) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		TreeNode node = new TreeNode(3);
		TreeNode node9 = new TreeNode(3);
		TreeNode node1 = new TreeNode(5);
		TreeNode node2 = new TreeNode(1);
		TreeNode node3 = new TreeNode(6);
		TreeNode node4 = new TreeNode(2);
		TreeNode node5 = new TreeNode(9);
		TreeNode node6 = new TreeNode(8);
		TreeNode node7 = new TreeNode(7);
		TreeNode node8 = new TreeNode(4);

		node.setLeft(node1);
		node.setRight(node2);
		node1.setLeft(node3);
		node1.setRight(node4);
		node2.setLeft(node5);
		node2.setRight(node6);
		node6.setLeft(node7);
		node6.setRight(node8);

		node9.setLeft(node1);
		node9.setRight(node2);
		node1.setLeft(node3);
		node1.setRight(node4);
		node2.setLeft(node5);
		node2.setRight(node6);
		node6.setLeft(node7);
		node6.setRight(node8);

		LeafSimilarTree872 lst = new LeafSimilarTree872();
		System.out.println(lst.leafSimilar(node, node9));

	}
}
