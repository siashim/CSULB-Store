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

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.firebase.ui.FirebaseListAdapter;

//import com.example.androidhive.helper.AlertDialogManager;
//import com.example.androidhive.helper.ConnectionDetector;
//import com.example.androidhive.helper.JSONParser;

public class ListingsActivity extends ListActivity {

    // Creating JSON Parser object
    private Firebase mFirebaseRef;
    ListAdapter adapter;
    FirebaseListAdapter<Posts> mListAdapter;
    DataSnapshot data;

    // albums JSON url
//    private static final String URL_ALBUMS = "http://web.csulb.edu/~cmendoza/dat.json";


    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listings);

        Firebase.setAndroidContext(this);
        mFirebaseRef = new Firebase("https://glowing-inferno-4690.firebaseio.com/posts");


//        Button sendButton = (Button) this.findViewById(R.id.send_button);

        // storing string resources into Array
//        String[] adobe_products = getResources().getStringArray(R.array.adobe_products);

        // Binding resources Array to ListAdapter
//        this.setListAdapter(new ArrayAdapter<String>(this, R.layout.albu, R.id.label, adobe_products));


        ActionBar actionBar = getActionBar();
        actionBar.hide();

//        setContentView(R.layout.single_list_view);

        Long tsLong = System.currentTimeMillis()/1000;
        final String ts = tsLong.toString();

//        sendButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String text = "data - " + ts;
//                Messages message = new Messages("Android User - " + ts, text);
//                mFirebaseRef.push().setValue(message);
//            }
//        });

        // Attach an listener to read the data at our posts reference
//        ref.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot snapshot) {
//                System.out.println(snapshot.getValue() + " - " + " asdf");
//            }
//
//            @Override
//            public void onCancelled(FirebaseError firebaseError) {
//            }
//        });

        // storing string resources into Array
//        String[] adobe_products = getResources().getStringArray(R.array.adobe_products);

        // Binding Array to ListAdapter
//        this.setListAdapter(new ArrayAdapter<String>(this, R.layout.activity_listings, R.id.label, adobe_products));


//        authData.
//        mUsername = ((String) authData.getProviderData().get("email"));
//        mFirebaseRef.child("a5a5c551-31d4-44a0-89f5-8036e33c2b53");



        mFirebaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
//                System.out.println(snapshot.getValue());
//                data = (DataSnapshot) snapshot.getChildren();
//                long count = snapshot.getChildrenCount();
                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
//                    Posts post = postSnapshot.getValue(Posts.class);
//                    System.out.println(post.getTitle() + " - " + post.getDescription());
                }
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }
        });




        adapter = new FirebaseListAdapter<Posts>(this, Posts.class, android.R.layout.two_line_list_item, mFirebaseRef.child("10057617-d33b-46e2-a63c-70f19547a1ca")){
//        mListAdapter = new FirebaseListAdapter<Messages>(this, Messages.class,
//                android.R.layout.two_line_list_item, mFirebaseRef) {
            @Override
            protected void populateView(View v, Posts model) {
                ((TextView)v.findViewById(android.R.id.text1)).setText(model.getTitle());
                ((TextView)v.findViewById(android.R.id.text2)).setText(model.getDescription());
            }
        };
        setListAdapter(adapter);

        System.out.println("PASSS");


        // listening to single list item on click
//        adapter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            public void onItemClick(AdapterView<?> parent, View view,
//                                    int position, long id) {
//
//                // selected item
//                String fproduct_label = ((TextView) view).getText().toString();
//
//                // Launching new Activity on selecting single List Item
//                Intent i = new Intent(getApplicationContext(), com.example.csulbstore.SingleListView.class);
//                // sending data to new activity
//                i.putExtra("fproduct_label", fproduct_label);
//                startActivity(i);
//
//            }
//        });
    }

//    @Override
//    public void onListItemClick(ListView l, View v, int position, long id) {
//        String fproduct_label = ((TextView) v).getText().toString();
//
//        // Launching new Activity on selecting single List Item
//        Intent i = new Intent(getApplicationContext(), com.example.csulbstore.SingleListView.class);
//        // sending data to new activity
//        i.putExtra("fproduct_label", fproduct_label);
////        startActivity(i);
//
//        System.out.println("What now?");
//    }


    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        // TODO Auto-generated method stub
        //super.onListItemClick(l, v, position, id);
//        String selection = l.getItemAtPosition(position).toString();
//        Toast.makeText(this, selection, Toast.LENGTH_LONG).show();
        String postTitle = ((TextView) v.findViewById(android.R.id.text1)).getText().toString();
        String postDesc = ((TextView) v.findViewById(android.R.id.text2)).getText().toString();


        Intent i = new Intent(getApplicationContext(), com.example.csulbstore.SingleListView.class);
        // sending data to new activity
        i.putExtra("postTitle", postTitle);
        i.putExtra("postDescription", postDesc);
//        i.putExtra("postEmail", postEmail);
        startActivity(i);

        System.out.println("What now?");
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        setListAdapter(null);
    }












































































    /* @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar actionBar = getActionBar();
        actionBar.hide();

        setContentView(R.layout.activity_listings);

        Button viewItem1 = (Button) findViewById(R.id.viewButton);
        Button viewItem2 = (Button) findViewById(R.id.viewBbutton2);
        final TextView description1 = (TextView) findViewById(R.id.listing1Title);
        final TextView description2 = (TextView) findViewById(R.id.listing2Title);



        viewItem1.setOnClickListener(new android.view.View.OnClickListener() {
            public void onClick(android.view.View v) {
                Intent i = new Intent(ListingsActivity.this, ItemPageActivity.class);
                i.putExtra("TITLE", description1.getText().toString());
                startActivity(i);
            }
        });
        viewItem2.setOnClickListener(new android.view.View.OnClickListener() {
            public void onClick(android.view.View v) {
                Intent i = new Intent(ListingsActivity.this, ItemPageActivity.class);
                i.putExtra("TITLE", description2.getText().toString());
                startActivity(i);
            }
        });
    } */
}
