package leetcode.dfs;

/**
 * @author clear
 */
public class SymmetricTree {
	public boolean isSymmetric(TreeNode root) {
		if(root == null) {
			return true;
		}
		return isSymmetric(root.getLeft(), root.getRight());
	}

	public boolean isSymmetric(TreeNode leftLeft, TreeNode rightRight) {
		if (leftLeft == null && rightRight == null) {
			return true;
		}
		if (leftLeft == null || rightRight == null) {
			return false;
		}
		if (leftLeft.getVal() != rightRight.getVal()) {
			return false;
		}
		return isSymmetric(leftLeft.getLeft(), rightRight.getRight()) &&
				isSymmetric(leftLeft.getRight(), rightRight.getLeft());
	}

	public static void main(String[] args) {
		SymmetricTree symmetricTree = new SymmetricTree();
		TreeNode p = new TreeNode(1);
		TreeNode pl = new TreeNode(2);
		TreeNode pr = new TreeNode(2);
		TreeNode prr = new TreeNode(2);
		p.setLeft(pl);
		p.setRight(pr);
		pr.setRight(prr);

		System.out.println(symmetricTree.isSymmetric(p));
	}

}
