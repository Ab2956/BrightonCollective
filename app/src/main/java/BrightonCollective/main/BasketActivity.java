package BrightonCollective.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;



public class BasketActivity extends AppCompatActivity {

    private ImageButton backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_basket);

        // Find views
        RecyclerView recyclerViewBasket = findViewById(R.id.recyclerViewBasket);
        TextView textViewTotalPrice = findViewById(R.id.textViewTotalPrice);
        Button buttonCheckout = findViewById(R.id.buttonCheckout);
        backBtn = findViewById(R.id.backButton);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BasketActivity.this, HomePage.class);
                startActivity(intent);
                finish();
            }
        });

        // Create a list of fake basket items
        List<BasketItem> basketItems = BasketManager.getInstance().getBasketItems();

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
            BasketManager.getInstance().clearBasket();
            adapter.notifyDataSetChanged();
            textViewTotalPrice.setText("Total: £0.00");
            Toast.makeText(this, "Checkout clicked!", Toast.LENGTH_SHORT).show();

        });
    }
}
