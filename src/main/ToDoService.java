package main;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/todo")
public class ToDoService {

	private DataGatherer d;

	public ToDoService(DataGatherer d) {
		this.d = d;
	}
	
	@GET
	public String getList() {
		return d.getCollection();
	}
	
	@GET
	@Path("{id: [0-9]+}")
	public String getInstance(@PathParam("id") String id) {
		return d.getRow(Integer.parseUnsignedInt(id));
	}
	
	@POST
	@Consumes("application/json")
	public void addInstance(String req) {
		d.addRow(req);
	}
	
	@DELETE
	@Path("{id: [0-9]+}")
	public void removeInstance(@PathParam("id") String id) {
		d.deleteRow(Integer.parseUnsignedInt(id));
	}
	
}
