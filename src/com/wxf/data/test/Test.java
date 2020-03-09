package com.wxf.data.test;

import java.util.Scanner;

public class Test {

	public Test() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * ���3n+1����
	 * 
	 * @param n
	 */
	public static int test(int n) {
		if (n < 0)
			throw new RuntimeException("the arguement is error");
//		 System.out.print(n + "  ");
		int count = 1;
		while (n != 1) {
			if (n % 2 == 0)
				n = n / 2;
			else {
				n = n * 3 + 1;
			}
			count++;
//			 System.out.print(n + "  ");
		}
//		 System.out.println();
		return count;
	}

	/**
	 * ���ҳ�i��j֮���������ѭ���ڵ����ֵ
	 * 
	 * @param i
	 * @param j
	 * @return
	 */
	public static int getMaxSize(int i, int j) {
		// 1.����ͨ��ѭ������ÿ��i��j֮���������ѭ���ڳ���
		// 2.��ʼ��һ������max�����¼����ѭ���ڳ���
		// 3.ÿ�μ�����һ��ѭ���ڳ��ȣ�Ȼ��ͽ��к�max�Ƚ�
		// 4��󷵻ظ�ֵa
		int max = 1;
		for (int k = i; k <=j; k++) {
			int temp = test(k);
			if (max < temp) {
				max = temp;
			}
		}
		System.out.println(i + "\t" + j + "\t" + max);
		return max;
	}

	public static void main(String[] args) {
		Scanner scanner=new Scanner(System.in);
		int i=scanner.nextInt();
		int j=scanner.nextInt();
		// System.out.println(getMaxSize(1,10));
		getMaxSize(1, 10);
		getMaxSize(100, 200);
		getMaxSize(201, 210);
		getMaxSize(900, 1000);
		getMaxSize(i, j);
	}
}
