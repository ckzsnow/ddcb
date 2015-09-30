package com.dd.dao;

public interface DaoConstant {

	enum CourseAuditStatus implements DaoConstant {
        AUDIT_PASSANDREJECT(0), AUDIT_PASS(1), AUDIT_REJECT(2);

		private int code;

	    private CourseAuditStatus(int code) {
	        this.code = code;
	    }

	    @Override
	    public String toString() {
	        return String.valueOf(this.code);
	    }
    }

    enum CourseType implements DaoConstant {
    	COMMON_COURSE(0), CHOICENESS_COURSE(1), ALL_COURSE(2);
    	
    	private int code;

	    private CourseType(int code) {
	        this.code = code;
	    }

	    @Override
	    public String toString() {
	        return String.valueOf(this.code);
	    }
    }
    
    enum UserType implements DaoConstant {
    	LISTEN_USER(0), TEACH_USER(1), ALL_USER(2);
    	
    	private int code;

	    private UserType(int code) {
	        this.code = code;
	    }

	    @Override
	    public String toString() {
	        return String.valueOf(this.code);
	    }
    }
    
    enum UserSex implements DaoConstant {
    	MALE(0), FEMALE(1);
    	
    	private int code;

	    private UserSex(int code) {
	        this.code = code;
	    }

	    @Override
	    public String toString() {
	        return String.valueOf(this.code);
	    }
    }
    
}
