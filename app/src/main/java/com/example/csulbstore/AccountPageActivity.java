package com.example.csulbstore;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.firebase.client.Firebase;

public class AccountPageActivity extends ActionBarActivity{

	Firebase myFirebaseRef;
//	Intent loginIntent = getIntent();


	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@Override

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		ActionBar actionBar = getActionBar();
		actionBar.hide();

//		Firebase.setAndroidContext(this);
		setContentView(R.layout.account_page);

		Button accountInfo = (Button) findViewById(R.id.updateaccntBtn);
		Button allListings = (Button) findViewById(R.id.viewListingsBtn);
		Button mylistings = (Button) findViewById(R.id.viewMyListingsBtn);
		Button postListing = (Button) findViewById(R.id.postNewListingsBtn);
		Button logout = (Button) findViewById(R.id.logout);

		Intent intent = getIntent();
		final String pwd = intent.getStringExtra("PASSWORD");
		final String token = intent.getStringExtra("TOKEN");
		final String email = intent.getStringExtra("EMAIL");
		final String userid = intent.getStringExtra("USERID");
		myFirebaseRef = new Firebase("https://glowing-inferno-4690.firebaseio.com/");

		accountInfo.setOnClickListener(new android.view.View.OnClickListener() {
			public void onClick(android.view.View v) {

				Intent i = new Intent(AccountPageActivity.this, AccountInfoActivity.class);
				i.putExtra("PASSWORD", pwd);
				i.putExtra("TOKEN", token);
				i.putExtra("EMAIL", email);
				startActivity(i);
			}
		});

		allListings.setOnClickListener(new android.view.View.OnClickListener() {
			public void onClick(android.view.View v) {
				Intent i = new Intent(AccountPageActivity.this, ListingsActivity.class);
				i.putExtra("TOKEN", token);
				startActivity(i);
			}
		});


		mylistings.setOnClickListener(new android.view.View.OnClickListener() {
			public void onClick(android.view.View v) {
				Intent i = new Intent(AccountPageActivity.this, MyListingsActivity.class);
				i.putExtra("TOKEN", token);
				i.putExtra("USERID", userid);
				startActivity(i);
			}
		});


		postListing.setOnClickListener(
			new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent i = new Intent(AccountPageActivity.this, PostAListing.class);
					i.putExtra("PASSWORD", pwd);
					i.putExtra("TOKEN", token);
					i.putExtra("EMAIL", email);
					startActivity(i);
				}
			});



		logout.setOnClickListener(new android.view.View.OnClickListener() {
			public void onClick(android.view.View v) {
//				myFirebaseRef.unauth();
				Toast.makeText(AccountPageActivity.this,
						"You've successfully logged off! Come back soon.", Toast.LENGTH_SHORT).show();
				myFirebaseRef.unauth();
				finish();
			}
		});

	}

}
