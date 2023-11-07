package com.example.api.accommodations;

record RoomUtilization(
        int premiumUsed,
        int economyUsed,
        int premiumGain,
        int economyGain
) {
}
