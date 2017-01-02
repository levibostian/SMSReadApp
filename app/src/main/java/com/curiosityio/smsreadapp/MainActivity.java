package com.curiosityio.smsreadapp;

import android.Manifest;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.github.kayvannj.permission_utils.Func;
import com.github.kayvannj.permission_utils.PermissionUtil;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_SMS = 0;
    private PermissionUtil.PermissionRequestObject mRequestObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRequestObject = PermissionUtil.with(this).request(Manifest.permission.RECEIVE_SMS).onAllGranted(
                new Func() {
                    @Override protected void call() {
                    }
                }).onAnyDenied(
                new Func() {
                    @Override protected void call() {
                        Toast.makeText(MainActivity.this, "You must allow access to SMS.", Toast.LENGTH_LONG).show();
                    }
                }).ask(REQUEST_CODE_SMS);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        mRequestObject.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

}
