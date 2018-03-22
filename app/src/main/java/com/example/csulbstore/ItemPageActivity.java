package com.example.csulbstore;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class ItemPageActivity extends Activity {
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar actionBar = getActionBar();
        actionBar.hide();

        setContentView(R.layout.item_page);

        Button sendB = (Button) findViewById(R.id.contactButton);
        final TextView emailT = (TextView) findViewById(R.id.emailText);
        final TextView descriptionText = (TextView) findViewById(R.id.descriptionText);


        Intent intent = getIntent();
        String listTitle = intent.getStringExtra("TITLE");
        descriptionText.setText(listTitle);



        sendB.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {
                        String email = emailT.getText().toString();

                        Intent i = new Intent(Intent.ACTION_SEND);
                        i.setType("message/rfc822");
                        i.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
                        i.putExtra(Intent.EXTRA_SUBJECT, "message about one of your items");

                        try {
                            startActivity(Intent.createChooser(i, "Send mail..."));
                            finish();
                        } catch (android.content.ActivityNotFoundException ex) {
                            Toast.makeText(ItemPageActivity.this,
                                    "There are no email clients installed.",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}
