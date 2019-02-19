package com.spiritwisdomcounseling.util;

import com.mailjet.client.ClientOptions;
import com.mailjet.client.MailjetClient;
import com.mailjet.client.MailjetRequest;
import com.mailjet.client.MailjetResponse;
import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.errors.MailjetSocketTimeoutException;
import com.mailjet.client.resource.Emailv31;
import com.spiritwisdomcounseling.model.Contact;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Dennis Miller
 */
public class EmailUtil {

    public static final String MJ_APIKEY_PUBLIC =  System.getenv("MJ_APIKEY_PUBLIC");
    public static final String MJ_APIKEY_PRIVATE =  System.getenv("MJ_APIKEY_PRIVATE");

    private EmailUtil(){

    }

    public static Map<String, String> sendContactEmail(Contact contact){

        Map<String, String> map = new HashMap<>();

        MailjetClient client;
        MailjetRequest request;
        MailjetResponse response;

        String fullname = contact.getFirstName()+" "+contact.getLastName();

        client = new MailjetClient(MJ_APIKEY_PUBLIC, MJ_APIKEY_PRIVATE, new ClientOptions("v3.1"));

        request = new MailjetRequest(Emailv31.resource)
                .property(Emailv31.MESSAGES, new JSONArray()
                        .put(new JSONObject()
                                .put(Emailv31.Message.FROM, new JSONObject()
                                        .put("Email", "dennis.miller@8hzsymmetry.com")
                                        .put("Name", "Spirit Wisdom Counseling"))
                                .put(Emailv31.Message.TO, new JSONArray()
                                        .put(new JSONObject()
                                                .put("Email", contact.getEmail())
                                                .put("Name", fullname)))
                                .put(Emailv31.Message.TEMPLATEID, 696968)
                                .put(Emailv31.Message.TEMPLATELANGUAGE, true)
                                .put(Emailv31.Message.TEMPLATEERROR_REPORTING, new JSONObject()
                                        .put("Email", "dennis.miller@8hzsymmetry.com").put("Name", "Dennis Miller"))
                                .put(Emailv31.Message.SUBJECT, "Thanks for the inquiry!")
                                .put(Emailv31.Message.VARIABLES, new JSONObject()
                                        .put("fullname", fullname ))));



        try{
            response = client.post(request);
            // TODO null checks
            System.out.println(response.getStatus());
            System.out.println(response.getData());
            JSONArray successarr = response.getData();
            JSONObject successobj = successarr.getJSONObject(0);

            map.put("Status", successobj.getString("Status"));
            // add any other props here

            return map;
        }catch (MailjetSocketTimeoutException e){
            e.printStackTrace();
        }catch (MailjetException e){
            e.printStackTrace();
        }
        return null;

    }

    public static Map<String, String> sendAdminEmail(Contact contact){

        Contact adminContact = new Contact();
        adminContact.setFirstName("Genevieve");
        adminContact.setLastName("Miller");
        adminContact.setEmail("spiritwisdomcounseling@gmail.com");

        String fullname = contact.getFirstName()+" "+contact.getLastName();

        System.out.println("TIME TO SEND TO GENNY!");

        Map<String, String> map = new HashMap<>();

        MailjetClient client;
        MailjetRequest request;
        MailjetResponse response;


        client = new MailjetClient(MJ_APIKEY_PUBLIC, MJ_APIKEY_PRIVATE, new ClientOptions("v3.1"));

        request = new MailjetRequest(Emailv31.resource)
                .property(Emailv31.MESSAGES, new JSONArray()
                        .put(new JSONObject()
                                .put(Emailv31.Message.FROM, new JSONObject()
                                        .put("Email", "dennis.miller@8hzsymmetry.com")
                                        .put("Name", "Admin at Spirit Wisdom Counseling"))
                                .put(Emailv31.Message.TO, new JSONArray()
                                        .put(new JSONObject()
                                                .put("Email", adminContact.getEmail())
                                                .put("Name", adminContact.getFirstName())))
                                .put(Emailv31.Message.CC, new JSONArray().put(new JSONObject().put("Email", "dennis.miller@8hzsymmetry.com").put("Name", "Dennis Miller")))
                                .put(Emailv31.Message.TEMPLATEID, 702409)
                                .put(Emailv31.Message.TEMPLATELANGUAGE, true)
                                .put(Emailv31.Message.TEMPLATEERROR_REPORTING, new JSONObject()
                                        .put("Email", "dennis.miller@8hzsymmetry.com").put("Name", "Dennis Miller"))
                                .put(Emailv31.Message.SUBJECT, "An inquiry from the website!")
                                .put(Emailv31.Message.VARIABLES, new JSONObject()
                                        .put("adminName", adminContact.getFirstName())
                                        .put("fullname", fullname )
                                        .put("email", contact.getEmail())
                                        .put("phone", contact.getPhone())
                                        .put("message", contact.getComments()))));



        try{
            response = client.post(request);
            // TODO null checks
            System.out.println(response.getStatus());
            System.out.println(response.getData());
            JSONArray successarr = response.getData();
            JSONObject successobj = successarr.getJSONObject(0);

            map.put("Status", successobj.getString("Status"));
            // add any other props here

            return map;
        }catch (MailjetSocketTimeoutException e){
            e.printStackTrace();
        }catch (MailjetException e){
            e.printStackTrace();
        }
        return null;

    }
}
