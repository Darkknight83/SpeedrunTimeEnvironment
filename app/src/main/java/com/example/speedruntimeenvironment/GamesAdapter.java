package com.example.speedruntimeenvironment;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.tsunderebug.speedrun4j.game.Game;

import java.util.List;

public class GamesAdapter extends RecyclerView.Adapter<GamesAdapter.ViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(Game item);
    }

    private final List<Game> liste;
    private final OnItemClickListener listener;

    public GamesAdapter(List<Game> liste, OnItemClickListener listener) {
        this.liste = liste;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_element_view, parent, false);
        return new ViewHolder(view);
    }

    //--------------Text an Textfeld binden
    @Override

    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(liste.get(position), listener);
    }

//--------------Anzahl Listenitems

    @Override
    public int getItemCount() {
        return liste.size();
    }

//--------------Textview mit Attribut assoziieren

    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView NameView;

        public ViewHolder(View itemView) {
            super(itemView);
            NameView = (TextView) itemView.findViewById(R.id.item_name);
        }

        //--------------Listener wird an das Item gebunden

    public void bind(final Game item, final OnItemClickListener listener){
        NameView.setText(item.toString());
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                listener.onItemClick(item);
            }
        });
    }
    }
}