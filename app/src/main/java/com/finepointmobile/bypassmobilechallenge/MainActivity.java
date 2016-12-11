package com.finepointmobile.bypassmobilechallenge;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.finepointmobile.bypassmobilechallenge.adapter.MainAdapter;
import com.finepointmobile.bypassmobilechallenge.model.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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

    /**
     * Basic setup for RecyclerView.
     */
    protected void setupRecyclerView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mDataset = new ArrayList<>();
    }

    /**
     * Load RecyclerView with dataset.
     */
    protected void addAdapter() {
        Collections.sort(mDataset, new CustomComparator());
        mAdapter = new MainAdapter(MainActivity.this, mDataset);
        mRecyclerView.setAdapter(mAdapter);
    }

    public class CustomComparator implements Comparator<User> {
        @Override
        public int compare(User o1, User o2) {
            return o1.getName().compareToIgnoreCase(o2.getName());
        }
    }
}
