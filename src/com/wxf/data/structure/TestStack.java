package com.wxf.data.structure;

import java.util.Stack;

public class TestStack {

	public TestStack() {
		// TODO Auto-generated constructor stub
	}
	public static void main(String[] args) {
		
		Stack<String> stack=new Stack<String>();

		for(int i=0;i<5;i++){
			stack.push("i"+i);
		}
		System.out.println(stack.size());
		System.out.println("***********test pop()*****************");
		System.out.println(stack.pop());
		System.out.println(stack.size());
		System.out.println("**********test peek()*******");
		System.out.println(stack.peek());
		System.out.println(stack.size());
	}
}
