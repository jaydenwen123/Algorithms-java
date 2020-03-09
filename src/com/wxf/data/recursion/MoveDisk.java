package com.wxf.data.recursion;

public class MoveDisk {

	/**
	 * �����ŵ������ �ݹ�ⷨ
	 * 
	 * �÷����Ĺ����ǰ�n�����ӽ�����aut���ӣ���from�ƶ���to������
	 * 
	 * ��������������3�����ΪA��B��C�����ӣ���A�����������n�����ӣ�ÿһ�������������Сһ�㣬
	 * Ҫ���A�������������ȫ���ƶ���C�������棬�ƶ�����Ϊ��
	 * 
	 * 1.һ��ֻ���ƶ�һ�����ӣ�2�ƶ������д����Ӳ��ܷ���С�������棬3���ƶ����������ӿ��� ����A,B,C������һ��������
	 * 
	 * // 1.�ݹ���� ��n=1ʱ������ֱ����� // 2.�ݹ��� ���Ȱ�n-1�����Ӵ�from�ƶ���aut������to //
	 * Ȼ���ٰɰ�n-1�����Ӵ�aut�ƶ���to��������from
	 * 
	 * @param n
	 * @param from
	 * @param to
	 * @param aut
	 */
	public static void tower(int n, String from, String to, String aut) {

		// 1.�ݹ���� ��n=1ʱ������ֱ�����
		// 2.�ݹ��� ���Ȱ�n-1�����Ӵ�from�ƶ���aut������to
		// Ȼ���ٰɰ�n-1�����Ӵ�aut�ƶ���to��������from
		if (n == 1) {
			System.out.println("������1��" + from + "������" + aut + "�ƶ���" + to);
			return;
		}
		tower(n - 1, from, aut, to);
		System.out.println("������" + n + "��" + from + "������" + aut + "�ƶ���" + to);
		tower(n - 1, aut, to, from);
	}

	public MoveDisk() {
		// TODO Auto-generated constructor stub
	}


	public static void main(String[] args) {
		tower(4, "A", "C", "B");
	}

}
