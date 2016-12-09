package com.finepointmobile.bypassmobilechallenge.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.finepointmobile.bypassmobilechallenge.R;
import com.finepointmobile.bypassmobilechallenge.image.ImageLoader;
import com.finepointmobile.bypassmobilechallenge.model.User;

import java.util.ArrayList;

/**
 * Created by danielmalone on 12/7/16.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    Context mContext;
    ArrayList<User> mDataset;

    public MyAdapter(Context mContext, ArrayList<User> mDataset) {
        this.mContext = mContext;
        this.mDataset = mDataset;
    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row, parent, false);

        ViewHolder vh = new ViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(MyAdapter.ViewHolder holder, int position) {
        ImageLoader.createImageLoader(mContext)
                .load(mDataset.get(position).getProfileURL())
                .into(holder.mImage);

        holder.mUsername.setText(mDataset.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView mImage;
        public TextView mUsername;

        public ViewHolder(View itemView) {
            super(itemView);

            mImage = (ImageView) itemView.findViewById(R.id.image);
            mUsername = (TextView) itemView.findViewById(R.id.username);
        }
    }
}
