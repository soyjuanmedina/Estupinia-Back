package com.estupinia.websockets.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import com.estupinia.models.User;
import com.estupinia.services.UserServices;
import com.estupinia.websockets.models.CommunicationProposal;
import com.estupinia.websockets.models.Greeting;
import com.estupinia.websockets.models.HelloMessage;

@Controller
public class ProposalController {
	
	@Autowired
	UserServices userServices;
	
	@Autowired
    private SimpMessagingTemplate messagingTemplate;
	
    @MessageMapping("/communicationproposal")
    public void communicationProposal(CommunicationProposal communicationProposal) throws Exception {
       	String mailTo = communicationProposal.getTo().getEmail();
    	if(communicationProposal.getType().equals("answer")) {
    		 mailTo = communicationProposal.getFrom().getEmail();
    	}
        messagingTemplate.convertAndSendToUser(mailTo, "/queue/reply", communicationProposal);
    }

}
