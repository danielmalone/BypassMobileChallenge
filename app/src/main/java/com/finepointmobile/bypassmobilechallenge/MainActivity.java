package com.finepointmobile.bypassmobilechallenge;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.finepointmobile.bypassmobilechallenge.adapter.MyAdapter;
import com.finepointmobile.bypassmobilechallenge.model.User;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends BaseActivity {

    RecyclerView mRecyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    ArrayList<User> mDataset;

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupRecyclerView();

        getEndpoint().getOrganizationMember("bypasslane", new Callback<List<User>>() {
            @Override
            public void success(List<User> users, Response response) {
                Log.d(TAG, "success: " + users);
                Log.d(TAG, "success: " + response);


                for (int i = 0; i < users.size(); i++) {
                    Log.d(TAG, "success: " + users.get(i).getName());
                    mDataset.add(users.get(i));
                }

                addAdapter();
            }

            @Override
            public void failure(RetrofitError error) {
            }
        });
    }

    /**
     * Basic setup for RecyclerView.
     */
    private void setupRecyclerView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mDataset = new ArrayList<>();
    }

    /**
     * Load RecyclerView with dataset.
     */
    private void addAdapter() {
        mAdapter = new MyAdapter(MainActivity.this, mDataset);
        mRecyclerView.setAdapter(mAdapter);
    }
}
