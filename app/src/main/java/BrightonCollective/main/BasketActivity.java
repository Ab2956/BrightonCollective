package BrightonCollective.main;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class BasketActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_basket);

        // Find views
        RecyclerView recyclerViewBasket = findViewById(R.id.recyclerViewBasket);
        TextView textViewTotalPrice = findViewById(R.id.textViewTotalPrice);
        Button buttonCheckout = findViewById(R.id.buttonCheckout);

        // Create a list of fake basket items
        List<BasketItem> basketItems = new ArrayList<>();
        basketItems.add(new BasketItem("Vintage Jacket", 1, 45.00));
        basketItems.add(new BasketItem("Handmade Necklace", 2, 25.00));
        basketItems.add(new BasketItem("Organic Soap", 3, 5.50));

        // Set up RecyclerView with BasketAdapter
        BasketAdapter adapter = new BasketAdapter(basketItems);
        recyclerViewBasket.setAdapter(adapter);
        recyclerViewBasket.setLayoutManager(new LinearLayoutManager(this));

        // Calculate and display total price
        double totalPrice = 0.0;
        for (BasketItem item : basketItems) {
            totalPrice += item.getPrice() * item.getQuantity();
        }
        textViewTotalPrice.setText("Total: £" + String.format("%.2f", totalPrice));

        // Set button action
        buttonCheckout.setOnClickListener(v -> {
            Toast.makeText(this, "Checkout clicked!", Toast.LENGTH_SHORT).show();
        });
    }
}
