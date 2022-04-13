package com;

import modal.Item;

//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 
//For JSON
import com.google.gson.*; 
//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 

@Path("/Users") 
public class ItemService {
	
	 Item itemObj = new Item(); 
	 
	 @GET
	 @Path("/") 
	 @Produces(MediaType.TEXT_HTML) 
	 public String readItems() 
	  { 
		  return itemObj.readItems(); 
	  }
	 
	 @POST
	 @Path("/")
	 @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	 @Produces(MediaType.TEXT_PLAIN)
	 public String insertItem(@FormParam("name") String name,
			 				@FormParam("address") String address,
			 				@FormParam("phone_no") String phone_no,
			 				@FormParam("nic") String nic)
	 {
		 String output = itemObj.insertItem(name, address, phone_no, nic); 
		 return output; 
	 }
	 
	 @PUT
	 @Path("/")
	 @Consumes(MediaType.APPLICATION_JSON)
	 @Produces(MediaType.TEXT_PLAIN)
	 public String updateItem(String itemData) {
		 
		//Convert the input string to a JSON object 
		 JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject();
		 
		//Read the values from the JSON object
		 String iduser = itemObject.get("iduser").getAsString(); 
		 String name = itemObject.get("name").getAsString(); 
		 String address = itemObject.get("address").getAsString(); 
		 String phone_no = itemObject.get("phone_no").getAsString(); 
		 String nic = itemObject.get("nic").getAsString(); 
		 
		 String output = itemObj.updateItem(iduser, name, address, phone_no, nic); 
		 return output; 
	 }
	 
	 
}
