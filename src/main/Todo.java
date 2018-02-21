package main;

public class Todo {
	private int id;
	private String description;
	private String title;
	
	public Todo(String title, String description) {
		this.title = title;
		this.description = description;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return this.id;
	}
	
	public String toString() {
		return this.title + " : " + this.description ;
	}
}
