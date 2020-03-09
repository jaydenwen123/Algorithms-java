package com.wxf.data.greedy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 解决磁盘排序问题 求解思路如下：
 * 
 * １.首先需要有一个排序的文件。
 * 
 * 2.然后将文件进行分段成m个子文件写出
 * 
 * 3.对不同个子文件进行内排序
 * 
 * 4.然后将排序后的子文件进行归并。
 * 
 * 5.整体的排序是进行k路归并排序
 * 
 * 6.k路归并的思路如同构建k阶哈弗曼树一样
 * 
 * @author Administrator
 * 
 */
public class SolveDiskSort {

	// 该变量表示的是每一段最多读取5个数据
	private static final int MAX = 5;
	// 用来记录总工分为几段
	public static int pipe = 1;

	public SolveDiskSort() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 构建k阶哈弗曼树的节点类
	 * 
	 * @author Administrator
	 * 
	 */
	public static class KHuffmanNode implements Comparable<KHuffmanNode> {

		// 父节点
		public KHuffmanNode parent;
		// 第一个孩子节点
		public KHuffmanNode first;
		// 第二个孩子节点
		public KHuffmanNode second;
		// 第三个孩子节点
		public KHuffmanNode thrid;
		// 第四个孩子节点
		public KHuffmanNode forth;
		// 该节点对应的文件
		public File file;
		// 该节点的权重
		public int weigh;

		public KHuffmanNode() {
			// TODO Auto-generated constructor stub
		}

		public KHuffmanNode(KHuffmanNode parent, KHuffmanNode first,
				KHuffmanNode second, KHuffmanNode thrid, KHuffmanNode forth,
				File file, int weigh) {
			super();
			this.parent = parent;
			this.first = first;
			this.second = second;
			this.thrid = thrid;
			this.forth = forth;
			this.file = file;
			this.weigh = weigh;
		}

		public KHuffmanNode(File file, int weigh) {
			super();
			this.file = file;
			this.weigh = weigh;
		}

		@Override
		public int compareTo(KHuffmanNode o) {
			// TODO Auto-generated method stub
			return this.weigh - o.weigh;
		}

	}

