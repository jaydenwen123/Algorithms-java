package com.wxf.data.greedy;

import java.util.ArrayList;
import java.util.List;

/**
 * ���ɾ������
 * 
 * @author Administrator
 * 
 */
public class SolveDeleteNumber {

	// ����ɾ������˼·���£�
	// 1.���ȶ���һ���࣬�����к��������Լ������ִ�����һλ����λ��ʮλ����λ��������
	// 2.��һ��5λ����ɾ�������ָ���Ϊ3Ϊ�������ȶ�λ������2�����ֽ��дӸߵ��͵������ҵ���С����
	// ���ȴӶԸ����ֵĵڰ�λ��ǰ������С������
	// Ȼ���ٴ��ҵ������µ����֣�������С�����ּ��ɣ�ֱ����õ����ָ���Ϊ5-3=2��
	public SolveDeleteNumber() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * ���ɾ������
	 * 
	 * @param n
	 * @param k
	 * @return
	 */
	public static int solveDeleteNumber(int d, int k) {
		int n = getNumberLength(d);
		if (k > n)
			return -1;
		// �ü��������洢�ҵ��ķ�����������Ž�
		List<Integer> list = new ArrayList<>();
		int min = getNumber(d, n);
		// i������ǴӸ�λ����Ȼ��Ӻ����ҷ������������
		int i = n - 1;
		// �ò�����ʾ��Ҫɾ���ĸ���
		int count = 0;
		for (; i >= k; i--) {
			if (min > getNumber(d, i)) {
				// �ҵ������λ����С����
				min = getNumber(d, i);
				count++;
			}
		}
		list.add(min);
		System.out.println(count);
		System.out.println(min);
		System.out.println(i);
		if(k-count-1==i){
			
		}
		return n;
	}

	/**
	 * ��ȡ����d��λ��
	 * 
	 * ��İ취
	 * 
	 * @param d
	 * @return
	 */
	private static int getNumberLength(int d) {
		// TODO Auto-generated method stub
		String s = d + "";
		return s.length();
	}

	/**
	 * @return
	 */
	public static int getNumber(int d, int n) {
		String s = d + "";
		int length = s.length() + 1;
		return Integer.parseInt(s.charAt(length - n - 1) + "");
	}

	public static void main(String[] args) {
		int n = 54178;
		// System.out.println(getNumberLength(n));
		// System.out.println(getNumber(n, 5));
		solveDeleteNumber(n, 3);
	}
}
