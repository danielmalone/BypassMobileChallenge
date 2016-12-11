package com.finepointmobile.bypassmobilechallenge;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.finepointmobile.bypassmobilechallenge.model.User;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by danielmalone on 12/9/16.
 */

public class Following extends MainActivity {

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

        setupRecyclerView();

        getEndpoint().getFollowingUser(stringUsername, new Callback<List<User>>() {
            @Override
            public void success(List<User> users, Response response) {

                for (int i = 0; i < users.size(); i++) {
                    mDataset.add(users.get(i));
                }

                addAdapter();
            }

            @Override
            public void failure(RetrofitError error) {
            }
        });
    }
}
