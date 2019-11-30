package leetcode.dfsbfs;

public class LCA235 {

	public TreeNode findAncestor(TreeNode root, TreeNode p, TreeNode q) {
		if (p.getVal() <= root.getVal() && root.getVal() <= q.getVal()) {
			return root;
		}
		if (q.getVal() <= root.getVal()) {
			return findAncestor(root.getLeft(), p, q);
		}
		if (root.getVal() <= p.getVal()) {
			return findAncestor(root.getRight(), p, q);
		}
		return null;
	}

	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		TreeNode minNode;
		TreeNode maxNode;
		minNode = p.getVal() < q.getVal() ? p : q;
		maxNode = p.getVal() > q.getVal() ? p : q;
		return findAncestor(root, minNode, maxNode);
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

		node9.setLeft(node10);

		LCA235 lca = new LCA235();
		System.out.println(lca.lowestCommonAncestor(node9, node9, node10).getVal());
	}
}
