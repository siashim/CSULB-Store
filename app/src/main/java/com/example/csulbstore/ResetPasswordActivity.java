package com.example.csulbstore;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

public class ResetPasswordActivity extends ActionBarActivity{
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ActionBar actionBar = getActionBar();
		actionBar.hide();
		Firebase.setAndroidContext(this);
		setContentView(R.layout.reset_password);


	}

	public void resetPass(View view) {
		//Intent intent = new Intent(this, DisplayMessageActivity.class);

		final Firebase myFirebaseRef = new Firebase("https://glowing-inferno-4690.firebaseio.com/");

		final EditText resetEmail = (EditText)findViewById(R.id.emailText);

		System.out.println("EMAIL TO RESET: " + resetEmail.getText().toString());

		myFirebaseRef.resetPassword(resetEmail.getText().toString(), new Firebase.ResultHandler() {



			@Override
			public void onSuccess() {

				Toast.makeText(ResetPasswordActivity.this,
						"A new password has been emailed to you.", Toast.LENGTH_LONG).show();
				myFirebaseRef.unauth();
				finish();
			}

			@Override
			public void onError(FirebaseError firebaseError){


				System.out.println("FIREBASE ERROR: " + firebaseError);
				System.out.println("FIREBASE CODE: " + firebaseError.getCode());
				System.out.println("FIREBASE Details: " + firebaseError.getDetails());
				System.out.println("FIREBASE Message: " + firebaseError.getMessage());
				System.out.println("FIREBASE STATIC NAME: " + FirebaseError.INVALID_EMAIL);


				switch (firebaseError.getCode()) {
					case -18:
						Toast.makeText(ResetPasswordActivity.this,
								firebaseError.getMessage() + " Try using a different email or resetting your account.", Toast.LENGTH_LONG).show();
						break;
					case -24:
						Toast.makeText(ResetPasswordActivity.this,
								"We can't log you in, please check your internet connection.", Toast.LENGTH_LONG).show();
						break;
					case -17:
						Toast.makeText(ResetPasswordActivity.this,
								firebaseError.getMessage() + " Create an account! It's free.", Toast.LENGTH_LONG).show();
						break;
					default:
						Toast.makeText(ResetPasswordActivity.this,
								firebaseError.getMessage(), Toast.LENGTH_LONG).show();
						break;
				}



			}


		});

	}

}
