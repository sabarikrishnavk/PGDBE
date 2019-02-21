package com.pgbde.assignment;

import java.util.Hashtable;
import java.util.Map;

import com.pgbde.assignment.ElectionCount;

public class ECHashTableImpl extends ElectionCount{
	
	private Map<Integer,Integer> db ;
	StringBuffer sb = new StringBuffer();
	public ECHashTableImpl() {
		super();
		db =  new Hashtable<>();
		loadDB();
	}

	@Override
	public String add(Integer voterId, Integer candidateId) {
		if(filter.isPresent(voterId) ) {

			int index = getVoterIndex(voterId);
			if(index >= 0) {
		        db.put(voterId,  candidateId);
		        totalCount++;
				return "ECHashTableImpl : Added (voterId, candidateId ) : ("+voterId + ","+ candidateId+") ";
			}else { 
				falsePositive ++;
				return "ECHashTableImpl : False positive :"+voterId;
			}

        }else {
        	return "ECHashTableImpl : Invalid voter :"+ voterId;
        }
		
	}

	@Override
	public String find(Integer voterId) {
		
		if(filter.isPresent(voterId) ) {
			int index = getVoterIndex(voterId);
			if(index >= 0) {
			    return "ECHashTableImpl : Candidate for voter : "+ voterId + " is :" + db.get(voterId)  ;
			}else {
	        	return "ECHashTableImpl : False positive :"+ voterId;
	        }
        }else {
        	return "ECHashTableImpl : Invalid voter :"+ voterId;
        }
	}

	

	@Override
	public String count(Integer candidateId) {
		int votes = 0;
		for(Integer candidate: db.values() ) {
			if(candidateId.equals(candidate)) {
//				System.out.println(voterId);
				votes++;
			}
		}
		return "ECHashTableImpl : Number of votes for candidate " +candidateId + " is "+votes;
	}
}
