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

    private EmailUtil(){

    }

    public static Map<String, String> sendContactEmail(Contact contact){


        String mjPrivate = SecurityUtil.getInstance().getMj_private();
        String mjPublic = SecurityUtil.getInstance().getMj_public();

        MailjetClient client;
        MailjetRequest request;

        String fullname = contact.getFirstName()+" "+contact.getLastName();

        client = new MailjetClient(mjPublic, mjPrivate, new ClientOptions("v3.1"));

        request = new MailjetRequest(Emailv31.resource)
                .property(Emailv31.MESSAGES, new JSONArray()
                        .put(new JSONObject()
                                .put(Emailv31.Message.FROM, new JSONObject()
                                        .put("Email", "genevieve.miller@spiritwisdomcounseling.com")
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



        Map<String, String> map = postRequest(request, client);

        return map;


    }

    public static Map<String, String> sendAdminEmail(Contact contact){

        Contact adminContact = new Contact();
        adminContact.setFirstName("Genevieve");
        adminContact.setLastName("Miller");
        adminContact.setEmail("genevieve.miller@spiritwisdomcounseling.com");

        String mjPrivate = SecurityUtil.getInstance().getMj_private();
        String mjPublic = SecurityUtil.getInstance().getMj_public();

        String fullname = contact.getFirstName()+" "+contact.getLastName();

        System.out.println("TIME TO SEND TO GENNY!");

        MailjetClient client;
        MailjetRequest request;

        client = new MailjetClient(mjPublic, mjPrivate, new ClientOptions("v3.1"));

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
                                .put(Emailv31.Message.CC, new JSONArray()
                                        .put(new JSONObject().put("Email", "dennis.miller@8hzsymmetry.com").put("Name", "Dennis Miller")))
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



        Map<String, String> map = postRequest(request, client);

        return map;

    }

    private static Map<String, String> postRequest( MailjetRequest request,  MailjetClient client){
        try{
            MailjetResponse response;
            Map<String, String> map = new HashMap<>();
            response = client.post(request);
            System.out.println(response.getStatus());
            System.out.println(response.getData());
            JSONArray successarr = response.getData();
            JSONObject successobj = successarr.getJSONObject(0);

            if(successobj != null){
                map.put("Status", successobj.getString("Status"));
            }

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
