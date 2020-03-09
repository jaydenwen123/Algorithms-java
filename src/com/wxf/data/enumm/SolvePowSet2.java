package com.wxf.data.enumm;


/**
 * @author Administrator
 * 
 */
public class SolvePowSet2 {

	// �����Լ�Ԫ�صĸ���
	public static final int MAXSIZE = 1000;
	// ����n�ĸ���
	public static final int MAXN = 10;
	// ��Ų�ͬ���Ӽ�Ԫ�أ����У�data[i][0]��ʾ���Ǹ��Ӽ�Ԫ�صĸ���
	public int[][] data;
	// �Ӽ��ĸ���
	public int n;

	public SolvePowSet2(int[][] data, int n) {
		super();
		this.data = data;
		this.n = n;
	}

	public SolvePowSet2(int[][] data) {
		super();
		this.data = data;
	}

	public SolvePowSet2() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * ͨ���ݹ鷽�������ٷ�������ݼ����� �÷�����ʾ����Ԫ��i���뵽�Ӽ���
	 * 
	 * @param i
	 * @param n
	 * @return
	 */
	public static SolvePowSet2 solvePowSet(int i, int n,
			SolvePowSet2 solvePowSet2) {
		int temp[] = new int[MAXN];
		// ��ȡ�Ӽ��ĸ���
		int m = solvePowSet2.n;
		if (i <= n) {
			for (int j = 0; j < m; j++) {
				// ��Ԫ�ز��뵽�Ӽ���
				// 1.����
				copyArray(solvePowSet2.data[j], temp, solvePowSet2.data[j][0]);
				temp[0]++;
				temp[temp[0]] = i;
				copyArray(temp, solvePowSet2.data[solvePowSet2.n], temp[0]);
				solvePowSet2.n++;
			}

			solvePowSet(i + 1, n, solvePowSet2);
		}

		return solvePowSet2;
	}

	/**
	 * ͨ�����淽������ݼ�����
	 * 
	 * @param n
	 * @return
	 */
	public static SolvePowSet2 solvePowSet(int n) {

		SolvePowSet2 solvePowSet2 = new SolvePowSet2(new int[MAXSIZE][MAXN]);
		// ����������ٷ���˼·���£�
		// 1.���ȳ�ʼ��һ��SolvePowSet2���󣬸ö����е�data�洢��ͬ�Ӽ�������Ԫ��
		// 2.�������ȶ���һ���ռ�����������ӵ�����������
		solvePowSet2.data[0][0] = 0;
		solvePowSet2.n = 1;
		int temp[] = new int[MAXN];
		// 3.��1��n����һ��ѭ����ÿ��ѭ����ʾ��������
		for (int i = 1; i <= n; i++) {
			// 4.�������ֵĹ������£�
			// a.����˼�����ҵ�Ŀǰ�ж��ٸ��Ӽ�,
			int m = solvePowSet2.n;
			for (int j = 0; j < m; j++) {
				copyArray(solvePowSet2.data[j], temp, solvePowSet2.data[j][0]);
				// b.Ȼ���ٶ�ÿ���Ӽ���Ӹ�Ԫ��
				// Ԫ�صĸ�����һ
				temp[0]++;
				// �ڽ����Ԫ�ز���
				temp[temp[0]] = i;
				// c.�����ɺ��䱣�浽����������
				copyArray(temp, solvePowSet2.data[solvePowSet2.n], temp[0]);
				// d.�Ӽ��ĸ�������1
				solvePowSet2.n++;
			}
		}
		// for(int i=0;i<solvePowSet2.n;i++){
		// System.out.println(Arrays.toString(solvePowSet2.data[i]));
		// }
		return solvePowSet2;
	}

	/**
	 * 
	 * ���ȫ�����ݼ�
	 * 
	 * @param solvePowSet2
	 */
	public static void display(SolvePowSet2 solvePowSet2) {
		int[][] data = solvePowSet2.data;
		int n = solvePowSet2.n;
		for (int i = 0; i < n; i++) {
			System.out.print("{  ");
			for (int j = 1; j <= data[i][0]; j++) {
				System.out.print(data[i][j] + "  ");
			}
			System.out.print("}  ");

		}
	}

	/**
	 * ��a�����е�m������Ԫ�ظ��Ƶ�b������
	 * 
	 * @param a
	 * @param b
	 * @param m
	 */
	public static void copyArray(int[] a, int b[], int m) {
		for (int i = 0; i <= m; i++) {
			b[i] = a[i];
		}
	}

	public static void main(String[] args) {
		SolvePowSet2 solvePowSet2 = new SolvePowSet2(new int[MAXSIZE][MAXN]);
		// ����ֵ�����ռ���ӵ��ü�����
		solvePowSet2.data[0][0] = 0;
		solvePowSet2.n = 1;
		
		
		SolvePowSet2 set2 = solvePowSet(3);
		System.out.println("ͨ�����淽������ݼ������⣺");
		display(set2);
		System.out.println();
		System.out.println("ͨ���ݹ鷽������ݼ����⣺");
		set2 = solvePowSet(1, 3,solvePowSet2);
		display(set2);

	}
}
