package com.tripify.demo.consts;

public class SecurityConst {
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public final static String SECRET_KEY = "tripify_jwt_token";
    public final static long EXPIRATION_TIME = (60*60*24*30*1000); //30 days


}
