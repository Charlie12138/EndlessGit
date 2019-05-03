package sort;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 冒泡排序
 * @author clear
 * @since 2019-04-29
 */
public class BubbleSort {

	private static void bubbleSort1(int[] arr, int length) {
		for (int i = 0; i < length; i++) {
			//j < length - i - 1: 每排序一趟数组最后有序的数字就多一个，后面的每一趟排序就少一个需要比较，所以i增多少j就减多少
			for (int j = 0; j < length - 1 -i; j++) {
				if (arr[j] > arr[j + 1]) {
					//异或
					arr[j] = arr[j+1] ^ arr[j];
					arr[j + 1] = arr[j] ^ arr[j + 1];
					arr[j] = arr[j] ^ arr[j + 1];
				}
			}
		}
	}

	private static void bubbleSort2(int[] arr, int length) {
		for (int i = 0; i < length; i++) {
			boolean flag = false;
			for (int j = 0; j < length - 1 -i; j++) {
				if (arr[j] > arr[j + 1]) {
					arr[j] = arr[j+1] ^ arr[j];
					arr[j + 1] = arr[j] ^ arr[j + 1];
					arr[j] = arr[j] ^ arr[j + 1];
					flag = true;
				}
			}
			//当有一趟排序中没有交换的时候说明数组已经有序，不必再循环下去了
			if (!flag) {
				break;
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
			bubbleSort2(number, length);
			System.out.println(Arrays.toString(number));
		}
	}


}
