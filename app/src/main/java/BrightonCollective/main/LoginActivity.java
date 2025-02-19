package BrightonCollective.main;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        Button button1 = findViewById(R.id.LoginButton);
        EditText editTextName1 = findViewById(R.id.UserInputEmail);
        EditText editTextName2 = findViewById(R.id.UserInputPassword);
        final String key = "28747215";

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailInput = editTextName1.getText().toString();
                String passwordInput = editTextName2.getText().toString();

                if(!emailInput.isEmpty()) {
                    try {
                        String encryptedEmail = encrypt(emailInput, key);

                    }
                }
            }

        });


        }

        //TODO - Jaydan code login page encrypt password and email for the user to the database
    }
