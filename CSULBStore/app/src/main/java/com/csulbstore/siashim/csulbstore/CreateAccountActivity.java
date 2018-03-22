package com.csulbstore.siashim.csulbstore;
import java.io.FileWriter;
import java.io.IOException;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateAccountActivity extends AppCompatActivity {
    String name, lastName, email, password, confirmPassword;
    Account account;

    public void generateNoteOnSD(String sFileName, String sBody) {
        FileWriter fWriter;
        try
        {

            fWriter = new FileWriter("/sdcard/" + sFileName + ".txt");
            fWriter.write(sBody);
            fWriter.flush();
            fWriter.close();
            Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
        }
        catch(IOException e)
        {
            e.printStackTrace();
            e.getMessage();
        }
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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


                                String accountInfo = account.getEmail() + ":" + account.getPassword();
                                generateNoteOnSD("Account", accountInfo);

                                Intent i = new Intent(Intent.ACTION_SEND);
                                i.setType("message/rfc822");
                                i.putExtra(Intent.EXTRA_EMAIL  , new String[]{email});
                                i.putExtra(Intent.EXTRA_SUBJECT, "Verification");
                                i.putExtra(Intent.EXTRA_TEXT   , "This is a verifiction message");
                                try {
                                    startActivity(Intent.createChooser(i, "Send mail..."));
                                } catch (android.content.ActivityNotFoundException ex) {
                                    Toast.makeText(CreateAccountActivity.this,
                                            "There are no email clients installed.",
                                            Toast.LENGTH_LONG).show();
                                }

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

}
