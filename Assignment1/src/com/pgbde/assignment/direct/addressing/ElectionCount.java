package com.pgbde.assignment.direct.addressing;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Set;

import com.pgbde.assignment.AbstractElectionCount;
import com.pgbde.assignment.util.BloomFilter;

public class ElectionCount implements AbstractElectionCount{
	
	private Integer[] db =  new Integer[500000];
	private Set<Integer> filter = null;
	/**
	 * Load bloom filter  
	 * temporary DB with direct addressing.
	 */
	public ElectionCount() {
		filter = BloomFilter.getFilter();
		loadDB();
	}
	

	/**
	 * read valid voters candidates contents to a Set
	 * @param fileName
	 * @return
	 * @throws Exception
	 */
	private void loadDB() {
		int voterId = 0;
		
		try {
			File file = new File(VOTER_CANDIDATE);
			BufferedReader reader = new BufferedReader(new FileReader(file));
		    String currentLine = reader.readLine();
		    while (currentLine != null) {
		        String[] split = currentLine.split(" ");
		        voterId = Integer.parseInt(split[0]);

		        if(split.length > 1 ) {
		        	int candidateId = Integer.parseInt(split[1]);
		        	add(voterId,candidateId);
		        }
		        
		        currentLine = reader.readLine();
		        
		    }
		     
		    reader.close();
		}catch(Exception e) {
			System.err.println("Error reading file : "+ VOTER_CANDIDATE);
		}
	}


	@Override
	public String add(Integer voterId, Integer candidateId) {
		// TODO Auto-generated method stub

		int index = 0;
		if(filter.contains(voterId) ) {
	        if(voterId >=600000 ) {
				index = voterId - 400000;
			}else {
				index = voterId - 200000;
			}
	        
	        if(index > 500000 || index < 0 ) {
	        	return "Invalid voter :"+ voterId;
	        }
	        
	        db[index] =  candidateId;
			return "Added (voterId, candidateId ) : ("+voterId + ","+ candidateId+") to index "+ index + " ";
        }else {
        	return "Invalid voter :"+ voterId;
        }
		
	}

	@Override
	public String find(Integer voterId) {
		int index = 0;
		if(filter.contains(voterId) ) {
	        if(voterId >=600000) {
				index = voterId - 400000;
			}else {
				index = voterId- 200000;
			}
	       return "Candidate for voter : "+ voterId + " is :" + db[index]  ;
        }else {
        	return "Invalid voter :"+ voterId;
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
		return "Number of votes for candidate " +candidateId + " is "+votes;
	}
}
