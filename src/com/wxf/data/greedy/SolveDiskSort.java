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
 * ��������������� ���˼·���£�
 * 
 * ��.������Ҫ��һ��������ļ���
 * 
 * 2.Ȼ���ļ����зֶγ�m�����ļ�д��
 * 
 * 3.�Բ�ͬ�����ļ�����������
 * 
 * 4.Ȼ�����������ļ����й鲢��
 * 
 * 5.����������ǽ���k·�鲢����
 * 
 * 6.k·�鲢��˼·��ͬ����k�׹�������һ��
 * 
 * @author Administrator
 * 
 */
public class SolveDiskSort {

	// �ñ�����ʾ����ÿһ������ȡ5������
	private static final int MAX = 5;
	// ������¼�ܹ���Ϊ����
	public static int pipe = 1;

	public SolveDiskSort() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * ����k�׹��������Ľڵ���
	 * 
	 * @author Administrator
	 * 
	 */
	public static class KHuffmanNode implements Comparable<KHuffmanNode> {

		// ���ڵ�
		public KHuffmanNode parent;
		// ��һ�����ӽڵ�
		public KHuffmanNode first;
		// �ڶ������ӽڵ�
		public KHuffmanNode second;
		// ���������ӽڵ�
		public KHuffmanNode thrid;
		// ���ĸ����ӽڵ�
		public KHuffmanNode forth;
		// �ýڵ��Ӧ���ļ�
		public File file;
		// �ýڵ��Ȩ��
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
	 * ͨ���������ṩ�����ݣ�����д�뵽�ļ�
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
	 * �������ļ��Ƿ��ȡ��ȷ
	 */
	private static void SortSubFile(String fileName) {
		File file = new File(fileName);
		// ���ļ��ж�ȡ���ݣ�תΪ����
		int[] arr = FromFileToArr(file);
		MergeSort(arr, 0, arr.length - 1);
		// System.out.println(Arrays.toString(arr));
		createFile(arr, fileName);
	}

	/**
	 * ����ʼҪ������ļ����ָ�ɼ������ļ�����������Ȼ�����
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
			// �Ӵ�������ļ��У������ݶ��룬Ȼ��ֶ�д�뵽���ļ���
			while ((len = is.read(buffer)) != -1) {
				String fileName = filePath + j + ".dat";
				File file = new File(fileName);
				os = new FileOutputStream(file);
				os.write(buffer, 0, len);
				j++;
				os.close();
				// �����ļ���������
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
	 * �鲢����
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
	 * �ϲ�����������������Ϊһ������Ĵ�����
	 * 
	 * @param data
	 * @param low
	 * @param mid
	 * @param high
	 */
	private static void merge(int[] data, int low, int mid, int high) {
		// TODO Auto-generated method stub
		// ���ںϲ����������˼·���£�
		// 1.���ȴ���һ�������飬���ȴ�СΪhigh-low+1
		// 2.Ȼ��ʼ���ϵĶ�������������� �Ƚϣ�������������ͨ��high���ָ�data�õ�
		// 3.����С��ֵ���浽��������
		// 4.����ڽ��������ֵ��ֵ��data����
		int length = high - low + 1;
		int[] newArr = new int[length];
		int i = low, j = mid + 1, k = 0;
		// �������������ҽ�С��Ԫ�أ�Ȼ��ֵ��������
		while (i <= mid && j <= high) {
			if (data[i] <= data[j])
				newArr[k++] = data[i++];
			else {
				newArr[k++] = data[j++];
			}
		}
		// һ������ѭ����Ϊ��ȷ�����Ƚ���ʣ�µ������е�ֵ������������
		// ֻ��ִ��һ��
		while (i <= mid) {
			newArr[k++] = data[i++];
		}
		while (j <= high) {
			newArr[k++] = data[j++];
		}
		// ���������ֵ��ֵ��ԭ����
		System.arraycopy(newArr, 0, data, low, length);
	}

	// **************************************************//
	// �����Ƕ�k������Ķν��й鲢
	// ����Ĺ��̲��ù鲢����
	/**
	 * @param files
	 * @param outPutFileName
	 * @param k
	 */
	public static File mergeFile(File[] files) {

		// �ü�����Ҫ�������ÿ�λ�ȡ����������ļ�ת���õ�������
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
			// ����������ϳ�һ�����飬Ȼ���ٹ鲢����
			System.arraycopy(data1, 0, data, 0, data1.length);
			System.arraycopy(data2, 0, data, data1.length, data2.length);
			MergeSort(data, 0, data.length - 1);
			list.add(data);
		}
		// �ڶ��ε�list�Ĵ�С���˴��뻹��Ҫ�Ľ���
		// *****************************

