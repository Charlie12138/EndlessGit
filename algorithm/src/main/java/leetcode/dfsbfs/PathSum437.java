package leetcode.dfsbfs;

public class PathSum437 {

	public int hasPathSum(TreeNode root, int sum) {
		int result = 0;
		if (root == null) {
			return result;
		}
		if (sum - root.getVal() == 0) {
			result++;
		}

		return result + hasPathSum(root.getLeft(), sum - root.getVal())
				+ hasPathSum(root.getRight(), sum - root.getVal());
	}

	public int pathSum(TreeNode root, int sum) {
		int result = 0;
		//先序遍历
		if (root != null) {
			result = hasPathSum(root, sum) + pathSum(root.getLeft(), sum) + pathSum(root.getRight(), sum);
		}
		return result;
	}

	public static void main(String[] args) {
		TreeNode node = new TreeNode(10);
		TreeNode node1 = new TreeNode(5);
		TreeNode node2 = new TreeNode(-3);
		TreeNode node3 = new TreeNode(3);
		TreeNode node4 = new TreeNode(2);
		TreeNode node5 = new TreeNode(11);
		TreeNode node6 = new TreeNode(3);
		TreeNode node7 = new TreeNode(-2);
		TreeNode node8 = new TreeNode(1);

		node.setLeft(node1);
		node.setRight(node2);

		node1.setLeft(node3);
		node1.setRight(node4);

		node2.setLeft(node5);

		node3.setRight(node6);
		node3.setLeft(node7);

		node4.setRight(node8);
		PathSum437 ps = new PathSum437();
		System.out.println(ps.pathSum(node, 21));
	}
}
