

Start the application
---------------------

Run the MainApplication.java file to execute the program. Input files are kept inside "input" folder and the file names are referenced inside ElectionCount.java which is an abstract class for Direct Addressing and Hashtable implementation of the program.



Step 1) Implement a Bloom filter to filter out invalid voters, i.e. you need to add only the list of voter_ids given in file 1 to your Bloom filter.(Step 1)

	(BloomFilter.java file has the logic to check if key (voterId ) is present in filter or not. During initialization of BloomFilter,validVoters)
 
Step 2) Using the Bloom filter developed in step 1, store the set of valid (voter_id, candidate_id) pairs from file 2 in an array with direct addressing. To achieve this, implement the following methods in a class named ElectionCount. You must use appropriately sized arrays in the direct addressing scheme.
	
	( Created an abstract class with methods for add,find and count. All the methods return text which is the status of operation.)

	ADD: Takes a (voter_id, candidate_id) pair from the given file as input, and stores it only if the voter_id is valid.
	FIND: Takes a voter_id as input and outputs the candidate_id for whom the vote was cast. If the voter_id is invalid, print "Invalid Voter".
	COUNT: Takes a candidate_id as input and outputs the total number of votes received by him/her. 

Step 3 ) Evaluate the worst-case running time of the above methods. (Ignore Bloom Filter i.e. simply evaluate the worst-case running time for ADD, FIND and COUNT)
	
	Ans: "Add" and "Find" operation will take O(1) for direct addressing and "Count" method would take O(n) 

Step 4) Implement the same methods (mentioned in point 2)using a hash table and evaluate their worst-case running time. 

	(FIND operation with N indices and storing M records equals O(1+M/N). If M=N, this equals O(1+1)=O(2)."Count" method would take O(n) )

Step 5) Report the number of false positives encountered while filtering out the voter_ids. Expected false positives are less than 1%. 
	
	(Option 4 in MainApplication would show the number of false positives for the implementation.)
Note: Both for direct addressing method and hash table method, you need to first filter out invalid voters using the Bloom filter designed in step 1.

Refer : https://github.com/sabarikrishnavk/PGDBE/tree/master/Assignment1