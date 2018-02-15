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
	private ArrayList<Todo> list = new ArrayList<Todo>();
	
	public StoryServlet() {
		super();
		list.add(new Todo("clean", "bathroom and bedroom"));
		list.add(new Todo("grocery", "buy some apples"));
		list.add(new Todo("study", "linear algebra and probability"));
	}
	
	private String jsonify(Object obj) {
		Gson gson = new Gson();
		String json = gson.toJson(obj);
		return json;
	}
	
	private Todo getElementById(int id) {
		for (Todo item : list) {
			if(item.getId() == id) {
				return item;
			}
		}
		return null;
	}
	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String pathInfo = request.getPathInfo();
		System.out.println(pathInfo);
		if (pathInfo == null) {
			response.getWriter().append(jsonify(list));
		} else {
			int id = Integer.parseInt(pathInfo.substring(1));
			response.getWriter().append(jsonify(getElementById(id)));
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
			response.getWriter().append(jsonify(list));
		}
	}
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("Apache Server: DELETE method invoked!");
		int id = Integer.parseInt(request.getParameter("id"));
		list.remove(getElementById(id));
		response.getWriter().append(jsonify(list));
	}

}
