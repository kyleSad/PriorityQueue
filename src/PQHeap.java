import java.io.PrintStream;

/**
 * This class does operations on a priority queue. Including insert, incrementing, and printing
 * It also checks to see how much of the array is filled and can search for a specific story
 *  
 * @author Kyle Sadler
 */
public class PQHeap {

	private int numItems;
	private NewsFeedStory curr;
	private NewsFeedStory[] priorityQueue;
	/**
	 * This is the constructor for the class
	 */
	public PQHeap(){
		numItems = 0;
		priorityQueue = new NewsFeedStory[10];
	}
	/**
	 * Inserts a story into the heap.  After inserting
	 * it checks the size of the array by the number of elements.
	 * If there are elements already in array it shifts to the right
	 * location of where to place the story
	 *
	 * @param  story     the story to be added
	 * @param likes      starts with zero likes
	 * @param age        starts the story at age 1
	 */
	public void insert(String story, int likes, int age){
		//set curr to story
		curr = new NewsFeedStory(story, likes, age);
		//insert at end of array and update numItems
		numItems++;//start at array index 1 so need to increment numItems first
		priorityQueue[numItems] = curr;
		int insertIndex = numItems;//keeps track of index that added item is at
		
		//resize array if more than 2/3 full
		this.checkArraySize(numItems);
		
		//shift until in correct place in array
		//story priority based on number of likes
		boolean done = false;//flag for swap while loop
		while(!done){
			//done if parent larger or is root
			if(insertIndex == 1){
				done = true;
			}
			else if(priorityQueue[insertIndex/2].getLikes() > curr.getLikes()){
				done = true;
			}
			//swap if parent is smaller
			else{
				//swap
				NewsFeedStory temp = priorityQueue[insertIndex/2];
				priorityQueue[insertIndex/2] = curr;
				priorityQueue[insertIndex] = temp;
				//update index of added item
				insertIndex = insertIndex/2;
			}
		}
	}
	/**
	 * Increases the amount of likes for a story given
	 * the number of likes to be added and the id of the story.
	 * If the amount of likes passes another story it has to
	 * reheapify the array.
	 *
	 * @param id     gets the id for a specific story
	 * @param likes  passed the number of likes to be increased by
	 */
	public void increaseLikes(int id, int likes){
		//find item in list and set curr to it
		curr = this.findItem(id);
		//increase likes
		curr.addLikes(likes);
		
		//create new array
		NewsFeedStory[] newQueue = new NewsFeedStory[priorityQueue.length];
		int newQueueItems = 0;
		//insert all stories in order of likes
		for(int i = 1; i < priorityQueue.length; i++){
				if(priorityQueue[i] == null){}
				else {
						//set curr to story
		 			 curr = priorityQueue[i];
						//insert at end of array and update numItems
					 newQueueItems++;//start at array index 1 so need to increment numItems first
					 newQueue[newQueueItems] = curr;
					 int insertIndex = newQueueItems;//keeps track of index that added item is at
						
					 //shift until in correct place in array
					 //story priority based on number of likes
					 boolean done = false;//flag for swap while loop
					 while(!done){
							//done if parent larger or is root
							if(insertIndex == 1){
								done = true;
							}
							else if(newQueue[insertIndex/2].getLikes() > curr.getLikes()){
								done = true;
							}
							//swap if parent is smaller
							else{
								//swap
								NewsFeedStory temp = newQueue[insertIndex/2];
								newQueue[insertIndex/2] = curr;
								newQueue[insertIndex] = temp;
								//update index of added item
								insertIndex = insertIndex/2;
							}
						}
					}
				}
				priorityQueue = newQueue;
				numItems = newQueueItems;
	}
	/**
	 * Increments all the stories by one day.
	 * If the story is more than 7 days old
	 * it reheapifies the array removing that story.
	 *
	 */
	public	void incrementDay(){
		//increment day of each story in queue
		for(int i = 1; i <= numItems; i++){
			priorityQueue[i].incDay();
		}
		//create new array
		NewsFeedStory[] newQueue = new NewsFeedStory[priorityQueue.length];
		int newQueueItems = 0;
		//insert all stories less than 7 days old in new array
		for(int i = 1; i < priorityQueue.length; i++){
			if(priorityQueue[i] == null){}
			else if(priorityQueue[i].getAge() < 7){
				//set curr to story
				curr = priorityQueue[i];
				//insert at end of array and update numItems
				newQueueItems++;//start at array index 1 so need to increment numItems first
				newQueue[newQueueItems] = curr;
				int insertIndex = newQueueItems;//keeps track of index that added item is at
				
				//shift until in correct place in array
				//story priority based on number of likes
				boolean done = false;//flag for swap while loop
				while(!done){
					//done if parent larger or is root
					if(insertIndex == 1){
						done = true;
					}
					else if(newQueue[insertIndex/2].getLikes() > curr.getLikes()){
						done = true;
					}
					//swap if parent is smaller
					else{
						//swap
						NewsFeedStory temp = newQueue[insertIndex/2];
						newQueue[insertIndex/2] = curr;
						newQueue[insertIndex] = temp;
						//update index of added item
						insertIndex = insertIndex/2;
					}
				}
			}
		}
		priorityQueue = newQueue;
		numItems = newQueueItems;
	}
	/**
	 * Displays the elements of the array in increaseing 
	 * order from most likes to least, and only the amount 
	 * specificed by the user.
	 *
	 * @param out  the form of how to print
	 * @param num  gets the number of stories to print
	 * 
	 */
	public 	void displayTop(PrintStream out, int num){
		//create copy array to remove stories from
		NewsFeedStory[] copyQueue = new NewsFeedStory[priorityQueue.length];
		int copyQueueItems = numItems;
		for(int i = 0; i < priorityQueue.length; i++){
			copyQueue[i] = priorityQueue[i];
		}
		
		//find the top num stories and print them
		for(int i = 0; i < num; i++){
			//largest story is in index 1
			//print story info
			out.println(copyQueue[1].getStory());
			out.println("Likes: " + copyQueue[1].getLikes() + ", Age: " 
					+ copyQueue[1].getAge() + ", ID: " + copyQueue[1].getId());
			int newQueueItems = 0;
			NewsFeedStory[] newQueue = new NewsFeedStory[priorityQueue.length];
			//remove largest story from array by copying everything after index 1
			for(int j = 2; j <= copyQueueItems; j++){
				//set curr to story
				curr = copyQueue[j];
				//insert at end of array and update numItems
				newQueueItems++;//start at array index 1 so need to increment numItems first
				newQueue[newQueueItems] = curr;
				int insertIndex = newQueueItems;//keeps track of index that added item is at
				
				//shift until in correct place in array
				//story priority based on number of likes
				boolean done = false;//flag for swap while loop
				while(!done){
					//done if parent larger or is root
					if(insertIndex == 1){
						done = true;
					}
					else if(newQueue[insertIndex/2].getLikes() > curr.getLikes()){
						done = true;
					}
					//swap if parent is smaller
					else{
						//swap
						NewsFeedStory temp = newQueue[insertIndex/2];
						newQueue[insertIndex/2] = curr;
						newQueue[insertIndex] = temp;
						//update index of added item
						insertIndex = insertIndex/2;
					}
				}
			}//end of remove largest loop
			//update the copy array to one with largest removed
			copyQueueItems --;
			copyQueue = newQueue;
		}//end of find top num stories loop
		
	}
	/**
	 * This method returns the amount of elements
	 * 
	 * @return how many items are in the list
	 */
	public int size(){
		return numItems;
		
	}
	/**
	 * This method returns the boolean true if the
	 * array is empty
	 *
	 * @return if the list is empty
	 */
	public boolean isEmpty(){
		if(this.size() == 0){
			return true;
		}
		else{
			return false;			
		}
		
	}
	/**
	 * This meathod checks to see it the
	 * array is getting full and if it 
	 * is it creates a new array double the size
	 *
	 * @param numItems   how many items are in the list
	 */
	private void checkArraySize(int numItems){
	    //double array size if more than 2/3 full
		if(priorityQueue.length/numItems >= 0.66){
			NewsFeedStory[] newQueue = new NewsFeedStory[priorityQueue.length*2];
			for(int i = 0; i < priorityQueue.length; i++){
				newQueue[i] = priorityQueue[i];
			}
			priorityQueue = newQueue;
	    }
	}
	/**
	 * this method searches the list for a specific story
	 * by getting the id number
	 *
	 * @param n  the id number to be found
	 */
	private NewsFeedStory findItem(int n){
		//search array for item with id n
		for(int i = 1; i < priorityQueue.length; i++){
			if(priorityQueue[i].getId() == n){
				return priorityQueue[i];
			}
		}
		return null;//need this so don't get error
	}

}
