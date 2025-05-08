package BrightonCollective.main;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SearchActivity extends AppCompatActivity {
    //private DatabaseReference databaseRef;
    private LinearLayout layout2;
    //private ExecutorService executorService;
    private ImageButton backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_search);


        ImageButton search_button = findViewById(R.id.SearchButton);

        //layout2 = findViewById(R.id.layoutStructure);
        //databaseRef = FirebaseDatabase.getInstance().getReference("products");
        //executorService = Executors.newFixedThreadPool(4);
        backBtn = findViewById(R.id.backButton);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchActivity.this, HomePage.class);
                startActivity(intent);
                finish();
            }
        });

        //fetchProductsFromDatabase();

        List<Product> product_list = new ArrayList<>();
        product_list.add(new Product(
                "jbl headphones",
                "Noise-canceling Wireless Headphones",
                "https://images.unsplash.com/photo-1619296794093-3df1ae6819a8?q=80&w=1925&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                39.99
        ));
        product_list.add(new Product(
                "macbook air",
                "Lightweight and Great for Note-taking",
                "https://images.unsplash.com/photo-1717865499857-ec35ce6e65fa?w=600&auto=format&fit=crop&q=60&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8bWFjYm9vayUyMGFpcnxlbnwwfHwwfHx8MA%3D%3D",
                349.99
        ));
        product_list.add(new Product(
                "sunglasses",
                "Rayban's Sunglasses",
                "https://images.unsplash.com/photo-1621331122533-465bdcfa6e01?w=600&auto=format&fit=crop&q=60&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Nnx8cmF5YmFuJTIwc3VuZ2xhc3Nlc3xlbnwwfHwwfHx8MA%3D%3D",
                94.99
        ));
        product_list.add(new Product(
                "t-shirt",
                "Uniqlo Plain White T-Shirt",
                "https://images.unsplash.com/photo-1722310752951-4d459d28c678?q=80&w=1964&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                24.99
        ));
        product_list.add(new Product(
                "smart clock",
                "Digital LED Clock",
                "https://images.unsplash.com/photo-1571251455684-2eb131fdb294?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                25.99
        ));
        product_list.add(new Product(
                "desk lamp",
                "Adjustable brightness lamp ",
                "https://images.unsplash.com/photo-1621447980929-6638614633c8?w=600&auto=format&fit=crop&q=60&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8ZGVzayUyMGxhbXB8ZW58MHx8MHx8fDA%3D",
                34.99
        ));
        product_list.add(new Product(
                "backpack",
                "Waterproof laptop backpack with USB charging port",
                "https://images.unsplash.com/photo-1553062407-98eeb64c6a62?w=600&auto=format&fit=crop&q=60&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8YmFja3BhY2t8ZW58MHx8MHx8fDA%3D",
                31.99
        ));
        product_list.add(new Product(
                "bluetooth speaker",
                "Portable speaker for music and podcasts",
                "https://images.unsplash.com/photo-1507878566509-a0dbe19677a5?w=600&auto=format&fit=crop&q=60&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTh8fGJsdWV0b290aCUyMHNwZWFrZXJ8ZW58MHx8MHx8fDA%3D",
                55.49
        ));
        product_list.add(new Product(
                "desk fan",
                "USB-powered mini fan for dorm or library desk",
                "https://images.unsplash.com/photo-1665298455913-dd43714f5ad1?w=600&auto=format&fit=crop&q=60&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MjR8fHNtYWxsJTIwZGVzayUyMGZhbnxlbnwwfHwwfHx8Mg%3D%3D",
                14.99
        ));
        product_list.add(new Product(
                "camera",
                "Capture HD Photos and Videos",
                "https://images.unsplash.com/photo-1452780212940-6f5c0d14d848?w=600&auto=format&fit=crop&q=60&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTd8fGNhbWVyYXxlbnwwfHwwfHx8Mg%3D%3D",
                49.99
        ));

        GridLayout gridLayout = findViewById(R.id.grid_layout);
        gridLayout.setColumnCount(2);

        for (Product product : product_list) {
            // Create CardView
            CardView cardView = new CardView(this);
            cardView.setRadius(20);
            cardView.setUseCompatPadding(true);

            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.width = 0;
            params.height = GridLayout.LayoutParams.WRAP_CONTENT;
            params.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f);
            params.setMargins(16, 16, 16, 16);
            cardView.setLayoutParams(params);

            // Create vertical layout
            LinearLayout linearLayout = new LinearLayout(this);
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            linearLayout.setPadding(16, 16, 16, 16);
            linearLayout.setGravity(Gravity.CENTER_HORIZONTAL);

            // ImageView for product image
            ImageView imageView = new ImageView(this);
            LinearLayout.LayoutParams imgParams = new LinearLayout.LayoutParams(200, 200);
            imageView.setLayoutParams(imgParams);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            Picasso.get().load(product.getImageUrl()).into(imageView); // load image from URL

            // TextView for product name
            TextView nameText = new TextView(this);
            nameText.setText(product.getName());
            nameText.setTextColor(Color.BLACK);
            nameText.setTextSize(16);
            nameText.setGravity(Gravity.CENTER);

            // TextView for product price
            TextView priceText = new TextView(this);
            priceText.setText("£" + String.format("%.2f", product.getPrice()));
            priceText.setTextSize(14);
            priceText.setTextColor(Color.BLACK);
            priceText.setGravity(Gravity.CENTER);

            // Add views to layout
            linearLayout.addView(imageView);
            linearLayout.addView(nameText);
            linearLayout.addView(priceText);

            // Add layout to card and card to grid
            cardView.addView(linearLayout);
            gridLayout.addView(cardView);

            // Set OnClickListener to open ProductDetailsActivity
            cardView.setOnClickListener(v -> {
                Intent intent = new Intent(this, ProductDetailsActivity.class);
                intent.putExtra("productName", product.getName());
                intent.putExtra("productDescription", product.getDescription());
                intent.putExtra("productPrice", product.getPrice());
                intent.putExtra("productImage", product.getImageUrl());
                startActivity(intent);
            });

        }

        search_button.setOnClickListener(v -> {
            search_product(product_list);
        });
    }

    private void search_product(List<Product> product_list) {
        EditText search_input = findViewById(R.id.SearchInput);
        GridLayout gridLayout = findViewById(R.id.grid_layout);
        TextView results = findViewById(R.id.results);
        gridLayout.removeAllViews();  // Clear grid before adding new results

        String search_term = search_input.getText().toString().trim().toLowerCase();  // ensure lowercase comparison

        boolean productFound = false;  // Flag to track if a product was found

        // First, set the results message to be empty
        results.setText("");  // Clear any previous results text

        for (int i = 0; i < product_list.size(); i++) {
            // Compare the search term to the product name (case-insensitive)
            if (product_list.get(i).getName().toLowerCase().contains(search_term)) {

                if (!productFound) {
                    results.setText("Your Results:");
                    productFound = true;
                }

                // Create CardView for the matching product
                CardView cardView = new CardView(this);
                cardView.setRadius(16);
                cardView.setCardElevation(8);
                cardView.setUseCompatPadding(true);

                if (cardView.getParent() != null) {
                    ((ViewGroup) cardView.getParent()).removeView(cardView);
                }

                GridLayout.LayoutParams params = new GridLayout.LayoutParams();
                params.width = 0;
                params.height = GridLayout.LayoutParams.WRAP_CONTENT;
                params.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f);
                params.setMargins(16, 16, 16, 16);
                cardView.setLayoutParams(params);

                // Create vertical layout for product content
                LinearLayout linearLayout = new LinearLayout(this);
                linearLayout.setOrientation(LinearLayout.VERTICAL);
                linearLayout.setPadding(16, 16, 16, 16);
                linearLayout.setGravity(Gravity.CENTER_HORIZONTAL);

                // ImageView for product image
                ImageView imageView = new ImageView(this);
                LinearLayout.LayoutParams imgParams = new LinearLayout.LayoutParams(200, 200);
                imageView.setLayoutParams(imgParams);
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                Picasso.get().load(product_list.get(i).getImageUrl()).into(imageView); // load image from URL

                // TextView for product name
                TextView nameText = new TextView(this);
                nameText.setText(product_list.get(i).getName());
                nameText.setTextSize(16);
                nameText.setGravity(Gravity.CENTER);

                // TextView for product price
                TextView priceText = new TextView(this);
                priceText.setText("£" + String.format("%.2f", product_list.get(i).getPrice()));
                priceText.setTextSize(14);
                priceText.setGravity(Gravity.CENTER);
                priceText.setTextColor(Color.GRAY);

                // Add views to the vertical layout
                linearLayout.addView(imageView);
                linearLayout.addView(nameText);
                linearLayout.addView(priceText);

                // Add the layout to the card and card to the grid layout
                cardView.addView(linearLayout);
                gridLayout.addView(cardView);

                // Set OnClickListener to open ProductDetailActivity
                int finalI = i;
                cardView.setOnClickListener(v1 -> {
                    Intent intent = new Intent(this, ProductDetailsActivity.class);
                    intent.putExtra("productName", product_list.get(finalI).getName());
                    intent.putExtra("productDescription", product_list.get(finalI).getDescription());
                    intent.putExtra("productPrice", product_list.get(finalI).getPrice());
                    intent.putExtra("productImage", product_list.get(finalI).getImageUrl());
                    startActivity(intent);
                });
            }
        }

        // If no products matched the search term
        if (!productFound) {
            results.setText("Product Not Found!!");
        }
    }


    //private void searchProductByName(String query) {
        //layout2.removeAllViews();

        //databaseRef.orderByChild("name").equalTo(query).addListenerForSingleValueEvent(new ValueEventListener() {
            //@Override
            //public void onDataChange(@NonNull DataSnapshot snapshot) {
                //if (snapshot.exists()) {
                    //for (DataSnapshot productSnapshot : snapshot.getChildren()) {
                        //Product product = productSnapshot.getValue(Product.class);
                        //if (product != null) {
                            //downloadImageAndCreateButton(product);
                        //}
                    //}
                //} else {
                    //Toast.makeText(SearchActivity.this, "No product found", Toast.LENGTH_SHORT).show();
                //}
            //}

            //@Override
            //public void onCancelled(@NonNull DatabaseError error) {
                //Toast.makeText(SearchActivity.this, "Search failed", Toast.LENGTH_SHORT).show();
            //}
        //});
    //}

    //private void fetchProductsFromDatabase() {
        //databaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
            //@Override
            //public void onDataChange(@NonNull DataSnapshot snapshot) {
                //for (DataSnapshot productSnapshot : snapshot.getChildren()) {
                    //Product product = productSnapshot.getValue(Product.class);
                    //if (product != null && product.getImageUrl() != null) {
                        //downloadImageAndCreateButton(product);
                    //}
                //}
            //}

            //@Override
            //public void onCancelled(@NonNull DatabaseError error) {
                //Toast.makeText(SearchActivity.this, "Failed to fetch data", Toast.LENGTH_SHORT).show();
            //}
        //});
    //}



    //private void downloadImageAndCreateButton(Product product) {
        //executorService.execute(() -> {
            //try {
                //URL url = new URL(product.getImageUrl());
               // HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                //connection.setDoInput(true);
                //connection.connect();
                //InputStream input = connection.getInputStream();
                //Bitmap bitmap = BitmapFactory.decodeStream(input);

                //runOnUiThread(() -> {
                    //ImageButton imageButton = new ImageButton(SearchActivity.this);
                    //imageButton.setImageBitmap(bitmap);
                    //imageButton.setBackgroundColor(android.graphics.Color.TRANSPARENT);

                    //LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                            //ViewGroup.LayoutParams.WRAP_CONTENT,
                            //ViewGroup.LayoutParams.WRAP_CONTENT
                    //);
                    //imageButton.setLayoutParams(params);

                    //imageButton.setOnClickListener(v -> {
                            //Toast.makeText(SearchActivity.this, "Clicked: " + product.getName(), Toast.LENGTH_SHORT).show();
                            //Intent intent = new Intent(SearchActivity.this, ProductDetailsActivity.class);
                            //intent.putExtra("name", product.name); // Or pass product ID or image URL
                            //startActivity(intent);
                    //});

                    //layout2.addView(imageButton);
                //});

            //} catch (Exception e) {
                //e.printStackTrace();
            //}
        //});
    //}

    //public static class Product {
        //public String name;
        //public String imageUrl;

        //public Product() {
            // Default constructor
        //}

        //public Product(String name, String imageUrl) {
            //this.name = name;
            //this.imageUrl = imageUrl;
        //}

        //public String getImageUrl() {
            //return imageUrl;
        //}

        //public String getName() {
            //return name;
        //}
    //}
}

    
    


