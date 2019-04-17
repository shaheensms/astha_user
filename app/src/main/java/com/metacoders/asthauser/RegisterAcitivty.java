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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

public class RegisterAcitivty extends AppCompatActivity {

    EditText mailinput , passwordfiled;
    Button registbtn ;
    String mail , pass ;
    ProgressBar mbar ;


FirebaseAuth mauth ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userregactivity);
        mailinput = findViewById(R.id.Mail_input);
        passwordfiled = findViewById(R.id.password_field);
        registbtn = findViewById(R.id.register_btn);
        mbar = findViewById(R.id.progressBarRegister);

        mauth = FirebaseAuth.getInstance();



        registbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mbar.setVisibility(View.VISIBLE);

                mail = mailinput.getText().toString();
                pass = passwordfiled.getText().toString();
                if(!TextUtils.isEmpty(mail) && !TextUtils.isEmpty(pass)){

                            registerPerson();

                }
                else {
                    mbar.setVisibility(View.INVISIBLE);

                    Toast.makeText(getApplicationContext() , "Please Enter Something ", Toast.LENGTH_SHORT)
                            .show();
                }






            }
        });


    }

    private void registerPerson() {

        mauth.createUserWithEmailAndPassword(mail , pass )
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){

                            Intent o = new Intent(getApplicationContext(), CreateYourProfile.class);
                            startActivity(o);
                            finish();


                        }
                        else {
                            mbar.setVisibility(View.INVISIBLE);

                            Toast.makeText(getApplicationContext(), "Error : "+task.getException() , Toast.LENGTH_SHORT)
                                    .show();
                        }





                    }
                });


    }
}
