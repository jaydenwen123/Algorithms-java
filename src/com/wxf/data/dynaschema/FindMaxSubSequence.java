package com.wxf.data.dynaschema;


public class FindMaxSubSequence {

	public FindMaxSubSequence() {
		// TODO Auto-generated constructor stub
	}

	// �������������������˼·���£�
	// 1.���ȸ�����������a��b��Ȼ������������еĳ���
	// �����������£�
	// a�����ȶ��������н����жϣ�����������е����һ��Ԫ����ȣ����Ԫ�ؿɶ��ǹ��������е�Ԫ�أ�
	// ����ת��Ϊ�����������е�ʣ�µ�Ԫ����������������е�����
	// b��������������е����һ��Ԫ�ز���ȡ����ҹ��������е����һ��Ԫ�ص���a���е����һ��Ԫ�أ�
	// ��ֻ��Ҫ�Ӹ����е���ʣ�µ�Ԫ�������������
	// c�������������һ��Ԫ�ص���b�����һ��Ԫ�أ���������

	// �������ͨ���ݹ��˼·���
	// 1.�ݹ����
	// �ݹ���������i=0 ���� j=0,�򳤶�Ϊ��
	// 2���ݹ���
	// �ݹ����ǣ�������Ԫ�����Ԫ�����ʱ�����ȼ�1
	// �����ҳ����������г��ȼ���

	// 2.��������������У�ͨ���������洢

	/**
	 * 
	 * ����󹫹������еĳ���
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static int getMaxSubSequeLength(String a, String b, int[][] length) {
		int alen = a.length();
		int blen = b.length();
		// ���ڵ�һ�����
		for (int i = 0; i < alen + 1; i++)
			length[i][0] = 0;
		for (int j = 0; j < blen + 1; j++)
			length[0][j] = 0;
		for (int i = 1; i <= alen; i++) {
			for (int j = 1; j <= blen; j++) {
				if (a.charAt(i - 1) == b.charAt(j - 1))
					// ����ڶ������
					length[i][j] = length[i - 1][j - 1] + 1;
				else {
					// ������������
					if (length[i][j - 1] >= length[i - 1][j]) {
						length[i][j] = length[i][j - 1];
					} else {
						length[i][j] = length[i - 1][j];
					}
				}
			}
		}
		return length[alen][blen];
	}

	/**
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static String getMaxSubSequence(String a, String b) {

		int i = a.length(), j = b.length();
		int[][] length = new int[i + 1][j + 1];
		int len = getMaxSubSequeLength(a, b, length);
		char[] subSeque = new char[len];
		int k = subSeque.length;
		while (k > 0) {
			if (length[i][j] == length[i][j - 1])
				j--;
			else if (length[i][j] == length[i - 1][j])
				i--;
			else {
				subSeque[--k] = a.charAt(i - 1);
				i--;
				j--;
			}
		}
		return new String(subSeque);
	}

	public static void main(String[] args) {
		String a = "abcbdb";
		String b = "accbbabdbb";
		System.out.println(getMaxSubSequence(a, b));
	}
}
