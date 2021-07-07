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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class SignUp extends AppCompatActivity {
    private FirebaseAuth mAuth;

    TextInputEditText textInputEditTextFullName, textInputEditTextUserName, textInputEditTextPassword, textInputEditTextmail;
    Button buttonSignUp;
    TextView textViewLogin;
    ProgressBar progressBar;
    String fullname, username, password, email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
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

                    fullname =  String.valueOf(textInputEditTextFullName.getText());
                    username =  String.valueOf(textInputEditTextUserName.getText());
                    password =  String.valueOf(textInputEditTextPassword.getText());
                    email =  String.valueOf(textInputEditTextmail.getText());

                    if (!password.equals("") && !email.equals("")) {


                        //Start ProgressBar first (Set visibility VISIBLE)
//                        progressBar.setVisibility(View.VISIBLE);
//                        Handler handler = new Handler(Looper.getMainLooper());
//                        handler.post(new Runnable() {
//                            @Override
//                            public void run() {
//                                //Starting Write and Read data with URL
//                                //Creating array for parameters
//                                String[] field = new String[4];
//                                field[0] = "fullname";
//                                field[1] = "username";
//                                field[2] = "password";
//                                field[3] = "email";
//                                //Creating array for data
//                                String[] data = new String[4];
//                                data[0] = fullname;
//                                data[1] = username;
//                                data[2] = password;
//                                data[3] = email;
//                                PutData putData =
//                                        new PutData("http://212.142.193.210:15001/german/signup.php", "POST", field, data);
//
//                                if (putData.startPut()) {
//                                    if (putData.onComplete()) {
//                                        progressBar.setVisibility(View.GONE);
//                                        String result = putData.getResult();
//                                        if (result.equals("Sign Up Success")){
//                                            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
//                                            Intent intent = new Intent(getApplicationContext(), Login.class);
//                                            startActivity(intent);
//                                            finish();
//                                        }else{
//                                            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
//                                        }
//                                        //End ProgressBar (Set visibility to GONE)
//                                        Log.i("PutData", result);
//
//                                    }
//                                }
//                                //End Write and Read data with URL
//                            }
//                        });

                        crearUser();
                    }else{
                        Toast.makeText(getApplicationContext(), "todos los campos son requeridos", Toast.LENGTH_LONG).show();
                    }
            }
        });




    }
    public void crearUser(){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    private static final String TAG ="Crear usuario" ;

                    @Override
                    public void onComplete(Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                            Intent intent = new Intent(getApplicationContext(), Login.class);
                            startActivity(intent);
                            finish();
//                            updateUI(user);

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(SignUp.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
//                            updateUI(null);
                        }
                    }
                });
    }
}