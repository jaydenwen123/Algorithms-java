package com.wxf.data.recursion;

public class Digit {

	public Digit() {
		// TODO Auto-generated constructor stub
	}
	
	public static void digit(int n){
		if(n!=0){
			digit(n/10);
			System.out.println(n%10);
		}
	}
	
	public static void main(String[] args) {
		digit(12345677);
	}

}
