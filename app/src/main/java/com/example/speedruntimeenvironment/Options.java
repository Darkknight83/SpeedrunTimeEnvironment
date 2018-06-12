package com.example.speedruntimeenvironment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Options extends Fragment {

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.activity_options, container, false);

        fragmentManager = getActivity().getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        Button favorites = (Button)v.findViewById(R.id.btn_favorites);
        favorites.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
               Intent intent = new Intent(getActivity(), Sub_MainActivity.class);
               intent.putExtra("Destination", getString(R.string.favorites));
               startActivity(intent);
            }
        });
        Button notifications = (Button)v.findViewById(R.id.btn_notifications);
        notifications.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Sub_MainActivity.class);
                intent.putExtra("Destination", getString(R.string.notifications));
                startActivity(intent);
            }
        });
        Button backup = (Button)v.findViewById(R.id.btn_backup);
        backup.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Sub_MainActivity.class);
                intent.putExtra("Destination", getString(R.string.backup));
                startActivity(intent);
            }
        });
        Button faq = (Button)v.findViewById(R.id.btn_faq);
        faq.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Sub_MainActivity.class);
                intent.putExtra("Destination", getString(R.string.faq));
                startActivity(intent);
            }
        });
        Button appinfo = (Button)v.findViewById(R.id.btn_appinfo);
        appinfo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Sub_MainActivity.class);
                intent.putExtra("Destination", getString(R.string.appinfo));
                startActivity(intent);
            }
        });
        return v;
    }
}