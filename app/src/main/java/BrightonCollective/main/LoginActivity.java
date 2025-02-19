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

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        Button button1 = findViewById(R.id.LoginButton);
        EditText editTextName1 = findViewById(R.id.UserInputEmail);
        EditText editTextName2 = findViewById(R.id.UserInputPassword);
        final String encryptKey = "28747215";

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailInput = editTextName1.getText().toString();
                String passwordInput = editTextName2.getText().toString();


                if(!emailInput.isEmpty() && !passwordInput.isEmpty()) {
                    String encryptedEmail = encrypt(emailInput, encryptKey);
                    String encryptedPass = encrypt(passwordInput, encryptKey);
                    Toast.makeText(LoginActivity.this, "Email: "+ encryptedEmail + "\nPassword: " + encryptedPass, Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(LoginActivity.this, "Incorrect Email or Password!\nTry Again!", Toast.LENGTH_SHORT).show();
                }

                public static String encrypt(String data, String secretKey) {
                    Key key = new SecretKeySpec(secretKey.getBytes(), "Blowfish");
                    Cipher cipher = Cipher.getInstance("Blowfish");
                    cipher.init(Cipher.ENCRYPT_MODE, key);
                    byte[] encrypted = cipher.doFinal(data.getBytes());
                    return Base64.getEncoder().encodeToString(encrypted);
                }

                //loop through database code

            }


        });


        }

    //TODO - Jaydan code login page encrypt password and email for the user to the database
    }
