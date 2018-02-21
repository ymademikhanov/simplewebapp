package main;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Path;

@Path("/todo")
public class ToDoService {
	private Map<Integer, Todo> listMap = new HashMap<Integer, Todo>();

	
	public ToDoService(Map listMap) {
		this.listMap = listMap;
	}
	
//	@GET
//	public String 
	
	
}
