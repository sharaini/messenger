package org.sharath.messenger.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.sharath.messenger.model.ErrorMessage;

//this annotation tells jax rs this is there and maps this to exception
//it registers with jaxrs
@Provider
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException>{

	@Override
	public Response toResponse(DataNotFoundException ex) {
		// TODO Auto-generated method stub
		ErrorMessage em=new ErrorMessage(ex.getMessage(),404,"https://sharath");
		
		//return Response.status(Status.NOT_FOUND).build();
		return Response.status(Status.NOT_FOUND).entity(em).build();
	}
	
}
