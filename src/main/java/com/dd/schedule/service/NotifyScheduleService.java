package com.dd.schedule.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.dd.constant.Constant;
import com.dd.dao.ICourseDao;
import com.dd.dao.IUserCourseDao;
import com.dd.models.CourseModel;
import com.dd.models.UserCourseModel;
import com.jpush.service.JPushService;

@Service("notifyScheduleService")
public class NotifyScheduleService {
	private static final Logger logger = LoggerFactory
			.getLogger(NotifyScheduleService.class);
	
	@Autowired
	private ICourseDao courseDao;
	
	@Autowired
	private IUserCourseDao userCourseDao;
	
	@Scheduled(fixedDelay=60000) //每60秒
	public void sendNotify() {
		List<CourseModel> courseList = courseDao.getNotifyCourseList();
		logger.debug("courseList size : {}", courseList != null ? courseList.size() : null);
		if(courseList != null && courseList.size() != 0) {
			for(CourseModel cm : courseList) {
				List<UserCourseModel> ucmStudentList = userCourseDao.getUserCourseByCourseIdAndUserType(cm.getId(), Constant.UserType.LISTEN, 0, 10000000);
				List<UserCourseModel> ucmTeacherList = userCourseDao.getUserCourseByCourseIdAndUserType(cm.getId(), Constant.UserType.TEACH, 0, 10000000);
				for(UserCourseModel ucm : ucmStudentList) {
					JPushService.sendPushMsgToUser(ucm.getUserId(), "尊敬的用户您好，您在点豆成兵上报名参加的课程还有10分钟就要开讲啦，请准时参加！");
				}
				for(UserCourseModel ucm : ucmTeacherList) {
					JPushService.sendPushMsgToUser(ucm.getUserId(), "尊敬的用户您好，您在点豆成兵上开设的课程还有10分钟就要开讲啦，请您准时参与上课互动环节！");
				}
			}
		}
		return;
	}
}