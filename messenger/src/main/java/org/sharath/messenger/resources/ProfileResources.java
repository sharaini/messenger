package org.sharath.messenger.resources;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.*;
import org.sharath.messenger.service.ProfilesService;
import org.sharath.messenger.model.Profile;
@Path("/profiles")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProfileResources {
	private ProfilesService profService = new ProfilesService();
	
	@GET
	public List<Profile> getProfiles(){
		return profService.getAllProfiles();
	}
	@GET
	@Path("/{profileName}")
	public Profile getProfile(@PathParam("profileName") String name){
		return profService.getProfile(name);
	}
	
	@POST
	public Profile insertProfile(Profile profile){
		profService.addProfile(profile);
		return profile;
	}
	@PUT
	@Path("/{profileName}")
	public Profile updateProfile(@PathParam("profileName") String name,Profile profile){
		profile.setProfileName(name);
		profService.addProfile(profile);
		return profile;
	}
	@DELETE
	@Path("/{profileName}")
	public void deleteProfile(@PathParam("profileName") String name){
		profService.removeProfile(name);
	}
}
