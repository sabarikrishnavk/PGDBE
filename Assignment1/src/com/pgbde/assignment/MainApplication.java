package com.pgbde.assignment;

import java.util.Scanner;

/**
 * Main application to check Direct Addresing and Hash table implementation 
 */
public class MainApplication {

	
	public static void main(String[] args)
    {
		
		ElectionCount directAddressing = new ECDirectAddressingImpl();
		ElectionCount htable = new ECHashTableImpl();
		
        Scanner scan = new Scanner(System.in);

//    	System.out.println("DirectAddressing : Count of falsePositives :" +directAddressing.falsePositive);
//	    System.out.println("Hastable : Count of falsePositives :" +htable.falsePositive);
		char ch;
        do    
        {
            System.out.println("\n Test directaddressing and hashtable implementation of election count\n");
            System.out.println("1. Search voter Id ");
            System.out.println("2. Count by candidate Id ");
            System.out.println("3. Add a voter ");
            System.out.println("4. Display false postives for all implementations");
            
            int choice = scan.nextInt();            
            switch (choice)
            {
	            case 1 : 
	                System.out.println("Enter voter Id to be searched (eg:200000) : ");
	                Integer voterId =  new Integer(scan.nextInt()) ;
	                System.out.println(directAddressing.find(voterId));
	                System.out.println(htable.find(voterId));
	                break;                          
	
	            case 2 : 
	                System.out.println("Enter Candidate Id (eg:123) : ");
	                Integer candidateId =  new Integer(scan.nextInt()) ;
	                System.out.println(directAddressing.count(candidateId));
	                System.out.println(htable.count(candidateId));
	                break;
	            case 3: 
	                System.out.println("Enter voter Id (eg:200000) : ");
	            	Integer votrId =  new Integer(scan.nextInt()) ; 
	                System.out.println("Enter Candidate Id (eg:123): ");
	                Integer candidId =  new Integer(scan.nextInt()) ;
	                System.out.println(directAddressing.add(votrId,candidId));
	                System.out.println(htable.add(votrId,candidId));
	                break;
	            
	            case 4 : 
	            	System.out.println("DirectAddressing : Count of falsePositives :" +directAddressing.falsePositive);
	    		    System.out.println("Hastable : Count of falsePositives :" +htable.falsePositive);
	                break;            
	            default : 
	                System.out.println("Invalid Option \n ");
	                break;   
            }    
 
            System.out.println("\nDo you want to continue (Type y or n) \n");
            ch = scan.next().charAt(0);                        
        } while (ch == 'Y'|| ch == 'y');
	}
}
