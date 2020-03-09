package com.wxf.data.withdraw;

public class SolveZeroOneBackPack {

	public SolveZeroOneBackPack() {
		// TODO Auto-generated constructor stub
	}

	public static int MAX = 20;
	// ������¼���Ž������
	public static int maxWeigh = 0;
	// ������¼���Ž�ļ�ֵ
	public static int maxValue = 0;

	public static int[] result = new int[MAX];

	/**
	 * ���0/1�������⣬ͨ�����ݷ�
	 * 
	 * ���ݷ��������֮����ͨ��������ռ�����Ȼ�����������ȱ��������ó����Ž�
	 * 
	 * @param n
	 *            n����Ʒ
	 * @param i
	 *            i ��ʾ��i����Ʒ
	 * @param W
	 *            �������
	 * @param w
	 *            ��Ʒ������
	 * @param v
	 *            ��Ʒ�ļ�ֵ
	 * @param tw
	 *            ������÷�֧���װ�뱳����������
	 * @param tv
	 *            ������÷�֧���װ�뱳�����ܼ�ֵ
	 * @param op
	 */
	public static void solveZeroOneBackPack1(int n, int i, int W, int[] w,
			int[] v, int tw, int tv, int[] op) {

		// ͨ�����ݷ���ⱳ�������˼·���£�
		// ͨ���ݹ�ķ�����⣬��Ҫ�ҵ�����Ҫ��
		// 1.�ݹ����
		// ������ĵݹ����Ϊ����i>nʱ����ʾ���е���Ʒ���Ѿ�������ɣ����㷨����
		if (i > n) {
			if (tw <= W && tv > maxValue) {
				maxValue = tv;
				maxWeigh = tw;
				for (int j = 1; j <= n; j++) {
					result[j] = op[j];
				}
			}
			return;
		}
		// 2���ݹ���
		// ��i<=nʱ��������Ʒi�����ֿ��ܵĽ������ѡȡ���߲�ѡȡ
		// Ȼ��ֱ��ѡȡ�Ͳ�ѡȡ��������еݹ鼴��
		op[i] = 1;
		solveZeroOneBackPack1(n, i + 1, W, w, v, tw + w[i], tv + v[i], op);
		op[i] = 0;
		solveZeroOneBackPack1(n, i + 1, W, w, v, tw, tv, op);
	}

	/**
	 * ���0/1�������⣬ͨ�����ݷ�
	 * 
	 * ���ݷ��������֮����ͨ��������ռ�����Ȼ�����������ȱ��������ó����Ž�
	 * 
	 * �÷����ǶԵ�һ�ַ����ĸĽ���ͨ������һ���޽����������м�֦����ֹ��Щ��֮�ڵ���������Ѿ�������W,��Ȼ����չ�亢�ӽڵ�
	 * 
	 * @param n
	 *            n����Ʒ
	 * @param i
	 *            i ��ʾ��i����Ʒ
	 * @param W
	 *            �������
	 * @param w
	 *            ��Ʒ������
	 * @param v
	 *            ��Ʒ�ļ�ֵ
	 * @param tw
	 *            ������÷�֧���װ�뱳����������
	 * @param tv
	 *            ������÷�֧���װ�뱳�����ܼ�ֵ
	 * @param op
	 */
	public static void solveZeroOneBackPack2(int n, int i, int W, int[] w,
			int[] v, int tw, int tv, int[] op) {

		// ͨ�����ݷ���ⱳ�������˼·���£�
		// ͨ���ݹ�ķ�����⣬��Ҫ�ҵ�����Ҫ��
		// 1.�ݹ����
		// ������ĵݹ����Ϊ����i>nʱ����ʾ���е���Ʒ���Ѿ�������ɣ����㷨����
		if (i > n) {
			if (tw <= W && tv > maxValue) {
				maxValue = tv;
				maxWeigh = tw;
				for (int j = 1; j <= n; j++) {
					result[j] = op[j];
				}
			}
			return;
		}
		// 2���ݹ���
		// ��i<=nʱ��������Ʒi�����ֿ��ܵĽ������ѡȡ���߲�ѡȡ
		// Ȼ��ֱ��ѡȡ�Ͳ�ѡȡ��������еݹ鼴��
		if (tw + w[i] < W) {
			op[i] = 1;
			solveZeroOneBackPack2(n, i + 1, W, w, v, tw + w[i], tv + v[i], op);
		}

		op[i] = 0;
		solveZeroOneBackPack2(n, i + 1, W, w, v, tw, tv, op);
	}

