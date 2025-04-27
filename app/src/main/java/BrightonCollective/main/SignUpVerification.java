package BrightonCollective.main;

import android.content.Intent;
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

public class SignUpVerification extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseUser tempUser;
    private String tempEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up_verification);

        // System insets setup
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Firebase auth
        mAuth = FirebaseAuth.getInstance();

        // UI elements
        EditText emailInput = findViewById(R.id.emailEditText);
        Button sendVerificationButton = findViewById(R.id.sendVerificationButton);
        Button verifyButton = findViewById(R.id.verifyButton); //

        // Send verification email
        sendVerificationButton.setOnClickListener(v -> {
            tempEmail = emailInput.getText().toString().trim();

            if (tempEmail.isEmpty()) {
                Toast.makeText(this, "Please enter a university email.", Toast.LENGTH_SHORT).show();
                return;
            }

            String dummyPassword = "Temporary123!"; // Satisfies Firebase

            mAuth.createUserWithEmailAndPassword(tempEmail, dummyPassword)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            tempUser = mAuth.getCurrentUser();
                            if (tempUser != null) {
                                tempUser.sendEmailVerification()
                                        .addOnCompleteListener(verifyTask -> {
                                            if (verifyTask.isSuccessful()) {
                                                Toast.makeText(this, "Verification email sent! Check your inbox.", Toast.LENGTH_SHORT).show();
                                            } else {
                                                Toast.makeText(this, "Failed to send email.", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                            }
                        } else {
                            Toast.makeText(this, "Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        });

        // Check verification status
        verifyButton.setOnClickListener(v -> {
            tempUser = mAuth.getCurrentUser();

            if (tempUser != null) {
                tempUser.reload().addOnCompleteListener(task -> {
                    if (tempUser.isEmailVerified()) {
                        // Delete temp account
                        tempUser.delete().addOnCompleteListener(deleteTask -> {
                            if (deleteTask.isSuccessful()) {
                                // Go to sign up page to add details
                                Intent intent = new Intent(this, SignUp.class);
                                intent.putExtra("universityEmail", tempEmail); // Optional
                                startActivity(intent);
                                finish();
                            } else {
                                Toast.makeText(this, "Couldn't delete temp account.", Toast.LENGTH_SHORT).show();
                            }
                        });
                    } else {
                        Toast.makeText(this, "Email not verified yet.", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}

