package com.wxf.data.withdraw;

import java.awt.peer.MenuItemPeer;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

public class SolveMiGong {

	public SolveMiGong() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * �����Թ���ÿһ������Ϊ
	 * 
	 * @author Administrator
	 * 
	 */
	public static class MiPath {
		public int x;
		public int y;
		public int pos;

		public MiPath() {
			// TODO Auto-generated constructor stub
		}

		public MiPath(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

		public MiPath(int x, int y, int pos) {
			super();
			this.x = x;
			this.y = y;
			this.pos = pos;
		}

		@Override
		public String toString() {
			return " (" + x + ", " + y + ")";
		}

	}

	/**
	 * ͨ�����ݷ��ݹ�������Թ�����
	 * 
	 * @param mg
	 *            �������Թ�������
	 * @param x1
	 *            ÿһ������������
	 * @param y1
	 *            ÿһ������������
	 * @param xe
	 *            �յ������
	 * @param ye
	 *            �յ�������
	 * @param path
	 *            ����ÿһ����·��
	 */
	public static void solveMiGong1(int[][] mg, int x1, int y1, int xe, int ye,
			MiPath[] path, int count) {

		// ͨ�����ݷ��ݹ�������Թ������˼·���£�
		// 1.����ȷ���ݹ����
		// solveMiGong(int[][] mg, int x1, int y1, int xe, int ye,
		// MiPath[] path)
		// �÷�����ʾ�������(x1,y1)��������(xe,ye)��·�����ҽ���(x1,y1)==(xe,ye)ʱ�㷨����
		// ��ʱ�����·��
		// 2.ȷ���ݹ���
		// ��(x1,y1)������Ȼ���ҵ�һ��������ͨ��·��������һ��·��Ϊ(i,j)
		// ������Ϊ�����⣬solveMiGong(int[][] mg, int i, int j, int xe, int ye,
		// MiPath[] path)ΪС�������ҵ�i��j֮����Ҫ�����¼�����
		// a.������ӵ�path·����
		// b.Ȼ�󽫸�·����Ϊ-1��
		// c.�ݹ��ҳ���
		// d.����ڻ��ݣ�����·����Ϊ0����Ŀ���ǻظ�ǰ�����Թ�·�еĻ���
		int di;
		int i = 0, j = 0;
		if (x1 == xe && y1 == ye) {
			path[count] = new MiPath(x1, y1);
			count++;
			outputResult(path, count);
		} else {
			if (mg[x1][y1] == 0) {
				di = 0;
				// ���Ƚ�����·�ӵ�·����
				// �ҵ�i��j
				while (di < 4) {
					path[count++] = new MiPath(x1, y1);
					switch (di) {
					case 0:
						i = x1 - 1;
						j = y1;
						break;
					case 1:
						i = x1;
						j = y1 + 1;
						break;
					case 2:
						i = x1 + 1;
						j = y1;
						break;
					case 3:
						i = x1;
						j = y1 - 1;
						break;
					}
					mg[x1][y1] = -1;
					solveMiGong1(mg, i, j, xe, ye, path, count);
					mg[x1][y1] = 0;
					count--;
					di++;
				}
			}

		}

	}

	/**
	 * չ�ֽ��
	 * 
	 * @param path
	 */
	public static void outputResult(MiPath[] path, int count) {
		for (int i = 0; i < count; i++) {
			System.out.print(path[i] + "\t");
		}
		System.out.println();

	}

	public static final int MAX = 20;

	public static class StackType {
		MiPath[] paths;
		int top;

		public StackType() {
			// TODO Auto-generated constructor stub
			paths = new MiPath[MAX];
			top = 0;
		}

	}

	/**
	 * ���û��ݷ��ǵݹ�������Թ�����
	 * 
	 * 
	 */
	public static void solveMiGong2(int[][] mg, int x1, int y1, int xe, int ye) {

		// ͨ�����ݷ��ǵݹ�������Թ������˼·���£�
		// ��.���Ƚ���ʼλ�õ�Ԫ����ջ�������ʼλ����Ϊ-1
		// 2.��ջ��Ϊ�յ�ʱ��һֱѭ��
		// 3.ȡ��ջ����Ԫ�غ��䷽λ�������λ���ǳ��ڣ������ջ�е�Ԫ�أ���һ��·����Ȼ���ٻ���λ����Ϊ0����ջ
		// 4.������ǳ��ڣ�����ջ��Ԫ�ص���һ����λdi���ڿ��ߵķ��飬����������������ķ��飬
		// ���ʾ����������ͬ�����ݣ�����Ԫ����Ϊ0����ջ��
		// 5.����������������ڷ��飬��ջ��Ԫ�صķ�λΪdi�����������ҵ������ڷ�����ջ��
		// 6.���ѭ��ֱ��������е��Թ�·��
		StackType stack = new StackType();
		int i = 0, j = 0;
		// ��Ӧ��һ���Ĳ���
		stack.top = -1;
		stack.top++;
		stack.paths[stack.top] = new MiPath(x1, y1, -1);
		mg[x1][y1] = -1;
		while (stack.top > -1) {
			// ȡ��ջ��Ԫ��
			MiPath temp = stack.paths[stack.top];
			int di = temp.pos;
			if (temp.x == xe && temp.y == ye) {
				outputResult(stack.paths, stack.top);
				// ջ��Ԫ�ػ��ݣ���ջ���ҷ�λ��Ϊ0
				mg[temp.x][temp.y] = 0;
				stack.top--;
				temp = stack.paths[stack.top];
				di = temp.pos;
			}

			// ������һ��Ԫ��
			boolean flag = false;
			while (di < 4 && flag == false) {
				di++;
				switch (di) {
				case 0:
					i = stack.paths[stack.top].x - 1;
					j = stack.paths[stack.top].y;
					break;
				case 1:
					i = stack.paths[stack.top].x;
					j = stack.paths[stack.top].y + 1;
					break;

				case 2:
					i = stack.paths[stack.top].x + 1;
					j = stack.paths[stack.top].y;
					break;

				case 3:
					i = stack.paths[stack.top].x;
					j = stack.paths[stack.top].y - 1;
					break;

				}

				if (i>=0 && j>=0 && mg[i][j] == 0) {
					flag = true;
				}
			}
			if (flag == true) {
				stack.paths[stack.top].pos = di;
				stack.paths[++stack.top] = new MiPath(i, j, -1);
				mg[i][j] = -1;
			} else {

				// ����4��ʾ���ǲ����������ķ���,����
				if(i>=0 && j>=0){
					mg[i][j] = 0;
					stack.top--;
				}
			}
		}
	}

	public static void main(String[] args) {
		int[][] mg = { { 1, 1, 1, 1, 1, 1 }, { 1, 0, 0, 0, 1, 1 },
				{ 1, 0, 1, 0, 0, 1 }, { 1, 0, 0, 0, 1, 1 },
				{ 1, 1, 0, 0, 0, 1 }, { 1, 1, 1, 1, 1, 1 } };

		MiPath path[] = new MiPath[20];
		// solveMiGong1(mg, 1, 1, 4, 4, path, 0);
		solveMiGong2(mg, 1, 1, 4, 4);
	}
}
