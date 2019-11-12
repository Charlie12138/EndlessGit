package leetcode.dfsbfs;

/**
 * @author clear
 * @since 2019-10-26
 */
public class SortedArrayToBST {
	public TreeNode sortedArrayToBST(int[] nums) {
		//左右等分作为子树，中间结点作为根
		return nums == null ? null : buildTree(nums, 0, nums.length - 1);
	}

	public TreeNode buildTree(int[] nums, int s, int e) {
		//根节点直接返回null
		if (s > e) {
			return null;
		}
		//算出中间结点
		int m = s + (e - s) / 2;
		TreeNode root = new TreeNode(nums[m]);
		root.setLeft(buildTree(nums, s, m - 1));
		root.setRight(buildTree(nums, m + 1, e));
		return root;
	}

	public static void main(String[] args) {
		int[] nums = new int[]{-10, -3, 0, 5, 9};
		SortedArrayToBST sortedArrayToBST = new SortedArrayToBST();
		sortedArrayToBST.sortedArrayToBST(nums);
		int a = 1/2;
		System.out.println(a);
	}
}

