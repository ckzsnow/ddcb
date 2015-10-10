package com.dd.redis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;

import com.dd.models.UserCourseModel;
import com.dd.redis.service.IUserCourseRedisService;

@Service("userCourseRedisService")
public class UserCourseRedisService implements IUserCourseRedisService {
	
	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Override
	public boolean addUserCourse(UserCourseModel userCourseModel) {
		boolean result = stringRedisTemplate.execute(new RedisCallback<Boolean>() {  
	        public Boolean doInRedis(RedisConnection connection)  
	                throws DataAccessException {  
	            RedisSerializer<String> serializer = stringRedisTemplate.getStringSerializer();
	            String courseId = String.valueOf(userCourseModel.getCourseId());
	            String userId = userCourseModel.getUserId();
	            byte[] key  = serializer.serialize(courseId + "_user_list");
	            byte[] value = serializer.serialize(userId);
	            connection.sAdd(key, value);
	            return true;
	        }  
	    }, false, true);
		return result;
	}
	
}
