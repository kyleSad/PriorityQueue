import java.io.PrintStream;

/**
* This is an ADT for a PriorityQueue. A PriorityQueue holds
* items in order of priority. This type of PriorityQueue holds
* NewsFeedStory items, prioritized by their number of likes.
* You should implement this interface in your PQHeap class, and
* write implementations of each of the methods.
*/
public interface PriorityQueueADT {

	/**
	* Inserts a new NewsFeedStory with the given story text, 
	* number of likes and age.
	* @param story the text of the story
	* @param likes the number of likes
	* @param age the age of the story in days
	*/	
	void insert(String story, int likes, int age);
	
	/**
	* Increases the likes of the story with the given id by
	* the given number of likes.
	* @param id the unique story ID
	* @param likes how many likes to increase the story by
	*/
	void increaseLikes(int id, int likes);
	
	/**
	* Increases the age of each NewsFeedStory by one day.
	* Removes all stories that are 7 days old or older.
	*/
	void incrementDay();
	
	/**
	* Prints to the specified printstream the top number of 
	* stories as specified.
	* @param out the PrintStream to print to
	* @param num how many stories to print
	*/
	void displayTop(PrintStream out, int num);
	
	/**
	* Returns the size of the queue (how many NewsFeedStories
	* are currently in the queue).
	* @return the size of the queue
	*/
	int size();
	
	/**
	* Returns whether the queue is empty.
	* @return whether or not the queue is currently empty
	*/
	boolean isEmpty();
}
