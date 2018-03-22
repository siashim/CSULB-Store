package com.example.csulbstore;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.core.view.View;

import java.util.HashMap;
import java.util.Map;

public class PostAListing extends Activity {

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar actionBar = getActionBar();
        actionBar.hide();

        setContentView(R.layout.activity_post_alisting);

        final EditText etPostTitle = (EditText) findViewById(R.id.etPostTitle);
        final EditText etDescription = (EditText) findViewById(R.id.etDescription);
        Button updateNameBtn = (Button) findViewById(R.id.addPostingBtn);

        Intent intent = getIntent();
        String pwd = intent.getStringExtra("PASSWORD");
        final String token = intent.getStringExtra("TOKEN");
        final Firebase mFirebaseRef = new Firebase("https://glowing-inferno-4690.firebaseio.com/posts");
        final Firebase mFirebaseRef2 = new Firebase("https://glowing-inferno-4690.firebaseio.com/users");
        final Firebase newPostRef = mFirebaseRef.child("10057617-d33b-46e2-a63c-70f19547a1ca").push();

        updateNameBtn.setOnClickListener(new android.view.View.OnClickListener() {
            public void onClick(android.view.View v) {

                mFirebaseRef.authWithCustomToken(token, new Firebase.AuthResultHandler() {

                    @Override
                    public void onAuthenticated(AuthData authData) {
                        // String text = "data - " + ts;
                        String postTitle = etPostTitle.getText().toString();
                        String postDescription = etDescription.getText().toString();


                        Posts message = new Posts(postTitle, postDescription);

                        // mFirebaseRef.child("posts").child(authData.getUid()).push().setValue(map);

//                        mFirebaseRef.push().setValue(message);
                        // mFirebaseRef.push().setValue(message);
//                        mFirebaseRef.child("10057617-d33b-46e2-a63c-70f19547a1ca").push().setValue(message);




//                        String daKey = mFirebaseRef.getKey();

//                        System.out.println("DAKEY:" + daKey);
//                        System.out.println("Messase:" + message);

                        Map<String, Object> nickname = new HashMap<String, Object>();
                        nickname.put("description", postDescription);
                        nickname.put("title", postTitle);
                        nickname.put("email", authData.getAuth().get("email"));
//                        authData
                        newPostRef.setValue(nickname);
//                        mFirebaseRef.child(authData.getUid()).updateChildren(nickname);
//                        mFirebaseRef.child(authData.getUid()).push().setValue(message);

                        String postId = newPostRef.getKey();

                        mFirebaseRef.child(authData.getUid()).child(postId).setValue(message);

//                        System.out.print("DAKEY: " + postId);
//
//                        Map<String, Object> nickname2 = new HashMap<String, Object>();
//                        nickname.put("description", postTitle);
//                        nickname.put("title", postDescription);
//                        mFirebaseRef.child(authData.getUid()).child(postId).setValue(nickname2);


                        etPostTitle.setText("");
                        etDescription.setText("");
                        Toast.makeText(PostAListing.this,
                                "You're post has been submitted!", Toast.LENGTH_SHORT).show();

                        finish();
                    }

                    @Override
                    public void onAuthenticationError(FirebaseError firebaseError) {
                        System.out.println("ERROR: " + firebaseError.getMessage());
                    }
                });





//        ref.authWithPassword("bots_64@mailinator.com", "FVeuuhrXebD6a2cd",
//                new Firebase.AuthResultHandler() {
//                    @Override
//                    public void onAuthenticated(AuthData authData) {
//                        //{ "msg": "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum." }
//                        // Authentication just completed successfully :)
//                        Map<String, String> map = new HashMap<String, String>();
//                        map.put("provider", authData.getProvider());
//                        if(authData.getProviderData().containsKey("displayName")) {
//                            map.put("displayName", authData.getProviderData().get("displayName").toString());
//                        }
//                        ref.child("users").child(authData.getUid()).setValue(map);
//                    }
//                    @Override
//                    public void onAuthenticationError(FirebaseError error) {
//                        System.out.println("SOMETHING HAPPEN");
//                    }
//                });
            }
        });

