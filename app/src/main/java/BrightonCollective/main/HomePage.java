
package BrightonCollective.main;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends AppCompatActivity {

    private DatabaseReference databaseReference;
    private List<Product> productList;
    private GridLayout grid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home_page);

        Button settingsBtn = findViewById(R.id.settingsBtn);        // Buttons
        ImageButton homeBtn = findViewById(R.id.homeBtn);
        Button sellBtn = findViewById(R.id.sellBtn);
        ImageButton searchBtn = findViewById(R.id.searchBtn);
        ImageButton messageBtn = findViewById(R.id.messagesBtn);
        ImageButton myAccountBtn = findViewById(R.id.myAccountBtn);
        ImageButton basketBtn = findViewById(R.id.basketBtn);
        grid = findViewById(R.id.grid_top_view);
        CardView c1 = findViewById(R.id.c1);
        CardView c2 = findViewById(R.id.c2);
        CardView c3 = findViewById(R.id.c3);
        CardView c4 = findViewById(R.id.c4);
        CardView c5 = findViewById(R.id.c5);
        CardView c6 = findViewById(R.id.c6);




        databaseReference = FirebaseDatabase.getInstance().getReference("products");
        productList = new ArrayList<>();




        basketBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BasketActivity.class);
                startActivity(intent);
                finish();
            }
        });

        settingsBtn.setOnClickListener(new View.OnClickListener() {     // Intent for the changing of the page when clicked
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
                startActivity(intent);
                finish();
            }
        });
        messageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MessageActivity.class);
                startActivity(intent);
                finish();
            }
        });
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), HomePage.class);
                startActivity(intent);
                finish();
            }
        });
        sellBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SellActivity.class);
                startActivity(intent);
                finish();
            }
        });
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                startActivity(intent);
                finish();
            }
        });
        myAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MyAccountActivity.class);
                startActivity(intent);
                finish();
            }
        });
        setCardViewClickListener(c1, 0);  // CardView 1
        setCardViewClickListener(c2, 1);  // CardView 2
        setCardViewClickListener(c3, 2);  // CardView 3
        setCardViewClickListener(c4, 3);  // CardView 4
        setCardViewClickListener(c5, 4);  // CardView 5
        setCardViewClickListener(c6, 5);  // CardView 6

        loadDummyProducts();
        loadProductImages();

    }

private void setCardViewClickListener(CardView cardView, int productIndex) {
    cardView.setOnClickListener(view -> {
        if (productList != null && productList.size() > productIndex) {
            Product selectedProduct = productList.get(productIndex);
            Intent intent = new Intent(getApplicationContext(), ProductDetailsActivity.class);
            // Pass the product information to the product detail page
            intent.putExtra("productName", selectedProduct.getName());
            intent.putExtra("productDescription", selectedProduct.getDescription());
            intent.putExtra("productImage", selectedProduct.getImageUrl());
            intent.putExtra("productPrice", selectedProduct.getPrice());
            startActivity(intent);
        } else {
            Toast.makeText(HomePage.this, "Product not available", Toast.LENGTH_SHORT).show();
        }
    });
}

    private void loadDummyProducts() {
        // products added to the list
        productList.add(new Product("Vintage Shirt", "Classic old shirt", "https://images.unsplash.com/photo-1596755094514-f87e34085b2c?q=80&w=1976&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D", 25.00));
        productList.add(new Product("Leather Jacket", "Stylish winter jacket", "https://images.unsplash.com/photo-1551028719-00167b16eac5?q=80&w=1935&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D", 58.00));
        productList.add(new Product("Nike Shoes", "Yellow Nike Shoes", "https://images.unsplash.com/photo-1628253747716-0c4f5c90fdda?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NDZ8fE5pa2UlMjBTaG9lc3xlbnwwfHwwfHx8Mg%3D%3D", 39.99));
        productList.add(new Product("Black Socks", "Cool black Socks", "https://images.unsplash.com/photo-1640026199235-c24aa417b552?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTd8fFNvY2tzJTIwZm9yJTIwc2FsZXxlbnwwfHwwfHx8Mg%3D%3D", 10.50));
        productList.add(new Product("Omega Watch", "Lovely Omega watch", "https://images.unsplash.com/photo-1523170335258-f5ed11844a49?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NHx8d2F0Y2h8ZW58MHx8MHx8fDI%3D", 2000.00));
        productList.add(new Product("Fluffy Coat", "Blue Long Fluffy Coat", "https://images.unsplash.com/photo-1608236159447-2d27116777f3?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8Q29hdCUyMGZvciUyMHNhbGV8ZW58MHx8MHx8fDI%3D", 190.00));



    }
    private void loadProductImages() {
        // array for the image,name and price
        int[] imageViewIds = {
                R.id.productImageView1,
                R.id.productImageView2,
                R.id.productImageView3,
                R.id.productImageView4,
                R.id.productImageView5,
                R.id.productImageView6
        };
        int[] nameIds = {
                R.id.productName1,
                R.id.productName2,
                R.id.productName3,
                R.id.productName4,
                R.id.productName5,
                R.id.productName6

        };
        int[] priceIds = {
                R.id.productPrice1,
                R.id.productPrice2,
                R.id.productPrice3,
                R.id.productPrice4,
                R.id.productPrice5,
                R.id.productPrice6
        };
        // loop through items and display the correct data
        for (int i = 0; i < productList.size() && i < imageViewIds.length; i++) {
            ImageView imageView = findViewById(imageViewIds[i]);
            TextView price = findViewById(priceIds[i]);
            TextView name = findViewById(nameIds[i]);

            Product product = productList.get(i);
            if (imageView != null) {
                Glide.with(this)
                        .load(productList.get(i).getImageUrl())
                        .into(imageView);
            }
            // Load name
            if (name != null) {
                name.setText(product.getName());
            }

            // Load price
            if (price != null) {
                price.setText("£" + String.format("%.2f", product.getPrice()));
            }

            }
        }
    }
