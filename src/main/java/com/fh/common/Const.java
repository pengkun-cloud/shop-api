package com.fh.common;

public class Const {

    public static Integer REDIS_CODE_EXPIRE_TIME = 3*60;
    public static final String SESSION_KEY = "user";
    public static final String COOKIE_LOGIN_KEY = "login_cookie";
    public static final int COOKIE_EXPIRE_TIME = 7 * 24 * 60 * 60;
    public static final String REDIS_USER_KEY = "redis_user_key";
    public static final String SHOPPING_CAR_KET = "shopCarId:";
    public static final Long REDIS_EXPIRE_TIME = 30 * 60 * 1000L;
    public static final Integer BILL_STATUS_FAIL = 0;
    public static final Integer BILL_STATUS_FINISH = 1;

}
