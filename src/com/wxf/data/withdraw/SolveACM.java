package com.wxf.data.withdraw;

import java.util.Arrays;
import java.util.Scanner;

import org.omg.CORBA.TCKind;

public class SolveACM {

	public SolveACM() {
		// TODO Auto-generated constructor stub
	}

	public static boolean hasResult = false;
	public static int n;
	public static int m = 0;
	public static int[] plan;
	public static int minC = 32767;

	public static void solveACM(int n, int m, int[] op, int i, int[] w, int tw) {
		int result = -1;
		// ͨ�����ݷ������ACMѵ�����˼·���£�
		// 1.���ȵõ�����ĸ���n�����������w[]���Լ���Ӧ�Ĵ��ӵ�����m�����ϵ�����ȫ���ɿ���̨����
		// 2.���ݻ��ݷ����ӵ�һ�����뿪ʼ�Ƚϣ����Ž�����������ǣ�����ĺ͵��ڴ��ӵ�������ͬʱѡȡ������ĸ�������
		if (i == n) {
			if (tw == m) {
				// �����Ž�
				result = getCount(op, n);
				if (minC > result) {
					minC = result;
					System.arraycopy(op, 0, plan, 0, op.length);
					// ��ʾ�н�
					hasResult = true;
				}
			}
		} else {
			// ��i������ѡȡ
			if (tw + w[i] <= m) {
				op[i] = 1;
				solveACM(n, m, op, i + 1, w, tw + w[i]);
			}
			// ��i�����벻ѡȡ
			op[i] = 0;
			solveACM(n, m, op, i + 1, w, tw);
		}
	}

	/**
	 * չ�ֽ��
	 * 
	 * @param w
	 * @param n
	 */
	public static void display(int[] w, int n, int[] plan) {
		System.out.println(Arrays.toString(plan));
		for (int i = 0; i < n; i++) {
			if (plan[i] == 1) {
				System.out.print(w[i] + "\t");
			}
		}
		System.out.println();
	}

	/**
	 * ��ȡԪ�صĸ���
	 * 
	 * @param op
	 * @param n
	 * @return
	 */
	public static int getCount(int[] op, int n) {
		int count = 0;
		for (int i = 0; i < n; i++) {
			if (op[i] == 1)
				count++;
		}
		return count;
	}

	public static void main(String[] args) {
		// n = 6;
		// n = 3;
		// int op[] = new int[6];

		// int[] w = { 1, 2, 3, 2, 3, 5 };
		System.out.println("�Ӽ�����������ĸ���n,���ӵ�����m:");
		System.out.println("�ڶ����������������");
		Scanner scanner = new Scanner(System.in);
		System.out.println("����������");
		// ����ĵ�һ����Ϊ����ĸ���
		n = scanner.nextInt();
		// ����ĵڶ�����Ϊ���ӵ�����
		m = scanner.nextInt();
		int op[] = new int[n];
		int[] w = new int[n];
		int i = 0;
		while (i < n) {
			w[i] = scanner.nextInt();
			i++;
		}
		// int[] w = { 5, 9, 1 };
		plan = new int[n];
		solveACM(n, m, op, 0, w, 0);
		if (hasResult) {
			System.out.println("�н⣺ѡȡ����С������ĸ���Ϊ��" + minC);
			System.out.println("�����£�");
			System.out.println("��������");
			display(w, n, plan);
		} else {
			System.out.println("û���ҵ����ʵĽ⣺");
		}
	}
}
