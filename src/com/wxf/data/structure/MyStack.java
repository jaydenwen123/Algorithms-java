package com.wxf.data.structure;

/**
 * 定义堆栈接口
 * 
 * @author Administrator
 * 
 */
public interface MyStack {

	// 入栈
	public void push(Object object);

	// 出栈
	public Object pop();

	// 获取栈顶元素
	public Object getTop();
}
