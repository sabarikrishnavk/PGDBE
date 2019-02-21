package com.pgbde.assignment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import com.pgbde.assignment.util.BloomFilter;
/**
 * Abstract class for election count sample
 * Implementation of db is done inside Direct addressing and hash table  
 *
 */
public abstract class ElectionCount {

	private final static String VOTER_CANDIDATE_FILE 	= "input/votersCandList.txt";
	private final static String VOTERS_FILE 			= "input/validVotersList.txt";
	

	protected int maxVoters 				= 500000;
	protected int BLOOMFILTER_BIT_SIZE		= 4792530; //Get the bitsize forhttps://hur.st/bloomfilter/?n=500000&p=.01&m=&k=
	protected int BLOOMFILTER_HASH_FUNC_CNT	= 7; // hash function counts as per above link
	
	protected int totalCount	 	= 0; // total Values saved in DB.
	protected int falsePositive 	= 0; //Count of false positives
	protected BloomFilter filter 	= null;
	
	public abstract String add(Integer voterId, Integer candidateId); //abstract functions for add to a db
	public abstract String find(Integer voterId); // abstract function to find based on voterId
	public abstract String count(Integer candidateId);
	
	/**
	 * Load bloom filter  
	 * temporary DB with direct addressing.
	 */
	public ElectionCount() {
		filter = new  BloomFilter(BLOOMFILTER_BIT_SIZE, BLOOMFILTER_HASH_FUNC_CNT,VOTERS_FILE);
	}
	

	/**
	 * read valid voters candidates contents to a Set
	 * @param fileName
	 * @return
	 * @throws Exception
	 */
	public void loadDB() {
		int voterId = 0;
		
		try {
			File file = new File(VOTER_CANDIDATE_FILE);
			BufferedReader reader = new BufferedReader(new FileReader(file));
		    String currentLine = reader.readLine();
		    while (currentLine != null) {
		        String[] split = currentLine.split(" ");
		        voterId = Integer.parseInt(split[0]);
		        if(split.length > 1 ) {
		        	int candidateId = Integer.parseInt(split[1]);
		        	add(voterId,candidateId); //add the voterId and candidateId into corresponding db's
		        }
		        currentLine = reader.readLine();
		    }
		     
		    reader.close();
		    
		}catch(Exception e) {
			System.err.println("Error reading file : "+ VOTER_CANDIDATE_FILE);
		}
	}
	/**
	 * Get Voter index based on voterds
	 * @param voterId
	 * @return
	 */
	public int getVoterIndex(Integer voterId) {
		if(voterId < 200000 || (voterId >= 400000 && voterId< 600000)
				|| voterId >= 900000) {
			return -1;
		}else {
			if(voterId >600000) {
				return  voterId - 400000;
			}else {
				return  voterId - 200000;
			}
		}
	}

}
