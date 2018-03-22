package com.example.csulbstore;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.firebase.client.Firebase;

public class MySingleListView extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_my_single_list_view);

        EditText etPostTitle = (EditText) findViewById(R.id.etPostTitle);
        final EditText postDescription = (EditText) findViewById(R.id.etPostDescription);
        Button updatepTitleBtn = (Button) findViewById(R.id.updatefNameBtn);
        Button updatePostDescBtn = (Button) findViewById(R.id.updatePostDescBtn);


        final Intent gid = getIntent();
        // getting attached intent data
        final String gid_postTitle = gid.getStringExtra("position");
        final int gid_listID = gid.getExtras().getInt("position");
//        final int gid_listID = gid.getIntExtra("id");
        final String userID = gid.getStringExtra("userID");


        final String gid_postDate = gid.getStringExtra("postDate");
        final String gid_postDescription = gid.getStringExtra("postDescription");
        final String gid_email = gid.getStringExtra("postEmail");

        final Firebase usersRef = new Firebase("https://glowing-inferno-4690.firebaseio.com/posts");
//        final Firebase usersRef1;
//
//        Query queryRef1 = usersRef.orderByChild("title").equalTo("Sider===");
////        Query queryRef2 = usersRef.orderByChild("title").equalTo("Sider===");
//
//        usersRef1 = queryRef1.getRef();
//
//
//
//        Map<String, Object> post1 = new HashMap<String, Object>();
//
//        post1.put("title", "cool stuff!");
//        usersRef1.updateChildren(post1);
//




        updatepTitleBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                System.out.println("USERID: " + userID);

//                Firebase alanRef = usersRef.child(userID).child("-179100989");
//                Map<String, Object> nickname = new HashMap<String, Object>();
//                nickname.put("description", "hello theres!");
//                alanRef.updateChildren(nickname);



//                System.out.println("Position: " + gid_postTitle);
                System.out.println("ID: " + gid_listID);
//                System.out.println("Post Date: " + gid_postDate);
//                bots_64@mailinator.com

                System.out.println("Post Desc: " + gid_postDescription);
//                System.out.println("Email: " + gid_email);

                //-179100989
                System.out.println("Clicked!!");


            }
        });

        updatePostDescBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                System.out.println("Position: " + gid_postTitle);
//                System.out.println("ID: " + gid_listID);
//                System.out.println("Post Date: " + gid_postDate);
                System.out.println("Post Desc: " + gid_postDescription);
                System.out.println("Email: " + gid_email);
                System.out.println("Clicked!!");
            }
        });
    }

}
