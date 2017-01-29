package org.sharath.messenger.database;
import java.util.*;

import org.sharath.messenger.database.*;
import org.sharath.messenger.model.Message;
import org.sharath.messenger.model.Profile;

public class DatabaseClass {
	private static Map<Long,Message> messages = new HashMap<>();
	public static Map<Long, Message> getMessages() {
		return messages;
	}
	public static Map<String, Profile> getProfiles() {
		return profiles;
	}
	private static Map<String,Profile> profiles = new HashMap<>();
}
