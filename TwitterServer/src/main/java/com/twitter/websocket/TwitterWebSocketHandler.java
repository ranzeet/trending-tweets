package com.twitter.websocket;

import org.springframework.web.client.RestTemplate;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import com.twitter.dto.TweetFetchListDTO;

public class TwitterWebSocketHandler extends AbstractWebSocketHandler {

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		System.out.println("I am handleTextMessage : " + message.toString());
		
		RestTemplate restTemplate = new RestTemplate();

		while (true) {
			TweetFetchListDTO tft = restTemplate.getForObject("http://192.168.0.104:8080/twitterspring/tweets",
					TweetFetchListDTO.class);
			
			for (String tweet : tft.getTweetFetchDTOList()) {
				session.sendMessage(new TextMessage(tweet));
				Thread.sleep(2000);
			}
		}
		// super.handleTextMessage(session, message);
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		//super.afterConnectionEstablished(session);
	}

	@Override
	protected void handleBinaryMessage(WebSocketSession session, BinaryMessage message) throws Exception {
		// super.handleBinaryMessage(session, message);
	}

}
