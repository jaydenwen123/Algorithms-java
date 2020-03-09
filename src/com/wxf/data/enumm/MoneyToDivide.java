package com.wxf.data.enumm;

import java.util.Date;

import javax.security.auth.kerberos.KerberosKey;

public class MoneyToDivide {

	public MoneyToDivide() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * ���Ǯ�Ҷһ�����
	 * 
	 * ͨ������ѭ�������
	 * 
	 * ���㷨��ʱ�临�Ӷ�ΪO(n3)
	 * 
	 * @param n
	 */
	public static void solveMoneytoDivide(int n) {

		// ����Ǯ�Ҷһ�����Ľ��˼·���£�
		// 1.����Ӳ��ֻ��1,2,5�������͡���n��Ǯ��ֽ�ҽ��жһ�
		// 2.����ͨ���ⷽ����ʵ����⣬��1�ֵĸ���Ϊx��2�ֵĸ���Ϊy��5�ֵĸ���Ϊz
		// 3.��Ӧ�ķ���Ϊ x+2*y+5*z=n
		// 4.���ϵ�x��y,z�ķ�Χ�� ��0<=x<=n,0<=y<=n/2,0<=z<=n/5��
		// 5.ͨ���о������
		int count = 0;
		for (int i = 0; i <= n; i++) {
			for (int j = 0; j <= n / 2; j++) {
				for (int k = 0; k <= n / 5; k++) {
					if (i + 2 * j + 5 * k == n) {
						System.out.println(i + "," + j + "," + k);
						count++;
					}
				}
			}
		}
		System.out.println("�ܹ���" + count + "�ֽ��");
	}

	/**
	 * ͨ������ѭ����������� ���㷨��ʱ�临�Ӷ�ΪO(n2)
	 * 
	 * @param n
	 */
	public static void solveMoneytoDivide2(int n) {
		int count = 0;
		for (int i = 0; i <= n; i++) {
			for (int j = 0; j <= n / 2; j++) {
				int r = n - i - 2 * j;
				if ((r > 0 && r % 5 == 0) || r == 0) {
					System.out.println(i + "," + j + "," + r / 5);
					count++;
				}
			}
		}
		System.out.println("�ܹ���" + count + "�ֽ��");
	}

	/**
	 * �Ľ�����
	 * 
	 * ͨ��һ��ѭ�����,Ч�����
	 * 
	 * @param n
	 */
	public static void solveMoneytoDivide3(int n) {

		int count = 0;
		for (int i = 0; i <= n / 5; i++) {
			int r = n - i * 5;
			if (r == 0) {
				System.out.println(0 + "," + 0 + "," + i);
				count++;
			} else if (r > 0) {
				for (int j = 0; j <= r / 2; j++) {
					int m = r - 2 * j;
					if (m >= 0) {
						System.out.println(m + "," + j + "," + i);
						count++;
					}
				}
			}
		}
		System.out.println("�ܹ���" + count + "�ֽ��");
	}


	public static void main(String[] args) {

		Date time1 = new Date(System.nanoTime());
		solveMoneytoDivide(10);
		Date time2 = new Date(System.nanoTime());
		solveMoneytoDivide2(10);
		Date time3 = new Date(System.nanoTime());
		solveMoneytoDivide3(10);
		Date time4 = new Date(System.nanoTime());
		System.out.println("���ַ����Ա����£�");
		System.out
				.println((float) (time2.getTime() - time1.getTime()) / 1000000);
		System.out
				.println((float) (time3.getTime() - time2.getTime()) / 1000000);
		System.out
				.println((float) (time4.getTime() - time3.getTime()) / 1000000);
	}
}
