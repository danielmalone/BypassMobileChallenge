package com.finepointmobile.bypassmobilechallenge;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by danielmalone on 12/9/16.
 */

public class Following extends BaseActivity {

    TextView mUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.following);

        mUsername = (TextView) findViewById(R.id.username);

        Intent intent = getIntent();
        String stringUsername = intent.getStringExtra("username");

        StringBuilder sb = new StringBuilder();
        sb.append(stringUsername);
        sb.append(" follows");
        mUsername.setText(sb);
    }
}
