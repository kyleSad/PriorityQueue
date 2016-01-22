import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 * This is the main class.  takes in two input files
 * and uses them on the operations and then can use user input
 * if the command file is done and the program hasn't stopped executed.
 *
 * @author Kyle Sadler
 */

public class NewsFeed {
	/**
	 * allows for two input files. One for a list of stories, the other for commands
	 * if there is no commands it allows the user to do the input.
	 * The options are to make a new story, increase the days, increase the likes of
	 * a story, and print the list
	 *
	 * @param args
	 */
	public static void main(String[] args) throws FileNotFoundException{
		Scanner stdin = new Scanner(System.in);  // for reading console input
		//make sure there are only two files
		if(args.length != 2){
	 		System.out.print( "Usage: java FileName");
	 		System.exit(0);
	 	}
		Scanner inputFile = null;
		Scanner commandFile = null;
	 	PQHeap myTimeLine = new PQHeap();
		int k = 0; // should be a zero for file input
		//code for input file
		try{
			//get the scanner object of the file
			inputFile = new Scanner (new File(args[0]));
			//code here for reading in file
			while(inputFile.hasNextLine()){
				//takes apart each line to insert
				String line = inputFile.nextLine();
                String[] split = line.split(",");
                String story = split[0];
                String second = split[1];
                String third = split[2];
                //changes the two strings to ints 
                int age = Integer.parseInt(third);
                int likes = Integer.parseInt(second);
				myTimeLine.insert(story, likes, age);
			}
			
		} catch (FileNotFoundException exception){
			//if the file does not exist
			System.out.println( "Error: cannot find input file");
		}
		
		//code for command file
		try{
			//get the scanner object of the file
			commandFile = new Scanner (new File(args[1]));
		} catch (FileNotFoundException exception){
			//if the file does not exist
			System.out.println( "Error: cannot find command file");
			 k = 1;
		}
		boolean done = false;
	    while (!done) {
	        System.out.println("Enter option - a, i, l, p, or q: "); 
	        //allows switch to user input
	        try{
	        	if( !commandFile.hasNextLine()){
	                 k = 1;
	        	}
	        }catch(NullPointerException not){
	        	
	        }
	        String input = "";
	        String option = "";
	        String remainder = "";
	        if(k == 1){
	        	input = stdin.nextLine();
	        }
	        else{
	            option = commandFile.nextLine();
	        }
	        if (input.length() > 0 || option.length() > 0) {   
	         	char choice = ' ';
	        	if(k != 1){
	        		 choice = option.charAt(0); 
	                 remainder = option.substring(1).trim(); //get remaining text
	        	}
	        	else{
	        		 choice = input.charAt(0);  // strip off option character
	                 remainder = input.substring(1).trim(); //get remaining text
	        	}
	            switch (choice) {
	            
	            case 'a' :
	            	//add story
	            	//give 1 day
	            	//and 0 likes
	            	String story = remainder;
	            	myTimeLine.insert(story, 0, 1);
	            	break;
	            	
	            case 'i' :
	               //increment the current day
	            	myTimeLine.incrementDay();
	                break;
	                
	            case 'l' :
	                  	//increase likes for a story
	                  	//given id 
	                  	//given # of likes
	                        String[] split = remainder.split(",");
	                        String first = split[0];
	                        String second = split[1];
	                        //changes the two strings to ints
	                        int id = Integer.parseInt(first);
	                        int likes = Integer.parseInt(second);
	                  		myTimeLine.increaseLikes(id, likes);         
	                
	            	
	                break;
	            
	            case 'p' :
	            	int number;
	            	if(remainder.length() > 0){
		            	 number = Integer.parseInt(remainder);	            		
	            	}
	            	else{
	            		 number = myTimeLine.size();
	            	}
	            	myTimeLine.displayTop(System.out, number);
	            	break;
	                
	            case 'q' :
	                System.out.println("quit");
	                done = true;
	                break;
	                
	            default :
	                System.out.println("Unknown Command");
	                break;
	           }
	        }
	    }
	  }
}
