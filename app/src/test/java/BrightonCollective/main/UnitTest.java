package BrightonCollective.main;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.google.firebase.auth.FirebaseAuth;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@RunWith(AndroidJUnit4.class)
public class UnitTest {

    @Test
    // test for user that is currently logged in
    public void testLogin() {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        boolean isLoggedIn = auth.getCurrentUser() != null;

        // sout for readability
        // will return false if not logged in and true if logged in
        System.out.println("User logged in: " + isLoggedIn);
        assertFalse("User should be signed out", isLoggedIn);
    }

    @Test
    // test to check an invalid user
    public void testLoginWithEmailAndPasswordFails() throws InterruptedException {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        // incorrect login params should fail
        CountDownLatch latch = new CountDownLatch(1);

        final boolean[] loginFailed = {false};

        auth.signInWithEmailAndPassword("invaliduser@invalid.com", "invalidpass")
                .addOnCompleteListener(task -> {
                    loginFailed[0] = !task.isSuccessful();
                    latch.countDown();
                });

        // allowing time to connect to the db and get the information
        boolean completedInTime = latch.await(10, TimeUnit.SECONDS);
        assertTrue("Firebase login did not respond in time", completedInTime);
        assertTrue("Login should fail with invalid credentials", loginFailed[0]);
    }
}

