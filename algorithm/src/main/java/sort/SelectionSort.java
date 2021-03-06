package sort;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 选择排序
 * @author clear
 * @since 2019-04-30
 */
public class SelectionSort {
	private static void selectionSort(int[] arr, int length) {
		for (int i = 0; i < length - 1; i++) {
			int small = i;
			for (int j = i + 1; j < length; j++) {
				if (arr[small] > arr[j]) {
					//只记录最小的记录
					small = j;
				}
			}
			//如果不同则需要交换
			if (i != small) {
				arr[i] = arr[i] ^ arr[small];
				arr[small] = arr[i] ^ arr[small];
				arr[i] = arr[i] ^ arr[small];
			}
		}
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
			selectionSort(number, length);
			System.out.println(Arrays.toString(number));
		}
	}
}
