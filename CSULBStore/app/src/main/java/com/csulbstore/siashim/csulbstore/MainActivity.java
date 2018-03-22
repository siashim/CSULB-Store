package com.csulbstore.siashim.csulbstore;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button signInB = (Button) findViewById(R.id.signInButton);
        Button createAccountB = (Button) findViewById(R.id.registerButton);
        Button resetPasswordB = (Button) findViewById(R.id.passwordButton);
        final EditText emailT = (EditText)findViewById(R.id.emailText);
        final EditText passwordT = (EditText)findViewById(R.id.passwordText);



        signInB.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String email = emailT.getText().toString().trim();
                String password = passwordT.getText().toString().trim();
                Intent i = new Intent(MainActivity.this, AccountPageActivity.class);
                startActivity(i);

                /*
                Log.i("clicks",email);

                if(email.matches("") || email.equals("")){
                    Toast.makeText(MainActivity.this,
                            "Email field cannot be empty.", Toast.LENGTH_SHORT).show();
                }
                else if(!email.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")){
                    Toast.makeText(MainActivity.this,
                            "You must enter a valid email address.", Toast.LENGTH_SHORT).show();
                }
                else if(password.matches("") || password.equals("")) {
                    Toast.makeText(MainActivity.this,
                            "Password field cannot be empty.", Toast.LENGTH_SHORT).show();
                }
                else {
                    String[] logInArray = new String[20];
                    logInArray = readFile("/sdcard/Account.txt");

                    if (emailT.getText().toString().equals(logInArray[0])
                            && passwordT.getText().toString().equals(logInArray[1])) {
                        Intent i = new Intent(MainActivity.this, AccountPageActivity.class);
                        startActivity(i);
                    } else {
                        Toast.makeText(MainActivity.this,
                                "The username and password do not match", Toast.LENGTH_LONG).show();
                    }
                }
                */

            };
        });
        createAccountB.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, CreateAccountActivity.class);
                startActivity(i);
            };
        });
        resetPasswordB.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ResetPasswordActivity.class);
                startActivity(i);
            };
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
