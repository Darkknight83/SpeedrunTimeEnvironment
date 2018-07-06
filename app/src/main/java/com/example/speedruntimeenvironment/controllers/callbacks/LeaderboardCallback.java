package com.example.speedruntimeenvironment.controllers.callbacks;

import com.example.speedruntimeenvironment.model.Leaderboard;

public interface LeaderboardCallback {
    void onLeaderboardReceived(Leaderboard leaderboard);
    void onFailure();
}
