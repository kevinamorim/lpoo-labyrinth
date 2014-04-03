package maze.logic;

public class Task {
	
	private String description;
	private boolean done;
	
	/**
	 * Constructor for the Task object.
	 * A task is an objective to be fulfilled by the player.
	 * 
	 * @param description
	 */
	public Task(String description) {
		this.setDescription(description);
		this.setDone(false);
	}

	/**
	 * Returns the Task description.
	 * What is to be done.
	 * 
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the Task description.
	 * 
	 * @param description : string value to be set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the value of this task (whether it is performed or not).
	 * 
	 * @return true if done, false otherwise
	 */
	public boolean isDone() {
		return done;
	}

	/**
	 * Sets the value of the parameter [done]
	 * 
	 * @param done : value to be set
	 */
	public void setDone(boolean done) {
		this.done = done;
	}
	
}
