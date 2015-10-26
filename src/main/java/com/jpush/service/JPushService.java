package com.jpush.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.APIConnectionException;
import cn.jpush.api.common.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.audience.AudienceTarget;

public class JPushService {

	public static String MASTER_SECRET = "9cd066212737275687412f69";

	public static String APP_KEY = "38e046e163fbb6669258b5bc";

	public static JPushClient jpushClient = null;

	private static final Logger logger = LoggerFactory
			.getLogger(JPushService.class);
	
	static {
		jpushClient = new JPushClient(MASTER_SECRET, APP_KEY, 3);
	}

	private static PushPayload buildPushObject_ios_audienceMore_messageWithExtras(String msg, String alias) {
		return PushPayload.newBuilder().setPlatform(Platform.android_ios())
				.setAudience(Audience.newBuilder().addAudienceTarget(AudienceTarget.alias(alias)).build())
				.setMessage(Message.newBuilder().setMsgContent(msg).build()).build();
	}
	
	public static void sendPushMsgToUser(String userId, String msg) {
		PushPayload payload = buildPushObject_ios_audienceMore_messageWithExtras(msg, userId);
        try {
            PushResult result = jpushClient.sendPush(payload);
            logger.info("Got result - " + result);

        } catch (APIConnectionException e) {
        	logger.error("Connection error, should retry later", e);
        } catch (APIRequestException e) {
            // Should review the error, and fix the request
        	logger.error("Should review the error, and fix the request : {}", e);
        	logger.info("HTTP Status: {}", e.getStatus());
        	logger.info("Error Code: {}", e.getErrorCode());
        	logger.info("Error Message: {}", e.getErrorMessage());
        }
	}

}