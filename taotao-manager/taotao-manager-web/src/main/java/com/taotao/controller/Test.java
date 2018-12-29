package com.taotao.controller;


 public class Test {
	
	public static int calNumber(int i) {
		if(i < 0) {
			return 0;
		}else if(i>0 && i <=2) {
			return 1;
		}else {
			return calNumber(i-1) + calNumber(i-2);
		}
	}
	public static void main(String args[]) {
		System.out.println(calNumber(30));
		System.out.println(calNumber(5));
		System.out.println(calNumber(2));
	}
}
