package com.wxf.data.structure;

import java.util.ArrayDeque;
import java.util.Queue;

public class TestQueue {

	public TestQueue() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		
		Queue<Integer> queue=new ArrayDeque<Integer>();
		for(int i=0;i<5;i++){
			queue.add(new Integer(i));
		}
		System.out.println(queue.size());
		for(Integer i:queue){
			System.out.println(i);
		}
		System.out.println("*****test peek()*****");
		System.out.println(queue.peek());
		System.out.println(queue.size());
		System.out.println("***********test poll()***********");
		System.out.println(queue.poll());
		System.out.println(queue.size());
	}
}
