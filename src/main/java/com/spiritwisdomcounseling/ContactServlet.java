package com.spiritwisdomcounseling;

import com.spiritwisdomcounseling.model.Contact;
import com.spiritwisdomcounseling.util.EmailUtil;
import com.spiritwisdomcounseling.util.SecurityUtil;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * @author Dennis Miller
 */
@WebServlet(name = "ContactServlet", urlPatterns = {"/contact"}, loadOnStartup = 1)
public class ContactServlet extends javax.servlet.http.HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        Contact contact = new Contact();

        // LOAD BASELINE DATA first
        InputStream resourceAsStream = ContactServlet.class.getResourceAsStream("/baseline.json"); // works!
        if (resourceAsStream != null){
            InputStreamReader streamReader = new InputStreamReader(resourceAsStream, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(streamReader);
            StringBuilder sb = new StringBuilder();
            for (String line; (line = reader.readLine()) != null;) {
                sb.append(line);
            }
            JSONParser parser = new JSONParser();
            try{
                JSONObject baseline = (JSONObject)parser.parse(sb.toString());
                String host = (String)baseline.get("host");
                String username = (String)baseline.get("username");
                String password = (String)baseline.get("password");
                String driver = (String)baseline.get("driver");
                String mj_private = (String)baseline.get("MJ_APIKEY_PRIVATE");
                String mj_public = (String)baseline.get("MJ_APIKEY_PUBLIC");
                System.out.println("HOST : " +" "+host);
                System.out.println("DBUSERNAME : " +" "+username);
                System.out.println("DBPASSWORD : " +" "+password);
                System.out.println("DRIVER : " +" "+driver);
                System.out.println("MJ PRIVATE : "+" "+mj_private);
                System.out.println("MJ PUBLIC :" +" "+mj_public);
                SecurityUtil.getInstance().setDriver(driver);
                SecurityUtil.getInstance().setHost(host);
                SecurityUtil.getInstance().setPassword(password);
                SecurityUtil.getInstance().setUsername(username);
                SecurityUtil.getInstance().setMj_private(mj_private);
                SecurityUtil.getInstance().setMj_public(mj_public);
            }catch(ParseException e){
                e.printStackTrace();
            }

            response.setContentType("text/html");

            String first = request.getParameter("first_name");
            String last = request.getParameter("last_name");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            String comments =  request.getParameter("comments");
            String referral = request.getParameter("referral");
            // clean
            String cleanfirstname = first.replaceAll("\\s", "");
            String cleanlastname = last.replaceAll("\\s", "");

            contact.setContactId(0);
            contact.setFirstName(cleanfirstname);
            contact.setLastName(cleanlastname);
            contact.setEmail(email);
            contact.setPhone(phone);
            contact.setComments(comments);

            System.out.println("FIRST NAME: "+cleanfirstname +" "+"LAST NAME: "+cleanlastname);
            System.out.println("EMAIL: "+email +" "+"PHONE: "+phone);
            System.out.println("COMMENTS: "+comments +" "+"REFERRAL: "+referral);

            int referralnumber = Integer.parseInt(referral);
            // testing comments
            //TODO create table for this

            String referralStr = "";
            switch (referralnumber){
                case 0:

                    break;

                case 1:
                    referralStr = "I'm an existing patient";

                    break;

                case 2:
                    referralStr = "Search engine";

                    break;

                case 3:
                    referralStr = "Mailer";

                    break;

                case 4:
                    referralStr = "Word of mouth";

                    break;

                case 5:
                    referralStr = "Social Media";

                    break;

                case 6:
                    referralStr = "Other";

                    break;
            }

            System.out.println("REFERRAL STRING IS: "+" "+referralStr);

            contact.setReferral(referralStr);

            boolean isValid = false;

            // Send email to customer
            Map<String, String> map = EmailUtil.sendContactEmail(contact);
            for(Map.Entry entry : map.entrySet()){
                System.out.println("KEY is: "+" "+entry.getKey());
                System.out.println("VALUE is: "+" "+entry.getValue());
                String val = entry.getValue().toString();
                if (val.equalsIgnoreCase("success")){
                    isValid  = true;
                }
            }
            // Send email to Genevieve
            if(isValid){
                EmailUtil.sendAdminEmail(contact);
            }


            // redirect contact to home
            response.sendRedirect(request.getContextPath()+"/");


        }


    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }
}
