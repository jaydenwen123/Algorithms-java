package com.wxf.data.divideConquer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;

public class RepeatNumber {

	// 该属性表示的是对应的众数
	public int number;
	// 该属性对应的是该众数出现的次数
	public int count;

	public RepeatNumber() {
		// TODO Auto-generated constructor stub
	}

	public RepeatNumber(int number, int count) {
		super();
		this.number = number;
		this.count = count;
	}

	@Override
	public String toString() {
		return "RepeatNumber [number=" + number + ", count=" + count + "]";
	}

	/**
	 * 解决众数问题
	 * 
	 * 从给定的数据集合中找出众数和其出现的次数
	 * 
	 * @param data
	 * @return
	 */
	public static RepeatNumber findMaxRepeatNumber(int[] data) {

		// 解决众数问题的思路如下：
		// 1、首先假设该数据元素由数组存放
		// 2、然后查找众数问题首先变为统计数字出现在的频率
		// 3.改统计结果由map集合来存放
		// 4、最后在找出出现次数最多的元素即可
		// 5.利用面向对象的方法来返回结果
		RepeatNumber repeat = null;
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		// 遍历该数据集合，然后将统计的结果存放在map中
		for (int a : data) {
			if (map.containsKey(a)) {
				map.put(a, map.get(a) + 1);
			} else {
				map.put(a, 1);
			}
		}
		// 初始化数据
		int max = map.get(data[0]);
		int index = data[0];
		// 以下遍历map集合，查找众数和其出现的重数
		for (Entry<Integer, Integer> entry : map.entrySet()) {
			int key = entry.getKey();
			int value = entry.getValue();
			if (value > max) {
				max = value;
				index = key;
			}
		}
		repeat = new RepeatNumber(index, max);
		return repeat;
	}

	public static void main(String[] args) {
		System.out.println("输入样本：");
		Scanner scanner = new Scanner(System.in);
		int[] data = PrepareData(scanner);
		System.out.println("输入的元素如下：");
		System.out.println(Arrays.toString(data));
		System.out.println("输出结果：");
		RepeatNumber repeat = findMaxRepeatNumber(data);
		System.out.println("该样本的众数为：\n" + repeat.number);
		System.out.println("该样本的重数为：\n" + repeat.count);

	}

	/**
	 * 该操作为处理数据，将从控制台读进来的数据，存放到数组中
	 * 
	 * @return
	 */
	private static int[] PrepareData(Scanner scanner) {
		int[] data;
		// 读进来的第一个数为元素的个数
		int count = scanner.nextInt();
		data = new int[count];
		int i = 0;
		// 然后循环给数组进行赋值
		while (i < count) {
			data[i++] = scanner.nextInt();
		}
		return data;
	}

}
