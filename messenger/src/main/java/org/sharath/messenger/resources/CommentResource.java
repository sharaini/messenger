package org.sharath.messenger.resources;

import java.util.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.sharath.messenger.model.Comment;
import org.sharath.messenger.service.CommentService;

//i want the path to be inferred from previous page
@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CommentResource {
	/*@GET
	public String test(){
		return "test";
	}
	//you can access parent resource parameter as well as sub resource parameter
	@GET
	@Path("{commentId}")
	public String comm(@PathParam("messageId")long messgId,@PathParam("commentId")long commId){
		return "method to return id "+commId+" messId "+messgId;
	}*/
	
	private CommentService commentService = new CommentService();
	@GET
	public List<Comment> getAllCOmments(@PathParam("messageId")long messageId){
		return commentService.getAllComments(messageId);
	}
	@POST
	public Comment addCOmment(@PathParam("messgeId")long messageId,Comment comment){
		return commentService.addComment(messageId, comment);
	}
	
	@PUT
	@Path("/{commentId}")
	public Comment updateComment(@PathParam("messgeId")long messageId,@PathParam("commentId")long commentId,Comment comment){
		comment.setId(commentId);
		return commentService.updateComment(messageId, comment);
	}
	
	@DELETE
	@Path("/{commentId}")
	public void deleteComment(@PathParam("messgeId")long messageId,@PathParam("commentId")long commentId){
		commentService.removeComment(messageId, commentId);
	}
	
	@GET
	@Path("/{commentId}")
	public Comment getComment(@PathParam("messgeId")long messageId,@PathParam("commentId")long commentId){
		return commentService.getComment(messageId, commentId);
	}
}
