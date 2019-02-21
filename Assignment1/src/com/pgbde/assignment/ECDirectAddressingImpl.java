package com.pgbde.assignment;

import com.pgbde.assignment.ElectionCount;

public class ECDirectAddressingImpl extends ElectionCount{
	
	private Integer[] db ;

	public ECDirectAddressingImpl() {
		super();
		db =  new Integer[maxVoters];
		loadDB();
	}

	@Override
	public String add(Integer voterId, Integer candidateId) {

		if(filter.isPresent(voterId) ) {
	        
	    	int index = getVoterIndex(voterId);
			if(index >= 0) {
		        db[index] =  candidateId;
		        totalCount++;
				return "ECDirectAddressingImpl : Added (voterId, candidateId ) : ("+voterId + ","+ candidateId+") ";
			}else {
				falsePositive ++;
				return "ECDirectAddressingImpl : False positive :"+voterId;
			}
        }else {
        	return "ECDirectAddressingImpl : Invalid voter :"+ voterId;
        }
		
	}

	@Override
	public String find(Integer voterId) {
		if(filter.isPresent(voterId) ) {
			int index = getVoterIndex(voterId);
			if(index >= 0) {
			     return "ECDirectAddressingImpl : Candidate for voter : "+ voterId + " is :" + db[index]  ;
			}else {
	        	return "ECDirectAddressingImpl : False positive :"+ voterId;
	        }
        }else {
        	return "ECDirectAddressingImpl :Invalid voter :"+ voterId;
        }
	}

	@Override
	public String count(Integer candidateId) {
		int votes = 0;
		for(Integer candidate: db ) {
			if(candidateId.equals(candidate)) {
				votes++;
			}
		}
		return "ECDirectAddressingImpl : Number of votes for candidate " +candidateId + " is "+votes;
	}
}
