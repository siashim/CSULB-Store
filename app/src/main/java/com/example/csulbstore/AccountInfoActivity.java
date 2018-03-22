package com.example.csulbstore;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.HashMap;
import java.util.Map;

public class AccountInfoActivity extends ActionBarActivity {


//	Firebase myFirebaseRef;
//	Intent loginIntent = getIntent();

    String uid;
    String fname;
    String lname;
    String passwd;

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar actionBar = getActionBar();
        actionBar.hide();

//		Firebase.setAndroidContext(this);
        setContentView(R.layout.activity_account_info);



//		Firebase myFirebaseRef = new Firebase("https://glowing-inferno-4690.firebaseio.com/");
        final Firebase myFirebaseRef = new Firebase("https://glowing-inferno-4690.firebaseio.com/");
//		final EditText unameEtxt = (EditText) findViewById(R.id.userNameEtext);
        final EditText udpatePass = (EditText) findViewById(R.id.updatePassEtext);
        final EditText fname = (EditText) findViewById(R.id.userfNameEtext);
        final EditText lname = (EditText) findViewById(R.id.userlNameEtext);
        final EditText email = (EditText) findViewById(R.id.updateEmail);

//        Button updateName = (Button) findViewById(R.id.updatefNameBtn);
        Button updatePassBtn = (Button) findViewById(R.id.updatePassBtn);
        Button updateEmailBtn = (Button) findViewById(R.id.updateEmailBtn);
        Button updatefnameBtn = (Button) findViewById(R.id.updatefNameBtn);
        Button updatelnameBtn = (Button) findViewById(R.id.updatelNameBtn);



//		updateName.setOnClickListener(new android.view.View.OnClickListener() {
//			public void onClick(android.view.View v) {
////				String uname = udpatePass.getText().toString();
//				Toast.makeText(AccountPageActivity.this,
//						"Updated Name", Toast.LENGTH_SHORT).show();
//
//			}
//		});

        Intent intent = getIntent();
        final String pwd = intent.getStringExtra("PASSWORD");
        final String token = intent.getStringExtra("TOKEN");
        final String accntEmail = intent.getStringExtra("EMAIL");
        final Intent intnt = new Intent(this, MainActivity.class);


        updatePassBtn.setOnClickListener(new android.view.View.OnClickListener() {
            public void onClick(android.view.View v) {



                final String updPass = udpatePass.getText().toString();

                myFirebaseRef.authWithCustomToken(token, new Firebase.AuthResultHandler() {

                    @Override
                    public void onAuthenticated(AuthData authData) {
                        authData.getUid();

                        System.out.println("EMAIL: " + accntEmail);

                        myFirebaseRef.changePassword(accntEmail, pwd, updPass, new Firebase.ResultHandler() {
                            @Override
                            public void onSuccess() {
                                Toast.makeText(AccountInfoActivity.this,
                                        "You've reset your password!", Toast.LENGTH_SHORT).show();
                                myFirebaseRef.unauth();
                                finish();



                                // Closing all the Activities
                                intnt.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                // Add new Flag to start new Activity
                                intnt.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                // Staring Login Activity
                                startActivity(intnt);



                            }

                            @Override
                            public void onError(FirebaseError firebaseError) {
                                Toast.makeText(AccountInfoActivity.this,
                                        "We weren't able to reset your password.", Toast.LENGTH_SHORT).show();
                                System.out.println("ERROR: " + firebaseError);
                            }
                        });
                    }

                    @Override
                    public void onAuthenticationError(FirebaseError firebaseError) {
                        System.out.println("ERROR: " + firebaseError);
                    }
                });




            }


        });



