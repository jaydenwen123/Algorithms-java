package com.wxf.data.structure;


/**
 * 
 * @author Administrator
 * 
 */
public class StackAppOfCheckExpression {

	/**
	 * 检测一个表达式是否括号匹配。即括号成对出现，如(),{},[] 通过堆栈来实现该功能
	 * 
	 * @param data
	 * @param n
	 * @return
	 */
	public static void CheckExpression1(String[] data, int n) {
		MyLinkStack stack = new MyLinkStack();
		// (, (, d, s, ), d, s, ), d, s, (, (, d, s, s, ), }, {
		for (int i = 0; i < n; i++) {
			// 如果是遇到左括号，则进行入栈
			if (data[i].equals(new String("("))
					|| data[i].equals(new String("{"))
					|| data[i].equals(new String("["))) {
				stack.push(data[i]);
			}
			// 如果栈不为空，同时遇到以下情况，则进行出栈操作
			else if (data[i].equals(new String("]")) && (!stack.isEmpty())
					&& stack.getTop().equals(new String("[")))
				stack.pop();
			else if ((data[i].equals(new String("]")) && (!stack.isEmpty()) && !stack
					.getTop().equals(new String("[")))) {
				System.out.println("该表达式括号不匹配");
				return;
			} else if (data[i].equals(new String("}")) && (!stack.isEmpty())
					&& stack.getTop().equals(new String("{")))
				stack.pop();
			else if ((data[i].equals(new String("}")) && (!stack.isEmpty()) && !stack
					.getTop().equals(new String("{")))) {
				System.out.println("该表达式括号不匹配");
				return;
			} else if (data[i].equals(new String(")")) && (!stack.isEmpty())
					&& stack.getTop().equals(new String("(")))
				stack.pop();
			// 如果栈已经为空，但是后面有遇到了右括号，则说明右括号多余左括号
			else if ((data[i].equals(new String(")")) && (!stack.isEmpty()) && !stack
					.getTop().equals(new String("(")))) {
				System.out.println("该表达式括号不匹配");
				return;
			} else if (stack.isEmpty()
					&& (data[i].equals(new String("}"))
							|| data[i].equals(new String(")")) || data[i]
								.equals(new String("]")))) {
				System.out.println("该表达式右括号多余左括号");
				return;
			}

		}
		if (!stack.isEmpty()) {
			System.out.println("该表达式左括号多余右括号");
		} else {
			System.out.println("该表达式括号配对");
		}
	}

	/**
	 * 将一个字符串转为一个字符串数组， 并返回
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

	public static void main(String[] args) {
		// 括号不配对
		String exp1 = "((ds)ds)ds((dss)}{";
		// System.out.println(Arrays.toString(strToStrArr(exp1)));
		CheckExpression1(strToStrArr(exp1), exp1.length());
		// 左括号多余右括号
		String exp2 = "([d])dfd{{sf}";
		CheckExpression1(strToStrArr(exp2), exp2.length());
		// 右括号多余左括号
		String exp3 = "(){dd}{()})";
		CheckExpression1(strToStrArr(exp3), exp3.length());
		// 正确配对
		String exp4 = "(dd{dfs}[df])";
		CheckExpression1(strToStrArr(exp4), exp4.length());
	}
}
