package com.example.butterbloom;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        Button createButton = findViewById(R.id.btn_create);
        createButton.setOnClickListener(view -> {
            // Extract data from EditTexts
            String email = ((EditText) findViewById(R.id.txt_email)).getText().toString();
            String password = ((EditText) findViewById(R.id.txt_password)).getText().toString();
            String name = ((EditText) findViewById(R.id.txt_name)).getText().toString();
            String phoneNumber = ((EditText) findViewById(R.id.txt_phNo)).getText().toString();
            String address = ((EditText) findViewById(R.id.txt_address)).getText().toString();

            // Call registerUser method
            registerUser(email, password, name, phoneNumber, address);
        });





        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    private void registerUser(String email, String password, String name, String phoneNumber, String address) {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();

        // Creating a new user in Firebase Authentication
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Send verification email
                        FirebaseUser user = mAuth.getCurrentUser();
                        if (user != null) {
                            user.sendEmailVerification()
                                    .addOnCompleteListener(task1 -> {
                                        if (task1.isSuccessful()) {
                                            Toast.makeText(RegisterActivity.this, "Verification email sent.", Toast.LENGTH_SHORT).show();
                                        }
                                    });

                            // Add user details to Firebase Realtime Database with "Customer" role
                            Map<String, Object> userDetails = new HashMap<>();
                            userDetails.put("Name", name);
                            userDetails.put("Email",email);
                            userDetails.put("PhoneNumber", phoneNumber);
                            userDetails.put("Address", address);
                            userDetails.put("Role", "Customer");

                            mDatabase.child("users").child(user.getUid()).setValue(userDetails)
                                    .addOnCompleteListener(task12 -> {
                                        if (task12.isSuccessful()) {
                                            Toast.makeText(RegisterActivity.this, "User registered successfully.", Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(RegisterActivity.this, "Failed to register user details.", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                        }
                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(RegisterActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}

