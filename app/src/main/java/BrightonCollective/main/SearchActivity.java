package BrightonCollective.main;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SearchActivity extends AppCompatActivity {
    private DatabaseReference databaseRef;
    private LinearLayout layout2;
    private ExecutorService executorService;
    private ImageButton backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_search);

        EditText search_input = findViewById(R.id.SearchInput);
        ImageButton search_button = findViewById(R.id.SearchButton);

        layout2 = findViewById(R.id.layoutStructure);
        databaseRef = FirebaseDatabase.getInstance().getReference("products");
        executorService = Executors.newFixedThreadPool(4);
        backBtn = findViewById(R.id.backButton);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchActivity.this, HomePage.class);
                startActivity(intent);
                finish();
            }
        });

        fetchProductsFromDatabase();

        search_button.setOnClickListener(v -> {
            String query = search_input.getText().toString().trim();
            if (!query.isEmpty()) {
                searchProductByName(query);
            }
        });

    }

    private void searchProductByName(String query) {
        layout2.removeAllViews();

        databaseRef.orderByChild("name").equalTo(query).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot productSnapshot : snapshot.getChildren()) {
                        Product product = productSnapshot.getValue(Product.class);
                        if (product != null) {
                            downloadImageAndCreateButton(product);
                        }
                    }
                } else {
                    Toast.makeText(SearchActivity.this, "No product found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(SearchActivity.this, "Search failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void fetchProductsFromDatabase() {
        databaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot productSnapshot : snapshot.getChildren()) {
                    Product product = productSnapshot.getValue(Product.class);
                    if (product != null && product.getImageUrl() != null) {
                        downloadImageAndCreateButton(product);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(SearchActivity.this, "Failed to fetch data", Toast.LENGTH_SHORT).show();
            }
        });
    }



    private void downloadImageAndCreateButton(Product product) {
        executorService.execute(() -> {
            try {
                URL url = new URL(product.getImageUrl());
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                Bitmap bitmap = BitmapFactory.decodeStream(input);

                runOnUiThread(() -> {
                    ImageButton imageButton = new ImageButton(SearchActivity.this);
                    imageButton.setImageBitmap(bitmap);
                    imageButton.setBackgroundColor(android.graphics.Color.TRANSPARENT);

                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT
                    );
                    imageButton.setLayoutParams(params);

                    imageButton.setOnClickListener(v -> {
                            Toast.makeText(SearchActivity.this, "Clicked: " + product.getName(), Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(SearchActivity.this, ProductPageActivity.class);
                            intent.putExtra("name", product.name); // Or pass product ID or image URL
                            startActivity(intent);
                    });

                    layout2.addView(imageButton);
                });

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public static class Product {
        public String name;
        public String imageUrl;

        public Product() {
            // Default constructor
        }

        public Product(String name, String imageUrl) {
            this.name = name;
            this.imageUrl = imageUrl;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public String getName() {
            return name;
        }
    }
}

    
    