        updatefnameBtn.setOnClickListener(new android.view.View.OnClickListener() {
            public void onClick(android.view.View v) {



                myFirebaseRef.authWithCustomToken(token, new Firebase.AuthResultHandler() {

                    @Override
                    public void onAuthenticated(AuthData authData) {
                        authData.getUid();

                        Firebase usersRef = myFirebaseRef.child("users").child(authData.getUid());
//                                Map<String, String> alanisawesomeMap = new HashMap<String, String>();
                        Map<String, Object> users = new HashMap<String, Object>();
//                                alanisawesomeMap.put("msg1", "some data the first one");
//                                alanisawesomeMap.put("msg2", "the second");
//                                Map<String, Map<String, String>> users = new HashMap<String, Map<String, String>>();
                        users.put("fname", fname.getText().toString());

//                                usersRef.setValue(users);
                        usersRef.updateChildren(users);

                        fname.setText("");

                        Toast.makeText(AccountInfoActivity.this,
                                "You've successfully updated your first name.", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onAuthenticationError(FirebaseError firebaseError) {
                        System.out.println("ERROR: " + firebaseError);
                    }
                });



            }
        });


        updatelnameBtn.setOnClickListener(new android.view.View.OnClickListener() {
            public void onClick(android.view.View v) {

                myFirebaseRef.authWithCustomToken(token, new Firebase.AuthResultHandler() {

                    @Override
                    public void onAuthenticated(AuthData authData) {
                        Firebase usersRef = myFirebaseRef.child("users").child(authData.getUid());
//                                Map<String, String> alanisawesomeMap = new HashMap<String, String>();
                        Map<String, Object> users = new HashMap<String, Object>();
//                                alanisawesomeMap.put("msg1", "some data the first one");
//                                alanisawesomeMap.put("msg2", "the second");
//                                Map<String, Map<String, String>> users = new HashMap<String, Map<String, String>>();
                        users.put("lname", lname.getText().toString());

                        usersRef.updateChildren(users);

                        lname.setText("");


                        Toast.makeText(AccountInfoActivity.this,
                                "You've successfully updated your last name.", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onAuthenticationError(FirebaseError firebaseError) {
                        System.out.println("ERROR: " + firebaseError);
                    }
                });



            }
        });

        updateEmailBtn.setOnClickListener(new android.view.View.OnClickListener() {
            public void onClick(android.view.View v) {

                String newEmail = email.getText().toString();
                myFirebaseRef.changeEmail("oldEmail", newEmail, "curPassord", new Firebase.ResultHandler() {

                    @Override
                    public void onSuccess() {
                        Toast.makeText(AccountInfoActivity.this,
                                "You've reset your password!", Toast.LENGTH_SHORT).show();
//                                "You've reset your password! Please log back in.", Toast.LENGTH_SHORT).show();
//                        finish();
                    }

                    @Override
                    public void onError(FirebaseError firebaseError) {
                        Toast.makeText(AccountInfoActivity.this,
                                "We weren't able to reset your password.", Toast.LENGTH_SHORT).show();
                        System.out.println("ERROR: " + firebaseError);
                    }
                });

            }
        });

    }








//	public void logout(View view) {
//		System.out.println("CLICKED ME!!");
//	}
//
//
//	public void updatePasswords(View view) {
//
//		Toast.makeText(AccountPageActivity.this,
//						"You've reset your password!", Toast.LENGTH_SHORT).show();
//
////		String tokenKey = loginIntent.getStringExtra("TOKEN_ID");
//		// Authenticate users with a custom Firebase token
////		myFirebaseRef.changePassword("123", "bots_64@mailinator.com", "FVeuuhrXebD6a2cd", new Firebase.ResultHandler() {
////			@Override
////			public void onSuccess() {
////				Toast.makeText(AccountPageActivity.this,
////						"You've reset your password!", Toast.LENGTH_SHORT).show();
////			}
////
////			@Override
////			public void onError(FirebaseError firebaseError) {
////				Toast.makeText(AccountPageActivity.this,
////						"We weren't able to reset your password.", Toast.LENGTH_SHORT).show();
////			}
////		});
//
////		myFirebaseRef.changePassword();
////		getAuth();
//
//
////		myFirebaseRef.authWithCustomToken(myFirebaseRef.getAuth().getToken(), new com.firebase.client.Firebase.AuthResultHandler handler){
////
////			@Override
////			onAuthenticated(AuthData authData){
////
////			}
////			@Override
////			onAuthenticationError(FirebaseError error){
////
////			}
//
////		}
//	}

}
