package com.wxf.data.structure;

/**
 * ���нӿ�
 * 
 * @author Administrator
 * 
 */
public interface MyQueue {

	// ��Ӳ���
	public void enQueue(Object object);

	// ���Ӳ���
	public Object deQueue();

	// ��ȡ��ͷԪ��
	public Object getQueue();

	// �ж϶����Ƿ�Ϊ��
	public boolean isEmpty();
}
