package com.wxf.data.structure;

/**
 * �ж�һ���ַ�Ѱ���Ƿ�Ϊ����
 * 
 * @author Administrator
 * 
 */
public class QueueAppOfHuiWenNum {

	public QueueAppOfHuiWenNum() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * ��һ���ַ���������ջ��Ȼ���ڳ�ջ������ԭ���н��бȽ��Ƿ���� �ó��������Ƿ����
	 * 
	 * @param src
	 * @return
	 */
	public static boolean checkIsHuiWen(String src) {
		String result = "";
		MyLinkStack stack = new MyLinkStack();
		char[] arr = src.toCharArray();
//		ִ����ջ�Ĳ���
		for (char c : arr) {
			stack.push(c);
		}
//		��ջ��ͬʱ����ļ�¼�ַ�����
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
