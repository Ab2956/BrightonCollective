package BrightonCollective.main;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class MessageActivity extends AppCompatActivity {

    private FirebaseFirestore db;
    private FirebaseAuth auth;
    private String currentUserId;
    private RecyclerView usersRecyclerView;
    private UserAdapter userAdapter;
    private List<User> userList = new ArrayList<>();
    private ImageButton backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        backBtn = findViewById(R.id.backBtn);
        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        currentUserId = auth.getCurrentUser().getUid();

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MessageActivity.this, HomePage.class);
                startActivity(intent);
                finish();
            }
        });

        usersRecyclerView = findViewById(R.id.usersRecyclerView);
        usersRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        userAdapter = new UserAdapter(userList, user -> {
            Intent intent = new Intent(MessageActivity.this, ChatActivity.class);
            intent.putExtra("chatPartnerId", user.getUid());
            startActivity(intent);
        });

        usersRecyclerView.setAdapter(userAdapter);

        loadUsers();
    }

    private void loadUsers() {
        db.collection("users").get().addOnSuccessListener(querySnapshot -> {
            userList.clear();
            for (DocumentSnapshot doc : querySnapshot) {
                User user = doc.toObject(User.class);
                if (user != null && !user.getUid().equals(currentUserId)) {
                    userList.add(user);
                }
            }
            userAdapter.notifyDataSetChanged();
        });
    }
}
