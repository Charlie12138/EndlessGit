package leetcode.tree;

public class CBTGT538 {

	private TreeNode pre;

	public void reverInnerOrder(TreeNode root) {
		if (root == null) return;
		reverInnerOrder(root.getRight());
		if (pre != null) root.setVal(root.getVal() + pre.getVal());
		pre = root;
		reverInnerOrder(root.getLeft());
	}

	public TreeNode convertBST(TreeNode root) {
		if (root == null) return null;
		reverInnerOrder(root);
		return root;
	}

	public static void main(String[] args) {
		TreeNode node1 = new TreeNode(2);
		TreeNode node2 = new TreeNode(5);
		TreeNode node3 = new TreeNode(13);

		node2.setLeft(node1);
		node2.setRight(node3);
		CBTGT538 cbtgt538 = new CBTGT538();
		cbtgt538.convertBST(node2);
		System.out.println();
	}
}
