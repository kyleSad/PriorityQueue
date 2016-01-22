/**
* This class represents one story in a news feed.
* Each story has text (story), a number of likes, an age
* (how many days old this story is), and a unique ID.
* Story IDs start at one and increase for each story created.
*/
public class NewsFeedStory {

	private static int idCounter = 0;
	private String story;
	private int likes;
	private int age;
	private int idNum;

	/**
	* Creates a new NewsFeedStory with the specified story,
	* number of likes, and age. Creates a unique ID for this story.
	*/	
	public NewsFeedStory(String story, int likes, int age) {
		this.story = story;
		this.likes = likes;
		this.age = age;
		this.idNum = NewsFeedStory.idCounter;
		NewsFeedStory.idCounter++;
	}
	
	/**
	* Returns the text of this story.
	* @return the text for this story
	*/
	public String getStory() {
		return story;
	}
	
	/**
	* Increases this story's likes by the specified number of likes.
	* @param likes the number of likes to add to this story
	*/
	public void addLikes(int likes) {
		this.likes += likes;
	}
	
	/**
	* Returns how many likes this story has.
	* @return how many likes this story has
	*/
	public int getLikes() {
		return likes;
	}
	
	/**
	* Returns the age of this story, in days.
	* @return the age of this story, in days
	*/
	public int getAge() {
		return age;
	}
	
	/**
	* Increases the age of this story by one day.
	*/
	public void incDay() {
		age++;
	}

	/**
	* Returns the unique ID of this story.
	* @return the unique ID of this story
	*/
	public int getId() {
		return idNum;
	}
}
