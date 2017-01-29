package org.sharath.messenger.resources;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

import org.sharath.messenger.service.*;
import org.sharath.messenger.model.*;
//resource path to use by web api  you had to add jar that does conversion to json because theres no jax p like for xml
//you can annotate whole class with produces and consumes if all the methods produce and consumes same format
@Path("/jsonmessages")
public class JSONResource {
	//you need to map http method to java method
	//this is a messeage resource handler whats the format it has to return
	MessageService messageService = new MessageService();
	//whatever parameters youll pass jerseys going to call these methods hence if you want to filter or paginate like /messages?year=2015 or ?start=1&size=2 using @Queryparam annotation
	/*public List<Message> getMessages(@QueryParam("year") int year,
	 @QueryParam("start") int start,
	 @QueryParam("end") int end){*/
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Message> getMessages(@BeanParam MessageFilterBean filterBean){
		if(filterBean.getYear()>0){
			return messageService.getAllMessagesForYear(filterBean.getYear());
		}
		else if(filterBean.getStart()>=0&&filterBean.getEnd()>0){
			return messageService.getAllMessagesPaginated(filterBean.getStart(), filterBean.getEnd());
		}
		return messageService.getAllMessages();
	}
	//post helps to map the method, consumes to say method consumes json, produce to say method produces json
	//instead of returning the message you can return the response in following way
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addMessage(Message message,@Context UriInfo uriinfo) throws URISyntaxException{
		Message newMess= messageService.addMessage(message);
		String newId=String.valueOf(message.getId());
		URI uri=uriinfo.getAbsolutePathBuilder().path(newId).build();
		return Response/*thereis a shortcut.status(Status.CREATED)*/
				.created(uri)
				.entity(newMess)			
				.build();				
		//return messageSercice.addMessage(message);
	}
	//dont do put for 1 and 2 its in constructor
	@PUT
	@Path("/{messageId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Message updateMessage(@PathParam("messageId") long messageId,Message message){
		message.setId(messageId);
		return messageService.updateMessage(message);
	}
	
	@DELETE
	@Path("/{messageId}")
	public void deleteMessage(@PathParam("messageId") long messageId){
		messageService.removeMessage(messageId);
	}
	//update and delete update as a put req /messages/2 with body message
	//we are looking at message body writer that converts java type to a json/xml format
	//jersey allows method level annotation and we can have more than one path params /something/{id1}/name{id2} 
	//we were mapping urlpattern to a response in a similar way we have to map json error message with the response
	//mapping exception to jax rs obj is using exception mapper
	@GET
	@Path("/{messageId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Message text(@PathParam("messageId") long messageId){
		return messageService.getMessage(messageId);
	}
	
	//instead of implementing sub resources here you can add a hook to the sub resources instead of {messageId}/comments
	//this is the delegation
	
	@Path("/{messageId}/comments")
	public CommentResource getCommentsResource(){
		return new CommentResource();
	}
	
}
