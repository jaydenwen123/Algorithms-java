package com.wxf.data.structure;

/**
 * 队列接口
 * 
 * @author Administrator
 * 
 */
public interface MyQueue {

	// 入队操作
	public void enQueue(Object object);

	// 出队操作
	public Object deQueue();

	// 获取队头元素
	public Object getQueue();

	// 判断队列是否为空
	public boolean isEmpty();
}
