package leetcode.dfsbfs;

import java.util.*;

public class CousinsInBinaryTree993 {

	public boolean isCousins(TreeNode root, int x, int y) {
		//放节点
		LinkedList<TreeNode> nodeQueue = new LinkedList<>();
		//放节点对应的值
		LinkedList<Integer> varQueue = new LinkedList<>();
		List<Integer> list = new ArrayList<>(Arrays.asList(x, y));
		nodeQueue.add(root);
		varQueue.add(root.getVal());
		int size;
		while(!nodeQueue.isEmpty() && !varQueue.isEmpty()) {
			size = nodeQueue.size();
			for (int i = 0; i < size; i++) {
				TreeNode curNode = nodeQueue.poll();
//				List<Integer> sonVarList = new ArrayList<>();
				varQueue.poll();
				boolean left = false, right = false;
				if (curNode != null) {
					if (curNode.getLeft() != null) {
						nodeQueue.add(curNode.getLeft());
						varQueue.add(curNode.getLeft().getVal());
//						sonVarList.add(curNode.getLeft().getVal());
						if (list.contains(curNode.getLeft().getVal())) {
							left = true;
						}
					}
					if (curNode.getRight() != null) {
						nodeQueue.add(curNode.getRight());
						varQueue.add(curNode.getRight().getVal());
//						sonVarList.add(curNode.getRight().getVal());
						if (list.contains(curNode.getRight().getVal())) {
							right = true;
						}
					}
				}
				//每经过一次父节点放入它的子节点后都要检查一下里面是否有x和y
//				if (sonVarList.containsAll(list)) {
//					return false;
//				}
				if (left && right) {
					return false;
				}
			}
			if (varQueue.containsAll(list)) {
				return true;
			}
		}
		return false;
	}

	public static void main(String[] args) {

	}
}
