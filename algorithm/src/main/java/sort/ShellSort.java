package sort;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 希尔排序
 * @author clear
 * @since 2019-05-02
 */
public class ShellSort {

	//将每一组的第一个数看成有序数，从第二个数开始在同组比较
	private static void shellSort2(int[] arr, int length){
		//希尔增量依次除以二
		for (int gap = length / 2; gap > 0; gap /= 2) {
			//从第gap个数开始
			for (int i = gap; i < length; i++) {
				//每个元素与自己组内的数据进行直接插入排序
				if (arr[i] < arr[i - gap]) {
					int temp = arr[i];
					int k = i - gap;
					while(k >= 0 && arr[k] > temp) {
						//将大数向后移动
						arr[k + gap] = arr[k];
						//向前比较
						k -= gap;
					}
					//放入正确位置
					arr[k + gap] = temp;
				}

			}
		}
	}

	private static void shellSort1(int[] arr, int length) {
		//希尔增量依次除以二
		for (int gap = length / 2; gap > 0; gap /= 2) {
			//控制简单的插入排序不会重复，因为i = gap是上一个分组的第二个数
			for (int i = 0; i < gap; i++) {
				//遍历同一分组进行简单插入排序
				for (int j = i + gap; j < length; j++) {
					if (arr[j] < arr[j - gap]) {
						int temp = arr[j];
						int k = j - gap;
						while(k >= 0 && arr[k] > temp) {
							//将大数向后移动
							arr[k + gap] = arr[k];
							//向前比较
							k -= gap;
						}
						//放入正确位置
						arr[k + gap] = temp;
					}
				}
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
			shellSort2(number, length);
			System.out.println(Arrays.toString(number));
		}
	}
}
