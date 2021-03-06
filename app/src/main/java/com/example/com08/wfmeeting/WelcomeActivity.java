package com.example.com08.wfmeeting;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import us.zoom.sdk.ZoomApiError;
import us.zoom.sdk.ZoomAuthenticationError;
import us.zoom.sdk.ZoomError;
import us.zoom.sdk.ZoomSDK;
import us.zoom.sdk.ZoomSDKAuthenticationListener;
import us.zoom.sdk.ZoomSDKInitializeListener;

/**
 * Created by com08 (16/02/2017).
 */

public class WelcomeActivity extends Activity implements Constants, ZoomSDKInitializeListener, ZoomSDKAuthenticationListener {

    View progressBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ZoomSDK sdk = ZoomSDK.getInstance();
        if(sdk.isLoggedIn()) {
            finish();
            showMainAct();
            return;
        }

        setContentView(R.layout.activity_welcome);
        initWidget();

        if(savedInstanceState == null) {
            sdk.initialize(this, APP_KEY, APP_SECRET, WEB_DOMAIN, this);
        }
    }

    private void initWidget()
    {
        progressBar = findViewById(R.id.progressPanel);
    }

    @Override
    public void onZoomSDKLoginResult(long result) {
        if((int)result == ZoomAuthenticationError.ZOOM_AUTH_ERROR_SUCCESS) {
            showMainAct();
            finish();
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void onZoomSDKLogoutResult(long l) {

    }

    @Override
    public void onZoomSDKInitializeResult(int errorCode, int internalErrorCode) {
        if(errorCode != ZoomError.ZOOM_ERROR_SUCCESS) {
            Toast.makeText(this, "Failed to initialize Zoom SDK. Error: " + errorCode + ", internalErrorCode=" + internalErrorCode, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Initialize Zoom SDK successfully.", Toast.LENGTH_LONG).show();
            ZoomSDK sdk = ZoomSDK.getInstance();
            if(sdk.tryAutoLoginZoom() == ZoomApiError.ZOOM_API_ERROR_SUCCESS) {
                sdk.addAuthenticationListener(this);
                progressBar.setVisibility(View.VISIBLE);
            } else {
                showLoginAct();
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ZoomSDK sdk = ZoomSDK.getInstance();
        sdk.removeAuthenticationListener(this);
    }

    private void showLoginAct()
    {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    private void showMainAct()
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
