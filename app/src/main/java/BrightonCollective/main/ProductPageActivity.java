package BrightonCollective.main;

import android.os.Bundle;
//import androidx.annotation.NonNull;
//import android.view.View; // for UI interactions
//import android.widget.Button;


import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


public class ProductPageActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ProductAdapter adapter;
    private List<Product> productList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_product_page);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // initialise recycler view and adapter
        productList = new ArrayList<>();
        adapter = new ProductAdapter(this, productList);
        recyclerView.setAdapter(adapter) ;
        //for now until firebase database created
        loadDummyPorudcts();
    }

    private void loadDummyPorudcts() {
        // Add some sample products. Later, replace this with Firebase calls.
        productList.add(new Product("Product 1", "Description for Product 1", "https://example.com/image1.jpg", 19.99));
        productList.add(new Product("Product 2", "Description for Product 2", "https://example.com/image2.jpg", 29.99));
        productList.add(new Product("Product 3", "Description for Product 3", "https://example.com/image3.jpg", 39.99));

//Notify the adapter that data has changed
        adapter.notifyDataSetChanged();
    }
}
        // define product class

class Product{
        private String name,description,imageUrl;
        private double price;

        public Product() {} //Empty constructor for Firebase later on

        public Product(String name, String description,String imageUrl,double price) {
            this.name = name;
            this.description = description;
            this.imageUrl = imageUrl;
            this.price = price;
        }

        //Getters
        public String getName() { return name; }
        public String getDescription() { return description; }
        public String getImageUrl() { return imageUrl; }
        public double getPrice() { return price; }

        public void setImageUrl(String imageUrl){
        this.imageUrl = imageUrl;
        }

    }

