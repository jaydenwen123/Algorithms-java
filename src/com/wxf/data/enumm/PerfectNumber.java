package com.wxf.data.enumm;

public class PerfectNumber {

	/**
	 * ��ӡ����n1��n2֮���ȫ����ȫ��
	 * 
	 * @param n1
	 * @param n2
	 */
	public static void printPerfectNumber(int n1, int n2) {
		// ˼·���£�
		// 1.���ȣ���Ҫ��n1 ��n2������б���
		// 2.Ȼ���ҳ�ÿ������ȫ��������
		// 3.Ȼ�������е�������ӣ��ں͸������бȽϣ������Ƚ������
		if (n1 < 0 || n2 < 0 || n1 > n2)
			return;
		for (int i = n1; i <= n2; i++) {
			int sum = 0;
			// ��ȡĳ������ȫ�����ӵ�˼·���£�
			// 1.����ĳ����m��ȫ������ȫ����1-m/2֮��
			// 2.��1��m/2֮�����������ܱ�m�������������ӷ�����������һ��
			for (int j = 1; j <= i / 2; j++) {
				if (i % j == 0) {
					sum += j;
				}
			}
			if (sum == i)
				System.out.print(i + "\n");
		}

	}

	/**
	 * ����ĳ������ȫ������
	 * 
	 * @param n
	 */
	public static void getFactor(int n) {
		for (int i = 1; i <= n / 2; i++) {
			if (n % i == 0)
				System.out.print(i + "\t");
		}
	}

	public static void main(String[] args) {

		printPerfectNumber(2, 1000);
		getFactor(28);

	}

}
