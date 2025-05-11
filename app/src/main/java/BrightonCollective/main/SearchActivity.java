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

        List<Product> productList = ProductList.getInstance().getProducts();
        

        GridLayout gridLayout = findViewById(R.id.grid_layout);
        gridLayout.setColumnCount(2);

        for (Product product : productList) {
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
            search_product(productList);
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
    
    


