package BrightonCollective.main;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.Firebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.ByteArrayOutputStream;
import java.io.IOError;
import java.io.IOException;

public class SellActivity extends AppCompatActivity {

    // initialise buttons and text
    private EditText title;
    private EditText desc;
    private EditText price;
    private Button uploadBtn, submitBtn;
    private ImageView imageView;

    private ImageButton backBtn;
    // pick image for the selectPhoto method
    private final int pickImageRequest = 22;
    private Uri filePath;

    // firebase storage
    FirebaseStorage firebaseStorage;
    StorageReference storageReference;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sell);

        // find views
        title = findViewById(R.id.titleOfProduct);
        desc = findViewById(R.id.descriptionOfProduct);
        price = findViewById(R.id.priceOfProduct);
        uploadBtn = findViewById(R.id.uploadPhoto);
        submitBtn = findViewById(R.id.submitButton);
        imageView = findViewById(R.id.imgView);
        backBtn = findViewById(R.id.backButton);

        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference();

        uploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectPhoto();
            }
        });

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadProductDetails();

            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SellActivity.this, HomePage.class);
                startActivity(intent);
                finish();
            }
        });

    }
    // method to select the image from device
    private void selectPhoto() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent,"Select Image"), pickImageRequest);
    }

    // override to be able to get the result
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){

        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == pickImageRequest && resultCode == RESULT_OK
                && data != null
                && data.getData() != null){

            filePath = data.getData();
            Log.d("File Path", "File Path: " + filePath);
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),filePath);
                imageView.setImageBitmap(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private void uploadProductDetails(){

        String productTitle = title.getText().toString().trim();
        String productDesc = desc.getText().toString().trim();
        String productPrice = price.getText().toString().trim();

        if (productTitle.isEmpty() || productDesc.isEmpty() || productPrice.isEmpty()) {
            Toast.makeText(SellActivity.this, "Please fill in all the fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Convert price to a double
        double priceValue = 0.0;
        try {
            priceValue = Double.parseDouble(productPrice);
        } catch (NumberFormatException e) {
            Toast.makeText(SellActivity.this, "Invalid price format", Toast.LENGTH_SHORT).show();
            return;
        }

        if (filePath != null) {
            // Convert image to Bitmap
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);


                String imageBase64 = encodeImageToBase64(bitmap);

                // Save the product to the database (with Base64 image)
                saveProductToDatabase(productTitle, productDesc, String.valueOf(priceValue), imageBase64);
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(SellActivity.this, "Failed to convert image", Toast.LENGTH_SHORT).show();
            }

        }
    }

    // method to save the product to the database
    private void saveProductToDatabase(String title, String desc, String price, String imageUrl) {
        FirebaseDatabase fireBaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference productReference = fireBaseDatabase.getReference("products");

        String productId = productReference.push().getKey();

        Product product = new Product(title, desc, imageUrl, Double.parseDouble(price));

        productReference.child(productId).setValue(product).addOnSuccessListener(aVoid -> {
            Toast.makeText(SellActivity.this, "Uploaded Product!", Toast.LENGTH_SHORT).show();

        }).addOnSuccessListener(aVoid -> {
                    Toast.makeText(SellActivity.this, "Product Uploaded Successfully!", Toast.LENGTH_SHORT).show();
                    // Optionally redirect to home page after success
                    Intent intent = new Intent(SellActivity.this, HomePage.class);
                    startActivity(intent);
                    finish();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(SellActivity.this, "Failed to upload product: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });;
    }
    private String encodeImageToBase64(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(byteArray, Base64.DEFAULT);
    }
}