package com.example.csulbstore;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.HashMap;
import java.util.Map;

public class temp_act extends ActionBarActivity {

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getActionBar();
        actionBar.hide();

        Firebase.setAndroidContext(this);
        setContentView(R.layout.activity_temp_act);

        Button createAccnt = (Button) findViewById(R.id.button2);
        Button createMsg = (Button) findViewById(R.id.button3);
        Button deleteMsg = (Button) findViewById(R.id.button5);
        Button viewList = (Button) findViewById(R.id.viewList);


        createAccnt.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        final Firebase ref = new Firebase("https://glowing-inferno-4690.firebaseio.com/");
                        ref.createUser("bobtony2@firebase.com", "correcthorsebatterystaple", new Firebase.ValueResultHandler<Map<String, Object>>() {
                            @Override
                            public void onSuccess(Map<String, Object> result) {

                                Map<String, String> map = new HashMap<String, String>();
                                map.put("First Name: ", "John");
                                map.put("Last Name: ", "Doe");
                                map.put("Active: ", "true");
//                                map.put("Email: ", result.get("email").toString());


//                                if (authData.getProviderData().containsKey("displayName")) {
//                                    map.put("displayName", authData.getProviderData().get("displayName").toString());
//                                }
//                                ref.child("users").setValue(result.get("uid").toString());
                                ref.child("users").child(result.get("uid").toString()).setValue(map);

                                System.out.println("Successfully created user account with uid: " + result.get("uid"));
                            }

                            @Override
                            public void onError(FirebaseError firebaseError) {
                                // there was an error
                            }
                        });
                    }

                });

        createMsg.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        final Firebase ref = new Firebase("https://glowing-inferno-4690.firebaseio.com/");
                        ref.authWithPassword("bots_64@mailinator.com", "abc",
                                new Firebase.AuthResultHandler() {
                                    @Override
                                    public void onAuthenticated(AuthData authData) {
                                        // Authentication just completed successfully :)
                                        Map<String, String> map = new HashMap<String, String>();
//                                        map.put("title", authData.getProvider());
                                        map.put("title", "Book for sale!");
                                        map.put("description", "Lorem Ipsum is simply dummy text of the printing and typesetting industry.");
                                        map.put("image", "false");
                                        if (authData.getProviderData().containsKey("displayName")) {
                                            map.put("displayName", authData.getProviderData().get("displayName").toString());
                                        }
                                        ref.child("messages").child(authData.getUid()).push().setValue(map);

//                                        ref.push().setValue(map);

                                        System.out.println("Button pressed");
                                    }

                                    @Override
                                    public void onAuthenticationError(FirebaseError error) {
                                        // Something went wrong :(
                                    }
                                });
                    }

                });

        deleteMsg.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Firebase ref = new Firebase("https://glowing-inferno-4690.firebaseio.com/");
                        ref.removeUser("bobtony@firebase.com", "correcthorsebatterystaple", new Firebase.ResultHandler() {
                            @Override
                            public void onSuccess() {
                                System.out.println("ACCOUNT DELETED!!");
                            }

                            @Override
                            public void onError(FirebaseError firebaseError) {
                                // error encountered
                            }
                        });
                    }

                });



        viewList.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent i = new Intent(getApplicationContext(), com.example.csulbstore.ListingsActivity.class);
                        startActivity(i);
                    }

                });



    }

    public void sendData(View view) {
        final Firebase ref = new Firebase("https://glowing-inferno-4690.firebaseio.com/");
        ref.authWithPassword("bots_64@mailinator.com", "abc",
                new Firebase.AuthResultHandler() {
                    @Override
                    public void onAuthenticated(AuthData authData) {
                        // Authentication just completed successfully :)
                        Map<String, String> map = new HashMap<String, String>();
                        map.put("provider", authData.getProvider());
                        if (authData.getProviderData().containsKey("displayName")) {
                            map.put("displayName", authData.getProviderData().get("displayName").toString());
                        }
                        ref.child("users").child(authData.getUid()).setValue(map);
                    }

                    @Override
                    public void onAuthenticationError(FirebaseError error) {
                        // Something went wrong :(
                    }
                });
    }


    public void login(View view) {
        final Firebase ref = new Firebase("https://glowing-inferno-4690.firebaseio.com/");
        ref.authWithPassword("bobtony@firebase.com", "correcthorsebatterystaple", new Firebase.AuthResultHandler() {
            @Override
            public void onAuthenticated(AuthData authData) {
                System.out.println("User ID: " + authData.getUid() + ", Provider: " + authData.getProvider());

                // Authentication just completed successfully :)
//                Map<String, String> map = new HashMap<String, String>();
//                map.put("title", authData.getProvider());
//                map.put("description", "Lorem Ipsum is simply dummy text of the printing and typesetting industry.");
//                map.put("image", "false");
//                if (authData.getProviderData().containsKey("displayName")) {
//                    map.put("displayName", authData.getProviderData().get("displayName").toString());
//                }
//                ref.child("messages").child(authData.getUid()).setValue(map);
//
//                System.out.println("Button pressed");


            }

            @Override
            public void onAuthenticationError(FirebaseError firebaseError) {
                // there was an error
            }
        });


    }
}