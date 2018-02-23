package main;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

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
		ResponseBuilder b;
		if (req != "") {
			d.addRow(req);
			String r = "Data instance has been successfully added.";
			b = Response.ok(r);
		} else {
			String r = "No content";
			b = Response.noContent();
		}
		return b.build();
	}
	
	@DELETE
	@Path("{id: [0-9]+}")
	public Response deleteInstance(@PathParam("id") String id) {
		ResponseBuilder b;
		String r;
		if (d.deleteRow(Integer.parseUnsignedInt(id))) {
			r = "The instance has successfully been deleted.";
			b = Response.ok(r);
		} else {
			b = Response.status(Status.NOT_FOUND);
		}
		return b.build();
	}
	
}