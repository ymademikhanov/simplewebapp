package main;

import java.io.IOException;
import java.util.HashMap;
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
	private Map<Integer, Todo> listMap = new HashMap<Integer, Todo>();
	
	public StoryServlet() {
		super();
		Todo first = new Todo("Software Engineering", "Decide on a sprint goal (what you will deliver after two weeks) and determine the corresponding backlog items");
		Todo second = new Todo("Macroeconomics", "Aggregate Expenditure and Equilibrium Output Chapter 23, The Government and Fiscal Policy Chapter 24.");
		listMap.put(first.getId(), first);
		listMap.put(second.getId(), second);
	}
	
	private String jsonify(Object obj) {
		Gson gson = new Gson();
		String json = gson.toJson(obj);
		return json;
	}
	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String pathInfo = request.getPathInfo();
		if (pathInfo == null) {
			response.getWriter().append(jsonify(listMap));
		} else {
			int id = Integer.parseInt(pathInfo.substring(1));
			response.getWriter().append(jsonify(listMap.get(id)));
		}
	}
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("POST method is invoked");
		Map<String, String[]> m = request.getParameterMap();
		if (m.containsKey("title") && m.containsKey("description")) {
			Todo temp = new Todo(m.get("title")[0], m.get("description")[0]);
			System.out.println(temp);
			listMap.put(temp.getId(), temp);
			response.getWriter().append(jsonify(listMap));
		}
	}
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Apache Server: DELETE method invoked!");
		int id = Integer.parseInt(request.getParameter("id"));
		listMap.remove(id);
		response.getWriter().append(jsonify(listMap));
	}

}
