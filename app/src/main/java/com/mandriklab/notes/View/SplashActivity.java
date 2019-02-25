package com.mandriklab.notes.View;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.mandriklab.notes.R;

public class SplashActivity extends Activity {
    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();

    }
}
