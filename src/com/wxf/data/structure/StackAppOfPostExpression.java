package com.wxf.data.structure;

/**
 * ������ʽ����
 * 
 * @author Administrator
 * 
 */
public class StackAppOfPostExpression {

	public StackAppOfPostExpression() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * ��һ���ַ���ת��Ϊһ���ַ�������
	 * 
	 * @param str
	 * @return
	 */
	public static String[] strToStrArr(String str) {
		String[] array = new String[str.length()];
		for (int i = 0; i < str.length(); i++) {
			array[i] = str.substring(i, i + 1);
		}
		return array;
	}

	/**
	 * ����һ����׺���ʽ�ַ��������ó����еĽ��
	 * 
	 * @param str
	 * @return
	 */
	public static Object postExp(String str) {
		MyLinkStack stack = new MyLinkStack();
		char[] arr = str.toCharArray();
		for (int i = 0; i < arr.length; i++) {
			// ������ַ���һ�����֣��������ջ��
			if (Character.isDigit(arr[i])) {
				stack.push(new Integer(Character.toString(arr[i])));
			}
			// �����ʾ�������ַ�Ϊ�����,���������,����Ҫ��ջ�е���������,Ȼ�������������ж�,
			// ִ�в�ͬ�ļ������
			else {
				int value2 = ((Integer) stack.pop()).intValue();
				int value1 = ((Integer) stack.pop()).intValue();
				switch (arr[i]) {
				case '+':
					value1 += value2;
					break;

				case '-':
					value1 -= value2;
					break;
				case '*':
					value1 *= value2;
					break;
				case '/':
					if (value2 == 0)
						throw new RuntimeException(" 0 is a valid /0");
					value1 /= value2;
					break;
				default:
					throw new RuntimeException("error!");
				}
				stack.push(value1);
			}
		}
		return stack.pop();
	}

	/**
	 * ��һ����׺���ʽת��Ϊһ����׺���ʽ
	 * 
	 * @param resExp
	 *            �������׺���ʽ
	 * @return ���صõ��ĺ�׺���ʽ
	 * 
	 * ���бȽϵĹ������£�
	 * ��str1λ���浱ǰջ��������ı�����str2λ������׺���ʽ��ǰ������������ı���
	 * ����˳�����׺���ʽ�ж���ĵ���Ϊ�����ʱ���͸�ֵ��str2��Ȼ��Ƚ�str1��str2
	 * �����ȼ�����str1�����ȼ�����str2 �����ȼ�����str1������ջ����Ϊ��׺���ʽ��һ
	 * �����������Ȼ����űȽ��µ�ջ�������str1��str2�����ȼ�����str1�����ȼ�����
	 * str2�����ȼ�����strt2��ֵ��ջ��Ȼ����Ŷ���һ�����ʣ���str1�����ȼ�����str2
	 * �����ȼ���str1λ������str2λ������ʱ����str1��ջ��Ȼ����Ŷ�����һ�����ʣ���str1
	 * �����ȼ�����str2�����ȼ���str1λ��#��str2Ϊ��#��ʱ���㷨����
	 * 
	 */
	public static String midExpToSufExp(String resExp) {
		String sufExp = "";
		// ��׺���ʽ--->��׺���ʽ
		// 3+(6-4/2)*5 -----> 3642/-5*+
		char[] arr = resExp.toCharArray();
		MyLinkStack stack = new MyLinkStack();
		stack.push(new String("#"));
		for (int i = 0; i < arr.length; i++) {
			if (Character.isDigit(arr[i])) {
				sufExp += arr[i];
			} else {
				String str1 = (String) stack.getTop();
				String str2 = Character.toString(arr[i]);
				char c = compare(str1, str2);
				while (c == '>') {
					sufExp += stack.pop().toString();
					str1 = (String) stack.getTop();
					c = compare(str1, str2);
				}
				if (c == '<') {
					stack.push(str2);
				} else if (c == '=' && str1.equals("(") && str2.equals(")")) {
					stack.pop();
				} else if (c == '=' && str1.equals("#") && str2.equals("#")) {
					break;
				}
			}
		}
		// ��ѭ������ʱ����ʾ���ַ����Ѿ�������ɡ���ʱ��Ҫע�⣬���stack�ĳ��Ȳ�Ϊ1����˵����ջ�л�����
		// ����������Ҫ�������
		while (stack.size() != 1) {
			sufExp += stack.pop().toString();
		}
		return sufExp;

	}

	/**
	 * �ж����������������ȼ�˳��
	 * 
	 * @param s1
	 *            ����һ��������
	 * @param s2
	 *            ���ڶ���������
	 * @return
	 * 
	 * 
	 */
	public static char compare(String s1, String s2) {
		char s = '0';
		char c = s2.charAt(0);
		switch (s1.charAt(0)) {
		case '+':
			/*
			 * if(c=='+' || c=='-' || c==')' || c=='#') s=true; else s=false;
			 */
		case '-':
			if (c == '+' || c == '-' || c == ')' || c == '#')
				s = '>';
			else
				s = '<';
			break;
		case '*':
		case '/':
			if (c == '(')
				s = '<';
			else {
				s = '>';
			}
			break;
		case '(':
			if (c == ')')
				s = '=';
			else {
				s = '<';
			}
			break;
		case ')':
			s = '>';
			break;
		case '#':
			if (c == '#')
				s = '=';
			else {
				s = '<';
			}

			break;
		}
		return s;
	}

	public static void main(String[] args) {

		System.out.println(midExpToSufExp("8*(6-4/2)-5"));

		int result = (int) postExp(midExpToSufExp("8*(6-4/2)-5"));
		System.out.println(result);
	}

}
