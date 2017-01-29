package org.sharath.messenger.service;

import java.util.*;

import org.sharath.messenger.database.DatabaseClass;
import org.sharath.messenger.model.Profile;

public class ProfilesService {
	private Map<String,Profile> profiles=DatabaseClass.getProfiles();
	public ProfilesService(){
		profiles.put("sharath", new Profile(1,"sharath","raini","chandra"));
		profiles.put("raini", new Profile(2,"yo","raini","chandra"));		
	}
	public List<Profile> getAllProfiles(){
		return new ArrayList<Profile>(profiles.values());
	}
	public Profile getProfile(String profilename){
		return profiles.get(profilename);
	}
	public Profile addProfile(Profile profile){
		profile.setId(profiles.size()+1);
		profiles.put(profile.getProfileName(), profile);
		return profile;
	}
	public Profile updateProfile(Profile profile){
		if(profile.getProfileName().isEmpty()){
			return null;
		}
		profiles.put(profile.getProfileName(),profile);
		return profile;
	}
	public Profile removeProfile(String profileName){
		return profiles.remove(profileName);
	}
}
