package com.spiritwisdomcounseling.util;

/**
 * @author Dennis Miller
 */
public class SecurityUtil {

    private static SecurityUtil instance;

    private String host;
    private String username;
    private String password;
    private String driver;
    private String mj_private;
    private String mj_public;
    private String recaptchaSecret;

    private SecurityUtil() {
    }

    public static synchronized SecurityUtil getInstance(){
        if(instance == null){
            instance = new SecurityUtil();
        }
        //

        return instance;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getMj_private() {
        return mj_private;
    }

    public void setMj_private(String mj_private) {
        this.mj_private = mj_private;
    }

    public String getMj_public() {
        return mj_public;
    }

    public void setMj_public(String mj_public) {
        this.mj_public = mj_public;
    }

    public String getRecaptchaSecret() {
        return recaptchaSecret;
    }

    public void setRecaptchaSecret(String recaptchaSecret) {
        this.recaptchaSecret = recaptchaSecret;
    }
}
