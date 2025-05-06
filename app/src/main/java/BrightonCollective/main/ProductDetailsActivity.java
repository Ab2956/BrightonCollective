package BrightonCollective.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;

public class ProductDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        // views
        TextView productNameTextView = findViewById(R.id.productName);
        TextView productDescriptionTextView = findViewById(R.id.productDescription);
        ImageView productImageView = findViewById(R.id.productImage);
        TextView productPriceTextView = findViewById(R.id.productPrice);
        ImageButton backBtn = findViewById(R.id.backButton);
        Button buyBtn = findViewById(R.id.buyBtn);
        // product info
        Intent intent = getIntent();
        String productName = intent.getStringExtra("productName");
        String productDescription = intent.getStringExtra("productDescription");
        String productImage = intent.getStringExtra("productImage");
        double productPrice = intent.getDoubleExtra("productPrice", 0.0);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductDetailsActivity.this, HomePage.class);
                startActivity(intent);
                finish();
            }
        });

        buyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BasketItem newItem = new BasketItem(productName, 1, productPrice);
                BasketManager.getInstance().addBasketItem(newItem);
                Toast.makeText(ProductDetailsActivity.this, productName + ": added to basket", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(ProductDetailsActivity.this, HomePage.class);
                startActivity(intent);
                finish();
            }
        });



        // clean up and make ui for the data
        productNameTextView.setText(productName);
        productDescriptionTextView.setText(productDescription);
        productPriceTextView.setText(String.format("£%.2f", productPrice));

        // Load the product image using Glide
        Glide.with(this)
                .load(productImage)
                .into(productImageView);
    }
}
