package leetcode.dfsbfs;

import java.awt.*;
import java.util.LinkedList;

public class RottingOranges994 {
	public int orangesRotting(int[][] grid) {
		//检查是否会腐烂
		LinkedList<Point> queue = new LinkedList<>();
		boolean fresh = false;
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j] == 2) {
					queue.add(new Point(i, j));
				}
				if (grid[i][j] == 1) {
					fresh = true;
				}
			}
		}
		//如果有腐烂的橘子但是没有新鲜的橘子
		if (queue.size() != 0 && !fresh) {
			return 0;
		}
		int result = 0;
		int x, y, curVal, upVal, downVal, leftVal, rightVal;
		while (!queue.isEmpty()) {
			boolean rotted = false;
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				Point curPoint = queue.poll();
				x = curPoint.x;
				y = curPoint.y;
				curVal = grid[x][y];
				//上
				if (x - 1 >= 0) {
					upVal = grid[x - 1][y];
					if (upVal == 1 && curVal == 2) {
						grid[x - 1][y] = 2;
						queue.add(new Point(x - 1, y));
						rotted = true;
					}
				}
				//下
				if (x + 1 < grid.length) {
					downVal = grid[x + 1][y];
					if (downVal == 1 && curVal == 2) {
						grid[x + 1][y] = 2;
						queue.add(new Point(x + 1, y));
						rotted = true;
					}
				}
				//左
				if ( y - 1 >= 0) {
					leftVal = grid[x][y - 1];
					if (leftVal == 1 && curVal == 2) {
						grid[x][y - 1] = 2;
						queue.add(new Point(x, y - 1));
						rotted = true;
					}
				}
				//右
				if ( y + 1 < grid[0].length) {
					rightVal = grid[x][y + 1];
					if (rightVal == 1 && curVal == 2) {
						grid[x][y + 1] = 2;
						queue.add(new Point(x, y + 1));
						rotted = true;
					}
				}
				if (rotted && size == i + 1) {
					result++;
				}
			}

		}
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j] == 1) {
					return -1;
				}
			}
		}
		return result;
	}
	public static void main(String[] args) {
		int[][] grid = new int[1][2];
		grid[0][0] = 1; grid[0][1] = 2;
//		grid[0][2] = 1;
//		grid[1][0] = 1; grid[1][1] = 1; grid[1][2] = 0;
//		grid[2][0] = 0; grid[2][1] = 1; grid[2][2] = 1;
		RottingOranges994 ro = new RottingOranges994();
		System.out.println(ro.orangesRotting(grid));
	}
}
