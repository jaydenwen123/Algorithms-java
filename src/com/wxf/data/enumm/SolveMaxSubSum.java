package com.wxf.data.enumm;


/**
 * 
 * 求解最大连续子序列和问题
 * 
 * 对于一个序列，data[0...n-1]该序列用数组来表示如下：data[]={-2,22,-43,45,5,7,-3};
 * 起任何连续子序列为data[i...j](i<＝j,0<=i<n-1,i<=j<=n-1)
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
	 * 方法一，求解最大连续子序列和的问题
	 * 
	 * O(n3)
	 * 
	 * @param data
	 * @return
	 */
	public static int solveMaxSubSum1(int data[]) {
		int maxSum = 0;
		// 方法一的思路如下：
		int n = data.length;
		for (int i = 0; i < n; i++) {
			for (int j = i; j < n; j++) {
				int sum = 0;
				// 1.首先得到不同的子序列，然后再将其累加。
				for (int k = i; k < j; k++) {
					// 2.在累加中找到最大值，返回返回
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
	 * 改进前面的算法。其实只需要在计算前面树的基础上线比较软后在加即可
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
	 * 第二种改进的方法O(n)
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
		System.out.println("第一种解法：");
		System.out.println(result);
		result = solveMaxSubSum1(data2);
		System.out.println(result);
		System.out.println("第二种解法：");
		result = solveMaxSubSum2(data);
		System.out.println(result);
		result = solveMaxSubSum2(data2);
		System.out.println(result);
		System.out.println("第三种解法:");
		result = solveMaxSubSum3(data);
		System.out.println(result);
		result = solveMaxSubSum3(data2);
		System.out.println(result);

	}

}
