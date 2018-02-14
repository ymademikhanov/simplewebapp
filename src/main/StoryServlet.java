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

/// Yerzhan Mademikhanov and Rustam Shumenov

@WebServlet("/story")
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
//		System.out.println(json);
		return json;
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append(this.jsonify());
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String[]> m = request.getParameterMap();
		if (m.containsKey("title") && m.containsKey("description")) {
			String[] s1 = m.get("title");
			String[] s2 = m.get("description");
			list.add(new Todo(s1[0], s2[0]));
			
			System.out.println(s1[0] + s2[0]);
			
			response.getWriter().append(this.jsonify());
		}
	}
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// to-do.
		System.out.println("success");
		response.getWriter().append(this.jsonify());
	}

}
