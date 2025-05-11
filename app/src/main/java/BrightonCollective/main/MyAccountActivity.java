package BrightonCollective.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MyAccountActivity extends AppCompatActivity {

    Button editProfileBtn, settingsBtn, logoutBtn;
    ImageButton homeBtn, searchBtn, messagesBtn, myAccountBtn, backBtn; // Added backBtn

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);

        // Initialize views safely
        editProfileBtn = findViewById(R.id.editProfileBtn);
        settingsBtn = findViewById(R.id.settingsBtn);
        logoutBtn = findViewById(R.id.logoutBtn);
        backBtn = findViewById(R.id.backBtn);  // Initialize the back button

        // Make the back button go to the main landing page (Home Page)
        if (backBtn != null) {
            backBtn.setOnClickListener(v -> {
                // Navigate to the Home Page activity (main landing page)
                Intent intent = new Intent(MyAccountActivity.this, HomePage.class);
                startActivity(intent);
                finish(); // Close current activity after opening the Home Page
            });
        }

        // Other button functionality remains unchanged
        if (editProfileBtn != null) {
            editProfileBtn.setOnClickListener(v -> {
                // Add logic to open edit profile activity
                Toast.makeText(this, "Edit Profile clicked", Toast.LENGTH_SHORT).show();
            });
        }

        if (settingsBtn != null) {
            settingsBtn.setOnClickListener(v -> {
                // Add logic to open settings activity
                Toast.makeText(this, "Settings clicked", Toast.LENGTH_SHORT).show();
            });
        }

        if (logoutBtn != null) {
            logoutBtn.setOnClickListener(v -> {
                // Go back to login activity
                Intent intent = new Intent(MyAccountActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            });
        }

        if (homeBtn != null) {
            homeBtn.setOnClickListener(v -> startActivity(new Intent(this, HomePage.class)));
        }

        if (searchBtn != null) {
            searchBtn.setOnClickListener(v -> startActivity(new Intent(this, SearchActivity.class)));
        }

        if (messagesBtn != null) {
            messagesBtn.setOnClickListener(v -> startActivity(new Intent(this, MessageActivity.class)));
        }

        if (myAccountBtn != null) {
            myAccountBtn.setOnClickListener(v -> recreate()); // Reload current activity
        }

        // If any views are missing, warn the developer
        checkForMissingViews();
    }

    // Override the back button press (both physical and gesture) to navigate to HomePage
    @Override
    public void onBackPressed() {
        // Navigate to the Home Page activity (main landing page)
        Intent intent = new Intent(MyAccountActivity.this, HomePage.class);
        startActivity(intent);
        finish(); // Close current activity after opening the Home Page
    }

    private void checkForMissingViews() {
        if (editProfileBtn == null || settingsBtn == null || logoutBtn == null ||
                homeBtn == null || searchBtn == null || messagesBtn == null || myAccountBtn == null || backBtn == null) {
            Toast.makeText(this, "Warning: One or more buttons are missing from the layout.", Toast.LENGTH_LONG).show();
        }
    }
}
