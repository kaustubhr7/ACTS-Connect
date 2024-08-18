package com.actsconnect.service;

import java.util.List;

import com.actsconnect.exception.ChatException;
import com.actsconnect.exception.MessageException;
import com.actsconnect.exception.UserException;
import com.actsconnect.model.Message;
import com.actsconnect.request.SendMessageRequest;

public interface MessageService  {
	
	public Message sendMessage(SendMessageRequest req) throws UserException, ChatException;
	
	public List<Message> getChatsMessages(Integer chatId) throws ChatException;
	
	public Message findMessageById(Integer messageId) throws MessageException;
	
	public String deleteMessage(Integer messageId) throws MessageException;

}
