package sort;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 快速排序
 * @author clear
 * @since 2019-05-05
 */
public class QuickSort {

	private static void quickSort(int[] arr, int left, int right) {
		if (left >= right) {
			return;
		}
		int i = left;
		int j = right;
		int temp = arr[left];
		while(i != j) {
			//从右边开始找到第一个比基准数小的数
			while(arr[j] >= temp && i < j) {
				j--;
			}
			//从左边开始找到第一个比基准数大的数
			while(arr[i] <= temp && i < j) {
				i++;
			}
			//交换
			if (i < j) {
				arr[i] = arr[i] ^ arr[j];
				arr[j] = arr[i] ^ arr[j];
				arr[i] = arr[i] ^ arr[j];
			}
		}
		//最终将基准数归位
		arr[left] = arr[i];
		arr[i] = temp;
		quickSort(arr, left, i - 1);
		quickSort(arr, i + 1, right);
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
			quickSort(number, 0, length - 1);
			System.out.println(Arrays.toString(number));
		}
	}
}
