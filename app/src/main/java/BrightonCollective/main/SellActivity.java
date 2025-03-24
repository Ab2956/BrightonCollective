package BrightonCollective.main;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.Firebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.IOError;
import java.io.IOException;

public class SellActivity extends AppCompatActivity {

    // initialise buttons and text
    private EditText title;
    private EditText desc;
    private EditText price;
    private Button uploadBtn, submitBtn;
    private ImageView imageView;

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

    }
    // method to select the image from device
    private void selectPhoto() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent,"Select Image"), pickImageRequest);
    }

    // override to be able to get the result and show it on the screen/imageView
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){

        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == pickImageRequest && resultCode == RESULT_OK
                && data != null
                && data.getData() != null){

            filePath = data.getData();
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

        if (filePath != null) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading Product...");
            progressDialog.show();

            StorageReference reference = storageReference.child("product_images/"
                        + System.currentTimeMillis());

            reference.putFile(filePath).addOnSuccessListener(taskSnapshot ->
                    reference.getDownloadUrl().addOnSuccessListener(uri -> {
                String imageUrl = uri.toString();
                saveProductToDatabase(productTitle, productDesc, productPrice, imageUrl);
                progressDialog.dismiss();
            }));
        }


    }

    // method to save the product to the database
    private void saveProductToDatabase(String title, String desc, String price, String imageUrl){
        FirebaseDatabase fireBaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference productReference = fireBaseDatabase.getReference("products");

        String productId = productReference.push().getKey();

        Product product = new Product(title, desc, imageUrl, Double.parseDouble(price));

        productReference.child(productId).setValue(product).addOnSuccessListener(aVoid -> {
            Toast.makeText(SellActivity.this, "Uploaded Product!", Toast.LENGTH_SHORT).show();
        });
    }
}