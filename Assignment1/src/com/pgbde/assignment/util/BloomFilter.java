package com.pgbde.assignment.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.BitSet;

/**
 * Class to implement BloomFilter
 * 
 * @author Sabari Krishna
 *
 */
public class BloomFilter {

	BitSet bs;//BloomFilter array of bits
	int collisionCount = 0;
	int size; //size of the bloom filter
	int numHashFunctions ;
	
	public BloomFilter() {
		super();
	}
	public BloomFilter(int x, int numHashs, String fileName) {
		/*** Constructor
		 * x indicate the size of bloom filter 
		 */
		bs = new BitSet(x);
		bs.clear(); 
		size = bs.size();
		numHashFunctions = numHashs;
		addVoters(fileName);
		
		System.out.println("BloomFilter :Collision count : "+collisionCount + " :size:" +size + " :numHashFunctions:" + numHashFunctions);
	}
	/**
	 * read valid voters contents from file and 
	 * add to BitSet
	 * 
	 * @param fileName
	 * @return
	 * @throws Exception
	 */
	private void addVoters(String fileName) {

		try {
			File file = new File(fileName);
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String currentLine = reader.readLine();
			while (currentLine != null) {
				if (currentLine != null) {
					add(Integer.parseInt(currentLine));
				}
				currentLine = reader.readLine();
			}
			reader.close();
		} catch (Exception e) {
			System.err.println("BloomFilter :Error reading file : " + fileName);
		}
	}
	/**
	 * Method to check if key is present in bloom filter or not
	 * @param key
	 * @return
	 */
	public boolean isPresent(Integer key) {
		int[] hash = genHashArray(key);
		for(int i = 1; i <= numHashFunctions; i++) {
			int bitkey = Math.abs((hash[0] * i + hash[1] ) % size);
			if(bs.get(bitkey)) {
				continue;
			}else {
				return false;
			}
		}
		
		return true;
		
	}
	/**
	 * Add keys to bloom filter 
	 * 
	 * @param key
	 */
	public void add(Integer key) {
		if(isPresent(key)) {
			collisionCount++;
		}
		int[] hash= genHashArray(key);
		for(int i = 1; i <= numHashFunctions; i++) {
			int bitkey = Math.abs((hash[0] * i + hash[1]) % size);
			
			bs.set(bitkey);
		}	
	}
	
	/**
	 * Get hashes for the voterId being passed.
	 * Used 2 keys to generate a number.
	 * @param x
	 * @return
	 */
	private int[] genHashArray(Integer x) {
		int[] hashes = new int[2];
		String s1 = "upgrad";
		int a = 1;
		int hash = x.hashCode();
		for(int i = 0; i < s1.length(); i++) {
			a = (7 * a) + s1.charAt(i) + hash;
		}
		hashes[0]=a;
		String s2 = "exam";
		int b = 1;
		for(int i = 0; i < s2.length(); i++) {
			b = (3 * b) + s2.charAt(i) + hash;
		}
		hashes[1]=b;
		return hashes;
	}

}
