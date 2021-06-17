package com.estupinia.websockets.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;

import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.user.SimpUser;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.util.HtmlUtils;

import com.estupinia.models.User;
import com.estupinia.services.UserServices;
import com.estupinia.websockets.models.Greeting;
import com.estupinia.websockets.models.HelloMessage;

@Controller
public class GreetingController {
		
	@Autowired 
	SimpUserRegistry simpUserRegistry;
	
	@Autowired
	UserServices userServices;

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {
        Thread.sleep(1000); // simulated delay   
        return new Greeting("Buenas tardes, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }
    
    @MessageMapping("/users")
    @SendTo("/topic/greetings")
    public ResponseEntity<List<User>> getConnected() {
//    	User user = new User();
//    	user.setName("Andresito");
//    	List<User> connectedUsers = new ArrayList<User>();
//    	connectedUsers.add(user);
//		return ResponseEntity.ok(connectedUsers);
    	final List<User> userList = userServices.getConnectedUsers();
		return ResponseEntity.ok(userList);
////        return this.simpUserRegistry
////                .getUsers()
////                .stream()
////                .map(SimpUser::getName)
////                .collect(Collectors.toList());
    }

}
