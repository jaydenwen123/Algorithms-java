package com.wxf.data.structure;


/**
 * 
 * @author Administrator
 * 
 */
public class StackAppOfCheckExpression {

	/**
	 * ���һ�����ʽ�Ƿ�����ƥ�䡣�����ųɶԳ��֣���(),{},[] ͨ����ջ��ʵ�ָù���
	 * 
	 * @param data
	 * @param n
	 * @return
	 */
	public static void CheckExpression1(String[] data, int n) {
		MyLinkStack stack = new MyLinkStack();
		// (, (, d, s, ), d, s, ), d, s, (, (, d, s, s, ), }, {
		for (int i = 0; i < n; i++) {
			// ��������������ţ��������ջ
			if (data[i].equals(new String("("))
					|| data[i].equals(new String("{"))
					|| data[i].equals(new String("["))) {
				stack.push(data[i]);
			}
			// ���ջ��Ϊ�գ�ͬʱ�����������������г�ջ����
			else if (data[i].equals(new String("]")) && (!stack.isEmpty())
					&& stack.getTop().equals(new String("[")))
				stack.pop();
			else if ((data[i].equals(new String("]")) && (!stack.isEmpty()) && !stack
					.getTop().equals(new String("[")))) {
				System.out.println("�ñ��ʽ���Ų�ƥ��");
				return;
			} else if (data[i].equals(new String("}")) && (!stack.isEmpty())
					&& stack.getTop().equals(new String("{")))
				stack.pop();
			else if ((data[i].equals(new String("}")) && (!stack.isEmpty()) && !stack
					.getTop().equals(new String("{")))) {
				System.out.println("�ñ��ʽ���Ų�ƥ��");
				return;
			} else if (data[i].equals(new String(")")) && (!stack.isEmpty())
					&& stack.getTop().equals(new String("(")))
				stack.pop();
			// ���ջ�Ѿ�Ϊ�գ����Ǻ����������������ţ���˵�������Ŷ���������
			else if ((data[i].equals(new String(")")) && (!stack.isEmpty()) && !stack
					.getTop().equals(new String("(")))) {
				System.out.println("�ñ��ʽ���Ų�ƥ��");
				return;
			} else if (stack.isEmpty()
					&& (data[i].equals(new String("}"))
							|| data[i].equals(new String(")")) || data[i]
								.equals(new String("]")))) {
				System.out.println("�ñ��ʽ�����Ŷ���������");
				return;
			}

		}
		if (!stack.isEmpty()) {
			System.out.println("�ñ��ʽ�����Ŷ���������");
		} else {
			System.out.println("�ñ��ʽ�������");
		}
	}

	/**
	 * ��һ���ַ���תΪһ���ַ������飬 ������
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
		// ���Ų����
		String exp1 = "((ds)ds)ds((dss)}{";
		// System.out.println(Arrays.toString(strToStrArr(exp1)));
		CheckExpression1(strToStrArr(exp1), exp1.length());
		// �����Ŷ���������
		String exp2 = "([d])dfd{{sf}";
		CheckExpression1(strToStrArr(exp2), exp2.length());
		// �����Ŷ���������
		String exp3 = "(){dd}{()})";
		CheckExpression1(strToStrArr(exp3), exp3.length());
		// ��ȷ���
		String exp4 = "(dd{dfs}[df])";
		CheckExpression1(strToStrArr(exp4), exp4.length());
	}
}
