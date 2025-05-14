package BrightonCollective.main;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class MyAccountActivity extends AppCompatActivity {

    Button editProfileBtn, settingsBtn, logoutBtn;
    ImageButton homeBtn, searchBtn, messagesBtn, myAccountBtn, backBtn;
    TextView userName;
    ImageView profileImage;

    private ActivityResultLauncher<PickVisualMediaRequest> pickMedia;
    private ActivityResultLauncher<Intent> legacyImagePickerLauncher;

    private SharedPreferences sharedPreferences;
    private static final String PREFS_NAME = "MyAccountPrefs";
    private static final String KEY_NAME = "user_name";
    private static final String KEY_IMAGE_URI = "profile_image_uri";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(android.graphics.Color.TRANSPARENT);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            getWindow().setDecorFitsSystemWindows(false);
        } else {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            );
        }
        setContentView(R.layout.activity_my_account);

        sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        // Initialize views
        editProfileBtn = findViewById(R.id.editProfileBtn);
        settingsBtn = findViewById(R.id.settingsBtn);
        logoutBtn = findViewById(R.id.logoutBtn);
        backBtn = findViewById(R.id.backBtn);
        userName = findViewById(R.id.userName);
        profileImage = findViewById(R.id.profileImage);
        backBtn = findViewById(R.id.backBtn);


        // Load saved data
        loadUserData();

        // Android 13+ photo picker
        pickMedia = registerForActivityResult(new ActivityResultContracts.PickVisualMedia(), uri -> {
            if (uri != null) {
                profileImage.setImageURI(uri);
                saveProfileImageUri(uri.toString());
            } else {
                Toast.makeText(this, "No image selected", Toast.LENGTH_SHORT).show();
            }
        });

        // Legacy fallback picker
        legacyImagePickerLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        Uri imageUri = result.getData().getData();
                        try {
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                            profileImage.setImageBitmap(bitmap);
                            saveProfileImageUri(imageUri.toString());
                        } catch (IOException e) {
                            e.printStackTrace();
                            Toast.makeText(this, "Failed to load image.", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );

        // Image click
        profileImage.setOnClickListener(v -> openImagePicker());

        if (backBtn != null) {
            backBtn.setOnClickListener(v -> {
                startActivity(new Intent(MyAccountActivity.this, HomePage.class));
                finish();
            });
        }

        if (editProfileBtn != null) {
            editProfileBtn.setOnClickListener(v -> showEditProfileDialog());
        }

        if (settingsBtn != null) {
            settingsBtn.setOnClickListener(v -> startActivity(new Intent(this, SettingsActivity.class)));
        }

        if (logoutBtn != null) {
            logoutBtn.setOnClickListener(v -> {
                startActivity(new Intent(this, LoginActivity.class));
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
            myAccountBtn.setOnClickListener(v -> recreate());
        }
    }

    private void openImagePicker() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            pickMedia.launch(new PickVisualMediaRequest.Builder()
                    .setMediaType(ActivityResultContracts.PickVisualMedia.ImageOnly.INSTANCE)
                    .build());
        } else {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            legacyImagePickerLauncher.launch(intent);
        }
    }

    private void showEditProfileDialog() {
        final EditText input = new EditText(this);
        input.setText(userName.getText());

        new AlertDialog.Builder(this, R.style.AlertDialogTheme)
                .setTitle("Edit Profile Name")
                .setMessage("Enter your new name:")
                .setView(input)
                .setPositiveButton("OK", (dialog, which) -> {
                    String newName = input.getText().toString().trim();
                    if (!newName.isEmpty()) {
                        userName.setText(newName);
                        saveUserName(newName);
                        Toast.makeText(this, "Name updated!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Name cannot be empty.", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancel", (dialog, which) -> dialog.cancel())
                .show();
    }

    private void loadUserData() {
        String savedName = sharedPreferences.getString(KEY_NAME, "");
        String savedImageUri = sharedPreferences.getString(KEY_IMAGE_URI, "");

        if (!savedName.isEmpty()) {
            userName.setText(savedName);
        }

        if (!savedImageUri.isEmpty()) {
            Uri uri = Uri.parse(savedImageUri);
            profileImage.setImageURI(uri);
        }
    }

    private void saveUserName(String name) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_NAME, name);
        editor.apply();
    }

    private void saveProfileImageUri(String uriString) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_IMAGE_URI, uriString);
        editor.apply();
    }


    public void backBtn() {
        startActivity(new Intent(MyAccountActivity.this, HomePage.class));
        finish();
    }
}
