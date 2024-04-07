package com.example.butterbloom;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.github.clans.fab.FloatingActionButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.checkerframework.checker.nullness.qual.NonNull;

public class UserDetailActivity extends AppCompatActivity {
    TextView txt_dName,txt_dEmail,txt_dPhNo,txt_dAddress;
    FloatingActionButton deleteButton;
    Button btnDelete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        txt_dName = findViewById(R.id.txt_dName);
        txt_dEmail = findViewById(R.id.txt_dEmail);
        txt_dPhNo = findViewById(R.id.txt_dPhNo);
        txt_dAddress = findViewById(R.id.txt_dAddress);
        deleteButton = findViewById(R.id.deleteButton);
        btnDelete =findViewById(R.id.btnDelete);

        Bundle bundle = getIntent().getExtras();
        if(bundle!= null){
            txt_dName.setText(bundle.getString("Name"));
            txt_dEmail.setText(bundle.getString("Email"));
            txt_dPhNo.setText(bundle.getString("Phone Number"));
            txt_dAddress.setText(bundle.getString("Address"));
        }
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dName = txt_dName.getText().toString().trim();
            deleteUserByUsername(dName);
            }
        });
    }
    public void deleteUserByUsername(final String username) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference usersRef = database.getReference("users");

        // Query the database to find the user with the specified username
        Query query = usersRef.orderByChild("Name").equalTo(username);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot userSnapshot: dataSnapshot.getChildren()) {
                    // Get the key of the user to delete
                    String key = userSnapshot.getKey();
                    if (key != null) {
                        // Delete the user with the obtained UID
                        usersRef.child(key).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Log.d("deleteUserByUsername", "User deleted successfully.");
                                } else {
                                    Log.d("deleteUserByUsername", "Failed to delete user.", task.getException());
                                }
                            }
                        });
                    } else {
                        Log.d("deleteUserByUsername", "User key is null, can't delete the user.");
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("deleteUserByUsername", "Database error: " + databaseError.getMessage());
            }
        });
    }
}