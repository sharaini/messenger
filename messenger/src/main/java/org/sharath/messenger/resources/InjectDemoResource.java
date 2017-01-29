package org.sharath.messenger.resources;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Path("/injectdemo")
@Consumes(MediaType.TEXT_PLAIN)
@Produces(MediaType.TEXT_PLAIN)
public class InjectDemoResource {
	//matrix params are seperated by semi colon for eg ;value=200
	@GET
	@Path("annotations")
	public String getParamInitAnnotations(@MatrixParam("value") String value,@HeaderParam("sessionId")String header,@CookieParam("name")String cookies){
		return "sharath"+value+header+cookies;
	}
	//custom header values can be accessed using headerparam
	//cookie can be accessed using cookieparam
	//if you dont know cookie name or you just want to get all the cookies ,that kind of stuff
	// if youre worried the parameters going to be huge list of annotations you can use context annotation to get uriinfo / http header as follows
	//other way is to use beanparam
	@GET
	@Path("/context")
	public String getParamUsingContext(@Context UriInfo uriInfo, @Context HttpHeaders headers){
		
		String pth=uriInfo.getAbsolutePath().toString();
		String cookies=headers.getCookies().toString();
		return pth+cookies;
	}
}
