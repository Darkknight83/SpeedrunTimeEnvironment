package com.example.speedruntimeenvironment;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class StringAdapter extends RecyclerView.Adapter<StringAdapter.ViewHolder> {
    private final List<String> liste;

    public StringAdapter(List<String> liste1) {
        this.liste = liste1;
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
        holder.NameView.setText(liste.get(position));
    }

//--------------Anzahl Listenitems

    @Override
    public int getItemCount() {
        return liste.size();
    }

//--------------Textview mit Attribut assoziieren

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView NameView;

        public ViewHolder(View view) {
            super(view);
            NameView = (TextView) view.findViewById(R.id.item_name);
        }

    }
}