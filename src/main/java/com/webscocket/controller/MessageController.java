package com.webscocket.controller;

import org.springframework.messaging.converter.MessageConversionException;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.webscocket.models.Message;

//@RestController
//public class MessageController {
//	
//	@MessageExceptionHandler(MessageConversionException.class)
//	@MessageMapping("/message")
//	//The message will send to this url by any client
//	@SendTo("/topic/returnTo")
//	//The clients whoever will be subscribing to this url will receive the messages
//	public Message getContent(@RequestBody Message message) {
//		
//		try {
//			System.out.print(message);
//			Thread.sleep(1000);
//			
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		
//		return message;
//	}
//
//}



@RestController
public class MessageController {

    private final SimpMessageSendingOperations messagingTemplate;

    public MessageController(SimpMessageSendingOperations messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @MessageExceptionHandler(MessageConversionException.class)
    @MessageMapping("/message")
    // The message will send to this URL by any client
    // The clients whoever will be subscribing to this URL will receive the messages
    public void getContent(@RequestBody Message message) {
        try {
            System.out.print(message);
            Thread.sleep(1000);
            // Broadcast the message to all subscribers of "/topic/returnTo"
            messagingTemplate.convertAndSend("/topic/returnTo", message);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

