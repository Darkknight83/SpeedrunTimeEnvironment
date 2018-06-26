package com.example.speedruntimeenvironment;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class StringAdapter extends RecyclerView.Adapter<StringAdapter.ViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(String item);
    }

    private final List<String> liste;
    private final OnItemClickListener listener;

    public StringAdapter(List<String> liste, OnItemClickListener listener) {
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

        public ViewHolder(View view) {
            super(view);
            NameView = (TextView) view.findViewById(R.id.item_name);
        }

        //--------------Listener wird an das Item gebunden

    public void bind(final String item, final OnItemClickListener listener){
        NameView.setText(item);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                listener.onItemClick(item);
            }
        });
    }
    }
}