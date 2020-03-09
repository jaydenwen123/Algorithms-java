package com.wxf.data.structure;

/**
 * 计算表达式问题
 * 
 * @author Administrator
 * 
 */
public class StackAppOfPostExpression {

	public StackAppOfPostExpression() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 将一个字符串转换为一个字符串数组
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
	 * 传入一个后缀表达式字符串，最后得出运行的结果
	 * 
	 * @param str
	 * @return
	 */
	public static Object postExp(String str) {
		MyLinkStack stack = new MyLinkStack();
		char[] arr = str.toCharArray();
		for (int i = 0; i < arr.length; i++) {
			// 如果该字符是一个数字，则进行入栈。
			if (Character.isDigit(arr[i])) {
				stack.push(new Integer(Character.toString(arr[i])));
			}
			// 否则表示遇到的字符为运算符,这种情况下,则需要从栈中弹出两个数,然后对运算符进行判断,
			// 执行不同的计算操作
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
	 * 将一个中缀表达式转换为一个后缀表达式
	 * 
	 * @param resExp
	 *            传入的中缀表达式
	 * @return 返回得到的后缀表达式
	 * 
	 * 进行比较的规则如下：
	 * 另str1位保存当前栈顶运算符的变量，str2位保存中缀表达式当前督导的运算符的变量
	 * 。当顺序从中缀表达式中读入的单词为运算符时，就赋值给str2，然后比较str1和str2
	 * 的优先级，若str1的优先级高于str2 的优先级；将str1进行退栈并作为后缀表达式的一
	 * 个单词输出，然后接着比较新的栈顶运算符str1和str2的优先级；若str1的优先级低于
	 * str2的优先级，将strt2的值进栈，然后接着读下一个单词，若str1的优先级等于str2
	 * 的优先级且str1位“（”str2位“）”时，将str1退栈，然后接着读入下一个单词，若str1
	 * 的优先级等于str2的优先级且str1位“#”str2为“#”时，算法结束
	 * 
	 */
	public static String midExpToSufExp(String resExp) {
		String sufExp = "";
		// 中缀表达式--->后缀表达式
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
		// 当循环结束时，表示该字符串已经遍历完成。此时需要注意，如果stack的长度不为1，则说明该栈中还存在
		// 操作符，需要将其输出
		while (stack.size() != 1) {
			sufExp += stack.pop().toString();
		}
		return sufExp;

	}

	/**
	 * 判断两个操作符的优先级顺序
	 * 
	 * @param s1
	 *            ，第一个操作符
	 * @param s2
	 *            ，第二个操作符
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
