package com.wxf.data.recursion;

/**
 * �����������
 * 
 * @author Administrator
 * 
 */
public class Comm {

	public Comm() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * ��ѧ�������������㷨ʵ��
	 * 
	 * @param n
	 * @param k
	 * @return
	 */
	public static int comm(int n, int k) {
		if (k == 0 || k == n)
			return 1;
		else
			// ��n�����г�ȡk����
			// �������µĻ��֣�
			// ���ȷ�Ϊ��һ�����Ƿ���k�����У��������ֻ��Ҫ��ʣ�µ�n-1�����У���ȡk-1����
			// ��������n-1�����г�ȡk���˼���
			return comm(n - 1, k - 1) + comm(n - 1, k);
	}

	public static void main(String[] args) {

		System.out.println(comm(5, 2));
		System.out.println(comm(5, 3));
	}

}
