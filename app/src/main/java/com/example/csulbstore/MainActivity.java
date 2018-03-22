package com.example.csulbstore;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class MainActivity extends ActionBarActivity {

	String mUsername;

	public String[] readFile(String path) {
      String[] split = new String[20];
		try {
      File file = new File(path);
      BufferedReader br = new BufferedReader(new FileReader(file));
      String logInInfo;
      while ((logInInfo = br.readLine()) != null) {       
          split = logInInfo.split(":"); 
      }

      br.close();
		}
		catch(IOException e)
	   {
	       	e.printStackTrace();
	         e.getMessage();
	   }
      return split;
	}


	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		Firebase.setAndroidContext(this);

		ActionBar actionBar = getActionBar();
		actionBar.hide();

		setContentView(R.layout.activity_main);

      Button signInB = (Button) findViewById(R.id.signInButton);
      Button createAccountB = (Button) findViewById(R.id.registerButton);
      Button resetPasswordB = (Button) findViewById(R.id.passwordButton);


//		Button testing = (Button) findViewById(R.id.testing);




	   final EditText emailT = (EditText)findViewById(R.id.emailText);
	   final EditText passwordT = (EditText)findViewById(R.id.passwordText);



      signInB.setOnClickListener(new View.OnClickListener() {
       public void onClick(View v) {



		   final String email = emailT.getText().toString().trim();
//		   String email = "bots_64@mailinator.com";
		   final String password = passwordT.getText().toString().trim();

      	 Log.i("clicks",email);
//		   Intent i = new Intent(MainActivity.this, AccountPageActivity.class);
//		   startActivity(i);

		   if(email.matches("") || email.equals("")){
			   Toast.makeText(MainActivity.this,
					   "Email field cannot be empty.", Toast.LENGTH_SHORT).show();
		   }


//		   [a-zA-Z0-9_\.\+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-\.]+
//		   [a-zA-Z0-9._-]+@[a-z]+\.+[a-z]+
//		   [a-zA-Z0-9_\.\+-]+@student.csulb.edu

//		   else if(!email.matches("[a-zA-Z0-9_\\.\\+-]+@student.csulb.edu")){
		   else if(false){
			   Toast.makeText(MainActivity.this,
					   "You must enter a valid CSULB email.", Toast.LENGTH_SHORT).show();
		   }
		   else if(password.matches("") || password.equals("")) {
			   Toast.makeText(MainActivity.this,
					   "Password field cannot be empty.", Toast.LENGTH_SHORT).show();
		   }
		   else {
//			   String[] logInArray = new String[20];
			   /*
			   * Call JSON parsor to fetch/read JSON data ASYNCRONOUSLY
			   *
			   * */
//			   logInArray = readFile("/sdcard/Account.txt");

//			   if (emailT.getText().toString().equals(logInArray[0])
//					   && passwordT.getText().toString().equals(logInArray[1])) {

			   final Firebase authWithPass = new Firebase("https://glowing-inferno-4690.firebaseio.com/");



			   authWithPass.authWithPassword(emailT.getText().toString(), passwordT.getText().toString(), new Firebase.AuthResultHandler() {
				   @Override
				   public void onAuthenticated(AuthData authData) {



//					   authWithPass.addAuthStateListener(new Firebase.AuthStateListener() {
//						   @Override
//						   public void onAuthStateChanged(AuthData authData) {
//							   if (authData != null) {
//								   System.out.println("Insde Second if auth: " + authData.getAuth());
//							   } else {
//								   System.out.println("Inside Second else auth: " + authData.getAuth());
//							   }
//						   }
//					   });

					   System.out.println("Logged In!!");
					   System.out.println("UID: " + authData.getUid());
					   System.out.println("provider: " + authData.getProvider());
					   System.out.println("token: " + authData.getToken());
					   System.out.println("expires: " + authData.getExpires());
					   System.out.println("auth: " + authData.getAuth());






//					   Intent i = new Intent(MainActivity.this, AccountPageActivity.class);
//					   startActivity(i);

					   passwordT.setText("");

					   Intent intent = new Intent(getBaseContext(), AccountPageActivity.class);
//					   intent.putExtra("TOKEN_ID", authData.getToken());
					   intent.putExtra("PASSWORD", password);
					   intent.putExtra("TOKEN", authData.getToken());
					   intent.putExtra("USERID", authData.getUid());
					   intent.putExtra("EMAIL", email);

					   startActivity(intent);

				   }

				   @Override
				   public void onAuthenticationError(FirebaseError firebaseError) {
						System.out.println("ERROR: " + firebaseError);
					   Toast.makeText(MainActivity.this,
						   "The username and password do not match", Toast.LENGTH_LONG).show();
				   }




			   });

			   authWithPass.addAuthStateListener(new Firebase.AuthStateListener() {
				   @Override
				   public void onAuthStateChanged(AuthData authData) {
					   if (authData != null) {
						   mUsername = ((String) authData.getProviderData().get("email"));
						   //findViewById(R.id.emailText).setVisibility(View.INVISIBLE);
					   } else {
						   mUsername = null;
						   //findViewById(R.id.emailText).setVisibility(View.VISIBLE);
					   }
				   }
			   });



//			   Firebase ref = new Firebase("https://glowing-inferno-4690.firebaseio.com/");
//			   ref.addAuthStateListener(new Firebase.AuthStateListener() {
//				   @Override
//				   public void onAuthStateChanged(AuthData authData) {
//					   if (authData != null) {
//						   System.out.println("Insde Second if auth: " + authData.getAuth());
//					   } else {
//						   System.out.println("Inside Second else auth: " + authData.getAuth());
//					   }
//				   }
//			   });

//			   AuthData authData = ref.getAuth();
//			   if (authData != null) {
//				   // user authenticated
//			   } else {
//				   // no user authenticated
//			   }








//			   authWithPass.addAuthStateListener(new Firebase.AuthStateListener() {
//				   @Override
//				   public void onAuthStateChanged(AuthData authData) {
//					   if (authData != null) {
//						   System.out.println("STILL LOGGED IN");
//					   } else {
//						   System.out.println("LOGGED OUT!");
//					   }
//				   }
//			   });



//			   p
		   }

       };
      });
      createAccountB.setOnClickListener(new View.OnClickListener() {
         public void onClick(View v) {
         	Intent i = new Intent(MainActivity.this, CreateAccountActivity.class);
//			 i.putExtra("PASSWORD", pwd);
         	startActivity(i);
         };
      	});
      resetPasswordB.setOnClickListener(new View.OnClickListener() {
         public void onClick(View v) {
         	Intent i = new Intent(MainActivity.this, ResetPasswordActivity.class);
//         	Intent i = new Intent(MainActivity.this, PostAListing.class);
//         	Intent i = new Intent(MainActivity.this, ListingsActivity.class);

         	startActivity(i);
         };
        });





		//temp
//		testing.setOnClickListener(new View.OnClickListener() {
//			public void onClick(View v) {
////         	Intent i = new Intent(MainActivity.this, CreateAccountActivity.class);
//				Intent i = new Intent(MainActivity.this, temp_act.class);
////			 i.putExtra("PASSWORD", pwd);
//				startActivity(i);
//			}
//
//			;
//		});


	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	

}
