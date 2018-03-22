package com.example.csulbstore;

import android.os.AsyncTask;
import android.util.Log;


/**
 * Created by cmendoza on 10/11/15.
 */
public abstract class SendEmail extends AsyncTask<String, Void, Mail> { {


    /* cm0dit - 10/10/15 */

    Mail m = new Mail("androidrobots64@gmail.com", "Sproject2015*");

    String[] toArr = {"bots_64@mailinator.com", "bots_65@mailinator.com"};
    m.setTo(toArr);
    m.setFrom("no-reply@none.com");
    m.setSubject("Reset Password Info");
    m.setBody("Click on the following link <a href='http://www.google.com/'></a> to activate your account.");

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

    /* cm0dit - 10/10/15 */


}
}


