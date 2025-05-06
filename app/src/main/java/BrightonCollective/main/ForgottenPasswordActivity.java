package BrightonCollective.main;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import android.widget.ImageButton;
import android.widget.Toast;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.firebase.auth.FirebaseAuth;

public class ForgottenPasswordActivity extends AppCompatActivity {
    private EditText resetEmailInput;
    private Button sendResetButton;
    private FirebaseAuth mAuth;
    private ImageButton backBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_forgotten_password);


       //UI elements
        resetEmailInput = findViewById(R.id.UserInputResetEmail);
        sendResetButton = findViewById(R.id.sendPasswordResetEmail);
        mAuth = FirebaseAuth.getInstance();
        backBtn = findViewById(R.id.backButton);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ForgottenPasswordActivity.this, HomePage.class);
                startActivity(intent);
                finish();
            }
        });

        sendResetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = resetEmailInput.getText().toString().trim();

                if (email.isEmpty()) {
                    Toast.makeText(ForgottenPasswordActivity.this, "Please enter your email", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.sendPasswordResetEmail(email)
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                Toast.makeText(ForgottenPasswordActivity.this,
                                        "Password reset email sent!", Toast.LENGTH_LONG).show();
                                // Redirect to login activity after delay
                      new Handler().postDelayed(() -> {
                            Intent intent = new Intent(ForgottenPasswordActivity.this, LoginActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                      }, 1500);

                            } else {
                                Toast.makeText(ForgottenPasswordActivity.this,
                                        "Error: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });
            }
        });
    }
}
