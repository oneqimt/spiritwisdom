package com.spiritwisdomcounseling.util;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * @author Dennis Miller
 */
public class RecaptchaUtil {

    private RecaptchaUtil(){}

    /**
     * Validates Google reCAPTCHA V2 or Invisible reCAPTCHA.
     * @param secretKey Secret key (key given for communication between your site and Google)
     * @param response reCAPTCHA response from client side. (g-recaptcha-response)
     * @return true if validation successful, false otherwise.
     */
    public static boolean isCaptchaValid(String secretKey, String response) {
        try {
            String url = "https://www.google.com/recaptcha/api/siteverify?"
                    + "secret=" + secretKey
                    + "&response=" + response;
            InputStream res = new URL(url).openStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(res, Charset.forName("UTF-8")));

            StringBuilder sb = new StringBuilder();
            int cp;
            while ((cp = rd.read()) != -1) {
                sb.append((char) cp);
            }
            String jsonText = sb.toString();
            System.out.println("ISCAPTCHA VALID JSON TEXT is: "+" "+jsonText);
            res.close();

            JSONObject json = new JSONObject(jsonText);
            return json.getBoolean("success");
        } catch (Exception e) {
            return false;
        }
    }
}
