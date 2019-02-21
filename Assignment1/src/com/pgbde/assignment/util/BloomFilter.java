package com.pgbde.assignment.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Set;

/**
 * Class to implement BloomFilter
 * 
 * @author Sabari Krishna
 *
 */
public class BloomFilter {

	public final static String VOTERS = "input/validVotersList.txt";
	private static Set<Integer> filter = null;

	public static Set<Integer> getFilter() {
		if (filter == null) {
			filter = getVoters();
		}
		return filter;
	}

	/**
	 * read valid voters contents to a Set
	 * 
	 * @param fileName
	 * @return
	 * @throws Exception
	 */
	private static Set<Integer> getVoters() {
		Set<Integer> result = new HashSet<>();

		try {
			File file = new File(VOTERS);
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String currentLine = reader.readLine();
			while (currentLine != null) {
				if (currentLine != null) {
					result.add(Integer.parseInt(currentLine));
				}
				currentLine = reader.readLine();
			}
			reader.close();
		} catch (Exception e) {
			System.err.println("Error reading file : " + VOTERS);
		}
		return result;
	}
}
