package BrightonCollective.main;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import android.util.Base64;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        Button button1 = findViewById(R.id.LoginButton);
        Button createABtn = findViewById(R.id.SignUpButton);
        EditText editTextName1 = findViewById(R.id.UserInputEmail);
        EditText editTextName2 = findViewById(R.id.UserInputPassword);
        final String encryptKey = "28747215";

        createABtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, CreateAccountActivity.class);
                startActivity(intent);
                finish();
            }
        });


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String emailInput = editTextName1.getText().toString();
                String passwordInput = editTextName2.getText().toString();



                if(!emailInput.isEmpty() && !passwordInput.isEmpty()) {

                    String encryptedEmail = encrypt(emailInput, encryptKey);
                    String encryptedPass = encrypt(passwordInput, encryptKey);



                    if (accountIsValid(encryptedEmail, encryptedPass)) {
                        Intent intent = new Intent(LoginActivity.this, HomePage.class);
                        startActivity(intent);
                        finish();

                    }else {
                        Toast.makeText(LoginActivity.this, "Incorrect Email or Password!\nTry Again!", Toast.LENGTH_SHORT).show();
                    }


                } else {
                    Toast.makeText(LoginActivity.this, "Enter password and email!\nTry Again!", Toast.LENGTH_SHORT).show();
                }

                //loop through database code

            }

        });

        }
        // The encryption method for the email and password
        public String encrypt(String data, String secretKey) {
            try {
                Key key = new SecretKeySpec(secretKey.getBytes(), "Blowfish");

                Cipher cipher = Cipher.getInstance("Blowfish");

                cipher.init(Cipher.ENCRYPT_MODE, key);

                byte[] encrypted = cipher.doFinal(data.getBytes());

                return Base64.encodeToString(encrypted, Base64.DEFAULT);

            }catch (Exception e){
                e.printStackTrace();
                return null;
            }
        }

        private boolean accountIsValid(String encryptedEmail, String encryptedPass){

        //TODO needs to be changed to go through database and check if the email and password match

            String validEmail = encrypt("user@gmail.com", "28747215");
            String validPass = encrypt("password", "28747215");
            Log.d("Encrypted", "Encrypted Email: " + encryptedEmail);
            Log.d("Encrypted", "Encrypted Password: " + encryptedPass);


            return encryptedEmail.equals(validEmail) && encryptedPass.equals(validPass);
        }



    }
