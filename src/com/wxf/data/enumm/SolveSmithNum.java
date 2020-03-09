package com.wxf.data.enumm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class SolveSmithNum {

	public SolveSmithNum() {
		// TODO Auto-generated constructor stub
	}

	public static final int MAXN = 20;

	/**
	 * �������һ��������N��Ȼ�������N����С��smith��
	 * 
	 * smith���������ǣ� ������һ��������ͬʱ������������λ���֮�͵����䱾����λ���֮��
	 * 
	 */
	public static int solveSmithNum(int n) {
		boolean flag = false;
		while (!flag) {
			if (isZhi(n)) {
				n = n + 1;
			}
			flag = isSmithNum(n);
			n++;
		}
		List<Integer> list = getSingleFactors(n - 1);
		System.out.print("������������Ϊ��");
		System.out.println(Arrays.asList(list));
		System.out.println("�������ֽ�ʽ��λ���֮��Ϊ��" + getFactorsSum(list));
		list = getEachNumber(n - 1, new ArrayList<Integer>());
		System.out.print("��������λ����Ϊ��");
		System.out.println(Arrays.asList(list));
		System.out.println("������λ֮��Ϊ��" + getEachSum(list));
		return n - 1;
	}

	/**
	 * �ж�һ�����Ƿ���smith��
	 * 
	 * @param n
	 * @return
	 */
	public static boolean isSmithNum(int n) {
		boolean result = false;
		// �ж�һ�����Ƿ�Ϊsmith����˼·���£�
		// 1.���Ȼ�ȡ�������������������������鱣��
		List<Integer> factors = getSingleFactors(n);
		// 2.Ȼ���ٻ�ȡ��������λ���֣�ͬʱҲ�����鱣��
		List<Integer> each = new ArrayList<Integer>();
		each = getEachNumber(n, each);
		// 3.����������Ϊ���ֻ�ȡ�������
		int sum1 = getEachSum(each);
		int sum2 = getFactorsSum(factors);
		// System.out.println(sum1+","+sum2);
		if (sum1 == sum2)
			result = true;
		// 4.�����ȣ��򷵻�true�����򣬷���false
		return result;
	}

	/**
	 * ��ȡһ����������λ������
	 * 
	 * @param n
	 * @return
	 */
	public static List<Integer> getEachNumber(int n, List<Integer> each) {
		if (n != 0) {
			getEachNumber(n / 10, each);
			each.add(n % 10);
			// System.out.println(n % 10);
		}
		return each;
	}

	/**
	 * ��ȡ�������������������ұ��浽list�����з���
	 * 
	 * @param n
	 * @return
	 */
	public static List<Integer> getFactors(int n) {
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 1; i <= n / 2; i++) {
			if (n % i == 0) {
				list.add(i);
			}
		}
		return list;
	}

	public static int getFactorsSum(List<Integer> list) {
		int sum = 0;
		// �������е�����
		for (Integer i : list) {
			List<Integer> each = new ArrayList<>();
			// ��ȡ��ͬ����������λ������
			each = getEachNumber(i, each);
			// ����������
			for (Integer j : each) {
				sum += j;
			}
		}
		return sum;
	}

	/**
	 * ��λ�����Ͳ�����
	 * 
	 * @param each
	 * @return
	 */
	public static int getEachSum(List<Integer> each) {
		int sum = 0;
		for (Integer i : each) {
			sum += i;
		}
		return sum;
	}

	/**
	 * ��ȡĳ�����������ֽ�ʽ�ĸ�����浽list�����з���
	 * 
	 * @param n
	 * @return
	 */
	public static List<Integer> getSingleFactors(int n) {
		List<Integer> list = new ArrayList<>();
		while (!isZhi(n)) {
			int first = getFirstFactor(n);
			list.add(first);
			n /= first;
		}
		list.add(n);
		return list;
	}

	/**
	 * ��ȡ�����ĵ�һ��������
	 * 
	 * @param n
	 * @return
	 */
	public static int getFirstFactor(int n) {
		int i = 2;
		for (; i < n / 2; i++) {
			if (n % i == 0) {
				break;
			}
		}
		return i;
	}

	/**
	 * �ж�һ�����Ƿ�Ϊ����
	 * 
	 * @param n
	 * @return
	 */
	public static boolean isZhi(int n) {
		boolean result = true;
		for (int i = 2; i < n / 2; i++) {
			if (n % i == 0) {
				result = false;
			}
		}
		return result;

	}

	public static void main(String[] args) {
		// List<Integer> each = new ArrayList<>();
		// getEachNumber(4937775, each);
		// for (Integer i : each) {
		// System.out.println(i);
		// }
		// System.out.println(getEachSum(each));
		// List<Integer> list = getSingleFactors(4937775);
		// for (Integer i : list) {
		// System.out.println(i);
		// }
		System.out.println("����������");
		Scanner scanner = new Scanner(System.in);
		List<Integer> cases = new ArrayList<>();
		int ca = scanner.nextInt();
		while (ca != 0) {
			cases.add(ca);
			ca = scanner.nextInt();
		}

		System.out.println("��������");
		for (Integer e : cases) {
			System.out.println(solveSmithNum(e));
		}

		// System.out.println(isSmithNum(4937775));

	}
}
