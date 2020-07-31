package com.example.mdevt;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;

public class loginScreen extends AppCompatActivity
{
    TextInputEditText tv1;
    TextInputEditText tv2;
    Button bt, bt1;
    TextView tv;
    ProgressBar pBar;
    SharedPreferences sharedPref;
    String token;


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
        pBar = (ProgressBar) findViewById(R.id.progressBar);
        sharedPref = getSharedPreferences("sharedPref", Context.MODE_PRIVATE);
        token = sharedPref.getString("access_token", "00");

        tokenReq tq= new tokenReq();
        try {
            tq.getToken(this, pBar);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tv1.getText().toString().isEmpty()){
                    tv1.setError("Username Empty");
                    tv1.requestFocus();
                }
                else if (tv2.getText().toString().isEmpty()){
                    tv2.setError("password empty");
                    tv2.requestFocus();
                }
                else{
                    loginApi obj=new loginApi();
                    try {
                        obj.loginUser(loginScreen.this,tv1.getText().toString(),tv2.getText().toString(),token);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
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

