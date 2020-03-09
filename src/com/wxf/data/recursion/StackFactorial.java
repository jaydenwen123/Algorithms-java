package com.wxf.data.recursion;

import com.wxf.data.structure.MyLinkStack;

/**
 * 
 * 通过栈来模拟计算阶乘的问题
 * 
 * @author Administrator
 * 
 */
public class StackFactorial {

	public StackFactorial() {
		// TODO Auto-generated constructor stub
	}

	public static class Factor {
		// 定义n的大小
		public int n;
		// 定义结果
		public int f;
		// 定义值是否计算出来，其值如果为1表示计算出来，否则，表示未计算出
		public int tag;

		public Factor() {
			// TODO Auto-generated constructor stub
		}

		public Factor(int n, int f, int tag) {
			super();
			this.n = n;
			this.f = f;
			this.tag = tag;
		}
	}

	/**
	 * 通过栈来模拟递归结构的阶乘计算
	 * 
	 * @param n
	 * @return
	 */
	public static int factorial(int n) {
		int result = 0;
		// 通过栈模拟递归计算阶乘的思路如下：
		// 1.初始化一个栈
		MyLinkStack stack = new MyLinkStack();
		// 2.创建一个对象，然后将其入栈
		Factor factor = new Factor(n, 0, 0);
		stack.push(factor);
		Factor factor2 = null;
		// 3.当栈比为空时，一直循环
		while (!stack.isEmpty()) {
			Factor temp = (Factor) stack.getTop();
			// 4.首先根据该元素的值是否计算出，来进行不断的入栈。
			// 表示值未计算出
			if (temp.tag == 0) {
				// 5.当n的值不断的减小为1时，开始执行出栈的操作
				factor2 = new Factor();
				if (temp.n>2) {
					factor2.n = temp.n - 1;
					factor2.f = 0;
					factor2.tag = 0;
					// 进行入栈
					stack.push(factor2);
				} else if(temp.n==2){
					factor2.f = temp.n-1;
					factor2.n = 1;
					factor2.tag = 1;
					stack.push(factor2);
				}
			} else {
				// 6.最后出栈的值即为要计算的阶乘
				if (temp.tag == 1) {
					// 出栈
					Factor f = (Factor) stack.pop();
					temp = (Factor) stack.getTop();
					temp.f = temp.n * f.f;
					temp.tag=1;
				}
				if (stack.size() == 1) {
					break;
				}
			}
		}
		result = ((Factor) stack.pop()).f;
		return result;
	}

	public static void main(String[] args) {
		System.out.println(factorial(5));
		System.out.println(factorial(6));
		System.out.println(factorial(7));
	}
}
