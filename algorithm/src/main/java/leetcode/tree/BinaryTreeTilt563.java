package leetcode.tree;

public class BinaryTreeTilt563 {


	public int sum(TreeNode root) {
		if (root == null) return 0;
		int l = sum(root.getLeft());
		int r = sum(root.getRight());
		return l + r + root.getVal();
	}

	public int findTilt(TreeNode root) {
		if (root == null) return 0;
		int l = findTilt(root.getLeft());
		int r = findTilt(root.getRight());
		return l + r + Math.abs(sum(root.getLeft()) - sum(root.getRight()));
	}

	public static void main(String[] args) {
		TreeNode node1 = new TreeNode(1);
		TreeNode node2 = new TreeNode(2);
		TreeNode node3 = new TreeNode(3);

		node1.setLeft(node2);
		node1.setRight(node3);
		BinaryTreeTilt563 btt = new BinaryTreeTilt563();
		System.out.println(btt.findTilt(node1));
	}
}
