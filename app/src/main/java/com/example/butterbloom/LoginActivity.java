package com.example.butterbloom;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.airbnb.lottie.L;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.nio.file.attribute.AclFileAttributeView;

public class LoginActivity extends AppCompatActivity {
    Button clickBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        TextView clickButton = findViewById(R.id.txt_click);
        clickButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });
        TextView txtforgetPass = findViewById(R.id.txt_forgetPass);
        txtforgetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,ResPassActivity.class);
                startActivity(intent);
                finish();
            }
        });
        Button loginButton = findViewById(R.id.btnSignIn); // Adjust ID as necessary
        loginButton.setOnClickListener(view -> {
            // Extract data from EditTexts
            String email = ((EditText) findViewById(R.id.txt_LMail)).getText().toString(); // Adjust ID as necessary
            String password = ((EditText) findViewById(R.id.txtLPass)).getText().toString(); // Adjust ID as necessary

            // Call loginUser method
            loginUser(email, password);
            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });

        });
    }
    private void loginUser(String email, String password) {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();

        // Sign in with Firebase Authentication
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, now check if email is verified
                        FirebaseUser currentUser = mAuth.getCurrentUser();
                        if (currentUser != null) {
                            if (!currentUser.isEmailVerified()) {
                                // Email is not verified, display a message
                                Toast.makeText(LoginActivity.this, "Email is not verified. Please check your inbox.", Toast.LENGTH_LONG).show();
                            } else {
                                // Email is verified, now check user role from Realtime Database
                                String userId = currentUser.getUid();
                                mDatabase.child("users").child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        if (dataSnapshot.exists()) {
                                            // Assuming the role is stored under a "role" key
                                            String role = dataSnapshot.child("role").getValue(String.class);
                                            if ("Admin".equals(role)) {
                                                // Navigate to Admin activity
                                                startActivity(new Intent(LoginActivity.this, AdminHomeActivity.class));
                                                finish();
                                            } else if ("Customer".equals(role)) {
                                                // Navigate to Customer activity
                                                startActivity(new Intent(LoginActivity.this, CusHomeActivity.class));
                                                finish();
                                            } else {
                                                // Handle unknown role
                                                Toast.makeText(LoginActivity.this, "Invalid role.", Toast.LENGTH_SHORT).show();
                                            }
                                        } else {
                                            // Handle user not found in database
                                            Toast.makeText(LoginActivity.this, "User role not found in the database.", Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {
                                        // Handle database read error
                                        Toast.makeText(LoginActivity.this, "Database error: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }
                    } else {
                        // If sign in fails, try to determine the reason
                       if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                            // The email address is formatted incorrectly or does not exist
                           Toast.makeText(LoginActivity.this, "User Crendentials Wrong", Toast.LENGTH_SHORT).show();
                        }
                       else
                       {
                           // Other types of errors
                            Toast.makeText(LoginActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    }
