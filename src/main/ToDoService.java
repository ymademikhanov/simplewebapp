package main;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

// Yerzhan Mademikhanov and Anuar Maratkhan

@Path("/todo")
public class ToDoService {

	private DataGatherer d;

	public ToDoService(DataGatherer d) {
		this.d = d;
	}
	
	@GET
	public Response getList() {
		String r = d.getCollection();
		ResponseBuilder b = Response.ok(r);
		b.header("header-name", "value");
		return b.build();
	}
	
	@GET
	@Path("{id: [0-9]+}")
	public String getInstance(@PathParam("id") String id) {
		return d.getRow(Integer.parseUnsignedInt(id));
	}
	
	@POST
	@Consumes("application/json")
	public void postInstance(String req) {
		d.addRow(req);
	}
	
	@DELETE
	@Path("{id: [0-9]+}")
	public void deleteInstance(@PathParam("id") String id) {
		d.deleteRow(Integer.parseUnsignedInt(id));
	}
	
}