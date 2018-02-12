package main.java;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

@Path("/parcel/sent")
public class ParcelOptions {
	
	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	@Path("options")
	public String selectOptions(String jsonOpt){
		Gson g = new Gson();
		PickupOption opt = g.fromJson(jsonOpt, PickupOption.class);
		
		//...
		
		
		return "Ausgewählte Option kostet 4.00 EUR extra";
	}
}
