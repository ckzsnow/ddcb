package com.dd.constant;

public interface Constant {

	enum CourseAuditStatus implements Constant {
        ONGOING(0), PASS(1), REJECT(2), ALL(3);

		private int code;

	    private CourseAuditStatus(int code) {
	        this.code = code;
	    }

	    @Override
	    public String toString() {
	        return String.valueOf(this.code);
	    }
    }

    enum CourseType implements Constant {
    	COMMON(0), CHOICENESS(1), ALL(2);
    	
    	private int code;

	    private CourseType(int code) {
	        this.code = code;
	    }

	    @Override
	    public String toString() {
	        return String.valueOf(this.code);
	    }
    }
    
    enum UserType implements Constant {
    	LISTEN(0), TEACH(1), ALL(2);
    	
    	private int code;

	    private UserType(int code) {
	        this.code = code;
	    }

	    @Override
	    public String toString() {
	        return String.valueOf(this.code);
	    }
    }
    
    enum UserSex implements Constant {
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
