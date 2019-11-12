package leetcode.dfsbfs;

public class FloodFill733 {

	public int[][] floodFill(int[][] image, int sr, int sc, int newColor, int oldColor) {

		if (image[sr][sc] == oldColor && newColor != oldColor) {
			image[sr][sc] = newColor;
		} else {
			return image;
		}
		//上
		if (sr - 1 >= 0) {
			floodFill(image, sr - 1, sc, newColor, oldColor);
		}
		//下
		if (sr + 1 < image.length) {
			floodFill(image, sr + 1, sc, newColor, oldColor);
		}
		//左
		if (sc - 1 >= 0) {
			floodFill(image, sr, sc - 1, newColor, oldColor);
		}
		//右
		if (sc + 1 < image[0].length) {
			floodFill(image, sr, sc + 1, newColor, oldColor);
		}
		return image;
	}

	public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
		if (image == null) {
			return null;
		}
		return floodFill(image, sr, sc, newColor, image[sr][sc]);
	}

	public static void main(String[] args) {
		int[][] image = new int[3][3];
		image[0][0] = 0;
		image[0][1] = 0;
		image[0][2] = 0;
		image[1][0] = 1;
		image[1][1] = 1;
		image[1][2] = 1;
		image[2][0] = 1;
		image[2][1] = 0;
		image[2][2] = 1;

		for (int i = 0; i < image.length; i++) {
			for (int j = 0; j < image[0].length; j++) {
				System.out.print(image[i][j] + " ");
			}
			System.out.println();
		}

		System.out.println("-------------------");

		FloodFill733 floodFill = new FloodFill733();
		floodFill.floodFill(image, 1, 1, 2);
		for (int i = 0; i < image.length; i++) {
			for (int j = 0; j < image[0].length; j++) {
				System.out.print(image[i][j] + " ");
			}
			System.out.println();
		}
	}
}
