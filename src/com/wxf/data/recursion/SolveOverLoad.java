package com.wxf.data.recursion;

public class SolveOverLoad {

	public SolveOverLoad() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * �����װ������
	 * 
	 * �÷����ĺ���Ϊ��data�е�Ԫ���е�0��i��Ԫ�����ж��Ƿ��н�
	 * 
	 * overLoad(int w, int[] data, int i)Ϊ�����⣬overLoad(int w, int[] data, int
	 * i-1 )΢С����
	 * 
	 * @param w
	 * @param data
	 * @param i
	 * @return
	 */
	// ����װ������
	// ����������
	// ����һ����װ��Ҫװ����һ��������ΪW���ִ������м�װ��i������Ϊwi������
	// ��n����װ����ѡ�����ɸ�װ���ִ���ʹ���ǵ�����֮������ΪW������ҵ����򷵻�true��
	// ���򣬷���false
	// ������⣺
	// ������data�����n����װ���������overLoad(int w, int[] data, int i)�����ж�
	// �ü�װ�������Ƿ��ν֣�����data���Ƿ��ҵ�����֮��ΪW�ļ�װ�䣬���н⣬�ú�������true
	// ���򷵻�false����overLoad(int w, int[] data, int i-1)����data��������֮��Ϊr
	// �ļ�װ�䡣overLoad(int w, int[] data, int i)�Ǵ����⣬overLoad(int w, int[]
	// data, int i-1)��С����

	// ��� data, int i-1)�Ĺ������£�
	// ��1����ȡ���һ����װ��dat[i]����r=w-data[i](���ִ�����װ�����������)
	// ��2����r=0,�ҵ�һ���⣬����true
	// ��3�����r>0����������û���ҵ��ļ�װ��ʱ�����������ת��Ϊ (r,data, int i-1),�����
	// С���� �ҵ��⣬�򷵻�true������������data[i]����data(0,n-1)���ҽ⣬��ת��Ϊ(w,data, int i-1)
	// С���⣬����ż�еļ�װ�඼�ҵ�����û����Ȼû���ҵ��⣬�򷵻�false
	// ��4����r<0,���������û���ҵ��ļ�װ��ʱ��������data[i]����data(0,i-1)���ҽ⣬��ת��Ϊ(w,data,
	// int i-1)С���⣬�����еļ�װ�඼�ҵ�û���ҵ��⣬����false
	//

	public static boolean overLoad(int w, int[] data, int i) {
		// ���еĺ����ǣ�����ȥ���һ����װ��data[i],w��ʾΪ������
		// ���r��ʾ����ʣ�������
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
		// ��ʾʣ����Ϊ�㣬���������Ԫ�أ���ʣ�µ�i-1��Ԫ������
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
