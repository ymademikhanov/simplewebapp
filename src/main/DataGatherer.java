package main;

import java.util.HashMap;
import java.util.Map;
import com.google.gson.Gson;

public class DataGatherer {
	static private int counter = 0;
	private Gson gson = new Gson();
	private Map<Integer, Todo> listMap = new HashMap<Integer, Todo>();
	
	public String getCollection() {
		System.out.println(gson.toJson(listMap));
		return gson.toJson(listMap);
	}
	
	public String getRow(Integer id) {
		return gson.toJson(listMap.get(id));
	}
	
	public void addRow(String json) {
		Todo todo = gson.fromJson(json, Todo.class);
		System.out.println(todo);
		todo.setId(++counter);
		listMap.put(todo.getId(), todo);
	}
	
	public void deleteRow(Integer id) {
		listMap.remove(id);
	}
}
