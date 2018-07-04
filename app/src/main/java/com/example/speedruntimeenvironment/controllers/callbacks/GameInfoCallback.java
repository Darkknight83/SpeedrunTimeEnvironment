package com.example.speedruntimeenvironment.controllers.callbacks;

import com.example.speedruntimeenvironment.model.Game;

public interface GameInfoCallback {

    void onSuccess(Game game);
    void onFail();
}
