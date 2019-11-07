package leetcode.dfs;

import java.util.LinkedList;

/**
 * @author clear
 */
public class SymmetricTree101 {
	//BFS
	public boolean isSymmetric(TreeNode root) {
		if(root == null) {
			return true;
		}
		LinkedList<TreeNode> leftList = new LinkedList<>();
		leftList.push(root.getLeft());
		LinkedList<TreeNode> rightList = new LinkedList<>();
		rightList.push(root.getRight());
		while (!leftList.isEmpty() && !rightList.isEmpty()) {
			//出队
			TreeNode curLeft = leftList.poll();
			TreeNode curRight = rightList.poll();
			if (curLeft != null && curRight != null) {
				if (curLeft.getVal() != curRight.getVal()) {
					return false;
				}
				//按从左到右入队
				leftList.add(curLeft.getLeft());
				leftList.add(curLeft.getRight());
				//按从右到左入队
				rightList.add(curRight.getRight());
				rightList.add(curRight.getLeft());
			}
			if (curLeft != null && curRight == null || curLeft == null && curRight != null) {
				return false;
			}
		}
		if (!leftList.isEmpty() || !rightList.isEmpty()) {
			return false;
		}
		return true;
	}

	//DFS
//	public boolean isSymmetric(TreeNode root) {
//		if(root == null) {
//			return true;
//		}
//		return isSymmetric(root.getLeft(), root.getRight());
//	}
//
//	public boolean isSymmetric(TreeNode leftLeft, TreeNode rightRight) {
//		if (leftLeft == null && rightRight == null) {
//			return true;
//		}
//		if (leftLeft == null || rightRight == null) {
//			return false;
//		}
//		if (leftLeft.getVal() != rightRight.getVal()) {
//			return false;
//		}
//		return isSymmetric(leftLeft.getLeft(), rightRight.getRight()) &&
//				isSymmetric(leftLeft.getRight(), rightRight.getLeft());
//	}

	public static void main(String[] args) {
		SymmetricTree101 symmetricTree = new SymmetricTree101();
		TreeNode p = new TreeNode(1);
		TreeNode pl = new TreeNode(2);
		TreeNode pr = new TreeNode(2);
		TreeNode prr = new TreeNode(2);
		p.setLeft(pl);
		p.setRight(pr);
//		pr.setRight(prr);

		System.out.println(symmetricTree.isSymmetric(p));
	}

}
