package main;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

// Yerzhan Mademikhanov and Anuar Maratkhan

@Path("/todo")
public class ToDoService {

	private DataGatherer dataGatherer;

	public ToDoService(DataGatherer d) {
		this.dataGatherer = d;
	}
	
	@GET
	public Response getList(@QueryParam("id") String id) {
		String result;
		if (id == null) {
			result = dataGatherer.getCollection();
		} else {
			result = dataGatherer.getRow(Integer.parseUnsignedInt(id));
		}
		return Response.ok(result).build();
	}
	
	@POST
	@Consumes("application/json")
	public Response postInstance(String data) {
		ResponseBuilder builder;
		if (data == "") {
			builder = Response.noContent();
		} else {
			dataGatherer.addRow(data);
			builder = Response.ok(dataGatherer.getCollection());
		}
		return builder.build();
	}
	
	@DELETE
	public Response deleteInstance(@QueryParam("id") String id) {
		ResponseBuilder builder;
		if (id != null && dataGatherer.deleteRow(Integer.parseUnsignedInt(id))) {
			builder = Response.ok(dataGatherer.getCollection());
		} else {
			builder = Response.status(Status.NOT_FOUND);
		}
		return builder.build();
	}
	
}