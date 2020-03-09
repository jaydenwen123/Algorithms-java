package com.wxf.data.recursion;

/**
 * ��ӡ��ͬ��������
 * 
 * @author Administrator
 * 
 */
public class PrintGraphic {

	public PrintGraphic() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * ��ӡ������
	 * 6  6  6  6  6  6  
	 * 5  5  5  5  5  
	 * 4  4  4  4  
	 * 3  3  3  
	 * 2  2  
	 * 1  
	 * @param n
	 */
	public static void printGraphic(int n) {
		if (n <= 0)
			return;
		else {
			for (int i = 0; i < n; i++) {
				System.out.print(n + "  ");
			}
			System.out.println();
			printGraphic(n - 1);
		}
	}

	/**
	 * ѭ���ṹ��ӡͼ�� 
	 * 1
	 * 2 2 
	 * 3 3 3 
	 * 4 4 4 4 
	 * 5 5 5 5 5
	 * 
	 * @param n
	 */
	public static void printGraphic2(int n) {
		for (int i = 1; i <= n; i++) {
			for (int j = 0; j < i; j++) {
				System.out.print(i + "  ");
			}
			System.out.println();
		}
	}

	private static int i = 1;

	/**
	 * ��ӡ���µ�ͼ�Σ�ͨ���ݹ�ķ�ʽ 
	 * 1 
	 * 2 2
	 * 3 3 3 
	 * 4 4 4 4 
	 * 5 5 5 5 5
	 * 
	 * @param n
	 */
	public static void printGraphic3(int n) {

		for (int j = 0; j < i; j++) {
			System.out.print(i + "  ");
		}
		System.out.println();
		i++;
		if (i > n)
			return;
		else {
			printGraphic3(n);
		}

	}

	public static void main(String[] args) {

		printGraphic(6);
	}
}
