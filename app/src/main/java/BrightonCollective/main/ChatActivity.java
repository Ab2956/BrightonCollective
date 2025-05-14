package BrightonCollective.main;

import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;



import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ChatActivity extends AppCompatActivity {

    private FirebaseFirestore db;
    private FirebaseAuth auth;
    private String currentUserId, chatPartnerId;
    private EditText messageInput;
    private RecyclerView recyclerView;
    private MessageAdapter messageAdapter;
    private List<Message> messages = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        currentUserId = auth.getCurrentUser().getUid();
        chatPartnerId = getIntent().getStringExtra("chatPartnerId");

        messageInput = findViewById(R.id.messageInput);
        Button sendButton = findViewById(R.id.sendButton);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        messageAdapter = new MessageAdapter(messages, currentUserId);
        recyclerView.setAdapter(messageAdapter);

        sendButton.setOnClickListener(v -> {
            String text = messageInput.getText().toString().trim();
            if (!text.isEmpty()) {
                sendMessage(text);
                messageInput.setText("");
            }
        });

        loadMessages();
    }

    private void sendMessage(String text) {
        Map<String, Object> msg = new HashMap<>();
        msg.put("senderId", currentUserId);
        msg.put("receiverId", chatPartnerId);
        msg.put("messageText", text);
        msg.put("timestamp", FieldValue.serverTimestamp());

        db.collection("messages").add(msg);
    }

    private void loadMessages() {
        db.collection("messages")
                .orderBy("timestamp", Query.Direction.ASCENDING)
                .addSnapshotListener((snapshots, e) -> {
                    if (snapshots == null) return;
                    messages.clear();
                    for (DocumentSnapshot doc : snapshots) {
                        Message msg = doc.toObject(Message.class);
                        if ((msg.getSenderId().equals(currentUserId) && msg.getReceiverId().equals(chatPartnerId)) ||
                                (msg.getSenderId().equals(chatPartnerId) && msg.getReceiverId().equals(currentUserId))) {
                            messages.add(msg);
                        }
                    }
                    messageAdapter.notifyDataSetChanged();
                    recyclerView.scrollToPosition(messages.size() - 1);
                });
    }
}
