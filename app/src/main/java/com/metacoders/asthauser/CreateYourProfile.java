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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateYourProfile extends AppCompatActivity {

    EditText name_in , phone_in ;
    Button nextBtn ;

    String namee , phonee ;
    ProgressBar pbar ;


FirebaseAuth mauth ;
DatabaseReference mref ;
String Uid ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_your_profile);

        mauth = FirebaseAuth.getInstance();
        Uid = mauth.getUid();
        mref = FirebaseDatabase.getInstance().getReference("UsersProfile");


        name_in = findViewById(R.id.name_input);
        phone_in = findViewById(R.id.phone_field);
        nextBtn = findViewById(R.id.Next_btn);
        pbar =findViewById(R.id.progressBarprofile);

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pbar.setVisibility(View.VISIBLE);
                namee = name_in.getText().toString();
                phonee= phone_in.getText().toString();
                if(!TextUtils.isEmpty(namee) && !TextUtils.isEmpty(phonee)){

                   modelForUser model = new modelForUser(namee , phonee, Uid ) ;
                    mref.child(Uid).setValue(model).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            Intent i = new Intent(getApplicationContext()  , Home_activity.class);
                            startActivity(i);
                            finish();

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            pbar.setVisibility(View.INVISIBLE);
                            Toast.makeText(getApplicationContext() , "Error : "+ e.getMessage(), Toast.LENGTH_SHORT)
                                    .show();
                        }
                    });


                }
                else {

                    pbar.setVisibility(View.INVISIBLE);
                    Toast.makeText(getApplicationContext() , "Please Enter Something ", Toast.LENGTH_SHORT)
                            .show();
                }




            }
        });


    }
}
