package com.metacoders.asthauser;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class Login_activity extends AppCompatActivity {
    String  mail , pass  ;
    EditText mail_inPut , paas_input ;
    Button signiNBtn  , recoverPass , RegisterBtn ;
    FirebaseAuth mauth ;
    FirebaseUser muser ;
    ProgressBar progressBar ;
    private String verificationid;

    private final static  int RC_SIGN_IN =2 ;
    FirebaseAuth.AuthStateListener mAuthListener ;
    SignInButton google_btn ;
    GoogleApiClient mGoogleApiClient ;

    FirebaseUser mUser ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mauth = FirebaseAuth.getInstance();

        try{
            getSupportActionBar().hide();
        }
        catch (NullPointerException e ){
            Toast.makeText(getApplicationContext() , "", Toast.LENGTH_SHORT)
                    .show();


        }



        mail_inPut = findViewById(R.id.Mail_input) ;
        paas_input = findViewById(R.id.phnNumber_input);
        signiNBtn =  findViewById(R.id.Next_btn);
        //recoverPass = findViewById(R.id.SignUPBTN);
        RegisterBtn = findViewById(R.id.Registerbtn);
        progressBar = findViewById(R.id.progressBarLogin);
        progressBar.setVisibility(View.INVISIBLE);

        RegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), RegisterAcitivty.class);
                startActivity(i);
                finish();


            }
        });





        signiNBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                mail = mail_inPut.getText().toString();
                pass = paas_input.getText().toString();

                if(!TextUtils.isEmpty(mail)&& !TextUtils.isEmpty(pass)){

                    signINUser();




                }
                else{
                    progressBar.setVisibility(View.VISIBLE);
                    Toast.makeText(getApplicationContext() , "Please Enter Proper Value ", Toast.LENGTH_SHORT)
                            .show();

                }



            }
        });







    }


    private void signINUser() {

        mauth.signInWithEmailAndPassword(mail , pass )
                .addOnCompleteListener(this , new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){

                            Intent i = new Intent(getApplicationContext(), RegisterAcitivty.class);
                            startActivity(i);
                            finish();



                        }
                        else {

                            progressBar.setVisibility(View.INVISIBLE);
                            Toast.makeText(getApplicationContext() , "Error : UserName & PassWord Does Not Match " ,
                                    Toast.LENGTH_SHORT).show();

                        }



                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(getApplicationContext() , "Error : "+ e.getMessage() ,
                        Toast.LENGTH_SHORT).show();


            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        mauth = FirebaseAuth.getInstance();
        muser = mauth.getCurrentUser();
        if(muser!=null){

            Intent i = new Intent(getApplicationContext(), Home_activity.class);
            startActivity(i);
            finish();



        }


    }
}
