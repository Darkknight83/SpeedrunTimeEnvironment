package com.example.speedruntimeenvironment.controllers.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.speedruntimeenvironment.R;


import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class GamesRecyclerAdapter extends RecyclerView.Adapter<GamesRecyclerAdapter.GameViewHolder>{
    private static final String TAG = "GamesRecyclerAdapter";

    private List<String> mImageNames = null;
    private List<String> mImages = null;
    // private List<String> mIds = null;




    private Context mContext;


    public GamesRecyclerAdapter(Context context, List<String> imageNames, List<String> images) {
        mImageNames = imageNames;
        mImages = images;
        // mIds = ids;
        mContext = context;
    }

    @NonNull
    @Override
    public GameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_element_view, parent, false);

        return new GameViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GameViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: called");



        // get images
        /*
        Glide.with(mContext)
                .asBitmap()
                .load(mImages.get(position))    // image url
                .into(holder.image);
        */
        Glide.with(mContext)
                .asBitmap()
                .load(mImages.get(position))    // image url
                .into(holder.image);

        // set image name
        holder.imageName.setText(mImageNames.get(position));

        holder.parentLayout.setOnClickListener(v -> {
            Log.d(TAG, "onClick: clicked on " + mImageNames.get(position));

            Toast.makeText(mContext, mImageNames.get(position), Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return mImageNames.size();
    }

    public void setImageNames(List<String> imageNames) {
        this.mImageNames = imageNames;
    }

    public void setImages(List<String> imageUrls) {
        this.mImages = imageUrls;
    }

    /*
    public void setIds(List<String> ids) {
        this.mIds = ids;
    }
    */

    // holds each element of the list in memory
    public class GameViewHolder extends RecyclerView.ViewHolder {

        CircleImageView image;
        TextView imageName;
        RelativeLayout parentLayout;

        public GameViewHolder(View itemView) {
            super(itemView);
            // attach widgets to their id
            image = itemView.findViewById(R.id.image);
            imageName = itemView.findViewById(R.id.image_name); // todo
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }


}