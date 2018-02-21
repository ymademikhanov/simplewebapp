package main;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

// Yerzhan Mademikhanov and Anuar Maratkhan

@ApplicationPath("/services")
public class ToDoApplication extends Application {
	private Set<Object> singletons = new HashSet<Object>();  
	private Set<Class<?>> empty = new HashSet<Class<?>>();
	
	public ToDoApplication() {
		singletons.add(new ToDoService(new DataGatherer()));
	}
	
	@Override
	public Set<Class<?>> getClasses() {
		return empty;
	}
	
	@Override
	public Set<Object> getSingletons() {
		return singletons;
	}
	
}
