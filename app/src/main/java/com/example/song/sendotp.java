package com.example.song;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;

public class sendotp extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sendotp);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        final EditText inputMobile = findViewById(R.id.inputMobile);
         Button send_otp = findViewById(R.id.send_otp);

         final ProgressBar progressBar=findViewById(R.id.progressBar);

        send_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(inputMobile.getText().toString().isEmpty()){
                    Toast.makeText(sendotp.this, "Enter mobile no.", Toast.LENGTH_SHORT).show();
                        return;
                }
                progressBar.setVisibility(View.VISIBLE);
                send_otp.setVisibility(View.INVISIBLE);

                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+91" + inputMobile.getText().toString(),
                        60,
                        TimeUnit.SECONDS,
                        sendotp.this,
                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks(){

                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                progressBar.setVisibility(View.GONE);
                                send_otp.setVisibility(View.VISIBLE);
                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                progressBar.setVisibility(View.GONE);
                                send_otp.setVisibility(View.VISIBLE);
                                Toast.makeText(sendotp.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCodeSent(@NonNull String verificationID, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {

                            progressBar.setVisibility(View.GONE);
                            send_otp.setVisibility(View.VISIBLE);

                                Intent intent = new Intent(getApplicationContext(),verifyotp.class);
                                intent.putExtra("mobile",inputMobile.getText().toString());
                                intent.putExtra("verificationID",verificationID);
                                startActivity(intent);
                            }
                        }
                );

            }
        });



    }
}