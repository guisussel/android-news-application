package com.example.newsapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowInsets;
import android.view.WindowInsetsController;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricPrompt;

public class SplashActivity extends AppCompatActivity {

    private BiometricPrompt biometricPrompt;
    private BiometricPrompt.PromptInfo promptInfo;

    private Button buttonAuthenticate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);



        // Make the activity full screen
        WindowInsetsController controller = getWindow().getInsetsController();
        if (controller != null) {
            controller.hide(WindowInsets.Type.statusBars() | WindowInsets.Type.navigationBars());
            controller.setSystemBarsBehavior(WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE);
        }

        // Hide the AppToolbar
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        buttonAuthenticate = findViewById(R.id.buttonAuthenticate);
        buttonAuthenticate.setVisibility(View.GONE);


        // Create a BiometricPrompt instance
        biometricPrompt = new BiometricPrompt(this, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                // Launch MainActivity on successful fingerprint authentication
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                Toast.makeText(getApplicationContext(), "Authentication failed",
                                Toast.LENGTH_SHORT)
                        .show();
                buttonAuthenticate.setVisibility(View.VISIBLE);
            }
                        @Override
            public void onAuthenticationError(int errorCode,
                                              @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                Toast.makeText(getApplicationContext(),
                                "Authentication error: " + errString, Toast.LENGTH_SHORT)
                        .show();
                buttonAuthenticate.setVisibility(View.VISIBLE);
            }
        });

        // Create a BiometricPrompt.PromptInfo instance
        promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Authenticate to continue")
                .setDescription("Please scan your fingerprint to continue")
                .setNegativeButtonText("Cancel")
                .build();

        // Prompt the user for a fingerprint scan
        biometricPrompt.authenticate(promptInfo);

        buttonAuthenticate.setOnClickListener(view -> {
            biometricPrompt.authenticate(promptInfo);
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        // Cancel the fingerprint authentication if the user navigates away from the app
        if (biometricPrompt != null) {
            biometricPrompt.cancelAuthentication();
        }
    }
}
