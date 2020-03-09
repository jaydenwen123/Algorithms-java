package com.wxf.data.dividelimt;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

import com.wxf.data.dividelimt.SolveZeroOneBackPack1.BackPackNode;

public class SolveZeroOneBackPack2 {

	public SolveZeroOneBackPack2() {
		// TODO Auto-generated constructor stub
	}

	public static final int MAXN = 20;

	/**
	 * ���������Ľڵ���
	 * 
	 * @author Administrator
	 * 
	 */
	public static class BackPackNode implements Comparable<BackPackNode> {
		// �ýڵ�ı��
		int no;
		// �ýڵ�Ĳ���
		int i;
		// װ�뱳��������
		int w;
		// װ�뱳���ļ�ֵ
		double v;
		// ���������������
		int x[] = new int[MAXN];
		// �ýڵ���Ͻ�
		double ub;

		public BackPackNode() {
			// TODO Auto-generated constructor stub
		}

		public BackPackNode(int no, int i, int w, double v, int[] x, double ub) {
			super();
			this.no = no;
			this.i = i;
			this.w = w;
			this.v = v;
			this.x = x;
			this.ub = ub;
		}

		public BackPackNode(int no, int i, int w, double v, double ub) {
			super();
			this.no = no;
			this.i = i;
			this.w = w;
			this.v = v;
			this.ub = ub;
		}

		public BackPackNode(int no, int i, int w, double v) {
			super();
			this.no = no;
			this.i = i;
			this.w = w;
			this.v = v;
		}

		@Override
		public int compareTo(BackPackNode o) {
			// TODO Auto-generated method stub
			return (int) (this.v - o.v);
		}

	}

	// ����һ�����У�������֯��ڵ�
	public static PriorityQueue<BackPackNode> queue = new PriorityQueue<>();
	private static int[] result;
	private static double maxv;

	/**
	 * ���ݸ����⣬�Զ������ӵĲ���
	 * 
	 * @param no
	 * @param i
	 * @param w
	 * @param v
	 * @return
	 */
	public static void enQueue(int n, BackPackNode node) {
		// ����Ҫ������еĶ���
		if (node.i == n) {
			if (node.v > maxv) {
				maxv = node.v;
				System.arraycopy(node.x, 0, result, 0, node.i);
			}
		} else {
			queue.offer(node);
		}
	}

	/**
	 * ͨ������ʽ��֧�޽編���0/1��������
	 * 
	 * @param n
	 * @param w
	 * @param v
	 */
	public static void solveZeroOneBackPack(int n, int W, int[] w, int[] v) {

		// ͨ������ʽ��֧�޽編���0/1���������˼·���£�
		// 1.���ȳ�ʼ��һ�����У�Ȼ�󽫸��ڵ����
		// 2.�����в�Ϊ��ʱ��һֱִ�г��ӵĲ���
		// 3.���������ӽڵ㣬Ȼ���ж����Ƿ����Լ�����������ϵ������£��������
		// 4.�����Һ��ӣ�����Ҳ������Ƿ����Լ�����������������
		BackPackNode e, e1, e2;
		// ��¼��ӽڵ�ĸ���
		int count = 1;
		// �������ڵ�,Ȼ��������Խ��и�ֵ
		e = new BackPackNode(count++, 0, 0, 0);
		for (int i = 0; i < MAXN; i++)
			e.x[i] = 0;
		// ����ø��ڵ���Ͻ�
		findBound(n, W, w, v, e);
		// �����ڵ����
		queue.offer(e);
		while (!queue.isEmpty()) {
			// ���������ӽڵ㣬Ȼ���ж����Ƿ����Լ�����������ϵ������£��������
			BackPackNode temp = queue.poll();
			int no;
			if ((temp.w + w[temp.i + 1]) <= W) {
				no = count++;
				e1 = new BackPackNode(no, temp.i + 1, temp.w + w[temp.i + 1],
						temp.v + v[temp.i + 1]);
				System.arraycopy(temp.x, 0, e1.x, 0, temp.i);
				e1.x[e1.i] = 1;
				findBound(n, W, w, v, e1);
				enQueue(n, e1);
			}
			// �����Һ��ӣ�����Ҳ������Ƿ����Լ�����������������
			no = count++;
			e2 = new BackPackNode(no, temp.i + 1, temp.w, temp.v);
			System.arraycopy(temp.x, 0, e2.x, 0, temp.i);
			e2.x[e2.i] = 0;
			findBound(n, W, w, v, e2);
			if (e2.ub > maxv) {
				enQueue(n, e2);
			}
		}

	}

	/**
	 * �����֧�ڵ�e���Ͻ�
	 * 
	 * @param n
	 * @param w
	 * @param v
	 * @param e
	 */
	public static void findBound(int n, int W, int[] w, int v[], BackPackNode e) {
		int i = e.i + 1;
		int sumw = e.w;
		double sumv = e.v;
		while (i <= n && (sumw + w[i]) <= W) {
			sumw += w[i];
			sumv += v[i];
			i++;
		}
		// ��ʾ���Ǹýڵ����һ���ڵ�ȫ��װ�벻�˱�����ֻ��װ����
		if (i <= n) {
			e.ub = sumv + (W - w[i]) * v[i] / w[i];
		} else {
			e.ub = sumv;
		}
	}

	public static void main(String[] args) {
		int w[] = { 0, 16, 15, 15 };
		int v[] = { 0, 45, 25, 25 };
		result = new int[MAXN];
		solveZeroOneBackPack(3, 30, w, v);
		for (int i = 1; i <= 3; i++) {
			System.out.print(result[i] + "\t");
		}
		System.out.println("\n�ܼ�ֵ��" + maxv);

	}

}
