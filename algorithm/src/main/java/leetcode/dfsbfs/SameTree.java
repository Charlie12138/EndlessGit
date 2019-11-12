package leetcode.dfsbfs;

/**
 * @author clear
 */
public class SameTree {
	public boolean isSameTree(TreeNode p, TreeNode q) {
		if(p == null && q == null) return true;
		if(p == null || q == null) return false;
		if(p.getVal() != q.getVal()) return false;
		return isSameTree(p.getRight(), q.getRight()) && isSameTree(p.getLeft(), q.getLeft());
	}

	public static void main(String[] args) {
		SameTree sameTree = new SameTree();
		TreeNode p = new TreeNode(1);
		TreeNode pl = new TreeNode(2);
		TreeNode pr = new TreeNode(3);
		p.setLeft(pl);
		p.setRight(pr);

		TreeNode q = new TreeNode(1);
		TreeNode ql = new TreeNode(2);
		TreeNode qr = new TreeNode(3);
		p.setLeft(ql);
		p.setRight(qr);

		System.out.println(sameTree.isSameTree(p, q));

	}
}


