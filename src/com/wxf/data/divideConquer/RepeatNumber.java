package com.wxf.data.divideConquer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;

public class RepeatNumber {

	// �����Ա�ʾ���Ƕ�Ӧ������
	public int number;
	// �����Զ�Ӧ���Ǹ��������ֵĴ���
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
	 * �����������
	 * 
	 * �Ӹ��������ݼ������ҳ�����������ֵĴ���
	 * 
	 * @param data
	 * @return
	 */
	public static RepeatNumber findMaxRepeatNumber(int[] data) {

		// ������������˼·���£�
		// 1�����ȼ��������Ԫ����������
		// 2��Ȼ����������������ȱ�Ϊͳ�����ֳ����ڵ�Ƶ��
		// 3.��ͳ�ƽ����map���������
		// 4��������ҳ����ִ�������Ԫ�ؼ���
		// 5.�����������ķ��������ؽ��
		RepeatNumber repeat = null;
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		// ���������ݼ��ϣ�Ȼ��ͳ�ƵĽ�������map��
		for (int a : data) {
			if (map.containsKey(a)) {
				map.put(a, map.get(a) + 1);
			} else {
				map.put(a, 1);
			}
		}
		// ��ʼ������
		int max = map.get(data[0]);
		int index = data[0];
		// ���±���map���ϣ���������������ֵ�����
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
		System.out.println("����������");
		Scanner scanner = new Scanner(System.in);
		int[] data = PrepareData(scanner);
		System.out.println("�����Ԫ�����£�");
		System.out.println(Arrays.toString(data));
		System.out.println("��������");
		RepeatNumber repeat = findMaxRepeatNumber(data);
		System.out.println("������������Ϊ��\n" + repeat.number);
		System.out.println("������������Ϊ��\n" + repeat.count);

	}

	/**
	 * �ò���Ϊ�������ݣ����ӿ���̨�����������ݣ���ŵ�������
	 * 
	 * @return
	 */
	private static int[] PrepareData(Scanner scanner) {
		int[] data;
		// �������ĵ�һ����ΪԪ�صĸ���
		int count = scanner.nextInt();
		data = new int[count];
		int i = 0;
		// Ȼ��ѭ����������и�ֵ
		while (i < count) {
			data[i++] = scanner.nextInt();
		}
		return data;
	}

}
