package leetcode.dfs;

/**
 * @author clear
 */
public class TreeNode {
    private Integer val;
	private TreeNode left;
	private TreeNode right;

    public TreeNode(Integer x) {
    	val = x;
    }

	public int getVal() {
		return val;
	}

	public void setVal(Integer val) {
		this.val = val;
	}

	public TreeNode getLeft() {
		return left;
	}

	public void setLeft(TreeNode left) {
		this.left = left;
	}

	public TreeNode getRight() {
		return right;
	}

	public void setRight(TreeNode right) {
		this.right = right;
	}
}
