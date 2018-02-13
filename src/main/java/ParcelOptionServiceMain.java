package main.java;

import java.io.IOException;
import java.util.List;

import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import com.sun.net.httpserver.HttpServer;

public class ParcelOptionServiceMain {

	public static void main(String[] args) {
		
		//SQLiteHandler db = new SQLiteHandler();
		//db.openConnection();
		//db.createPickupOptionTable();
		//db.createParcelSentOptionTable();
		//db.createParcelDeliveryOptionTable();
		
		
		//db.insertSentOption("Abgabe im Paketshop", 0.0);
		//db.insertSentOption("Abholung an der Haustüre", 4.0);
		
		/*db.insertDeliveryOption("Sendung@Home", 0.50f);
		db.insertDeliveryOption("Sendung@Shop", 0.00f);
		
		List<ParcelDeliveryOption> opts = db.getDeliveryOptions();
		for(ParcelDeliveryOption s : opts)
			System.out.println(s.getName() + ": " + s.getPrice());
		*/
		HttpServer server;
		
		
		try {
			server = HttpServerFactory.create("http://localhost:1400/parcel");
			server.start();
			
			//while(true){}
			//JOptionPane.showMessageDialog(null, "ParcelSize-Service started!\nClick to end");
			//server.stop(0);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
