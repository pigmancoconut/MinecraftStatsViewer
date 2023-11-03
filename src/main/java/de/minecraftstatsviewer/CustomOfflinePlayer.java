package de.minecraftstatsviewer;

public class CustomOfflinePlayer {
    private String uuid;
    private String playerName;
    private String lastOnlineStatus;

    private int mobKills;
    private int playerKills;
    private int deaths;
    private long travelledDistance;

    private int playTime;
    private int totalPosition;

    public CustomOfflinePlayer(String uuid, String playerName, String lastOnlineStatus, int mobKills, int playerKills, int deaths, long travelledDistance, int playTime) {
        this.uuid = uuid;
        this.playerName = playerName;
        this.lastOnlineStatus = lastOnlineStatus;
        this.mobKills = mobKills;
        this.playerKills = playerKills;
        this.deaths = deaths;
        this.travelledDistance = travelledDistance;
        this.playTime = playTime;
        resetTotalPosition();
    }

    public String getLastOnlineStatus() {
        return lastOnlineStatus;
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getUuid() {
        return uuid;
    }

    public int getMobKills() {
        return mobKills;
    }

    public int getPlayerKills() {
        return playerKills;
    }

    public int getDeaths() {
        return deaths;
    }

    public long getTravelledDistance() {
        return travelledDistance;
    }

    public int getPlayTime() {
        return playTime;
    }

    public void resetTotalPosition() {
        totalPosition = 0;
    }

    public int getTotalPosition() {
        return totalPosition;
    }

    public void addTotalPosition(int amount) {
        totalPosition += amount;
    }
}
