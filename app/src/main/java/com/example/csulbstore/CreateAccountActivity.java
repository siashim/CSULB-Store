package com.example.csulbstore;


import android.annotation.TargetApi;
import android.app.ActionBar;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.HashMap;
import java.util.Map;

public class CreateAccountActivity extends ActionBarActivity {
	String name, lastName, password, confirmPassword;
	Account account;

	static String email;


	//creat and write to file
//	public void generateNoteOnSD(String sFileName, String sBody) {
//      FileWriter fWriter;
//	    try
//	    {
//
//          fWriter = new FileWriter("/sdcard/" + sFileName + ".txt");
//          fWriter.write(sBody);
//          fWriter.flush();
//          fWriter.close();
//	        Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
//	    }
//	    catch(IOException e)
//	    {
//	         e.printStackTrace();
//	         e.getMessage();
//	    }
//	   }


	public static String getEmail(){

		return email;
	}



	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ActionBar actionBar = getActionBar();
		actionBar.hide();

		Firebase.setAndroidContext(this);
		setContentView(R.layout.create_account);

		Button confirmB = (Button)findViewById(R.id.confirmButton);
	   final EditText nameT = (EditText)findViewById(R.id.nameText);
	   final EditText lastNameT = (EditText)findViewById(R.id.lastNameText);
	   final EditText emailT = (EditText)findViewById(R.id.emailText);
	   final EditText passwordT = (EditText)findViewById(R.id.passwordText);
	   final EditText confirmPasswordT = 
	    (EditText)findViewById(R.id.confirmPasswordText);



	   confirmB.setOnClickListener(
	   		new View.OnClickListener()
			{
	   			public void onClick(View view)
	            {
	   				name = nameT.getText().toString();
	               lastName = lastNameT.getText().toString();
	               email = emailT.getText().toString();
	               password = passwordT.getText().toString();
	               confirmPassword = confirmPasswordT.getText().toString();

					if(email.equals("") || email.equals(null)){
						Toast.makeText(CreateAccountActivity.this,
								"Email field cannot be empty.", Toast.LENGTH_LONG).show();
					}
					else if(password.equals("") || password.equals(null)){
						Toast.makeText(CreateAccountActivity.this,
							"Password field cannot be empty.", Toast.LENGTH_LONG).show();
					}
					else{
						if (password.equals(confirmPassword))
						{
							account = new Account(name, lastName, email, password);

							//connect to firebase and create account
//							FirebaseInterface.createAccount(account.getEmail(), account.getPassword());

							Firebase fbaseURLRef;
//							final Firebase usersRef;
//							usersRef = new com.firebase.client.Firebase("https://glowing-inferno-4690.firebaseio.com/users");
//							usersRef = fbaseURLRef.child("users");


							final Firebase ref = new Firebase("https://glowing-inferno-4690.firebaseio.com/");
							ref.createUser(email, password, new Firebase.ValueResultHandler<Map<String, Object>>() {
								@Override
								public void onSuccess(Map<String, Object> result) {

									Map<String, String> map = new HashMap<String, String>();
									map.put("fname: ", account.getName().toString());
									map.put("lname: ", account.getLastName().toString());
									map.put("email: ", account.getEmail().toString());
									map.put("Active: ", "true");


//                                if (authData.getProviderData().containsKey("displayName")) {
//                                    map.put("displayName", authData.getProviderData().get("displayName").toString());
//                                }
//                                ref.child("users").setValue(result.get("uid").toString());

//											Map<String, String> alanisawesomeMap = new HashMap<String, String>();
//											Map<String, Object> users = new HashMap<String, Object>();
//                                alanisawesomeMap.put("msg1", "some data the first one");
//                                alanisawesomeMap.put("msg2", "the second");
//                                Map<String, Map<String, String>> users = new HashMap<String, Map<String, String>>();

//											users.put("verified", true);
//									users.put("email", result.get("password.email").toString());
//											users.put("fname", account.getName().toString());
//											users.put("lname", account.getLastName().toString());

//                                usersRef.setValue(users);
//											usersRef.updateChildren(users);


//									usersRef.child(result.get("uid").toString()).setValue(newUser);

									System.out.println("Successfully created user account with uid: " + result.get("uid"));

									System.out.println("Successfully created user account with provider: " + result.get("provider"));
									System.out.println("Successfully created user account with token: " + result.get("token"));
									System.out.println("Successfully created user account with auth: " + result.get("auth"));
									System.out.println("Successfully created user account with expires: " + result.get("expires"));
									System.out.println("Successfully created user account with password: " + result.get("password"));
									System.out.println("Successfully created user account with password.email: " + result.get("password.email"));
									System.out.println("Successfully created user account with password.isTempPass: " + result.get("password.isTemporaryPassword"));
									System.out.println("Successfully created user account with pass.profileImageUrl: " + result.get("password.profileImageURL"));

//                                map.put("Email: ", result.get("email").toString());


//                                if (authData.getProviderData().containsKey("displayName")) {
//                                    map.put("displayName", authData.getProviderData().get("displayName").toString());
//                                }
//                                ref.child("users").setValue(result.get("uid").toString());
									ref.child("users").child(result.get("uid").toString()).setValue(map);

									System.out.println("Successfully created user account with uid: " + result.get("uid"));

									/* cm0dit - 10/11/15 */
									new SendMail().execute(result.get("uid").toString());
									Toast.makeText(CreateAccountActivity.this,
//											"You've successfully created an account. You'll need to verify it via email before you're able to login.", Toast.LENGTH_LONG).show();
											"You've successfully created an account. We've sent you your username in case you misplace it.", Toast.LENGTH_LONG).show();
									finish();
               						 /* cm0dit - 10/11/15 */
								}

							@Override
							public void onError(FirebaseError firebaseError) {
//									System.out.println("Error occurred: " + firebaseError);

							System.out.println("FIREBASE ERROR: " + firebaseError);
							System.out.println("FIREBASE CODE: " + firebaseError.getCode());
							System.out.println("FIREBASE Details: " + firebaseError.getDetails());
							System.out.println("FIREBASE Message: " + firebaseError.getMessage());
							System.out.println("FIREBASE STATIC NAME: " + FirebaseError.INVALID_EMAIL);


							switch (firebaseError.getCode()) {
								case -18:
									Toast.makeText(CreateAccountActivity.this,
											firebaseError.getMessage() + " Try using a different email or resetting your account.", Toast.LENGTH_LONG).show();
									break;
								case -24:
									Toast.makeText(CreateAccountActivity.this,
											"We can't log you in, please check your internet connection.", Toast.LENGTH_LONG).show();
									break;
//										case c3:
//										case c4:
//											statements // they are executed if variable ==  any of the above c's
//											break;
//										. . .
								default:
									Toast.makeText(CreateAccountActivity.this,
											firebaseError.getMessage(), Toast.LENGTH_LONG).show();
									break;
							}


						}
							});


						}
						else
						{
							Toast.makeText(CreateAccountActivity.this,
									"The passwords do not match", Toast.LENGTH_LONG).show();
						}
					}

	            }
	        });



	}







	/**
	 * Async task class to get json by making HTTP call
	 * */
	private static class SendMail extends AsyncTask<String, Void, Void> {



		private String email = CreateAccountActivity.getEmail();


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

			String[] toArr = {email};
			m.setTo(toArr);
			m.setFrom("no-reply@none.com");
			m.setSubject("Your Account Has Been Created");
//			m.setBody("You've successfully created an account. Try loggin in! GUID: https://glowing-inferno-4690.firebaseio.com/verified/" + accInfo[0]);
			m.setBody("You've successfully created an account. Try logging in.");

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
