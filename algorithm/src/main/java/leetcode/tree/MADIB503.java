package leetcode.tree;

import java.util.LinkedList;
import java.util.List;

public class MADIB503 {

	private int result = Integer.MAX_VALUE;
	private TreeNode pre = null;
	public void innerOrder(TreeNode root) {
		if (root == null) return;
		innerOrder(root.getLeft());
		if (pre != null) {
			result = Math.min(result, Math.abs(root.getVal() - pre.getVal()));
		}
		pre = root;
		innerOrder(root.getRight());
	}

	public int getMinimumDifference2(TreeNode root) {
		if (root == null) return 0;
		innerOrder(root);
		return result;
	}

	public int getMin(List<Integer> list) {
		int result = Integer.MAX_VALUE;
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				for (int j = 0; j < list.size(); j++) {
					if (i != j && result > Math.abs(list.get(i) - list.get(j))) {
						result = Math.abs(list.get(i) - list.get(j));
						if (result == 0) {
							return result;
						}
					}
				}
			}
		}
		return result;
	}

	public List<Integer> getVals(TreeNode root, List<Integer> list) {
		if (root == null) {
			return list;
		}
		list.add(root.getVal());
		getVals(root.getLeft(), list);
		getVals(root.getRight(), list);
		return list;
	}

	public int getMinimumDifference(TreeNode root) {
		List<Integer> list = new LinkedList<>();
		list.add(root.getVal());
		int left = getMin(getVals(root.getLeft(), list));
		int right = getMin(getVals(root.getRight(), list));
		return left < right ? left : right;
	}

	public static void main(String[] args) {
		TreeNode node1 = new TreeNode(1);
		TreeNode node2 = new TreeNode(2);
		TreeNode node3 = new TreeNode(3);

		node1.setRight(node3);
		node3.setLeft(node2);
		MADIB503 madib503 = new MADIB503();
		long start = System.nanoTime();
		System.out.println(madib503.getMinimumDifference2(node1));
		System.out.println(System.nanoTime() - start);
	}
}
