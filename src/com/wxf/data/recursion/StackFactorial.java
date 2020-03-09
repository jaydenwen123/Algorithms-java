package com.wxf.data.recursion;

import com.wxf.data.structure.MyLinkStack;

/**
 * 
 * ͨ��ջ��ģ�����׳˵�����
 * 
 * @author Administrator
 * 
 */
public class StackFactorial {

	public StackFactorial() {
		// TODO Auto-generated constructor stub
	}

	public static class Factor {
		// ����n�Ĵ�С
		public int n;
		// ������
		public int f;
		// ����ֵ�Ƿ�����������ֵ���Ϊ1��ʾ������������򣬱�ʾδ�����
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
	 * ͨ��ջ��ģ��ݹ�ṹ�Ľ׳˼���
	 * 
	 * @param n
	 * @return
	 */
	public static int factorial(int n) {
		int result = 0;
		// ͨ��ջģ��ݹ����׳˵�˼·���£�
		// 1.��ʼ��һ��ջ
		MyLinkStack stack = new MyLinkStack();
		// 2.����һ������Ȼ������ջ
		Factor factor = new Factor(n, 0, 0);
		stack.push(factor);
		Factor factor2 = null;
		// 3.��ջ��Ϊ��ʱ��һֱѭ��
		while (!stack.isEmpty()) {
			Factor temp = (Factor) stack.getTop();
			// 4.���ȸ��ݸ�Ԫ�ص�ֵ�Ƿ������������в��ϵ���ջ��
			// ��ʾֵδ�����
			if (temp.tag == 0) {
				// 5.��n��ֵ���ϵļ�СΪ1ʱ����ʼִ�г�ջ�Ĳ���
				factor2 = new Factor();
				if (temp.n>2) {
					factor2.n = temp.n - 1;
					factor2.f = 0;
					factor2.tag = 0;
					// ������ջ
					stack.push(factor2);
				} else if(temp.n==2){
					factor2.f = temp.n-1;
					factor2.n = 1;
					factor2.tag = 1;
					stack.push(factor2);
				}
			} else {
				// 6.����ջ��ֵ��ΪҪ����Ľ׳�
				if (temp.tag == 1) {
					// ��ջ
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
