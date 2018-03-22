package com.example.csulbstore;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.io.FileReader;
import java.util.Map;
import java.util.Objects;
//import com.example.csulbstore.SendEmail;

/**
 * Created by cmendoza on 10/18/15.
 */
public class FirebaseInterface {

    private static Firebase fbaseURLRef;
//    private static SendEmail sendMail;
    private String username;
    private String password;


    public static void createAccount(String account, String password){

        fbaseURLRef = new com.firebase.client.Firebase("https://glowing-inferno-4690.firebaseio.com/");
        fbaseURLRef.createUser(account, password, new com.firebase.client.Firebase.ValueResultHandler<Map<String, Object>>() {
            @Override
            public void onSuccess(Map<String, Object> result) {
                System.out.println("Successfully created user account with uid: " + result.get("uid"));
                System.out.println("Successfully created user account with provider: " + result.get("provider"));
                System.out.println("Successfully created user account with token: " + result.get("token"));
                System.out.println("Successfully created user account with auth: " + result.get("auth"));
                System.out.println("Successfully created user account with expires: " + result.get("expires"));
                System.out.println("Successfully created user account with password: " + result.get("password"));
                System.out.println("Successfully created user account with password.email: " + result.get("password.email"));
                System.out.println("Successfully created user account with password.isTempPass: " + result.get("password.isTemporaryPassword"));
                System.out.println("Successfully created user account with pass.profileImageUrl: " + result.get("password.profileImageURL"));

                /* cm0dit - 10/11/15 */
                new SendMail().execute(result.get("uid").toString());
                /* cm0dit - 10/11/15 */


            }
            @Override
            public void onError(FirebaseError firebaseError) {
                System.out.println("Error occurred: " + firebaseError);
            }
        });


    }

    public static void logIn(){

    }

    public static void logOut(){

    }

    public static void createPost(){

    }

    public static void changePassword(){

    }

    public static void auth(){

    }



    /**
     * Async task class to get json by making HTTP call
     * */
    private static class SendMail extends AsyncTask<String, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
//			pDialog = new ProgressDialog(CreateAccountActivity.this);
//			pDialog.setMessage("Please wait...");
//			pDialog.setCancelable(false);
//			pDialog.show();

        }

        @Override
        protected Void doInBackground(String... accInfo) {
            // Creating service handler class instance

            Mail m = new Mail("androidrobots64@gmail.com", "Sproject2015*");

            String[] toArr = {"bots_64@mailinator.com", "bots_65@mailinator.com"};
            m.setTo(toArr);
            m.setFrom("no-reply@none.com");
            m.setSubject("Reset Password Info");
            m.setBody("Click on the following link <a href='http://www.google.com/'></a> to activate your account. GUID: " + accInfo[0]);

            try {
                // m.addAttachment("/sdcard/filelocation");
                if(m.send()) {
                    // Toast.makeText(CreateAccountActivity.this, "Email was sent successfully.", Toast.LENGTH_LONG).show();
                    System.out.println("Email was sent successfully.");
                } else {
                    // Toast.makeText(CreateAccountActivity.this, "Email was not sent.", Toast.LENGTH_LONG).show();
                    System.out.println("Email was not sent.");
                }
            } catch(Exception e) {
                //Toast.makeText(MailApp.this, "There was a problem sending the email.", Toast.LENGTH_LONG).show();
                Log.e("MailApp", "Could not send email", e);
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
//			if (pDialog.isShowing())
//				pDialog.dismiss();
//			/**
//			 * Updating parsed JSON data into ListView
//			 * */
//			ListAdapter adapter = new SimpleAdapter(
//					MainActivity.this, contactList,
//					R.layout.list_item, new String[] { TAG_NAME, TAG_EMAIL,
//					TAG_PHONE_MOBILE }, new int[] { R.id.name,
//					R.id.email, R.id.mobile });
//
//			setListAdapter(adapter);


            System.out.println("RESULT: " + result);

        }

    }


}
