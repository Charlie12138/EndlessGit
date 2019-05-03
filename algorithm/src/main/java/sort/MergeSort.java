package sort;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 归并排序
 * @author clear
 * @since 2019-05-03
 **/
public class MergeSort {

	private static void sort(int[] arr, int left, int right, int[] temp) {
		if (left < right) {
			int mid = (left + right) / 2;
			sort(arr, left, mid, temp);
			sort(arr, mid + 1, right, temp);
			merge(arr, left, mid, right, temp);
		}
	}

	private static void merge(int[] arr, int left, int mid, int right, int[] temp) {
		int i = left;
		int j = mid + 1;
		int k = 0;
		while(i <= mid && j <= right) {
			if (arr[i] <= arr[j]) {
				temp[k++] = arr[i++];
			} else {
				temp[k++] = arr[j++];
			}
		}
		//将左边剩余元素填充进temp中
		while(i <= mid) {
			temp[k++] = arr[i++];
		}
		//将右边剩余元素填充进temp中
		while(j <= right) {
			temp[k++] = arr[j++];
		}
		k = 0;
		while(left <= right) {
			arr[left++] = temp[k++];
		}
	}



	public static void main(String[] args) {
		System.out.println("输入排序数组长度：");
		Scanner scanner = new Scanner(System.in);
		while(scanner.hasNext()) {
			int length = scanner.nextInt();
			System.out.println("输入数组：");
			int[] number = new int[length];
			//创建临时数组
			int[] temp = new int[length];
			for (int i = 0; i < length; i++) {
				number[i] = scanner.nextInt();
			}

			sort(number, 0, length - 1, temp);
			System.out.println(Arrays.toString(number));
		}
	}


}
