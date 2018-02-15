package main;

public class Todo {
	static private int counter = 0;
	private int id;
	private String description;
	private String title;
	
	public Todo(String title, String description) {
		this.id = ++counter;
		this.title = title;
		this.description = description;
	}
	
	public int getId() {
		return this.id;
	}
}
