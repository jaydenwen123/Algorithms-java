package com.wxf.data.sort;

import java.util.Arrays;

/**
 * ���������㷨
 * 
 * @author Administrator
 * 
 */
public class AllSort {

	public AllSort() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * ֱ�Ӳ������� �ȶ�
	 * 
	 * @param data
	 * @return
	 */
	public static int[] insertSort(int[] data) {
		int n = data.length;
		int j = 0, key;
		int k = 0;
		// 1.��ǰ�������
		// 2.���ȵõ�Ҫ�����ֵ
		for (int i = 1; i < n; i++) {
			key = data[i];
			j = i;

			// 3.Ȼ����һ�α�����ǰ�����
			for (k = j - 1; k >= 0; k--) {
				if (data[k] > key) {
					data[k + 1] = data[k];
				} else {
					break;
				}
			}
			data[k + 1] = key;
			// 4.�ҵ�Ҫ�����λ��
		}
		return data;
	}

	/**
	 * ϣ������
	 * 
	 * @param data
	 * @return
	 */
	public static int[] shellSort(int[] data) {
		// ��һ����Ʒ���ĸ��������ּ���
		int p = 0;
		int key = 0;
		for (int i = 5; i >= 1; i = i - 2) {
			// �ڶ���
			for (int j = 0; j < data.length - i; j++) {
				// �����ǽ������ڵ�ֱ�Ӳ������򣬺�ֱ�Ӳ��������������ÿ���ƶ�������ĸ�����Ԫ�أ�������1
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
	 * ֱ��ѡ�����򣬲��ȶ������ǿ��Խ����޸�
	 * 
	 * @param data
	 * @return
	 */
	public static int[] selectSort(int[] data) {
		int n = data.length;
		int index = 0;
		int temp;
		// 0.��ѭ�����ƱȽϵ�������
		// 1.����ÿ�ζ��ҵ���С����
		// 2.Ȼ�����δ�ԭ����ĵ�һ��λ�ÿ�ʼ��������
		// 3��ֱ�����ʣ��һ��Ԫ��Ϊֹ���㷨����
		for (int i = 0; i < n - 1; i++) {
			// ÿ���Ե�i�����ٶ�Ϊ��Сֵ
			int min = data[i];
			// ��ѭ������Ӹ���֮��Ѱ����С��ֵ
			for (int j = i + 1; j < n; j++) {
				if (data[j] < min) {
					// ����Сֵ
					min = data[j];
					index = j;
				}
			}
			// 4. ѭ���������ҵ���Сֵ��Ȼ�󽻻�˳�򼴿�
			if (min != data[i]) {
				temp = data[index];
				data[index] = data[i];
				data[i] = temp;
			}
		}

		return data;
	}

	/**
	 * ������ �������˼·���£� 1.������Ҫ���ѣ������Խ�����Ϊ�� 2.���öѺ�ͨ�����µĲ�����ж�����
	 * 1��ÿ�ν��Զ�Ԫ��a[0]�͵�ǰ�ѵ����һ��Ԫ�ؽ��н��� 2��Ȼ�����ѵĸ�����1 3�����µ������ڵ㽨��
	 * 
	 * @param data
	 * @return
	 */
	public static int[] heapSort(int[] data) {
		// 1.
		// 1.������Ҫ���ѣ������Խ�����Ϊ��
		// 2.���öѺ�ͨ�����µĲ�����ж�����
		// 1��ÿ�ν��Զ�Ԫ��a[0]�͵�ǰ�ѵ����һ��Ԫ�ؽ��н���
		// 2��Ȼ�����ѵĸ�����1
		// 3�����µ������ڵ㽨��
		int n = data.length;
		for (int i = n - 1; i > 0; i--) {
			// �ҳ�һ�����ֵ��ÿ�ζ����д�����
			createHeap(data, i);
			// System.out.println(Arrays.toString(data));
			exchangeElement(data, 0, i);
		}
		return data;
	}

	/**
	 * ͨ����С����ʵ������
	 * 
	 * @param data
	 * @return
	 */
	public static int[] heapSort2(int[] data) {
		// 1.
		// 1.������Ҫ���ѣ������Խ���С��Ϊ��
		// 2.���öѺ�ͨ�����µĲ�����ж�����
		// 1��ÿ�ν��Զ�Ԫ��a[0]�͵�ǰ�ѵ����һ��Ԫ�ؽ��н���
		// 2��Ȼ�����ѵĸ�����1
		// 3�����µ������ڵ㽨��
		int n = data.length;
		for (int i = n - 1; i > 0; i--) {
			// �ҳ�һ�����ֵ��ÿ�ζ����д�����
			createMinHeap(data, i);
			// System.out.println(Arrays.toString(data));
			exchangeElement(data, 0, i);
		}
		return data;
	}

	/**
	 * ������ ˼·���£� 1.���룺�������е�Ԫ�� 2.���ѵĹ��̣���������ֵĵ�һ����Ҷ�ӽ�㿪ʼ���дӺ���ǰ���ѣ�ֱ�����ڵ�Ϊ��
	 * 3.��һ����Ҷ�ӽ���λ�ã�ֻҪ�������ӽڵ�Ϳ��Թ�2*i<=n��,����i��ֵΪ��n-1��/2
	 * 
	 * @param data
	 * @param lastIndex
	 */
	public static void createHeap(int[] data, int lastIndex) {

		for (int i = (lastIndex - 1) / 2; i >= 0; i--) {
			// ���²���Ϊ��ĳ����Ҷ�ӽ����е����Ĺ��̣�Ҳ���Խ����ȡ��Ϊ�����ĺ���
			// �Ե�����ĳ����Ҷ�ӽڵ�����Ĳ������£�
			// 1.����֪����ڵ���±���������±꣬�Һ�������еĻ��±�Ϊ����С���һ
			int parent = i;
			while (2 * parent + 1 <= lastIndex) {
				int child = 2 * parent + 1;
				// �ж��Ƿ����Һ���,���������бȽϣ�Ȼ������Һ��Ӵ������ӣ�
				// ��ʹleftChild ��1��ָ�����ӣ�lefChileһֱΪ�ϴ���Ǹ���
				// 2.�����Һ��ӽڵ����ҳ��ϴ���Ǹ�ֵ
				if (child < lastIndex && data[child] < data[child + 1])
					child++;
				// 3.Ȼ�󽫽ϴ�ֵ��÷�Ҷ�ӽ����бȽϣ������ǰ�߽ϴ󣬽��н����������˳�ѭ��
				if (data[parent] < data[child]) {
					exchangeElement(data, parent, child);
				} else
					break;
			}

		}
	}

	/**
	 * ��������������Ԫ�ص�ֵ
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

	// ������С��
	// ˼·�ʹ�������һ��
	public static void createMinHeap(int[] data, int lastIndex) {
		// �ӵ�һ����Ҷ�ӽ�㿪ʼ
		for (int i = (lastIndex - 1) / 2; i >= 0; i--) {
			int father = i;
			while (2 * father + 1 <= lastIndex) {
				int child = 2 * father + 1;
				// �ҳ����Һ����н�С��Ԫ��
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
	 * ð������
	 * 
	 * @param data
	 * @return
	 */
	public static int[] dubbleSort(int[] data) {
		// ð�������˼·���£�
		// 1.����ȷ��n����������Ҫ���бȽ�n-1��
		for (int i = 1; i < data.length; i++) {
			int flag = 0;
			// 2.ÿ�αȽϵĴ������£�n-1��n-2...0��data.length-�Ƚϵ�����
			for (int j = 0; j < data.length - i; j++) {
				// 3.����Ԫ���������бȽ�
				if (data[j] > data[j + 1]) {
					int temp = data[j];
					data[j] = data[j + 1];
					data[j + 1] = temp;
					flag = 1;
				}
			}
			// 4.Ϊ�˷�ֹ�����������������Ҫ���һ�����Ϊ���������Ч��
			if (flag == 0) {
				break;
			}
		}
		return data;
	}

	/**
	 * ð�������
	 * 
	 * @param data
	 * @return
	 */
	public static int[] dubbleSort2(int[] data) {
		int i = 1;
		boolean flag = true;
		// ͨ����־λ������ѭ������������break�ؼ���
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
	 * ��������
	 * 
	 * @param data
	 *            �����������
	 * @param low
	 *            ��Ӧ���������ʼԪ���±�
	 * @param high
	 *            ��Ӧ���������ֹԪ�ص��±�
	 * @return ���ص�������������
	 */
	public static int[] quickSort(int[] data, int low, int high) {
		if (low < high) {
			// { 64, 5, 7, 89, 6, 24 }
			// ��������˼·���£�
			// 1.���ȼٶ���һ��Ԫ��Ϊ��ʼԪ�أ������䱣�浽����temp��
			int temp = data[low];
			int i = low, j = high;
			// 2.Ȼ������һ��Ԫ����ǰ�ң������������С��Ԫ�أ�����н���
			while (i < j) {
				while (i < j && temp < data[j])
					j--;
				if (i < j) {
					data[i] = data[j];
					i++;
				}
				// 3.������ �ִӸ�Ԫ�ص���һ��Ԫ�ش�ǰ�����ң�������������ģ��ͽ��к��ϴ����µĿ�λ����
				while (i < j && temp > data[i])
					i++;
				if (i < j) {
					data[j] = data[i];
					j--;
				}
			}
			// 4.ֱ����ǰ����ʹӺ���ǰ�ҵ�����������ʱ��ѭ��������Ȼ�󽫸�Ԫ�طŵ��õ�
			// ��ʱi==j
			data[i] = temp;
			// 5.��ʱ�ڸ�Ԫ����ߵ���ȫ��С�ڸ�Ԫ�أ���Ԫ���ұߵ�����ȫ�����ڸ�Ԫ��
			// 6.����ڲ��ϵ��������ߵ�����������еݹ�
			if (low < i)
				quickSort(data, low, i - 1);
			if (i < high)
				quickSort(data, i + 1, high);
		}
		return data;
	}

	/**
	 * �鲢����
	 * 
	 * @param data
	 *            �����������
	 * @param low
	 *            ��ʼ�±�
	 * @param high
	 *            ��ֹ�±�
	 */
	public static int[] mergeSort(int[] data, int low, int high) {
		if (low < high) {
			int mid = (low + high) / 2;
			// �鲢�����˼·���£�
			// 1.���Ƚ�����������ݹ�
			mergeSort(data, low, mid);
			mergeSort(data, mid + 1, high);
			// 2��Ȼ����кϲ�
			merge(data, low, mid, high);
			// �㷨����
		}
		return data;
	}

	/**
	 * ������������������кϲ�
	 * 
	 * @param data
	 * @param low
	 *            ��һ��������±�
	 * @param mid
	 *            ͨ��mid�±�����������з�Ϊ���������� �ڶ���������±�
	 * @param high
	 *            �ڶ����������ֹ�±�
	 * 
	 */
	private static void merge(int[] data, int low, int mid, int high) {
		// 1,2,6,8
		// 3,4,5,7
		// �����µ�һ�����飬�����������ϲ�������
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
		// һ�µ�ѭ����֤��ʣ������ݣ�ȫ�����Ƶ��������У�����ѭ�����ֻ��һ����ִ��
		while (i <= mid)
			newArr[k++] = data[i++];
		while (j <= high)
			newArr[k++] = data[j++];
		// �����ݿ�����ԭ������
		System.arraycopy(newArr, 0, data, low, newArr.length);
	}

	/**
	 * ��������
	 * 
	 * @param data
	 * @param d
	 *            ����ѭ���Ĵ��������Ƚϼ���ֵΪ1,10,100��
	 * @return
	 */
	public static int[] radixSort(int[] data, int d) {
		// ���������˼·����:
		int n = data.length;
		// 1.���ȳ�ʼ��ʮ��Ͳ,�ֱ�������0-9��ʮ������
		// �����Ǹ�Ͳ��ͬʱͲ����󳤶�Ϊdata������Ԫ�صĸ���
		int[][] bucket = new int[10][n];
		// 2.��ʼ��һ������,ר���������ÿ��Ͳ�����ŵ�����Ԫ�صĸ���
		int[] order = new int[10];
		int m=0;
		int i = 1;
		while (i < d) {
			// 3.����ѭ����ȡdata�����е�Ԫ�صĸ�λ,ʮλ,��λ.Ȼ���䲻�εķ��뵽Ͳ��,�ڽ��䲻�ϵ�ȡ��
			// ��Ͳ���������
			for (int j = 0; j < n; j++) {
				int digit = (data[j] / i) % 10;
				bucket[digit][order[digit]] = data[j];
				order[digit]++;
			}
			// ����Ͳ�����ݣ��д�ŵ�data������
			for (int j = 0; j < order.length; j++) {
				if (order[j] != 0) {
					for(int p=0;p<order[j];p++){
						data[m++] = bucket[j][p];
					}
				}
				// ����������
				order[j] = 0;
			}

			i *= 10;
			m=0;
		}
		// 4.����˳�ѭ��,�㷨����
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
		// ��ȡ���������Ԫ��ͨ��ջ������
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
