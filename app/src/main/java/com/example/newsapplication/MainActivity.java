package com.example.newsapplication;

import static androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG;
import static androidx.biometric.BiometricManager.Authenticators.DEVICE_CREDENTIAL;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.newsapplication.databinding.ActivityMainBinding;

import java.util.concurrent.Executor;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private Executor executor;
    private BiometricPrompt biometricPrompt;
    private BiometricPrompt.PromptInfo promptInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//
//        executor = ContextCompat.getMainExecutor(this);
//        biometricPrompt = new BiometricPrompt(MainActivity.this,
//                executor, new BiometricPrompt.AuthenticationCallback() {
//            @Override
//            public void onAuthenticationError(int errorCode,
//                                              @NonNull CharSequence errString) {
//                super.onAuthenticationError(errorCode, errString);
//                Toast.makeText(getApplicationContext(),
//                                "Authentication error: " + errString, Toast.LENGTH_SHORT)
//                        .show();
//            }
//
//            @Override
//            public void onAuthenticationSucceeded(
//                    @NonNull BiometricPrompt.AuthenticationResult result) {
//                super.onAuthenticationSucceeded(result);
//                Toast.makeText(getApplicationContext(),
//                        "Authentication succeeded!", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onAuthenticationFailed() {
//                super.onAuthenticationFailed();
//                Toast.makeText(getApplicationContext(), "Authentication failed",
//                                Toast.LENGTH_SHORT)
//                        .show();
//            }
//        });
//
//        promptInfo = new BiometricPrompt.PromptInfo.Builder()
//                .setTitle("Biometric login required!")
//                .setSubtitle("Log in using your biometric credential")
//                .setNegativeButtonText("Use account password")
//                .build();
//
//        BiometricManager biometricManager = BiometricManager.from(this);
//        switch (biometricManager.canAuthenticate(BIOMETRIC_STRONG | DEVICE_CREDENTIAL)) {
//            case BiometricManager.BIOMETRIC_SUCCESS:
//                Log.d("MY_APP_TAG", "App can authenticate using biometrics.");
//                biometricPrompt.authenticate(promptInfo);
//                break;
//            case BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE:
//                Log.e("MY_APP_TAG", "No biometric features available on this device.");
//                break;
//            case BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE:
//                Log.e("MY_APP_TAG", "Biometric features are currently unavailable.");
//                break;
//            case BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED:
//                Log.e("MY_APP_TAG", "BIOMETRIC_ERROR_NONE_ENROLLED.");
//                break;
//        }

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }

}