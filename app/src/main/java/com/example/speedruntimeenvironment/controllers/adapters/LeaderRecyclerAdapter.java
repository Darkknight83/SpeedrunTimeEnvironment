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


import com.bumptech.glide.Glide;
import com.example.speedruntimeenvironment.R;



import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class LeaderRecyclerAdapter extends RecyclerView.Adapter<LeaderRecyclerAdapter.LeaderViewHolder>{
    private static final String TAG = "LeaderRecyclerAdapter";

    private List<String> rank = null;
    private List<String> player = null;
    private List<String> time = null;
    private List<String> platform = null;
    private List<String> date = null;

    public interface OnItemClickListener {
        void onItemClick(String RunID);
    }

    private final OnItemClickListener listener;

    private static Context mContext;
    private View view;

    public LeaderRecyclerAdapter(Context context, List<String> rank, List<String> player, List<String> time, List<String> platform, List<String> date,OnItemClickListener listener) {
        this.rank = rank;
        this.player = player;
        this.time = time;
        this.platform = platform;
        this.date = date;
        mContext = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public LeaderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.leader_view, parent, false);

        return new LeaderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LeaderViewHolder holder, int position) {

        Log.d(TAG, "onBindViewHolder: called");

        holder.bind(rank.get(position), listener);

        holder.trank.setText(rank.get(position));
        holder.tplayer.setText(player.get(position));
        holder.ttime.setText(time.get(position));
        holder.tplatform.setText(platform.get(position));
        holder.tdate.setText(date.get(position));

    }

    @Override
    public int getItemCount() {
        return rank.size();
    }

    public void setRank(List<String> rank) {
        this.rank = rank;
    }
    public void setPlayer(List<String> rank) {
        this.player = player;
    }
    public void setTime(List<String> rank) {
        this.time = time;
    }
    public void setPlatform(List<String> rank) {
        this.platform = platform;
    }
    public void setDate(List<String> rank) {
        this.date = date;
    }



    // holds each element of the list in memory
    public static class LeaderViewHolder extends RecyclerView.ViewHolder {
        TextView trank;
        TextView tplayer;
        TextView ttime;
        TextView tplatform;
        TextView tdate;
        RelativeLayout leaderLayout;

        public LeaderViewHolder(View itemView) {
            super(itemView);
            // attach widgets to their id
            trank = itemView.findViewById(R.id.rank);
            tplayer = itemView.findViewById(R.id.player);
            ttime = itemView.findViewById(R.id.time);
            tplatform = itemView.findViewById(R.id.platform);
            tdate = itemView.findViewById(R.id.date);

            leaderLayout = itemView.findViewById(R.id.leader_layout);

        }

        public void bind(final String RankAsID, final OnItemClickListener listener){
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(RankAsID);
                }
            });
        }
    }
}
