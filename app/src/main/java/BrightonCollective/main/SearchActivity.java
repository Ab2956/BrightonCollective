package BrightonCollective.main;

import android.content.Intent;
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
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import com.bumptech.glide.Glide;
import java.util.ArrayList;
import java.util.List;

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
        // back button for ui
        backBtn = findViewById(R.id.backButton);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchActivity.this, HomePage.class);
                startActivity(intent);
                finish();
            }
        });

        List<Product> product_list = new ArrayList<>();
        product_list.add(new Product(
                "bucket hat",
                "A bucket hat with a pink ribbon",
                "https://images.unsplash.com/photo-1733814489989-f7b6fa2a44cb?q=80&w=2030&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                19.99
        ));
        product_list.add(new Product(
                "gray tracksuit",
                "A gray Tracksuit",
                "https://images.unsplash.com/photo-1715609105250-ee1a91942ee2?q=80&w=2076&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                22.99
        ));
        product_list.add(new Product(
                "sunglasses",
                "Rayban's Sunglasses",
                "https://images.unsplash.com/photo-1621331122533-465bdcfa6e01?w=600&auto=format&fit=crop&q=60&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Nnx8cmF5YmFuJTIwc3VuZ2xhc3Nlc3xlbnwwfHwwfHx8MA%3D%3D",
                79.99
        ));
        product_list.add(new Product(
                "t-shirt",
                "Uniqlo Plain White T-Shirt",
                "https://images.unsplash.com/photo-1722310752951-4d459d28c678?q=80&w=1964&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                24.99
        ));
        product_list.add(new Product(
                "beanie",
                "A Black Beanie",
                "https://images.unsplash.com/photo-1648483092137-6e63796c8b06?q=80&w=1965&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                12.99
        ));
        product_list.add(new Product(
                "blue jeans",
                "Baggy Blue Jeans",
                "https://images.unsplash.com/photo-1624378439575-d8705ad7ae80?q=80&w=1994&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                15.99
        ));
        product_list.add(new Product(
                "floral scarf",
                "A red and black printed floral scarf",
                "https://images.unsplash.com/photo-1601244005535-a48d21d951ac?q=80&w=1970&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                7.99
        ));
        product_list.add(new Product(
                "black wool scarf",
                "A Black wool Scarf",
                "https://images.unsplash.com/photo-1551381912-4e2e29c7fd17?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                4.99
        ));
        product_list.add(new Product(
                "black leather jacket",
                "A black leather Jacket",
                "https://images.unsplash.com/photo-1727515546577-f7d82a47b51d?q=80&w=1936&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                29.99
        ));
        product_list.add(new Product(
                "leather belt",
                "Black and Brown-bordered leather belt",
                "https://images.unsplash.com/photo-1664286074240-d7059e004dff?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                11.99
        ));
        product_list.add(new Product(
                "swim shorts",
                "brown swim shorts",
                "https://images.unsplash.com/photo-1532788031780-73fddc7e94fe?w=600&auto=format&fit=crop&q=60&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTF8fHN3aW0lMjBzaG9ydHN8ZW58MHx8MHx8fDI%3D",
                9.99
        ));
        product_list.add(new Product(
                "sliders",
                "Black Sliders with White stripes",
                "https://images.unsplash.com/photo-1651913506606-2cac8f5e69a9?w=600&auto=format&fit=crop&q=60&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTB8fHNsaWRlcnMlMjBzbGlwcGVyc3xlbnwwfHwwfHx8Mg%3D%3D",
                3.99
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
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);// load image from URL
            Glide.with(this)
                    .load(product.getImageUrl())
                    .into(imageView);

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
                // glide to get image
                Glide.with(this)
                        .load(product_list.get(i).getImageUrl())
                        .into(imageView);// load image from URL

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

}
    
    


