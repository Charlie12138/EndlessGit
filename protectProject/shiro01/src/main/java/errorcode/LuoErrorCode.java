package errorcode;

public enum LuoErrorCode {
	
	NULL_OBJ("LUO001","对象为空"),
	ERROR_ADD_USER("LUO002","添加用户失败"),
	LOGIN_VERIFY_FAILURE("LUO003","登录验证失败，请检查用户名密码是否正确"),
	UNKNOWN_ERROR("LUO999","系统繁忙，请稍后再试....");
	
	
    private String value;
    private String desc;

    private LuoErrorCode(String value, String desc) {
        this.setValue(value);
        this.setDesc(desc);
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
    
    @Override
    public String toString() {
    	return "[" + this.value + "]" + this.desc;
    }
}
