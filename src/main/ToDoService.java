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
//		b.header("header-name", "value"); - do we really need this?
		return b.build();
	}
	
	@GET
	@Path("{id: [0-9]+}")
	public Response getInstance(@PathParam("id") String id) {
		String r = d.getRow(Integer.parseUnsignedInt(id));
		ResponseBuilder b = Response.ok(r);
		return b.build();
	}
	
	@POST
	@Consumes("application/json")
	public Response postInstance(String req) {
		if (req != "") {
			d.addRow(req);
			String r = "Data instance has been successfully added.";
			ResponseBuilder b = Response.ok(r);
		} else {
			String r = "No content";
			ResponseBuilder b = Response.noContent();
		}
		return b.build();
	}
	
	@DELETE
	@Path("{id: [0-9]+}")
	public void deleteInstance(@PathParam("id") String id) {
		d.deleteRow(Integer.parseUnsignedInt(id));
	}
	
}