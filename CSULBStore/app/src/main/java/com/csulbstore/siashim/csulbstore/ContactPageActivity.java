package com.csulbstore.siashim.csulbstore;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ContactPageActivity  extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_page);

        Button sendB = (Button) findViewById(R.id.sendButton);
        final EditText emailT = (EditText) findViewById(R.id.emailText);
        final EditText messageT = (EditText) findViewById(R.id.messageText);


        sendB.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {
                        String email = emailT.getText().toString();
                        String message = messageT.getText().toString();

                        Intent i = new Intent(Intent.ACTION_SEND);
                        i.setType("message/rfc822");
                        i.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
                        i.putExtra(Intent.EXTRA_SUBJECT, "message about one of your items");
                        i.putExtra(Intent.EXTRA_TEXT, message);
                        try {
                            startActivity(Intent.createChooser(i, "Send mail..."));
                        } catch (android.content.ActivityNotFoundException ex) {
                            Toast.makeText(ContactPageActivity.this,
                                    "There are no email clients installed.",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}
