package com.wxf.data.structure;

/**
 * 判断一个字符寻列是否为回文
 * 
 * @author Administrator
 * 
 */
public class QueueAppOfHuiWenNum {

	public QueueAppOfHuiWenNum() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 将一个字符序列先入栈，然后在出栈，最后和原序列进行比较是否相等 得出该序列是否回文
	 * 
	 * @param src
	 * @return
	 */
	public static boolean checkIsHuiWen(String src) {
		String result = "";
		MyLinkStack stack = new MyLinkStack();
		char[] arr = src.toCharArray();
//		执行入栈的操作
		for (char c : arr) {
			stack.push(c);
		}
//		出栈，同时逆向的记录字符序列
		while (!stack.isEmpty())
			result += stack.pop();
		return src.equals(result);
	}

	public static void main(String[] args) {
		String str = "ABCDE5EDCBA";
		if (checkIsHuiWen(str)) {
			System.out.println("is huiwen");
		} else {
			System.out.println("is not huiwen");
		}
	}

}
