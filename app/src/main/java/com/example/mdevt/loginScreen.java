package com.example.mdevt;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

public class loginScreen extends AppCompatActivity
{
    TextInputEditText tv1;
    TextInputEditText tv2;
    Button bt, bt1;
    TextView tv;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        tv = (TextView) findViewById(R.id.forgot);
        tv1 = (TextInputEditText) findViewById(R.id.username);
        tv2 = (TextInputEditText) findViewById(R.id.password);
        bt = (Button) findViewById(R.id.login);
        bt1 = (Button) findViewById(R.id.signup);
        tv.setPaintFlags(tv.getPaintFlags()|Paint.UNDERLINE_TEXT_FLAG);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tv1.getText().toString().equals("0000000000") && tv2.getText().toString().equals("12345")) {
                    AlertDialog.Builder Builder = new AlertDialog.Builder(
                            loginScreen.this
                    );
                    Builder.setIcon(R.drawable.ic);
                    Builder.setTitle("Logged in Successfully !!!");
                    Builder.setMessage("U may proceed");
                    Builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            startActivity(new Intent(getApplicationContext(), loggedin.class));

                        }
                    });
                    AlertDialog alertDialog = Builder.create();
                    alertDialog.show();
                } else if (tv1.getText().toString().isEmpty() || tv2.getText().toString().isEmpty())
                {
                    AlertDialog.Builder Build = new AlertDialog.Builder(
                            loginScreen.this
                    );
                    Build.setIcon(R.drawable.ic1);
                    Build.setTitle("Username or password is empty.");
                    Build.setMessage("Enter your login credentials.");
                    Build.setNegativeButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }

                    });
                    AlertDialog alertDialog = Build.create();
                    alertDialog.show();
                }
                else{
                    AlertDialog.Builder B = new AlertDialog.Builder(
                            loginScreen.this
                    );
                    B.setIcon(R.drawable.ic1);
                    B.setTitle("Incorrect username or password.");
                    B.setMessage("Please check your login credentials.");
                    B.setNegativeButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }

                    });
                    AlertDialog alertDialog = B.create();
                    alertDialog.show();

                }
            }
        });
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), signup.class));


            }
        });

    }

    public void reset_password(View view)
    {
        startActivity(new Intent(getApplicationContext(), forget.class));
    }
}