		int n2 = list.size();
		int data1[] = (int[]) list.get(n1);
		int data2[] = (int[]) list.get(n1 + 1);
		int[] data = new int[data1.length + data2.length];
		System.arraycopy(data1, 0, data, 0, data1.length);
		System.arraycopy(data2, 0, data, data1.length, data2.length);
		MergeSort(data, 0, data.length - 1);
		// *******************************
		// ���鲢�������д�뵽�ļ�������
		File f = createFile(data, "temp.dat");
		return f;
	}

	/**
	 * ���ж�m�����ļ�ִ��k·�鲢��ͬʱ�������������ļ����
	 * 
	 * �鲢�Ĺ��̲��ù���k�׹���������ʵ��
	 * 
	 * @param files
	 * @param k
	 * @param outputFileName
	 */
	public static File outPutSortedFile(List<File> files, int k,
			String outputFileName) {
		// ˼·���£�
		// 1�����Ȼ�ȡ����m
		// 2������k·�鲢
		// 3��ͨ�����ȼ���
		int m = files.size();
		// ����Ҫ���ӵĳ���Ϊ0�����
		int zCount = (k - 1) - (m - 1) % (k - 1);
		PriorityQueue<KHuffmanNode> queue = new PriorityQueue<SolveDiskSort.KHuffmanNode>();
		int i = 0;
		queue.add(new KHuffmanNode(new File("zero.dat"), 0));
		// ���½��д���Ҷ�ӽڵ�
		while (i < m) {
			File file = files.get(i);
			KHuffmanNode node = new KHuffmanNode(file, (int) file.length());
			queue.add(node);
			i++;
		}
		// Ȼ�󲻶ϵĴ����ȼ������е����ĸ�Ȩ�ؽ�С���ļ�
		int n = queue.size();
		int p = 1;
		for (; p < n % k; p++) {
			// �����ĸ�Ȩֵ��С����
			KHuffmanNode first = queue.poll();
			KHuffmanNode second = queue.poll();
			KHuffmanNode thrid = queue.poll();
			KHuffmanNode forth = queue.poll();
			// �����������Ľڵ���й鲢
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
		// ��󵯳��鲢������ս��
		KHuffmanNode root = queue.poll();
		File f = root.file;
		// ������յĽ��
		f = outPutFinalResult(outputFileName, f);
		return f;
	}

	/**
	 * �����ս�����
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
	 * ���ļ��е�����תΪint����
	 * 
	 * @param file
	 * @return
	 */
	public static int[] FromFileToArr(File file) {
		int length = (int) file.length();
		// ����һ�����ļ���Сһ��������
		int[] data = new int[length];
		InputStream is = null;
		int i = 0;
		try {
			is = new FileInputStream(file);
			// ���ļ��е����ݴ洢��������
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
		// System.out.println("����ǰ��");
		// System.out.println(Arrays.toString(data));
		// MergeSort(data, 0, data.length-1);
		// System.out.println("�����");
		// System.out.println(Arrays.toString(data));

		// ԭʼ����
		int[] data = { 6, 21, 2, 9, 11, 26, 31, 27, 30, 5, 8, 20, 1, 7, 23, 15,
				16, 25, 29, 3, 4, 24, 10, 12, 13, 17, 28 };
		// ������������ļ�
		File file = createFile(data, "E://in.dat");
		// ����Ҫ�鲢�����ļ�,
		List<File> list = initFile(file, "E://");
		// �������ļ�
		// File[] files = new File[4];
		// for (int i = 0; i < files.length; i++) {
		// files[i] = new File("E://" + i + ".dat");
		// }
		// File f = mergeFile(files);
		// System.out.println(Arrays.toString(FromFileToArr(f)));
		System.out.println("����ǰ�Ľ����");
		System.out.println(Arrays.toString(data));
		File file2 = outPutSortedFile(list, 4, "E://sort.dat");
		System.out.println("�����Ľ����");
		System.out.println(Arrays.toString(FromFileToArr(file2)));

	}
}