        /* resetPasswordB.setOnClickListener(new android.view.View.OnClickListener() {
            public void onClick(android.view.View v) {
//         	Intent i = new Intent(MainActivity.this, ResetPasswordActivity.class);

                Intent intent = getIntent();
                String tokenID = intent.getStringExtra("TOKEN_ID");

                Firebase myFirebaseRef = new Firebase("https://glowing-inferno-4690.firebaseio.com/");





                myFirebaseRef.changePassword("123", "bots_64@mailinator.com", "FVeuuhrXebD6a2cd", new Firebase.ResultHandler() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(PostAListing.this,
                                "You've reset your password!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(FirebaseError firebaseError) {
                        Toast.makeText(PostAListing.this,
                                "We weren't able to reset your password.", Toast.LENGTH_SHORT).show();
                    }
                });





                Toast.makeText(PostAListing.this,
                        "Updated Password", Toast.LENGTH_SHORT).show();
            };
        }); */



        /* updateName.setOnClickListener(new android.view.View.OnClickListener() {
            public void onClick(android.view.View v) {


                final Firebase ref = new Firebase("https://glowing-inferno-4690.firebaseio.com");
                ref.authWithPassword("bots_64@mailinator.com", "FVeuuhrXebD6a2cd",
                        new Firebase.AuthResultHandler() {
                            @Override
                            public void onAuthenticated(AuthData authData) {
                                //{ "msg": "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum." }
                                // Authentication just completed successfully :)


                                Firebase usersRef = ref.child("messages");
                                Map<String, String> alanisawesomeMap = new HashMap<String, String>();
                                alanisawesomeMap.put("msg1", "some data the first one");
                                alanisawesomeMap.put("msg2", "the second");
                                Map<String, Map<String, String>> users = new HashMap<String, Map<String, String>>();
                                users.put("messages", alanisawesomeMap);
                                usersRef.setValue(users);


//                                Map<String, String> map = new HashMap<String, String>();
//                                map.put("provider", authData.getProvider());
//                                if (authData.getProviderData().containsKey("displayName")) {
//                                    map.put("displayName", authData.getProviderData().get("displayName").toString());
//                                }
//                                ref.child("users").child(authData.getUid()).setValue(map);
                            }

                            @Override
                            public void onAuthenticationError(FirebaseError error) {
                                System.out.println("Error: " + error);
                            }
                        });





                Toast.makeText(PostAListing.this,
                        "added stuff", Toast.LENGTH_SHORT).show();
            };
        }); */


    }


//    public void updateName(View v){
//
//        final Firebase ref = new Firebase("https://glowing-inferno-4690.firebaseio.com/");
//        ref.authWithPassword("bots_64@mailinator.com", "FVeuuhrXebD6a2cd",
//                new Firebase.AuthResultHandler() {
//                    @Override
//                    public void onAuthenticated(AuthData authData) {
//                        //{ "msg": "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum." }
//                        // Authentication just completed successfully :)
//                        Map<String, String> map = new HashMap<String, String>();
//                        map.put("provider", authData.getProvider());
//                        if(authData.getProviderData().containsKey("displayName")) {
//                            map.put("displayName", authData.getProviderData().get("displayName").toString());
//                        }
//                        ref.child("users").child(authData.getUid()).setValue(map);
//                    }
//                    @Override
//                    public void onAuthenticationError(FirebaseError error) {
//                        System.out.println("SOMETHING HAPPEN");
//                    }
//                });

//
//
//    }

    public void updateEmail(View v){
//        System.out.println("updateEmail");
    }

//    public void updateStuff(View v){
//        System.out.println("updateEmail");
//    }

    public void logout(View view){
//        System.out.println("logout");
    }

}
