package com.example.speedruntimeenvironment.controllers.alerts;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.view.ContextThemeWrapper;
import android.util.Log;
import android.view.Window;
import android.widget.Toast;

import com.example.speedruntimeenvironment.R;
import com.example.speedruntimeenvironment.daos.api.GamesDAO;
import com.example.speedruntimeenvironment.daos.impl.GamesDAOImpl;
import com.example.speedruntimeenvironment.model.Game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class DeleteDialog extends DialogFragment {

    private GamesDAO gamesDAO;
    private static final String TAG = "Favorites";
    private List<Game> FGameList;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        gamesDAO = new GamesDAOImpl();
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(getActivity(), android.R.style.Theme_Holo_Light_Dialog_NoActionBar));
        builder.setMessage(R.string.deletefavq)
                .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        try {
                            FGameList = gamesDAO.getFavoriteGamesFromFile(getString(R.string.favorites), getActivity());
                        } catch (IOException e) {
                            Log.e(TAG, "initGames: File not found", e);
                        }
                        List<Game> empty = new ArrayList<>();
                        try {
                            gamesDAO.favoriteGamesToFile(getString(R.string.favorites), getActivity(), empty);
                            Toast.makeText(getActivity(),"all favorites removed", Toast.LENGTH_SHORT).show();
                        } catch (IOException e) {
                            Log.e(TAG, "initGames: File not found", e);
                        }
                    }
                });
                builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }


}
