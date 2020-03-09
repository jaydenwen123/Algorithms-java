package com.wxf.data.withdraw;

public class SolveFullComb {

	public SolveFullComb() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * ���û��ݷ����ȫ��������,�ǵݹ��㷨���
	 * 
	 * @param a
	 *            ���ÿ�����еĽ��������
	 * @param n
	 *            ��n����
	 * @param k
	 *            ��k������ȫ����
	 */
	public static int solveFullComb1(int a[], int n, int k, int count) {
		// ���÷ǵݹ��˼��ͨ�����ݷ����ȫ���������˼·����:
		// �����ҵ����������������������������iΪ������±��0��ʼС��k
		// 1)a[i+1]>a[i]
		// 2)a[i]-i<=n-k+1
		// 1.���ȳ�ʼ������a[0]λ��Сֵ1,����������2���������һ��2,3
		// 2.�ж����i=k-1ʱ����˵���Ѿ��ҵ�һ�����У��������Ȼ����a[k-1]��Ԫ�ؼ�1
		// 3.���i<k-1ʱ����i++,Ѱ����һ��Ԫ��,a[i]=a[i-1]+1;
		// 4.�������2����������ʾ����a[i]�ĺ�ѡԪ���Ѿ���̽��ϣ������ʱi��ֵΪ0����ʾ
		// ����λ�ö��Ѿ���̽��ϣ��㷨����������i>0��ʱ��������ݼ�i-1,���ݵ�ǰһ��λ�ã�ʹ��a[i]+1;
		int i = 0;
		a[0] = 1;
		while (true) {
			if (a[i] - i <= n - k + 1) {
				if (i == k - 1) {
					displayResult(a, k);
					a[k - 1] = a[k - 1] + 1;
					count++;
				} else {
					i++;
					a[i] = a[i - 1] + 1;
				}
			} else {
				if (i == 0)
					break;
				a[--i]++;
			}
		}
		return count;
	}

	/**
	 * ��ʾ���
	 * 
	 * @param a
	 * @param k
	 */
	public static void displayResult(int[] a, int k) {
		for (int i = 0; i < k; i++) {
			System.out.print(a[i] + "\t");
		}
		System.out.println();
	}

	/**
	 * ���û��ݷ��ݹ��˼����ȫ��������
	 * 
	 * @param a
	 * @param n
	 * @param k
	 * @param i
	 */
	public static void solveFullComb2(int[] a, int n, int k, int i) {

		// ���������û��ݷ��ݹ��˼·���
		if (i < 0)
			return;
		while (a[i] - i <= n - k + 1) {
			if (i == k - 1) {
				displayResult(a, k);
				a[i]++;
			} else {
				i++;
				a[i] = a[i - 1] + 1;
				solveFullComb2(a, n, k, i);
			}
		}
		if (i > 0) {
			a[--i]++;
			solveFullComb2(a, n, k, i);
		}
	}

	public static void main(String[] args) {
		int[] a = new int[10];
		int n = 5, k = 3;
		int count = 0;
		System.out.println("n=5��k=3��ȫ�������£�");
		count = solveFullComb1(a, n, k, count);
		System.out.println("�ܹ���" + count + "�ֽ��");
		System.out.println("�ڶ��ַ�����");
		a[0] = 1;
		solveFullComb2(a, n, k, 0);
	}
}
