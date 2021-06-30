package com.germany.loginregister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class SignUp extends AppCompatActivity {


    TextInputEditText textInputEditTextFullName, textInputEditTextUserName, textInputEditTextPassword, textInputEditTextmail;
    Button buttonSignUp;
    TextView textViewLogin;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        textInputEditTextFullName =findViewById(R.id.fullname);
        textInputEditTextUserName =findViewById(R.id.username);
        textInputEditTextPassword =findViewById(R.id.password);
        textInputEditTextmail =findViewById(R.id.email);
        buttonSignUp= findViewById(R.id.buttonSignUp);
        textViewLogin = findViewById(R.id.loginText);

        progressBar = findViewById(R.id.progress);

        textViewLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });
        buttonSignUp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String fullname, username, password, email;
                    fullname =  String.valueOf(textInputEditTextFullName.getText());
                    username =  String.valueOf(textInputEditTextUserName.getText());
                    password =  String.valueOf(textInputEditTextPassword.getText());
                    email =  String.valueOf(textInputEditTextmail.getText());

                    if (!fullname.equals("") && !username.equals("") && !password.equals("") && !email.equals("")) {


                        //Start ProgressBar first (Set visibility VISIBLE)
                        progressBar.setVisibility(View.VISIBLE);
                        Handler handler = new Handler(Looper.getMainLooper());
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                //Starting Write and Read data with URL
                                //Creating array for parameters
                                String[] field = new String[4];
                                field[0] = "fullname";
                                field[1] = "username";
                                field[2] = "password";
                                field[3] = "email";
                                //Creating array for data
                                String[] data = new String[4];
                                data[0] = fullname;
                                data[1] = username;
                                data[2] = password;
                                data[3] = email;
                                PutData putData =
                                        new PutData("http://212.142.193.210:15001/german/signup.php", "POST", field, data);

                                if (putData.startPut()) {
                                    if (putData.onComplete()) {
                                        progressBar.setVisibility(View.GONE);
                                        String result = putData.getResult();
                                        if (result.equals("Sign Up Success")){
                                            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(getApplicationContext(), Login.class);
                                            startActivity(intent);
                                            finish();
                                        }else{
                                            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                        }
                                        //End ProgressBar (Set visibility to GONE)
                                        Log.i("PutData", result);

                                    }
                                }
                                //End Write and Read data with URL
                            }
                        });
                    }else{
                        Toast.makeText(getApplicationContext(), "todos los campos son requeridos", Toast.LENGTH_LONG).show();
                    }
            }
        });




    }
}