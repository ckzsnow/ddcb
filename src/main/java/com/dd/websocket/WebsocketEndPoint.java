package com.dd.websocket;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.dd.models.WebSocketDataModel;

import net.sf.json.JSONObject;

public class WebsocketEndPoint extends TextWebSocketHandler {

	private static final Logger logger = LoggerFactory.getLogger(WebsocketEndPoint.class);

	private static final Map<String, WebSocketSession> userSessionMap = new ConcurrentHashMap<>();

	private static final Map<String, Set<String>> courseToUserSetMap = new WeakHashMap<>();
	
	private static final Map<String, Map<String, String>> userInfoMap = new WeakHashMap<>();

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Override
	public void afterConnectionEstablished(WebSocketSession session) {
		logger.debug("connect to the websocket success......");
		String userId = (String) session.getAttributes().get("userId");
		userSessionMap.put(userId, session);
		if (userId != null) {
			broadcastUserEnter(userId);
		}
	}

	private void broadcastUserEnter(String userId) {
		WebSocketSession session = userSessionMap.get(userId);
		if (session == null) return;
		String courseId = (String) session.getAttributes().get("courseId");
		String message = (String) session.getAttributes().get("userName") + "进入了";
		Set<String> exculdeUserIdSet = new HashSet<>();
		exculdeUserIdSet.add(userId);
		sendSystemMessageToCourseUser(courseId, message, exculdeUserIdSet);
	}

	/*private void broadcastUserLeave(String userId) {
		WebSocketSession session = userSessionMap.get(userId);
		if (session == null) return;
		String courseId = (String) session.getHandshakeAttributes().get("courseId");
		String message = (String) session.getHandshakeAttributes().get("userName") + "离开了";
		Set<String> exculdeUserIdSet = new HashSet<>();
		exculdeUserIdSet.add(userId);
		sendSystemMessageToCourse(courseId, message, exculdeUserIdSet);
	}*/

	private void sendSystemMessageToCourseUser(String courseId, String message, Set<String> exculdeUserIdSet) {
		Set<String> userIdSet = getAllUserByCourseId(courseId);
		if (userIdSet == null) return;
		for (String userId : userIdSet) {
			if (exculdeUserIdSet != null && exculdeUserIdSet.contains(userId)) continue;
			WebSocketSession session = userSessionMap.get(userId);
			if(session == null) continue;
			WebSocketDataModel wsd = new WebSocketDataModel();
			wsd.setMessageType("0");
			wsd.setMessage(message);
			JSONObject json = JSONObject.fromObject(wsd);
			TextMessage returnMessage = new TextMessage(json.toString());
			try {
				session.sendMessage(returnMessage);
			} catch (IOException e) {
				logger.error(e.toString());
			}
		}
	}
	
	private Set<String> getAllUserByCourseId(String courseId) {
		Set<String> result = null;
		if (!courseToUserSetMap.containsKey(courseId)) {
			result = stringRedisTemplate.execute(new RedisCallback<Set<String>>() {
				public Set<String> doInRedis(RedisConnection connection) throws DataAccessException {
					RedisSerializer<String> serializer = stringRedisTemplate.getStringSerializer();
					byte[] key = serializer.serialize(courseId + "_user_list");
					Set<byte[]> valueSet = connection.sMembers(key);
					if (valueSet == null) {
						return null;
					}
					Set<String> userSet = new HashSet<>();
					for (byte[] value : valueSet) {
						userSet.add(serializer.deserialize(value));
					}
					return userSet;
				}
			});
		} else {
			result = courseToUserSetMap.get(courseId);
		}
		return result;
	}

	private Map<String, String> getUserInfo(String userId) {
		Map<String, String> result = null;
		if (!userInfoMap.containsKey(userId)) {
			result = stringRedisTemplate.execute(new RedisCallback<Map<String, String>>() {
				public Map<String, String> doInRedis(RedisConnection connection) throws DataAccessException {
					RedisSerializer<String> serializer = stringRedisTemplate.getStringSerializer();
					byte[] key = serializer.serialize(userId);
					Map<byte[], byte[]> map = connection.hGetAll(key);
					if (map == null) {
						return null;
					}
					Map<String, String> userMap = new HashMap<>();
					for (Entry<byte[], byte[]> entry : map.entrySet()) {
						userMap.put(serializer.deserialize(entry.getKey()), serializer.deserialize(entry.getValue()));
					}
					return userMap;
				}
			});
		} else {
			result = userInfoMap.get(userId);
		}
		return result;
	}
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) {
		String userId = (String) session.getAttributes().get("userId");
		String courseId = (String) session.getAttributes().get("courseId");
		sendUserMessageToOthers(userId, courseId, message.getPayload());
	}
	
	private void sendUserMessageToOthers(String excludeUserId, String courseId, String message) {
		Set<String> userIdSet = getAllUserByCourseId(courseId);
		if (userIdSet == null) return;
		WebSocketSession excludeUserSession = userSessionMap.get(excludeUserId);
		if(excludeUserSession == null) return;
		for (String userId : userIdSet) {
			if(excludeUserId != null && excludeUserId.equals(userId)) continue;
			WebSocketSession session = userSessionMap.get(userId);
			if(session == null) continue;
			if(!userInfoMap.containsKey(userId)) {
				userInfoMap.put(userId, getUserInfo(userId));
			}
			String userName = userInfoMap.get(userId).get("userName");
			String userPhoto = userInfoMap.get(userId).get("userPhoto");
			WebSocketDataModel wsd = new WebSocketDataModel();
			wsd.setMessageType("1");
			wsd.setUserName(userName);
			wsd.setUserPhoto(userPhoto);
			wsd.setMessage(message);
			JSONObject json = JSONObject.fromObject(wsd);
			TextMessage returnMessage = new TextMessage(json.toString());
			try {
				session.sendMessage(returnMessage);
			} catch (IOException e) {
				logger.error(e.toString());
			}
		}
	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) {
		try {
			if (session.isOpen()) {
				session.close();
			}
			logger.debug("websocket connection closed......");
			userSessionMap.remove((String) session.getAttributes().get("userId"));
		} catch (IOException e) {
			logger.error(e.toString());
		}
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) {
		logger.debug("websocket connection closed......");
		userSessionMap.remove((String) session.getAttributes().get("userId"));
	}

	@Override
	public boolean supportsPartialMessages() {
		return false;
	}

}