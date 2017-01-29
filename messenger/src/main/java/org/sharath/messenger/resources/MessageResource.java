package org.sharath.messenger.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import java.util.*;

import org.sharath.messenger.service.*;
import org.sharath.messenger.model.*;
//resource path to use by web api  
@Path("/messages")
public class MessageResource {
	//you need to map http method to java method
	//this is a messeage resource handler whats the format it has to return
	MessageService messageService = new MessageService();
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public List<Message> getMessages(){
		return messageService.getAllMessages();
	}
	//jersey allows method level annotation and we can have more than one path params /something/{id1}/name{id2} 
	@GET
	@Path("/{messageId}")
	@Produces(MediaType.APPLICATION_XML)
	public Message text(@PathParam("messageId") long messageId){
		return messageService.getMessage(messageId);
	}
}
