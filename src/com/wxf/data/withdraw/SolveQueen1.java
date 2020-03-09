package com.wxf.data.withdraw;

public class SolveQueen1 {

	public static int MAX = 20;
	public static int[] q = new int[MAX];

	/**
	 * ��һ�ַ���
	 * 
	 * ������ջ���n�ʺ�����
	 * 
	 * @param n
	 */
	public static int solveQueen(int n) {

		// ������ջ���n�ʺ������˼·���£�
		// 1.���ȳ�ʼ�������õ�һ��Ԫ��Ϊ��һ�е�0��
		// 2.Ȼ��ʼ���ѭ�������ҳ����еĽ�
		// 3.�����н�ʱ����ζ����Ҫ�ӳ�ʼԪ�صĵ�һ������������̽��֪����󵽴��n��
		// 4.�����е����У���Ԫ�ؿ��Է��õ������������������������ڣ�����ǿ��Է�ֹ���£���place��������0
		// 5.���ڲ�ѭ���˳�ʱ�������������һ���Ǹ�Ԫ�طŲ��£���ʱ��Ҫ���ݣ���k--���ڶ���������Ѿ����������е��С�
		// ��ʱ��Ҫ�жϣ����k=n���ʾ���������е�Ԫ�أ������һ��⣬�����ʾ����Ԫ��û���꣬������һ��Ԫ��

		// ��һ��
		int i = 1;
		int count = 0;
		q[i] = 0;
		// �ڶ���
		while (i <= n && i > 0) {
			q[i] = q[i] + 1;
			// ������
			while (q[i] <= n && place(i) == 0) {
				q[i]++;
			}
			if (q[i] <= n) {
				// ���Ĳ�
				if (i == n) {
					dispasolutons(n);
					count++;
				} else {
					i++;
					q[i] = 0;
				}
				// ���岽
			} else {
				i--;
			}

		}
		return count;
	}

	/**
	 * �ο��ô���
	 * 
	 * @param n
	 * @return
	 */
	public static int solveQueen1(int n) {
		int k = 1;
		int count = 0;
		q[k] = 0;
		while (k <= n && k > 0) {
			q[k] = q[k] + 1;
			while (q[k] <= n && place(k) == 0) {
				q[k]++;
			}
			if (q[k] <= n) {
				if (n == k) {
					dispasolutons(n);
					count++;
				} else {
					k++;
					q[k] = 0;
				}
			} else {
				k--;
			}
		}
		return count;
	}

	/**
	 * չ�ֽ��
	 * 
	 * @param n
	 */
	private static void dispasolutons(int n) {
		// TODO Auto-generated method stub
		for (int i = 1; i <= n; i++) {
			System.out.print("(" + i + "," + q[i] + ")\t");
		}
		System.out.println();
	}

	/**
	 * ���õ�k��Ԫ��
	 * 
	 * @param k
	 * @return
	 */
	public static int place(int k) {
		int i = 1;
		while (i < k) {
			// �ܷ��ø�Ԫ�ص������Ǹ�Ԫ��
			if ((q[i] == q[k]) || (Math.abs(q[i] - q[k]) == Math.abs(i - k))) {
				return 0;
			}
			i++;
		}

		return 1;
	}


	public static void main(String[] args) {
		System.out.println("4�ʺ����п��ܵ�������£�");
		int count = solveQueen(4);
		System.out.println("4�ʺ��ܹ���" + count + "�ַ���");
		System.out.println("5�ʺ����п��ܵ�������£�");
		count = solveQueen(5);
		System.out.println("5�ʺ��ܹ���" + count + "�ַ���");
	}
}
