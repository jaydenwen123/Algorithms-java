package com.wxf.data.recursion;

public class SolveOverLoad {

	public SolveOverLoad() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 解决简单装在问题
	 * 
	 * 该方法的含义为从data中的元素中的0到i个元素中判断是否有解
	 * 
	 * overLoad(int w, int[] data, int i)为大问题，overLoad(int w, int[] data, int
	 * i-1 )微小问题
	 * 
	 * @param w
	 * @param data
	 * @param i
	 * @return
	 */
	// 求解简单装载问题
	// 问题描述：
	// 设有一批集装箱要装到上一艘载重量为W的轮船，其中集装箱i的重量为wi，现在
	// 从n个集装箱中选出若干个装上轮船，使它们的重量之和正好为W，如果找到解则返回true，
	// 否则，返回false
	// 问题求解：
	// 用数组data存放着n个集装箱的重量。overLoad(int w, int[] data, int i)用于判断
	// 该简单装在问题是否游街，即在data中是否找到重量之和为W的集装箱，若有解，该函数返回true
	// 否则返回false，则overLoad(int w, int[] data, int i-1)是在data中找重量之和为r
	// 的集装箱。overLoad(int w, int[] data, int i)是大问题，overLoad(int w, int[]
	// data, int i-1)是小问题

	// 求解 data, int i-1)的过程如下：
	// （1）先取最后一个集装箱dat[i]，置r=w-data[i](即轮船还能装入的余下重量)
	// （2）若r=0,找到一个解，返回true
	// （3）如果r>0，若还存在没有找到的集装相时，则求解问题转化为 (r,data, int i-1),如果该
	// 小问题 找到解，则返回true，否则舍弃该data[i]，在data(0,n-1)中找解，即转化为(w,data, int i-1)
	// 小问题，若徐偶有的集装相都找到并且没有仍然没有找到解，则返回false
	// （4）若r<0,如果还存在没有找到的集装箱时，则舍弃data[i]，在data(0,i-1)中找解，即转化为(w,data,
	// int i-1)小问题，若所有的集装相都找到没有找到解，返回false
	//

	public static boolean overLoad(int w, int[] data, int i) {
		// 该行的含义是，首先去最后一个集装箱data[i],w表示为总重量
		// 因此r表示的是剩余的重量
		// 2, 9, 5, 6, 3
		// 4, no
		// 10, yes 3,5,2
		// 12 yes 3,9
		// 21 no
		int r = w - data[i];
		if (r == 0) {
			System.out.println(data[i]);
			return true;
		} else if (r > 0) {
			if (i > 0) {
				if (overLoad(r, data, i - 1)) {
					System.out.println(data[i]);
					return true;
				} else {
					return overLoad(w, data, i - 1);
				}
			} else {
				return false;
			}
		}
		// 表示剩余量为零，因此舍弃该元素，从剩下的i-1个元素中找
		else {
			if (i > 0)
				return overLoad(w, data, i - 1);
			else {
				return false;
			}
		}
	}

	public static void main(String[] args) {
		int[] data = { 2, 9, 5, 6, 3 };
		int w = 4, i = data.length - 1;
		System.out.println("w=" + w + "," + overLoad(w, data, i));
		w = 10;
		System.out.println("w=" + w + "," + overLoad(w, data, i));
		w = 12;
		System.out.println("w=" + w + "," + overLoad(w, data, i));
		w = 21;
		System.out.println("w=" + w + "," + overLoad(w, data, i));
	}

}