	/**
	 * ���0/1�������⣬ͨ�����ݷ�
	 * 
	 * ���ݷ��������֮����ͨ��������ռ�����Ȼ�����������ȱ��������ó����Ž�
	 * 
	 * �÷����ǶԵڶ��ַ����ĸĽ���ͨ������һ���޽����������м�֦����ֹ��Щ��֮�ڵ���������Ѿ�������W,��Ȼ����չ�亢�ӽڵ�
	 * 
	 * ͬʱ���ֶ��Һ��ӽڵ���������ƣ�ʵ���϶������������ƺ��ѣ��������������Ž�����ȡ���е�3����Ʒ������ѡ����Ʒ��С�ڵ���1�����������һ������
	 * ���Ӷ�������һ����֮��Ľ�ռ���
	 * 
	 * @param n
	 *            n����Ʒ
	 * @param i
	 *            i ��ʾ��i����Ʒ
	 * @param W
	 *            �������
	 * @param w
	 *            ��Ʒ������
	 * @param v
	 *            ��Ʒ�ļ�ֵ
	 * @param tw
	 *            ������÷�֧���װ�뱳����������
	 * @param tv
	 *            ������÷�֧���װ�뱳�����ܼ�ֵ
	 * @param op
	 */
	public static void solveZeroOneBackPack3(int n, int i, int W, int[] w,
			int[] v, int tw, int tv, int[] op) {

		// ͨ�����ݷ���ⱳ�������˼·���£�
		// ͨ���ݹ�ķ�����⣬��Ҫ�ҵ�����Ҫ��
		// 1.�ݹ����
		// ������ĵݹ����Ϊ����i>nʱ����ʾ���е���Ʒ���Ѿ�������ɣ����㷨����
		if (i > n) {
			if (tw <= W && tv > maxValue) {
				maxValue = tv;
				maxWeigh = tw;
				for (int j = 1; j <= n; j++) {
					result[j] = op[j];
				}
			}
			return;
		}
		// 2���ݹ���
		// ��i<=nʱ��������Ʒi�����ֿ��ܵĽ������ѡȡ���߲�ѡȡ
		// Ȼ��ֱ��ѡȡ�Ͳ�ѡȡ��������еݹ鼴��
		if (tw + w[i] < W) {
			op[i] = 1;
			solveZeroOneBackPack3(n, i + 1, W, w, v, tw + w[i], tv + v[i], op);
		}

		op[i] = 0;
		int m = 0;
		for (int j = 0; j < i; j++) {
			if (op[i] == 0)
				m++;
		}
		if (m <= 1) {
			solveZeroOneBackPack3(n, i + 1, W, w, v, tw, tv, op);
		}
	}

	/**
	 * ��ӡ����data�е�ǰn����
	 * 
	 * @param data
	 * @param n
	 */
	public static void display(int[] data, int n) {
		for (int i = 1; i <= n; i++) {
			System.out.print(data[i] + "\t");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		int n = 4;
		int W = 7;
		int[] w = { 0, 5, 3, 2, 1 };
		int[] v = { 0, 4, 4, 3, 1 };
		int[] op = new int[MAX];
		solveZeroOneBackPack3(n, 1, W, w, v, 0, 0, op);
		System.out.println("ѡȡ����Ʒ��Ϊ��");
		display(result, n);
		System.out.println("����ֵΪ��"+maxValue + ",�������Ϊ��" + maxWeigh);
	}
}
