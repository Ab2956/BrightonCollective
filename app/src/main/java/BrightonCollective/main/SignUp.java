package BrightonCollective.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class SignUp extends AppCompatActivity {

    private EditText fullNameInput, emailInput, passwordInput, confirmPasswordInput;
    private RadioGroup universityGroup;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        fullNameInput = findViewById(R.id.UserInputFullName);
        emailInput = findViewById(R.id.UserInputEmail);
        passwordInput = findViewById(R.id.UserInputPassword);
        confirmPasswordInput = findViewById(R.id.UserConfirmPassword);
        universityGroup = findViewById(R.id.universityRadioGroup);
        Button signUpButton = findViewById(R.id.SignUpButton);
        ImageButton backBtn = findViewById(R.id.backButton);


        signUpButton.setOnClickListener(v -> createAccount());

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUp.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void createAccount() {
        String fullName = fullNameInput.getText().toString().trim();
        String email = emailInput.getText().toString().trim();
        String password = passwordInput.getText().toString().trim();
        String confirmPassword = confirmPasswordInput.getText().toString().trim();

        int selectedUniversityId = universityGroup.getCheckedRadioButtonId();
        RadioButton selectedUniversity = findViewById(selectedUniversityId);
        String university = selectedUniversity != null ? selectedUniversity.getText().toString() : "";

        if (fullName.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || university.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!password.equals(confirmPassword)) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        String userId = mAuth.getCurrentUser().getUid();
                        db.collection("users").document(userId).set(
                                new User(fullName, email, university)
                        );

                        Toast.makeText(this, "Account created successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(this, HomePage.class));
                        finish();
                    } else {
                        Toast.makeText(this, "Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public static class User {
        public String fullName, email, university;

        public User() {} // Required for Firestore
        public User(String fullName, String email, String university) {
            this.fullName = fullName;
            this.email = email;
            this.university = university;
        }
    }
}


