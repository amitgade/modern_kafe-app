package com.smartcode.websockets;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/kitchenManagement")
public class KitchenDisplayWebSocket {

	@OnOpen
	public void open(Session session) {
		KitchenDisplaySessionHandler handler = KitchenDisplaySessionHandlerFactory.getHandler();
		handler.addSession(session);
	}

	@OnClose
	public void close(Session session) {
		KitchenDisplaySessionHandler handler = KitchenDisplaySessionHandlerFactory.getHandler();
		handler.removeSession(session);
	}

	@OnError
	public void onError(Throwable error) {
		throw new RuntimeException(error);
	}
}
