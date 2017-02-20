package com.example.com08.wfmeeting;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import us.zoom.sdk.ZoomApiError;
import us.zoom.sdk.ZoomAuthenticationError;
import us.zoom.sdk.ZoomSDK;
import us.zoom.sdk.ZoomSDKAuthenticationListener;

/**
 * Created by com08 (15/02/2017).
 */

public class LoginActivity extends Activity implements ZoomSDKAuthenticationListener, View.OnClickListener {

    EditText edUsername, edPass;
    Button btnSignIn;
    View progressBarLogin;
    LinearLayout lnrLogin;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initWidget();
        initListener();
    }

    @Override
    protected void onResume() {
        super.onResume();
        ZoomSDK zoomSDK = ZoomSDK.getInstance();
        if(zoomSDK.isInitialized()) {
            zoomSDK.addAuthenticationListener(this);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        ZoomSDK zoomSDK = ZoomSDK.getInstance();
        if(zoomSDK.isInitialized()) {
            zoomSDK.removeAuthenticationListener(this);
        }
    }

    private void initWidget()
    {
        edUsername = (EditText)findViewById(R.id.edUsernameLogin);
        edPass = (EditText)findViewById(R.id.edPassLogin);
        btnSignIn = (Button)findViewById(R.id.btnLogin);
        progressBarLogin = findViewById(R.id.lnrProgressLogin);
        lnrLogin = (LinearLayout)findViewById(R.id.lnrLogin);
    }

    private void initListener()
    {
        btnSignIn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btnLogin) {
            setEnable(false);
            onClickBtnLogin();
        }
    }

    public void onClickBtnLogin() {
        String userName = edUsername.getText().toString().trim();
        String password = edPass.getText().toString().trim();
        if(userName.length() == 0 || password.length() == 0) {
            setEnable(true);
            Toast.makeText(this, "You need to enter user name and password.", Toast.LENGTH_LONG).show();
            return;
        }
        ZoomSDK zoomSDK = ZoomSDK.getInstance();
        if(!(zoomSDK.loginWithZoom(userName, password) == ZoomApiError.ZOOM_API_ERROR_SUCCESS)) {
            Toast.makeText(this, "ZoomSDK has not been initialized successfully or sdk is logging in.", Toast.LENGTH_LONG).show();
        } else {
            setEnable(false);
            progressBarLogin.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onZoomSDKLoginResult(long l) {
        if(l == ZoomAuthenticationError.ZOOM_AUTH_ERROR_SUCCESS) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "Login failed result code = " + l, Toast.LENGTH_SHORT).show();
        }
        setEnable(true);
        progressBarLogin.setVisibility(View.GONE);
    }

    @Override
    public void onZoomSDKLogoutResult(long result) {
        if(result == ZoomAuthenticationError.ZOOM_AUTH_ERROR_SUCCESS) {
            Toast.makeText(this, "Logout successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Logout failed result code = " + result, Toast.LENGTH_SHORT).show();
        }
    }

    private void setEnable(boolean isEnabled)
    {
        edUsername.setEnabled(isEnabled);
        edPass.setEnabled(isEnabled);
        btnSignIn.setEnabled(isEnabled);
    }
}
