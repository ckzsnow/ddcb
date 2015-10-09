package com.dd.constant;

public interface Constant {

	enum CourseAuditStatus implements Constant {
        ONGOING("ONGOING", 0), PASS("PASS", 1), REJECT("REJECT", 2), ALL("ALL", 3);

		private int code;
		
		private String name;

	    private CourseAuditStatus(String name, int code) {
	        this.code = code;
	        this.name = name;
	    }

	    public int value() {
	    	return this.code;
	    }
	    
	    @Override
	    public String toString() {
	        return this.name;
	    }
    }

    enum CourseType implements Constant {
    	COMMON("COMMON", 0), CHOICENESS("CHOICENESS", 1), RECOMMEND("RECOMMEND", 2), ALL("ALL", 3);
    	
    	private int code;
    	
    	private String name;

	    private CourseType(String name, int code) {
	        this.code = code;
	        this.name = name;
	    }

	    public int value() {
	    	return this.code;
	    }
	    
	    @Override
	    public String toString() {
	        return this.name;
	    }
    }
    
    enum UserType implements Constant {
    	LISTEN("LISTEN", 0), TEACH("TEACH", 1);
    	
    	private int code;
    	
    	private String name;

	    private UserType(String name, int code) {
	        this.code = code;
	        this.name = name;
	    }

	    public int value() {
	    	return this.code;
	    }
	    
	    @Override
	    public String toString() {
	        return this.name;
	    }
    }
    
    /*enum UserSex implements Constant {
    	MALE(0), FEMALE(1);
    	
    	private int code;

	    private UserSex(int code) {
	        this.code = code;
	    }

	    public int value() {
	    	return this.code;
	    }
	    
	    @Override
	    public String toString() {
	        return String.valueOf(this.code);
	    }
    }*/
    
}