	/**
	 * 通过数组中提供的数据，将其写入到文件
	 * 
	 * @param data
	 */
	public static File createFile(int[] data, String fileName) {
		OutputStream os = null;
		File file = null;
		try {
			file = new File(fileName);
			os = new FileOutputStream(file);
			for (int i = 0; i < data.length; i++) {
				byte buffer = (byte) data[i];
				os.write(new byte[] { buffer }, 0, 1);
			}
			os.flush();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return file;
	}

	/**
	 * 测试子文件是否读取正确
	 */
	private static void SortSubFile(String fileName) {
		File file = new File(fileName);
		// 从文件中读取数据，转为数组
		int[] arr = FromFileToArr(file);
		MergeSort(arr, 0, arr.length - 1);
		// System.out.println(Arrays.toString(arr));
		createFile(arr, fileName);
	}

	/**
	 * 将初始要排序的文件，分割成几个子文件，并且排序，然后输出
	 * 
	 * @param is
	 * @param filePath
	 */
	public static List<File> initFile(File in, String filePath) {
		List<File> list = new ArrayList<>();
		OutputStream os = null;
		InputStream is = null;
		int j = 0;
		try {
			is = new FileInputStream(in);
			int len = 0;
			byte[] buffer = new byte[MAX];
			// 从待排序的文件中，将数据读入，然后分段写入到子文件中
			while ((len = is.read(buffer)) != -1) {
				String fileName = filePath + j + ".dat";
				File file = new File(fileName);
				os = new FileOutputStream(file);
				os.write(buffer, 0, len);
				j++;
				os.close();
				// 对子文件进行排序
				SortSubFile(fileName);
				list.add(file);
			}

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return list;
	}

	/**
	 * 归并排序
	 * 
	 * @param data
	 * @param low
	 * @param high
	 */
	public static void MergeSort(int[] data, int low, int high) {
		if (low < high) {
			int mid = (low + high) / 2;
			MergeSort(data, low, mid);
			MergeSort(data, mid + 1, high);
			merge(data, low, mid, high);
		}
	}

	/**
	 * 合并两个有序的子数组的为一个有序的大数组
	 * 
	 * @param data
	 * @param low
	 * @param mid
	 * @param high
	 */
	private static void merge(int[] data, int low, int mid, int high) {
		// TODO Auto-generated method stub
		// 关于合并两个数组的思路如下：
		// 1.首先创建一个新数组，长度大小为high-low+1
		// 2.然后开始不断的对两个子数组进行 比较，其两个子数组通过high来分割data得到
		// 3.将较小的值保存到新数组中
		// 4.最后在将新数组的值赋值给data即可
		int length = high - low + 1;
		int[] newArr = new int[length];
		int i = low, j = mid + 1, k = 0;
		// 从两个数组中找较小的元素，然后赋值给新数组
		while (i <= mid && j <= high) {
			if (data[i] <= data[j])
				newArr[k++] = data[i++];
			else {
				newArr[k++] = data[j++];
			}
		}
		// 一下两个循环是为了确保将比较完剩下的数组中的值拷贝到新数组
		// 只会执行一次
		while (i <= mid) {
			newArr[k++] = data[i++];
		}
		while (j <= high) {
			newArr[k++] = data[j++];
		}
		// 将新数组的值赋值给原数组
		System.arraycopy(newArr, 0, data, low, length);
	}

	// **************************************************//
	// 以下是对k个有序的段进行归并
	// 排序的过程采用归并排序
	/**
	 * @param files
	 * @param outPutFileName
	 * @param k
	 */
	public static File mergeFile(File[] files) {

		// 该集合主要用来存放每次获取到的有序的文件转化得到的数组
		List list = new ArrayList();
		int m = files.length;
		int i = 0;

		while (i < m) {
			list.add(FromFileToArr(files[i]));
			i++;
		}
		int n1 = list.size();
		for (int j = 0; j < n1; j = j + 2) {
			int[] data1 = (int[]) list.get(j);
			int[] data2 = (int[]) list.get(j + 1);
			int[] data = new int[data1.length + data2.length];
			// 将两个数组合成一个数组，然后再归并排序
			System.arraycopy(data1, 0, data, 0, data1.length);
			System.arraycopy(data2, 0, data, data1.length, data2.length);
			MergeSort(data, 0, data.length - 1);
			list.add(data);
		}
		// 第二次的list的大小，此代码还需要改进，
		// *****************************

		int n2 = list.size();
		int data1[] = (int[]) list.get(n1);
		int data2[] = (int[]) list.get(n1 + 1);
		int[] data = new int[data1.length + data2.length];
		System.arraycopy(data1, 0, data, 0, data1.length);
		System.arraycopy(data2, 0, data, data1.length, data2.length);
		MergeSort(data, 0, data.length - 1);
		// *******************************
		// 将归并后的数据写入到文件并返回
		File f = createFile(data, "temp.dat");
		return f;
	}

	/**
	 * 进行对m个子文件执行k路归并，同时将最后的排序后的文件输出
	 * 
	 * 归并的过程采用构建k阶哈弗曼树来实现
	 * 
	 * @param files
	 * @param k
	 * @param outputFileName
	 */
	public static File outPutSortedFile(List<File> files, int k,
			String outputFileName) {
		// 思路如下：
		// 1、首先获取段数m
		// 2、进行k路归并
		// 3、通过优先级来
		int m = files.size();
		// 计算要附加的长度为0的虚段
		int zCount = (k - 1) - (m - 1) % (k - 1);
		PriorityQueue<KHuffmanNode> queue = new PriorityQueue<SolveDiskSort.KHuffmanNode>();
		int i = 0;
		queue.add(new KHuffmanNode(new File("zero.dat"), 0));
		// 以下进行创建叶子节点
		while (i < m) {
			File file = files.get(i);
			KHuffmanNode node = new KHuffmanNode(file, (int) file.length());
			queue.add(node);
			i++;
		}
		// 然后不断的从优先级队列中弹出四个权重较小的文件
		int n = queue.size();
		int p = 1;
		for (; p < n % k; p++) {
			// 弹出四个权值较小的数
			KHuffmanNode first = queue.poll();
			KHuffmanNode second = queue.poll();
			KHuffmanNode thrid = queue.poll();
			KHuffmanNode forth = queue.poll();
			// 对上述弹出的节点进行归并
			File[] files2 = new File[k];
			files2[0] = first.file;
			files2[1] = second.file;
			files2[2] = thrid.file;
			files2[3] = forth.file;
			File file = mergeFile(files2);
			int[] arr = FromFileToArr(file);
			KHuffmanNode parent = new KHuffmanNode(null, first, second, thrid,
					forth, file, (int) file.length());
			first.parent = parent;
			second.parent = parent;
			thrid.parent = parent;
			forth.parent = parent;
			queue.add(parent);
		}
		// 最后弹出归并后的最终结果
		KHuffmanNode root = queue.poll();
		File f = root.file;
		// 输出最终的结果
		f = outPutFinalResult(outputFileName, f);
		return f;
	}

	/**
	 * 将最终结果输出
	 * 
	 * @param outputFileName
	 * @param f
	 */
	private static File outPutFinalResult(String outputFileName, File f) {
		File file = null;
		OutputStream os = null;
		InputStream is = null;
		try {
			file = new File(outputFileName);
			os = new FileOutputStream(file);
			is = new FileInputStream(f);
			int len = is.read();
			while (len != -1) {
				os.write(len);
				len = is.read();
			}
			os.flush();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return file;
	}

	/**
	 * 将文件中的数据转为int数组
	 * 
	 * @param file
	 * @return
	 */
	public static int[] FromFileToArr(File file) {
		int length = (int) file.length();
		// 构建一个和文件大小一样的数组
		int[] data = new int[length];
		InputStream is = null;
		int i = 0;
		try {
			is = new FileInputStream(file);
			// 将文件中的数据存储到数组中
			while (i < length) {
				data[i++] = is.read();
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return data;

	}

	public static void main(String[] args) {
		// int[] data={1,3,45,5,6,8,2,6};
		// System.out.println("排序前：");
		// System.out.println(Arrays.toString(data));
		// MergeSort(data, 0, data.length-1);
		// System.out.println("排序后：");
		// System.out.println(Arrays.toString(data));

		// 原始数据
		int[] data = { 6, 21, 2, 9, 11, 26, 31, 27, 30, 5, 8, 20, 1, 7, 23, 15,
				16, 25, 29, 3, 4, 24, 10, 12, 13, 17, 28 };
		// 创建待排序的文件
		File file = createFile(data, "E://in.dat");
		// 创建要归并的子文件,
		List<File> list = initFile(file, "E://");
		// 测试子文件
		// File[] files = new File[4];
		// for (int i = 0; i < files.length; i++) {
		// files[i] = new File("E://" + i + ".dat");
		// }
		// File f = mergeFile(files);
		// System.out.println(Arrays.toString(FromFileToArr(f)));
		System.out.println("排序前的结果：");
		System.out.println(Arrays.toString(data));
		File file2 = outPutSortedFile(list, 4, "E://sort.dat");
		System.out.println("排序后的结果：");
		System.out.println(Arrays.toString(FromFileToArr(file2)));

	}
}
