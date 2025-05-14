package BrightonCollective.main;


import java.util.Map;


public class Message {
    private String senderId;
    private String receiverId;
    private String messageText;
    private com.google.firebase.Timestamp timestamp;

    public Message() {} // Firestore needs empty constructor

    public String getSenderId() { return senderId; }
    public String getReceiverId() { return receiverId; }
    public String getMessageText() { return messageText; }
    public com.google.firebase.Timestamp getTimestamp() { return timestamp; }
}
