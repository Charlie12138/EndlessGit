package leetcode.dfs;

import java.util.Arrays;
import java.util.List;

public class MaximumDepthOfN_aryTree559 {
	public int maxDepth(Node root) {
		int result = 0;
		if (root == null) {
			return result;
		}
		List<Node> children = root.children;
		if (children != null) {
//			result = children.stream().mapToInt(child -> maxDepth(child)).max().getAsInt();
			int max = 0;
			for (Node child : children) {
				max = maxDepth(child);
				result = max > result ? max : result;
			}
		}
		return result + 1;
	}

	public static void main(String[] args) {
		Node node2 = new Node(3, null);
		Node node3 = new Node(4, null);
		Node node4 = new Node(5, null);
		Node node5 = new Node(6, null);
		Node node1 = new Node(2, Arrays.asList(node4, node5));
		Node node = new Node(1, Arrays.asList(node1, node2, node3));
		MaximumDepthOfN_aryTree559 depth = new MaximumDepthOfN_aryTree559();
		long start = System.currentTimeMillis();
		System.out.println(depth.maxDepth(node));
		System.out.println("耗时：" + (System.currentTimeMillis() - start));
	}
}
class Node {
	public int val;
	public List<Node> children;

	public Node() {}

	public Node(int val, List<Node> children) {
		this.val = val;
		this.children = children;
	}
}
