package com.example.api.hotel;

record RoomUtilization(
        int premiumUsed,
        int economyUsed,

        int premiumGain,
        int economyGain

) {
}
