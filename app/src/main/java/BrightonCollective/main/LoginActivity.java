package BrightonCollective.main;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    private static final String ENCRYPTION_KEY = "28747215";
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        Button button1 = findViewById(R.id.LoginButton);
        EditText editTextName1 = findViewById(R.id.UserInputEmail);
        EditText editTextName2 = findViewById(R.id.UserInputPassword);


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailInput = editTextName1.getText().toString();
                String passwordInput = editTextName2.getText().toString();


                if (!emailInput.isEmpty() && !passwordInput.isEmpty()) {
                    String encryptedEmail = encrypt(emailInput, ENCRYPTION_KEY);
                    String encryptedPass = encrypt(passwordInput, ENCRYPTION_KEY);
                    Toast.makeText(LoginActivity.this, "Email: " + encryptedEmail + "\nPassword: " + encryptedPass, Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(LoginActivity.this, "Incorrect Email or Password!\nTry Again!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
                private String encrypt(String data, String secretKey) {
                    try {
                        // Generate a key from the given secret key
                        Key key = new SecretKeySpec(secretKey.getBytes(), "Blowfish");

                        // Initialize the cipher with Blowfish encryption
                        Cipher cipher = Cipher.getInstance("Blowfish");
                        cipher.init(Cipher.ENCRYPT_MODE, key);

                        // Encrypt the data
                        byte[] encrypted = cipher.doFinal(data.getBytes());

                        // Return the encrypted data as a Base64 encoded string
                        return android.util.Base64.encodeToString(encrypted, android.util.Base64.DEFAULT);

                    } catch (Exception e) {
                        e.printStackTrace();
                        return null;
                }
            }
    private void signInWithEmailPassword(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Sign-in success
                        Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        // If sign-in fails, show the error message
                        String errorMessage = "Authentication Failed: " + task.getException().getMessage();
                        Toast.makeText(LoginActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
