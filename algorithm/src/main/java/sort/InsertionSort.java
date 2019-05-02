package sort;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 插入排序
 * @author clear
 * @since 2019-05-01
 */
public class InsertionSort {

	private static int[] insertionSort(int[] arr, int legth) {
		for(int i = 1; i < legth; i++) {
			//要排序的记录的前一个记录下标
			int preIndex = i - 1;
			//要排序的记录
			int current = arr[i];
			while(preIndex >= 0 && arr[preIndex] > current) {
				//将大于要排序记录向后移一位
				arr[preIndex + 1] = arr[preIndex];
				//向前比较
				preIndex--;
			}
			//放入正确的位置
			arr[preIndex + 1] = current;
		}
		return arr;
	}

	public static void main(String[] args) {
		System.out.println("输入排序数组长度：");
		Scanner scanner = new Scanner(System.in);
		while(scanner.hasNext()) {
			int length = scanner.nextInt();
			System.out.println("输入数组：");
			int[] number = new int[length];
			for (int i = 0; i < length; i++) {
				number[i] = scanner.nextInt();
			}
			System.out.println(Arrays.toString(insertionSort(number, length)));
		}
	}
}
