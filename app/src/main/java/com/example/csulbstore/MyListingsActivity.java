package com.example.csulbstore;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.ui.FirebaseListAdapter;

import java.util.Map;

//import com.example.androidhive.helper.AlertDialogManager;
//import com.example.androidhive.helper.ConnectionDetector;
//import com.example.androidhive.helper.JSONParser;

public class MyListingsActivity extends ListActivity {

    private Firebase mFirebaseRef;
    private Map<String, Object> userID;
    String uID;
    ListAdapter adapter;
    FirebaseListAdapter<Posts> mListAdapter;

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_listings);

        Intent intent = getIntent();
        final String token = intent.getStringExtra("TOKEN");
        final String userid = intent.getStringExtra("USERID");





        Firebase.setAndroidContext(this);
        mFirebaseRef = new Firebase("https://glowing-inferno-4690.firebaseio.com/posts");


        mFirebaseRef.authWithCustomToken(token, new Firebase.AuthResultHandler() {
            @Override
            public void onAuthenticationError(FirebaseError error) {
                System.err.println("Login Failed! " + error.getMessage());
            }
            @Override
            public void onAuthenticated(AuthData authData) {

                userID = authData.getAuth();
                uID = userID.get("uid").toString();
//                userID = (Map<String, Object>) userID.get("uid");
                System.out.print(userID.get("uid"));
                System.out.println("Login Succeeded2!");
            }
        });






        ActionBar actionBar = getActionBar();
        actionBar.hide();


//        authData.
//        mUsername = ((String) authData.getProviderData().get("email"));
//        mFirebaseRef.child("a5a5c551-31d4-44a0-89f5-8036e33c2b53");
        adapter = new FirebaseListAdapter<Posts>(this, Posts.class, android.R.layout.two_line_list_item, mFirebaseRef.child(userid)) {


            //        mListAdapter = new FirebaseListAdapter<Messages>(this, Messages.class,
//                android.R.layout.two_line_list_item, mFirebaseRef) {
            @Override
            protected void populateView(View v, Posts model) {
                ((TextView) v.findViewById(android.R.id.text1)).setText(model.getTitle());
                ((TextView) v.findViewById(android.R.id.text2)).setText(model.getDescription());
            }
        };
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        // TODO Auto-generated method stub
        //super.onListItemClick(l, v, position, id);
//        String selection = l.getItemAtPosition(position).toString();
//        Toast.makeText(this, selection, Toast.LENGTH_LONG).show();
        String postTitle = ((TextView) v.findViewById(android.R.id.text1)).getText().toString();
        String postDesc = ((TextView) v.findViewById(android.R.id.text2)).getText().toString();


        Intent i = new Intent(getApplicationContext(), com.example.csulbstore.MySingleListView.class);
        // sending data to new activity
//        i.putExtra("view", v.toString());
        i.putExtra("position", position);
        i.putExtra("id", id);
//        i.putExtra("postDate", id);
        i.putExtra("postDescription", postDesc);
        i.putExtra("postTitle", postTitle);
        i.putExtra("userID", uID.toString());


        System.out.println("view: " + v.toString());
        System.out.println("postTitle: " + postTitle);
        System.out.println("postDescription: " + postDesc);
        System.out.println("position: " + position);
        System.out.println("id: " + id);
        System.out.println("PUSH KEY VALUE: " + id);
        System.out.println("What now?");


//        i.putExtra("postEmail", postEmail);
        startActivity(i);




    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        setListAdapter(null);
    }

}