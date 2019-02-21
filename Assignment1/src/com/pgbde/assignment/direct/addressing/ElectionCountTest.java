package com.pgbde.assignment.direct.addressing;

public class ElectionCountTest {

	public static void main(String[] args) {
		ElectionCount count  =new ElectionCount();
		
		
		System.out.println("-------------------------");
		System.out.println("-----Valid scenarios-----");
		System.out.println("-------------------------");
		System.out.println(count.find(200001));
		System.out.println(count.count(835));
		System.out.println(count.add(200000, 835));
		System.out.println(count.count(835));
		

		System.out.println("-------------------------");
		System.out.println("-----InValid scenarios-----");
		System.out.println("-------------------------");
		System.out.println(count.add(20000, 123));
		System.out.println(count.find(400001));
		System.out.println(count.add(400001, 123));
		System.out.println(count.find(900000));
		System.out.println(count.add(900000, 123));
		System.out.println("-------------------------");
	}
}
