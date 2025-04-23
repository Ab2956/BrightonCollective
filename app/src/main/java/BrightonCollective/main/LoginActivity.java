package BrightonCollective.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        Button loginButton = findViewById(R.id.LoginButton);
        Button ForgottenPassword = findViewById(R.id.ForgottenPassword);
        Button signUpButton = findViewById(R.id.SignUpButton);
        EditText emailInput = findViewById(R.id.UserInputEmail);
        EditText passwordInput = findViewById(R.id.UserInputPassword);

        // Sign Up Button - Redirects to Account Creation
        signUpButton.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this, SignUpVerification.class);
            startActivity(intent);
            finish();
        });

        // Forgotten Password Button - Redirects to Forgotten Password Page
        ForgottenPassword.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this, ForgottenPasswordActivity.class);
            startActivity(intent);
            finish();
        });



        // Login Button - Authenticates User
        loginButton.setOnClickListener(v -> {
            String email = emailInput.getText().toString().trim();
            String password = passwordInput.getText().toString().trim();

            if (!email.isEmpty() && !password.isEmpty()) {
                signInWithEmailPassword(email, password);
            } else {
                Toast.makeText(LoginActivity.this, "Enter Email and Password!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Firebase Authentication - Sign In Method
    private void signInWithEmailPassword(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Successful login
                        FirebaseUser user = mAuth.getCurrentUser();
                        if (user != null) {
                            Toast.makeText(LoginActivity.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(LoginActivity.this, HomePage.class));
                            finish();
                        }
                    } else {
                        // Failed login
                        Toast.makeText(LoginActivity.this, "Authentication Failed: " +
                                task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
