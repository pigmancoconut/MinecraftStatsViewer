package de.minecraftstatsviewer;

import com.sun.net.httpserver.HttpServer;
import de.handler.ServerHandler;
import de.listeners.onPlayerQuit;
import de.utils.Config;
import de.webui.IndexHTML;
import de.webui.PlayersHTML;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.Statistic;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public final class MinecraftStatsViewer extends JavaPlugin {
    private static MinecraftStatsViewer instance;
    private HttpServer server;
    public static IndexHTML index;
    public static PlayersHTML players;

    public static ArrayList<CustomOfflinePlayer> customOfflinePlayers = new ArrayList<>();

    public static MinecraftStatsViewer getInstance() {
        return instance;
    }

    private static Config settings;

    public static Config getSettings() {
        return settings;
    }

    @Override
    public void onEnable() {
        settings = new Config("settings.yml", getDataFolder());
        if(!settings.getFileConfiguration().contains("admin_page.password")) {
            settings.getFileConfiguration().set("admin_page.password", "CHANGE_ME");
            settings.getFileConfiguration().set("admin_page.enabled", true);
            settings.save();
        }
        instance = this;
        index = new IndexHTML();
        players = new PlayersHTML();
        createServer();
        updateStats();
        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            public void run() {
                updateStats();
            }
        }, 0, 20*60*15); // every 15 minutes
        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            public void run() {
                players.updateHTML();
                index.updateHTML();
            }
        }, 0, 20*60); // 20 * seconds (in this case every 60 seconds)
        listenerRegistration();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void updateStats() {
        ArrayList<CustomOfflinePlayer> newCustomOfflinePlayers = new ArrayList<>();
        for(OfflinePlayer offlinePlayer : Bukkit.getOfflinePlayers()) {
            String uuid = offlinePlayer.getUniqueId().toString();
            String name = offlinePlayer.getName();
            String lastPlayed = timstampToDate(offlinePlayer.getLastPlayed());
            int mobKills = offlinePlayer.getStatistic(Statistic.MOB_KILLS);
            int playerKills = offlinePlayer.getStatistic(Statistic.PLAYER_KILLS);
            int deaths = offlinePlayer.getStatistic(Statistic.DEATHS);
            long travelledDistance = offlinePlayer.getStatistic(Statistic.WALK_ONE_CM)
                    + offlinePlayer.getStatistic(Statistic.FLY_ONE_CM) // eg. elytra
                    + offlinePlayer.getStatistic(Statistic.SPRINT_ONE_CM)
                    + offlinePlayer.getStatistic(Statistic.CROUCH_ONE_CM)
                    + offlinePlayer.getStatistic(Statistic.BOAT_ONE_CM)
                    + offlinePlayer.getStatistic(Statistic.CLIMB_ONE_CM)
                    + offlinePlayer.getStatistic(Statistic.PIG_ONE_CM)
                    + offlinePlayer.getStatistic(Statistic.STRIDER_ONE_CM)
                    + offlinePlayer.getStatistic(Statistic.SWIM_ONE_CM)
                    + offlinePlayer.getStatistic(Statistic.MINECART_ONE_CM);
            int timePlayed = offlinePlayer.getStatistic(Statistic.PLAY_ONE_MINUTE);

            CustomOfflinePlayer customOfflinePlayer = new CustomOfflinePlayer(uuid, name, lastPlayed, mobKills, playerKills, deaths, travelledDistance, timePlayed);
            newCustomOfflinePlayers.add(customOfflinePlayer);
            customOfflinePlayers = newCustomOfflinePlayers;
        }
        index.updateHTML();
        players.updateHTML();
    }

    public String timstampToDate(long timestamp) {
        String date = new java.text.SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(timestamp);
        return date;
    }

    public static String convertTimeAgo(long timestamp) {
        long currentTime = System.currentTimeMillis();
        long timeDifference = currentTime - timestamp;
        long seconds = TimeUnit.MILLISECONDS.toSeconds(timeDifference);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(timeDifference);
        long hours = TimeUnit.MILLISECONDS.toHours(timeDifference);
        long days = TimeUnit.MILLISECONDS.toDays(timeDifference);

        if (seconds < 60) {
            return seconds + " seconds ago";
        } else if (minutes < 60) {
            return minutes + " minutes ago";
        } else if (hours < 24) {
            return hours + " hours ago";
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date date = new Date(timestamp);
            return sdf.format(date) + " (" + days + " days ago)";
        }
    }

    public void listenerRegistration() {
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new onPlayerQuit(), this);
    }

    private void createServer() {
        try {
            server = HttpServer.create(new InetSocketAddress(80), 0);
            server.createContext("/", new ServerHandler());
            server.start();
            getLogger().info("Webserver gestartet");
            getLogger().info(server.getAddress().getHostName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
