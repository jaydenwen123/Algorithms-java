package com.wxf.data.divideConquer;

public class FindMidNumber {

	/**
	 * ��Ѱ�������ȳ��������е���λ��
	 * 
	 * �ݹ���β���
	 * 
	 * @param a
	 * @param b
	 * @param low1
	 * @param low2
	 * @param high1
	 * @param high2
	 * @return
	 */
	public static int findMidNumber(int[] a, int[] b, int low1, int low2,
			int high1, int high2) {
		// ����Ѱ�������ȳ��������е���λ����˼·���£�
		// 1.�����ж�����a��b��Ԫ�صĸ�������������ż���������������mid=(s+t)/2;
		// ����ż��Ϊmid=(s+t)/2��(s+t)/2+1
		// 2.������������ȣ��򷵻أ��������a[mid]>b[mid]�������a���ϰ벿�ֺ�b���°벿�֣�
		// 3.�������a���°벿�ֺ�b���ϰ벿�֡�
		// 4.���εݹ���ң�֪������Ԫ�صĸ���Ϊ1ʱ���㷨����
		if (high1 == low1 && high2 == low2) {
			return a[low1] < b[low2] ? a[low1] : b[low2];
		} else {
			int mid1 = (high1 + low1) / 2, mid2 = (high2 + low2) / 2;
			if (a[mid1] == b[mid2])
				return a[mid1];
			if (a[mid1] < b[mid2]) {

				return findMidNumber(a, b, getPostPart(low1, high1), low2,
						high1, getPrePart(low2, high2));
			} else {
				return findMidNumber(a, b, low1, getPostPart(low2, high2),
						getPrePart(low1, high1), high2);
			}
		}

	}

	/**
	 * ѭ���ṹ��Ѱ����λ���㷨
	 * 
	 * @param a
	 * @param low1
	 * @param high1
	 * @param b
	 * @param low2
	 * @param high2
	 * @return
	 */
	public static int findMidNumber(int[] a, int low1, int high1, int[] b,
			int low2, int high2) {
		while (low1 != high1 && low2 != high2) {
			int mid1 = (low1 + high1) / 2;
			int mid2 = (low2 + high2) / 2;
			if (a[mid1] == b[mid2])
				return a[mid1];
			else if (a[mid1] < b[mid2]) {
				low1 = getPostPart(low1, high1);
				high2 = getPrePart(low2, high2);
			} else {
				high1 = getPrePart(low1, high1);
				low2 = getPostPart(low2, high2);
			}
		}
		return a[low1] < b[low2] ? a[low1] : b[low2];
	}

	/**
	 * ��ȡǰ��ε���ʼ�±�
	 * 
	 * @param s
	 * @param t
	 * @return
	 */
	public static int getPrePart(int s, int t) {
		return (s + t) / 2;
	}

	/**
	 * ��ȡ���ε���ʼ�±�
	 * 
	 * @param s
	 * @param t
	 * @return
	 */
	public static int getPostPart(int s, int t) {
		int m = (s + t) / 2;
		if ((s + t) % 2 == 0) {
			return m;
		} else {
			return m + 1;
		}
	}

	public static void main(String[] args) {
		int[] a = { 11, 13, 15, 16, 19 };
		int[] b = { 2, 4, 6, 8, 20 };
		System.out.println("����һͨ���ݹ���εķ�����a��b���е���λ��������λ��Ϊ��");
		System.out
				.println(findMidNumber(a, b, 0, 0, a.length - 1, b.length - 1));
		System.out.println("������ͨ��ѭ���ķ�����a��b���е���λ��������λ��Ϊ��");
		System.out
				.println(findMidNumber(a, 0, a.length - 1, b, 0, b.length - 1));
	}

}
