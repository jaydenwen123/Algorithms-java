package com.wxf.data.withdraw;

public class SolveQueen2 {

	public SolveQueen2() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * ����ջ
	 * 
	 * @author Administrator
	 * 
	 */
	public static class QueenStack {
		public int[] q = new int[20];
		public int top;

		public QueenStack() {
			// TODO Auto-generated constructor stub
		}

	}

	/**
	 * ����ջ���n�ʺ�����
	 * 
	 * @param n
	 */
	public static int solveQueenWithStack(int n) {
		// ����ջ�����n�ʺ������˼·���£�
		// 1.��ʼ����1��Ԫ��Ȼ������ջ
		// 2.��ջ��Ϊ��ʱ��һֱѭ����ȡջ��Ԫ�صĻʺ����k
		// 3.�ӵ�k���ʺ����һ�е���n������һ�����ʵ�λ�ã��������������λ�ã�k��j������ջ���ʺ������޸�Ϊj��
		// ���k==n��ʾ���лʺ������ϣ����ռ��Ԫ�ع��ɵ�һ���⣻���k<n��ʾ�ʺ�û�з��꣬����k+1���ʺ��ջ
		// ���½��Ļʺ����ž�Ϊ0��ʼ��̽�����������������λ�ã�k,j������ʾ��k���ʺ�Ų��£�����ݵ���k-1���ʺ�Ҳ������ջһ��
		// �õ�k-1���ʺ��Ϊջ���ʺ�Ȼ����ͬ���Ĵ�����������ѭ����ջ��Ϊֹ
		QueenStack stack = new QueenStack();
		int k = 1,count=0;
		stack.top = 0;
		stack.top++;
		stack.q[stack.top] = 0;
		while (stack.top > 0) {
			k = stack.top;
			int find = 0;
			for (int j = stack.q[k]+1; j <= n; j++) {
				// ��һ�����ʵ�λ��
				if (place(stack, k, j) != 0) {
					stack.q[stack.top] = j;
					find = 1;
					break;
				}
			}
			if (find == 1) {
				if (k == n) {
					display(stack);
					count++;
				} else {
					// ����k+1��Ԫ����ջ
					stack.top++;
					stack.q[stack.top] = 0;
				}
			} else {
				stack.top--;
			}
		}

		return count;
	}

	/**
	 * ����Ƿ���Էŵ��µ�k���ʺ�
	 * 
	 * @param stack
	 * @param k
	 * @param j
	 * @return
	 */
	public static int place(QueenStack stack, int k, int j) {
		int i = 1;
		if (k == 1)
			return 1;
		while (i < k) {
			if (stack.q[i] == j
					|| (Math.abs(j - stack.q[i]) == Math.abs(i - k)))
				return 0;
			i++;
		}
		return 1;
	}

	public static void display(QueenStack stack) {
		for (int i = 1; i <= stack.top; i++) {
			System.out.print("(" + i + "," + stack.q[i] + ")\t");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		System.out.println("4�ʺ����п��ܵ�������£�");
		int count = solveQueenWithStack(4);
		System.out.println("4�ʺ��ܹ���" + count + "�ַ���");
		System.out.println("5�ʺ����п��ܵ�������£�");
		count = solveQueenWithStack(5);
		System.out.println("5�ʺ��ܹ���" + count + "�ַ���");
	}

}
