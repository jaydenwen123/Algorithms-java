package com.wxf.data.sort;

import java.util.Arrays;

/**
 * 各种排序算法
 * 
 * @author Administrator
 * 
 */
public class AllSort {

	public AllSort() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 直接插入排序 稳定
	 * 
	 * @param data
	 * @return
	 */
	public static int[] insertSort(int[] data) {
		int n = data.length;
		int j = 0, key;
		int k = 0;
		// 1.从前往后插入
		// 2.首先得到要插入的值
		for (int i = 1; i < n; i++) {
			key = data[i];
			j = i;

			// 3.然后再一次遍历它前面的数
			for (k = j - 1; k >= 0; k--) {
				if (data[k] > key) {
					data[k + 1] = data[k];
				} else {
					break;
				}
			}
			data[k + 1] = key;
			// 4.找到要插入的位置
		}
		return data;
	}

	/**
	 * 希尔排序
	 * 
	 * @param data
	 * @return
	 */
	public static int[] shellSort(int[] data) {
		// 第一层控制分组的个数，即分几组
		int p = 0;
		int key = 0;
		for (int i = 5; i >= 1; i = i - 2) {
			// 第二层
			for (int j = 0; j < data.length - i; j++) {
				// 以下是进行组内的直接插入排序，和直接插入排序的区别是每次移动的是组的个数个元素，而不是1
				for (int k = j; k < data.length; k = k + i) {
					key = data[k];
					int m = k;
					for (p = k - i; p >= 0; p = p - i) {
						if (data[p] > key) {
							data[p + i] = data[p];
						} else {
							break;
						}
					}
				}
				data[p + i] = key;
			}
		}
		return data;
	}

	/**
	 * 直接选择排序，不稳定，但是可以进行修改
	 * 
	 * @param data
	 * @return
	 */
	public static int[] selectSort(int[] data) {
		int n = data.length;
		int index = 0;
		int temp;
		// 0.外循环控制比较的趟数，
		// 1.首先每次都找到最小的数
		// 2.然后依次从原数组的第一个位置开始交换数据
		// 3、直到最后剩下一个元素为止，算法结束
		for (int i = 0; i < n - 1; i++) {
			// 每次以第i个数假定为最小值
			int min = data[i];
			// 内循环负责从概述之后寻找最小的值
			for (int j = i + 1; j < n; j++) {
				if (data[j] < min) {
					// 找最小值
					min = data[j];
					index = j;
				}
			}
			// 4. 循环结束，找到最小值。然后交换顺序即可
			if (min != data[i]) {
				temp = data[index];
				data[index] = data[i];
				data[i] = temp;
			}
		}

		return data;
	}

	/**
	 * 堆排序 堆排序的思路如下： 1.首先需要建堆，本例以建最大堆为例 2.键好堆后，通过以下的步骤进行堆排序
	 * 1）每次将对顶元素a[0]和当前堆的最后一个元素进行交换 2）然后将最大堆的个数减1 3）重新调整根节点建堆
	 * 
	 * @param data
	 * @return
	 */
	public static int[] heapSort(int[] data) {
		// 1.
		// 1.首先需要建堆，本例以建最大堆为例
		// 2.键好堆后，通过以下的步骤进行堆排序
		// 1）每次将对顶元素a[0]和当前堆的最后一个元素进行交换
		// 2）然后将最大堆的个数减1
		// 3）重新调整根节点建堆
		int n = data.length;
		for (int i = n - 1; i > 0; i--) {
			// 找出一个最大值后每次都进行创建堆
			createHeap(data, i);
			// System.out.println(Arrays.toString(data));
			exchangeElement(data, 0, i);
		}
		return data;
	}

	/**
	 * 通过最小堆来实现逆序
	 * 
	 * @param data
	 * @return
	 */
	public static int[] heapSort2(int[] data) {
		// 1.
		// 1.首先需要建堆，本例以建最小堆为例
		// 2.键好堆后，通过以下的步骤进行堆排序
		// 1）每次将对顶元素a[0]和当前堆的最后一个元素进行交换
		// 2）然后将最大堆的个数减1
		// 3）重新调整根节点建堆
		int n = data.length;
		for (int i = n - 1; i > 0; i--) {
			// 找出一个最大值后每次都进行创建堆
			createMinHeap(data, i);
			// System.out.println(Arrays.toString(data));
			exchangeElement(data, 0, i);
		}
		return data;
	}

