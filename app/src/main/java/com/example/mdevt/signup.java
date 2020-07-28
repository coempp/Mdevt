package com.example.mdevt;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

public class signup extends AppCompatActivity {
    Button button,button1;
    TextInputEditText input1;
    TextInputEditText input2;
    TextInputEditText input3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        button=(Button) findViewById(R.id.cancel);
        button1=(Button) findViewById(R.id.next);
        input1=(TextInputEditText) findViewById(R.id.sname);
        input2=(TextInputEditText) findViewById(R.id.spass);
        input3=(TextInputEditText) findViewById(R.id.sconfirm);

        input1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String username=input1.getText().toString();
                    if(username.isEmpty()){
                        button1.setEnabled(false);
                    }else{
                        input2.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                            }

                            @Override
                            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                                String pass=input2.getText().toString();
                                    if (pass.isEmpty()){
                                        button1.setEnabled(false);
                                    }else{
                                        input3.addTextChangedListener(new TextWatcher() {
                                            @Override
                                            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                                            }

                                            @Override
                                            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                                                String confirm=input3.getText().toString();
                                                if(confirm.isEmpty()){
                                                    button1.setEnabled(false);
                                                }else{
                                                    button1.setEnabled(true);
                                                }

                                            }

                                            @Override
                                            public void afterTextChanged(Editable editable) {

                                            }
                                        });

                                    }

                            }

                            @Override
                            public void afterTextChanged(Editable editable) {

                            }
                        });
                    }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), loginScreen.class));

            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(
                        signup.this
                );
                builder.setTitle("Confirm submission!!");
                builder.setMessage("Click confirm to continue");
                builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        AlertDialog.Builder bui = new AlertDialog.Builder(
                                signup.this
                        );
                        bui.setTitle("Sign up Successful. ");
                        bui.setMessage("Click to Login");
                        bui.setPositiveButton("Login", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                startActivity(new Intent(getApplicationContext(), loginScreen.class));

                            }
                        });
                        AlertDialog alertDialog = bui.create();
                        alertDialog.show();

                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }
}