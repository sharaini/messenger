package org.sharath.messenger.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.sharath.messenger.model.ErrorMessage;

@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable>{

	@Override
	public Response toResponse(Throwable ex) {
		// TODO Auto-generated method stub
				ErrorMessage em=new ErrorMessage(ex.getMessage(),404,"https://sharath");
				
				//return Response.status(Status.NOT_FOUND).build();
				return Response.status(Status.INTERNAL_SERVER_ERROR).entity(em).build();
	}
	
}
