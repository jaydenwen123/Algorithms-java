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
	 * 定义迷宫的每一步的行为
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
	 * 通过回溯法递归框架求解迷宫问题
	 * 
	 * @param mg
	 *            给定的迷宫的条件
	 * @param x1
	 *            每一步的起点横坐标
	 * @param y1
	 *            每一步起点的纵坐标
	 * @param xe
	 *            终点横坐标
	 * @param ye
	 *            终点纵坐标
	 * @param path
	 *            保存每一步的路径
	 */
	public static void solveMiGong1(int[][] mg, int x1, int y1, int xe, int ye,
			MiPath[] path, int count) {

		// 通过回溯法递归框架求解迷宫问题的思路如下：
		// 1.首先确定递归出口
		// solveMiGong(int[][] mg, int x1, int y1, int xe, int ye,
		// MiPath[] path)
		// 该方法表示的是求从(x1,y1)出发到达(xe,ye)的路径当且仅当(x1,y1)==(xe,ye)时算法结束
		// 此时输出该路径
		// 2.确定递归体
		// 从(x1,y1)出发，然后找到一条可以走通的路径，记下一个路径为(i,j)
		// 该问题为大问题，solveMiGong(int[][] mg, int i, int j, int xe, int ye,
		// MiPath[] path)为小问题吗。找到i和j之后，需要做以下几件事
		// a.将其添加到path路径中
		// b.然后将该路径置为-1，
		// c.递归找出口
		// d.最后在回溯，将该路径置为0，其目的是回复前面求迷宫路中的环境
		int di;
		int i = 0, j = 0;
		if (x1 == xe && y1 == ye) {
			path[count] = new MiPath(x1, y1);
			count++;
			outputResult(path, count);
		} else {
			if (mg[x1][y1] == 0) {
				di = 0;
				// 首先将该条路加到路径中
				// 找到i和j
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
	 * 展现结果
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
	 * 采用回溯法非递归框架求解迷宫问题
	 * 
	 * 
	 */
	public static void solveMiGong2(int[][] mg, int x1, int y1, int xe, int ye) {

		// 通过回溯法非递归框架求解迷宫问题的思路如下：
		// １.首先将初始位置的元素入栈，将其初始位置置为-1
		// 2.当栈不为空的时候，一直循环
		// 3.取出栈顶的元素和其方位，如果该位置是出口，则输出栈中的元素，即一条路径，然后再回溯位置置为0，退栈
		// 4.如果不是出口，则找栈顶元素的下一个方位di相邻可走的方块，，如果不存在这样的方块，
		// 则表示进入了死胡同，回溯（将其元素置为0并退栈）
		// 5.如果存在这样的相邻方块，置栈顶元素的方位为di，，并将其找到的相邻方块入栈，
		// 6.如此循环直到输出所有的迷宫路径
		StackType stack = new StackType();
		int i = 0, j = 0;
		// 对应第一步的操作
		stack.top = -1;
		stack.top++;
		stack.paths[stack.top] = new MiPath(x1, y1, -1);
		mg[x1][y1] = -1;
		while (stack.top > -1) {
			// 取出栈顶元素
			MiPath temp = stack.paths[stack.top];
			int di = temp.pos;
			if (temp.x == xe && temp.y == ye) {
				outputResult(stack.paths, stack.top);
				// 栈顶元素回溯，退栈并且方位设为0
				mg[temp.x][temp.y] = 0;
				stack.top--;
				temp = stack.paths[stack.top];
				di = temp.pos;
			}

			// 扎到下一个元素
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

				// 大于4表示的是不存在这样的方块,回溯
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
