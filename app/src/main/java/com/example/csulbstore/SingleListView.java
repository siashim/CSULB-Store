package com.example.csulbstore;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by cmendoza on 11/7/15.
 */
public class SingleListView extends Activity{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getActionBar();
        actionBar.hide();
        this.setContentView(R.layout.single_list_view);

        TextView postTitle = (TextView) findViewById(R.id.post_title);
        TextView postAuthor = (TextView) findViewById(R.id.post_author);
        TextView postDate = (TextView) findViewById(R.id.post_date);
        TextView postDescription = (TextView) findViewById(R.id.post_description);
        Button contactSeller = (Button) findViewById(R.id.contactSeller);

        final Intent gid = getIntent();
        // getting attached intent data
        String gid_postTitle = gid.getStringExtra("postTitle");
        String gid_postAuthor = gid.getStringExtra("postAuthor");
        String gid_postDate = gid.getStringExtra("postDate");
        String gid_postDescription = gid.getStringExtra("postDescription");
        final String gid_email = gid.getStringExtra("postEmail");

        // displaying selected product name
        postTitle.setText(gid_postTitle);
        postAuthor.setText(gid_postAuthor);
        postDate.setText(gid_postDate);
        postDescription.setText(gid_postDescription);

        contactSeller.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //open email activity

                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/rfc822");
//                i.putExtra(Intent.EXTRA_EMAIL  , new String[]{gid_email});
                i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"bots_64@mailinator.com"});
                i.putExtra(Intent.EXTRA_SUBJECT, "An inquiry regarding your post");
                i.putExtra(Intent.EXTRA_TEXT   , "");
                try {
                    startActivity(Intent.createChooser(i, "Send mail..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(SingleListView.this,
                            "There are no email clients installed.",
                            Toast.LENGTH_LONG).show();
                }

                System.out.println("Clicked!!");
            }
        });
    }
}