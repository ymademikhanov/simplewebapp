package main;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/services")
public class ToDoApplication extends Application {
	private Set<Object> singletons = new HashSet<Object>();  
	private Set<Class<?>> empty = new HashSet<Class<?>>();
	
	public ToDoApplication() {
//		singletons.add()
	}
	
	
}
