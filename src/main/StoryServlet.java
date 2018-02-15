package main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/// Yerzhan Mademikhanov and Anuar Maratkhan

@WebServlet({"/story", "/story/*"})
public class StoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArrayList<Todo> list;
	
	public StoryServlet() {
		super();
		this.list = new ArrayList<Todo>();
		list.add(new Todo("clean", "bathroom and bedroom"));
		list.add(new Todo("grocery", "buy some apples"));
		list.add(new Todo("study", "linear algebra and probability"));
	}
	
	private String jsonify() {
		Gson gson = new Gson();
		String json = gson.toJson(this.list);
		return json;
	}
	
	private Todo getElementById(int id) {
		for(int i = 0; i < this.list.size(); i++) {
			Todo element = this.list.get(i);
			if(element.getId() == id) {
				return element;
			}
		}
		
		return null;
	}
	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String pathInfo = request.getPathInfo();
		System.out.println(pathInfo);
		if (pathInfo == null) {
			response.getWriter().append(this.jsonify());
		} else {
			int id = Integer.parseInt(pathInfo.substring(1));
			Todo element = getElementById(id);
			Gson gson = new Gson();
			String json = gson.toJson(element);
			response.getWriter().append(json);
		}
	}
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Map<String, String[]> m = request.getParameterMap();
		if (m.containsKey("title") && m.containsKey("description")) {
			String[] s1 = m.get("title");
			String[] s2 = m.get("description");
			list.add(new Todo(s1[0], s2[0]));
			
			System.out.println(s1[0] + ": " + s2[0]);
			
			response.getWriter().append(this.jsonify());
		}
	}
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("Apache Server: DELETE method invoked!");
		int id = Integer.parseInt(request.getParameter("id"));
		Todo element = this.getElementById(id);
		this.list.remove(element);
		response.getWriter().append(this.jsonify());
	}

}
