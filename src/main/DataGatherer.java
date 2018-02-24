package main;

import java.util.HashMap;
import java.util.Map;
import com.google.gson.Gson;

// Yerzhan Mademikhanov and Anuar Maratkhan

public class DataGatherer {
	static private int counter = 0;
	private Gson gson = new Gson();
	private Map<Integer, Todo> listMap = new HashMap<Integer, Todo>();
	
	public String getCollection() {
		return gson.toJson(listMap);
	}
	
	public String getRow(Integer id) {
		return gson.toJson(listMap.get(id));
	}
	
	public void addRow(String json) {
		Todo todo = gson.fromJson(json, Todo.class);
		todo.setId(++counter);
		listMap.put(todo.getId(), todo);
	}
	
	public Boolean deleteRow(Integer id) {
		return listMap.remove(id) != null ? true : false;
	}
}
