package org.sharath.messenger.service;

import java.util.*;

import org.sharath.messenger.database.DatabaseClass;
import org.sharath.messenger.exceptions.DataNotFoundException;
import org.sharath.messenger.model.*;
public class MessageService {
	private Map<Long,Message>messages=DatabaseClass.getMessages();
	public MessageService(){
		messages.put(1L,new Message(1,"hello","sharath"));
		messages.put(2L,new Message(2,"hello ","dinesh"));
	}
	public List<Message> getAllMessages(){
		return new ArrayList<Message>(messages.values());
	}
	public Message getMessage(long id){
		Message message = messages.get(id);
		//we re throwing this exception and we have to write something to handle the exception in resource for jaxrs to handle it
		if(message == null){
			throw new DataNotFoundException("Message with id"+id+"not found");
		}
		return message;
	}
	public Message addMessage(Message message){
		message.setId(messages.size()+1);
		messages.put(message.getId(),message);
		return message;
	}
	public Message updateMessage(Message message){
		if(message.getId()<=0)
			return null;
		messages.put(message.getId(),message);
		return message;
	}
	public Message removeMessage(long id){
		return messages.remove(id);
	}
	public List<Message> getAllMessagesForYear(int year){
		List<Message> messagesforYear = new ArrayList<>();
		Calendar cal=Calendar.getInstance();
		for(Message message:messages.values()){
			cal.setTime(message.getCreated());
			if(cal.get(Calendar.YEAR)==year){
				messagesforYear.add(message);
			}
		}
		return messagesforYear;
	}
	public List<Message> getAllMessagesPaginated(int start,int end){
		ArrayList<Message> list = new ArrayList<Message>(messages.values());
		if(start+end>list.size())return new ArrayList<>();
		return list.subList(start, start+end);
	}
}
