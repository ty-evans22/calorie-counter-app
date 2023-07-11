package coms309.backend.roundtrip.controllers;

import java.io.IOException;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import coms309.backend.roundtrip.models.Actor;
import coms309.backend.roundtrip.models.ChatMessage;
import coms309.backend.roundtrip.repositories.ActorRepository;
import coms309.backend.roundtrip.repositories.ChatMessageRepository;

@Controller 
@ServerEndpoint(value = "/chat/{username}")
public class ChatController {

	@Autowired
	ActorRepository actorRepo;

	private static ChatMessageRepository chatRepo; 
	@Autowired
	public void setMessageRepository(ChatMessageRepository repo) {
		chatRepo = repo;  //setting the static variable
	}

	//socket session and username
	private static Map<Session, String> session_username_map = new Hashtable<>();
	private static Map<String, Session> username_session_map = new Hashtable<>();

	//for logging
	private final Logger logger = LoggerFactory.getLogger(ChatController.class);

	//opening session
	@OnOpen
	public void onOpen(Session session, @PathParam("username") String username) 
      throws IOException {
		List<Actor> actors = actorRepo.findAll();
		int count = actors.size();
		for(Actor a: actors)
		{
			if(username.equals(a.getUserName()))
				break;
			else count--;
		}
		if(count == 0)
		{
			logger.info("Invalid username");
			return;
		}
		logger.info("Session open entered");
        //store connecting user information
		session_username_map.put(session, username);
		username_session_map.put(username, session);

		//Send chat history to the newly connected user
		sendTextToUser(username, getChatHistory(username));
		
		//broadcast that new user joined
		String message = "User: " + username + " has joined the chat";
		broadcast(message);
	}

	//messaging
	@OnMessage
	public void onMessage(Session session, String message) throws IOException {

		//handle new messages
		logger.info("Message entered - Got message:" + message);
		String username = session_username_map.get(session);

		//Direct message to a user using the format "@username <message>"
		if (message.startsWith("@")) {
			String destUsername = message.split(" ")[0].substring(1); 

			//send the message to the sender and receiver
			sendTextToUser(destUsername, "[DM] " + username + ": " + message);
			sendTextToUser(username, "[DM] " + username + ": " + message);

		} 
		else {
			//broadcast
			broadcast(username + ": " + message);
		}

		//save chat history to repo
		chatRepo.save(new ChatMessage(username, message));
	}


	//closing session
	@OnClose
	public void onClose(Session session) throws IOException {
		logger.info("Session close entered");

		//remove the user connection information
		String username = session_username_map.get(session);
		session_username_map.remove(session);
		username_session_map.remove(username);

		//broadcast that the user disconnected
		String message = username + " disconnected";
		broadcast(message);
	}


	//error handling
	@OnError
	public void onError(Session session, Throwable throwable) {
		logger.info("Error occured");
		throwable.printStackTrace();
	}

	/****** HELPERS **********/

	//send message privately to user
	private void sendTextToUser(String username, String message) {
		try {
			username_session_map.get(username).getBasicRemote().sendText(message);
		} 
    catch (IOException e) {
			logger.info("Exception: " + e.getMessage().toString());
			e.printStackTrace();
		}
	}

	//broadcast message
	private void broadcast(String message) {
		session_username_map.forEach((session, username) -> {
			try {
				session.getBasicRemote().sendText(message);
			} 
      catch (IOException e) {
				logger.info("Exception: " + e.getMessage().toString());
				e.printStackTrace();
			}

		});

	}
	

	//get chat history from repo
	private String getChatHistory(String username) {
		List<ChatMessage> messages = chatRepo.findAll();

		StringBuilder sb = new StringBuilder();
		if(messages != null && messages.size() != 0) {
			for (ChatMessage message : messages) {
				if(message.getUserName().equals(username) || message.getContent().contains("@"+ username))
					sb.append(message.getUserName() + ": " + message.getContent() + "\n");
			}
		}
		return sb.toString();
	}

}