	/**
	 * 创建堆 思路如下： 1.输入：对数组中的元素 2.建堆的过程：从数组的种的第一个非叶子结点开始进行从后往前建堆，直到根节点为至
	 * 3.第一个非叶子结点的位置（只要其有左孩子节点就可以故2*i<=n）,所以i的值为（n-1）/2
	 * 
	 * @param data
	 * @param lastIndex
	 */
	public static void createHeap(int[] data, int lastIndex) {

		for (int i = (lastIndex - 1) / 2; i >= 0; i--) {
			// 以下操作为对某个非叶子结点进行调整的过程，也可以将其抽取出为单独的函数
			// 对单独的某个飞叶子节点调整的步骤如下：
			// 1.首先知道其节点的下标和其左孩子下标，右孩子如果有的话下标为左孩子小标加一
			int parent = i;
			while (2 * parent + 1 <= lastIndex) {
				int child = 2 * parent + 1;
				// 判断是否有右孩子,如果有则进行比较，然后如果右孩子大于左孩子，
				// 则使leftChild 加1，指向左孩子，lefChile一直为较大的那个数
				// 2.在左右孩子节点中找出较大的那个值
				if (child < lastIndex && data[child] < data[child + 1])
					child++;
				// 3.然后将较大值与该非叶子结点进行比较，如果其前者较大，进行交换。否则退出循环
				if (data[parent] < data[child]) {
					exchangeElement(data, parent, child);
				} else
					break;
			}

		}
	}

	/**
	 * 交换数组中两个元素的值
	 * 
	 * @param data
	 * @param i
	 * @param j
	 */
	public static void exchangeElement(int[] data, int i, int j) {
		int temp = data[i];
		data[i] = data[j];
		data[j] = temp;
	}

	// 创建最小堆
	// 思路和创建最大堆一样
	public static void createMinHeap(int[] data, int lastIndex) {
		// 从第一个非叶子结点开始
		for (int i = (lastIndex - 1) / 2; i >= 0; i--) {
			int father = i;
			while (2 * father + 1 <= lastIndex) {
				int child = 2 * father + 1;
				// 找出左右孩子中较小的元素
				if (child < lastIndex && data[child] > data[child + 1])
					child++;
				if (data[father] > data[child])
					exchangeElement(data, father, child);
				else
					break;
			}
		}
	}

	/**
	 * 冒泡排序
	 * 
	 * @param data
	 * @return
	 */
	public static int[] dubbleSort(int[] data) {
		// 冒泡排序的思路如下：
		// 1.首先确定n个数排序需要进行比较n-1趟
		for (int i = 1; i < data.length; i++) {
			int flag = 0;
			// 2.每次比较的次数如下：n-1，n-2...0即data.length-比较的趟数
			for (int j = 0; j < data.length - i; j++) {
				// 3.相邻元素两两进行比较
				if (data[j] > data[j + 1]) {
					int temp = data[j];
					data[j] = data[j + 1];
					data[j + 1] = temp;
					flag = 1;
				}
			}
			// 4.为了防止已排序的数组有序，需要设计一个标记为，用来提高效率
			if (flag == 0) {
				break;
			}
		}
		return data;
	}

	/**
	 * 冒泡排序二
	 * 
	 * @param data
	 * @return
	 */
	public static int[] dubbleSort2(int[] data) {
		int i = 1;
		boolean flag = true;
		// 通过标志位来结束循环，而不是用break关键字
		while (i < data.length && flag == true) {
			flag = false;
			for (int j = 0; j < data.length - i; j++) {
				if (data[j] > data[j + 1]) {
					int temp = data[j];
					data[j] = data[j + 1];
					data[j + 1] = temp;
					flag = true;
				}
			}
		}
		return data;
	}

	/**
	 * 快速排序
	 * 
	 * @param data
	 *            待排序的数组
	 * @param low
	 *            对应的排序的起始元素下标
	 * @param high
	 *            对应的排序的终止元素的下标
	 * @return 返回的是排序后的数组
	 */
	public static int[] quickSort(int[] data, int low, int high) {
		if (low < high) {
			// { 64, 5, 7, 89, 6, 24 }
			// 快速排序思路如下：
			// 1.首先假定第一个元素为起始元素，并将其保存到变量temp中
			int temp = data[low];
			int i = low, j = high;
			// 2.然后从最后一个元素往前找，如果遇到比其小的元素，则进行交换
			while (i < j) {
				while (i < j && temp < data[j])
					j--;
				if (i < j) {
					data[i] = data[j];
					i++;
				}
				// 3.交换后， 又从该元素的下一个元素从前往后找，如果遇到比其大的，就进行和上次留下的空位交换
				while (i < j && temp > data[i])
					i++;
				if (i < j) {
					data[j] = data[i];
					j--;
				}
			}
			// 4.直到从前往后和从后往前找到两个点相遇时，循环结束，然后将该元素放到该点
			// 此时i==j
			data[i] = temp;
			// 5.此时在该元素左边的数全部小于该元素，该元素右边的数，全部大于该元素
			// 6.最后在不断的在其两边的左右区间进行递归
			if (low < i)
				quickSort(data, low, i - 1);
			if (i < high)
				quickSort(data, i + 1, high);
		}
		return data;
	}

