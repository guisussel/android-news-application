package com.example.newsapplication;

import static androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG;
import static androidx.biometric.BiometricManager.Authenticators.DEVICE_CREDENTIAL;

import androidx.biometric.BiometricManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowInsets;
import android.view.WindowInsetsController;
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

        checkForActiveBiometricsAndAuthenticate();

    }

    public void checkForActiveBiometricsAndAuthenticate() {
        BiometricManager biometricManager = BiometricManager.from(this);

        switch (biometricManager.canAuthenticate(BIOMETRIC_STRONG | DEVICE_CREDENTIAL)) {
            case BiometricManager.BIOMETRIC_SUCCESS:
                Log.d("NEWS_APP_BIOMETRY", "App can authenticate using biometrics.");
                authenticateViaBiometrics();
                break;
            case BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE:
                Log.e("NEWS_APP_BIOMETRY", "No biometric features available on this device.");
                startMainActivity();
                break;
            case BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE:
                Log.e("NEWS_APP_BIOMETRY", "Biometric features are currently unavailable.");
                startMainActivity();
                break;
            case BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED:
                Log.e("NEWS_APP_BIOMETRY", "BIOMETRIC_ERROR_NONE_ENROLLED.");
                startMainActivity();
                break;
        }
    }

    public void authenticateViaBiometrics() {

        createBiometricPrompt();

        promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Authenticate to continue")
                .setDescription("Please scan your fingerprint to continue")
                .setNegativeButtonText("Cancel")
                .build();

        biometricPrompt.authenticate(promptInfo);

        buttonAuthenticate.setOnClickListener(view -> {
            biometricPrompt.authenticate(promptInfo);
        });

    }

    public void createBiometricPrompt() {
        biometricPrompt = new BiometricPrompt(this, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                startMainActivity();
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                Toast.makeText(getApplicationContext(), "Authentication failed",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                Toast.makeText(getApplicationContext(), "Authentication error: "
//                                + errString,
                            + "please register a fingerprint to proceed or try again.",
                        Toast.LENGTH_SHORT).show();
                buttonAuthenticate.setVisibility(View.VISIBLE);
            }
        });
    }

    public void startMainActivity() {
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
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
