package main.java;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

@Path("/sent")
public class ParcelOptionService {
	private SQLiteHandler db;
	
	public ParcelOptionService() {
		// TODO Auto-generated constructor stub
		db = new SQLiteHandler();
		db.openConnection();
	}

	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	@Path("option/price")
	public Response getPriceForOptions(String json){
		Gson g = new Gson();
		Parceloption selectedOpt = g.fromJson(json, Parceloption.class);
		double price = 0;
		for(PickupOption o: selectedOpt.getOptions()){
			PickupOption opt = db.getOptionByName(o.getName());
			price += opt.getPrice();
		}
		
		selectedOpt.setPrice(price);
		String resp = g.toJson(selectedOpt);
		System.out.println(resp);
		return Response
	      .status(200)
	      .header("Access-Control-Allow-Origin", "*")
	      .header("Access-Control-Allow-Credentials", "true")
	      .header("Access-Control-Allow-Headers",
	        "origin, content-type, accept, authorization")
	      .header("Access-Control-Allow-Methods", 
	        "GET, POST, PUT, DELETE, OPTIONS, HEAD")
	      .entity(resp)
	      .build();
	}
	
	@GET
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	@Path("option/sentlist")
	public Response getSentOptions(){
		Gson g = new Gson();
		List<PickupOption> options = db.getSentOptions();
		String resp = g.toJson(options);
		
		return Response
			      .status(200)
			      .header("Access-Control-Allow-Origin", "*")
			      .header("Access-Control-Allow-Credentials", "true")
			      .header("Access-Control-Allow-Headers",
			        "origin, content-type, accept, authorization")
			      .header("Access-Control-Allow-Methods", 
			        "GET, POST, PUT, DELETE, OPTIONS, HEAD")
			      .entity(resp)
			      .build();
	}
	
	@GET
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	@Path("option/deliverylist")
	public Response getDeliveryOptions(){
		Gson g = new Gson();
		List<PickupOption> options = db.getDeliveryOptions();
		String resp = g.toJson(options);
		
		return Response
			      .status(200)
			      .header("Access-Control-Allow-Origin", "*")
			      .header("Access-Control-Allow-Credentials", "true")
			      .header("Access-Control-Allow-Headers",
			        "origin, content-type, accept, authorization")
			      .header("Access-Control-Allow-Methods", 
			        "GET, POST, PUT, DELETE, OPTIONS, HEAD")
			      .entity(resp)
			      .build();
	}
	
}
