package sort;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 堆排序
 * @author clear
 * @since 2019-05-07
 */
public class HeapSort {
	private static void swap(int[] arr, int i, int j) {
		arr[i] = arr[i] ^ arr[j];
		arr[j] = arr[i] ^ arr[j];
		arr[i] = arr[i] ^ arr[j];
	}

	private static void heapSort(int[] arr, int length) {
		//构建大顶堆
		for (int i = length / 2 - 1; i >= 0; i--) {
			//从最后一个非叶子节点从下至上，从右往左 调整结构
			adjustHeap(arr, i, length);
		}
		for (int j = length - 1; j > 0; j--) {
			swap(arr, 0, j);
			//将堆顶元素“沉”到合适的位置
			adjustHeap(arr, 0, j);
		}
	}

	private static void adjustHeap(int[] arr, int index, int length) {
		//先取出当前元素index
		int temp = arr[index];
		//从i结点的左子结点开始，也就是2i+1处开始
		for (int k = index * 2 + 1; k < length; k = k * 2 + 1) {
			//如果左子结点小于右子结点，k指向右子结点
			if(k + 1 < length && arr[k] < arr[k+1]) {
				k++;
			}
			if (arr[k] > temp) {
				//如果子节点大于父节点，将子节点值赋给父节点（不用进行交换）
				arr[index] = arr[k];
				//指向子节点
				index = k;
			} else {
				break;
			}
		}
		//将temp值放到最终的位置
		arr[index] = temp;
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
			heapSort(number, length);
			System.out.println(Arrays.toString(number));
		}
	}
}
