package com.smartcode.websockets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.websocket.Session;

import org.json.JSONObject;

import com.smartcode.domain.Order;

public class KitchenDisplaySessionHandler {

	private List<Session> sessions = new ArrayList<Session>();

	public void addSession(Session session) {
		sessions.add(session);
	}

	public void removeSession(Session session) {
		sessions.remove(session);
	}

	public void sendMessage(JSONObject message) {
		for (Session session : sessions) {
			try {
				session.getBasicRemote().sendText(message.toString());
			} catch (IOException e) {
				removeSession(session);
			}
		}
	}

	public void newOrder(Order order) {
		JSONObject json = new JSONObject();
		json.append("id", order.getId().toString());
		json.append("status", order.getStatus());
		json.append("content", order.toString());
		json.append("action", "add");
		json.append("update", new Date().toString());
		sendMessage(json);
	}

	public void amendOrder(Order order) {
		JSONObject json = new JSONObject();
		json.append("id", order.getId().toString());
		json.append("action", "remove");
		sendMessage(json);
		newOrder(order);
	}
}