	/**
	 * 归并排序
	 * 
	 * @param data
	 *            待排序的数组
	 * @param low
	 *            起始下标
	 * @param high
	 *            终止下标
	 */
	public static int[] mergeSort(int[] data, int low, int high) {
		if (low < high) {
			int mid = (low + high) / 2;
			// 归并排序的思路如下：
			// 1.首先进行左右区间递归
			mergeSort(data, low, mid);
			mergeSort(data, mid + 1, high);
			// 2、然后进行合并
			merge(data, low, mid, high);
			// 算法结束
		}
		return data;
	}

	/**
	 * 对两个排序后的数组进行合并
	 * 
	 * @param data
	 * @param low
	 *            第一个数组的下标
	 * @param mid
	 *            通过mid下标来将数组进行分为两个子数组 第二个数组的下标
	 * @param high
	 *            第二个数组的终止下标
	 * 
	 */
	private static void merge(int[] data, int low, int mid, int high) {
		// 1,2,6,8
		// 3,4,5,7
		// 创建新的一个数组，用来存放排序合并的数据
		int[] newArr = new int[high - low + 1];
		int i = low;
		int j = mid + 1;
		int k = 0;
		while (i <= mid && j <= high) {
			if (data[i] < data[j])
				newArr[k++] = data[i++];
			else {
				newArr[k++] = data[j++];
			}
		}
		// 一下的循环保证把剩余的数据，全部复制到新数组中，两个循环最多只有一个会执行
		while (i <= mid)
			newArr[k++] = data[i++];
		while (j <= high)
			newArr[k++] = data[j++];
		// 将数据拷贝到原数组中
		System.arraycopy(newArr, 0, data, low, newArr.length);
	}

	/**
	 * 基数排序
	 * 
	 * @param data
	 * @param d
	 *            控制循环的次数，即比较几次值为1,10,100等
	 * @return
	 */
	public static int[] radixSort(int[] data, int d) {
		// 基数排序的思路如下:
		int n = data.length;
		// 1.首先初始化十个筒,分别代表的是0-9这十个数字
		// 定义是个筒，同时筒的最大长度为data的数据元素的个数
		int[][] bucket = new int[10][n];
		// 2.初始化一个数组,专门用来存放每个筒里面存放的数据元素的个数
		int[] order = new int[10];
		int m=0;
		int i = 1;
		while (i < d) {
			// 3.依次循环获取data数组中的元素的个位,十位,百位.然后将其不段的放入到筒中,在将其不断的取出
			// 往筒中添加数据
			for (int j = 0; j < n; j++) {
				int digit = (data[j] / i) % 10;
				bucket[digit][order[digit]] = data[j];
				order[digit]++;
			}
			// 将如筒的数据，有存放到data数组中
			for (int j = 0; j < order.length; j++) {
				if (order[j] != 0) {
					for(int p=0;p<order[j];p++){
						data[m++] = bucket[j][p];
					}
				}
				// 将计数清零
				order[j] = 0;
			}

			i *= 10;
			m=0;
		}
		// 4.最后退出循环,算法结束
		return data;
	}

	public static void main(String[] args) {
		int[] data = new int[] { 64, 5, 7, 89, 6, 24 };
		System.out.println("before sort:");
		System.out.println(Arrays.toString(data));
		System.out.println("after sort:");
		
		
		System.out.println("insert sort:");
		System.out.println(Arrays.toString(insertSort(data)));
		System.out.println("shell sort:");
		System.out.println(Arrays.toString(shellSort(data)));
		System.out.println("select sort:");
		System.out.println(Arrays.toString(selectSort(data)));
		System.out.println("heap sort:");
		System.out.println("from small to big: heap sort:");
		System.out.println(Arrays.toString(heapSort(data)));
		System.out.println("from big to small: heap sort:");
		System.out.println(Arrays.toString(heapSort2(data)));
		// 获取升序的排序元素通过栈来逆序
		// System.out.println("through the stack to change order:");
		// int[] array = heapSort(data);
		// MyLinkStack stack = new MyLinkStack();
		// for (int a : array) {
		// stack.push(a);
		// }
		// int i = 0;
		// while (!stack.isEmpty()) {
		// data[i++] = (int) stack.pop();
		// }
		//
		// System.out.println(Arrays.toString(data));
		System.out.println("dubble sort:");
		System.out.println(Arrays.toString(dubbleSort(data)));
		System.out.println(Arrays.toString(dubbleSort2(data)));
		System.out.println("quick sort:");
		System.out
				.println(Arrays.toString(quickSort(data, 0, data.length - 1)));
		System.out.println("merge sort:");
		System.out
				.println(Arrays.toString(mergeSort(data, 0, data.length - 1)));
		System.out.println("radix sort:");
		System.out.println(Arrays.toString(radixSort(data, 1000)));
	}
}
