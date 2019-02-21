package com.pgbde.assignment;

public interface AbstractElectionCount {

	
	public final static String VOTER_CANDIDATE = "input/votersCandList.txt";
	
	public String add(Integer voterId, Integer candidateId);
	public String find(Integer voterId);
	public String count(Integer candidateId);
}
