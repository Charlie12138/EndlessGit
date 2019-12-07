package leetcode.dfsbfs;

import java.util.Arrays;

public class FindMode501 {
	private TreeNode preNode = null;
	private int[] modes;
	private int maxCount = 0;
	private int curCount = 1;
	public int[] findMode(TreeNode root) {
		if (root == null) {
			return new int[0];
		}
		findMode(root.getLeft());
		//如果按中序遍历前面有结点
		if (preNode != null) {
			//如果和前面的结点值一样就加1，不一样说明是新值，置1
			curCount = root.getVal() == preNode.getVal() ? curCount + 1 : 1;
		}
		//如果与众数个数一样就加入众数数组
		if (curCount == maxCount) {
			int[] temp = Arrays.copyOf(modes, modes.length + 1);
			temp[modes.length] = root.getVal();
			modes = temp;
		} else if (curCount > maxCount) {
			//如果大于众数个数就更新众数数组
			modes = new int[1];
			modes[0] = root.getVal();
			maxCount = curCount;
		}
		preNode = root;
		findMode(root.getRight());
		return modes;
	}

	public static void main(String[] args) {
		TreeNode node1 = new TreeNode(2);
		TreeNode node = new TreeNode(1);
		TreeNode node2 = new TreeNode(2);
		TreeNode node3 = new TreeNode(1);
		TreeNode node4 = new TreeNode(2);
		node1.setLeft(node);
		node.setRight(node2);
		node2.setLeft(node3);
		node3.setRight(node4);

		FindMode501 fm = new FindMode501();
		int[] result = fm.findMode(node1);
		for (int i : result) {
			System.out.print(i);
		}
	}
}
