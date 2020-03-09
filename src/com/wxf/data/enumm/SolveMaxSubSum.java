package com.wxf.data.enumm;


/**
 * 
 * ���������������к�����
 * 
 * ����һ�����У�data[0...n-1]����������������ʾ���£�data[]={-2,22,-43,45,5,7,-3};
 * ���κ�����������Ϊdata[i...j](i<��j,0<=i<n-1,i<=j<=n-1)
 * 
 * @author Administrator
 * 
 */
public class SolveMaxSubSum {

	public SolveMaxSubSum() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * ����һ�����������������к͵�����
	 * 
	 * O(n3)
	 * 
	 * @param data
	 * @return
	 */
	public static int solveMaxSubSum1(int data[]) {
		int maxSum = 0;
		// ����һ��˼·���£�
		int n = data.length;
		for (int i = 0; i < n; i++) {
			for (int j = i; j < n; j++) {
				int sum = 0;
				// 1.���ȵõ���ͬ�������У�Ȼ���ٽ����ۼӡ�
				for (int k = i; k < j; k++) {
					// 2.���ۼ����ҵ����ֵ�����ط���
					sum += data[k];
				}
				if (sum > maxSum) {
					maxSum = sum;
				}
			}
		}
		return maxSum;
	}

	/**
	 * O(n2)
	 * �Ľ�ǰ����㷨����ʵֻ��Ҫ�ڼ���ǰ�����Ļ������߱Ƚ�����ڼӼ���
	 * 
	 * @param data
	 * @return
	 */
	public static int solveMaxSubSum2(int data[]) {
		int maxSum = 0;

		int n = data.length;
		for (int i = 0; i < n; i++) {
			int sum = 0;
			for (int j = i; j < n; j++) {
				sum += data[j];
				if (maxSum < sum) {
					maxSum = sum;
				}
			}
		}
		return maxSum;
	}

	/**
	 * �ڶ��ָĽ��ķ���O(n)
	 * 
	 * @param data
	 * @return
	 */
	public static int solveMaxSubSum3(int data[]) {

		int maxSum=0;
		int n=data.length;
		int sum=0;
		for(int i=0;i<n;i++){
			sum+=data[i];
			if(sum<0)
				sum=0;
			if(maxSum<sum)
				maxSum=sum;
		}
		return maxSum;
	}

	public static void main(String[] args) {
		int[] data = { -2, 11, -4, 13, -5, -2 };
		int[] data2 = { -6, 2, 4, -7, 5, 3, 2, -1, 6, -9, 10, -2 };
		int result = solveMaxSubSum1(data);
		System.out.println("��һ�ֽⷨ��");
		System.out.println(result);
		result = solveMaxSubSum1(data2);
		System.out.println(result);
		System.out.println("�ڶ��ֽⷨ��");
		result = solveMaxSubSum2(data);
		System.out.println(result);
		result = solveMaxSubSum2(data2);
		System.out.println(result);
		System.out.println("�����ֽⷨ:");
		result = solveMaxSubSum3(data);
		System.out.println(result);
		result = solveMaxSubSum3(data2);
		System.out.println(result);

	}

}
