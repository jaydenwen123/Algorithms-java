package com.wxf.data.enumm;

public class findNumber {

	public findNumber() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * ��һ����λ�������ȸ���λ����ǧλ�ϵ����ֺͰ�λ�ϵ�����δ֪��ֻ֪����Ϊ�͸�λ�ϵ� ����Ϊ1
	 * ��2����֪��������ȥ7Ȼ����Ա�7��������ȥ8���Ա�8��������ȥ9���Ա�9����
	 *
	 * @return
	 */
	public static int find() {
		int num = -1;
		for (int i = 1; i <= 9; i++) {
			for (int j = 0; j <= 9; j++) {
				int n = 1000 * i + 100 * j + 12;
				if ((n - 7) % 7 == 0 && (n - 8) % 8 == 0 && (n - 9) % 9 == 0) {
					num = n;
					break;
				}
			}
		}
		return num;
	}

	public static void main(String[] args) {
		System.out.println("������ĸ���Ϊ��");
		System.out.println(find());
	}

}
