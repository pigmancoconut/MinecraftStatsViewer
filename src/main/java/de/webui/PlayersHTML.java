package de.webui;

import de.minecraftstatsviewer.CustomOfflinePlayer;
import de.minecraftstatsviewer.MinecraftStatsViewer;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class PlayersHTML {
    public String html = "";

    public PlayersHTML() {
        updateHTML();
    }
    public void updateHTML() {
        String output = "";
        output += "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>Document</title>\n" +
                "    <link rel=\"preconnect\" href=\"https://fonts.googleapis.com\">\n" +
                "    <link rel=\"preconnect\" href=\"https://fonts.gstatic.com\" crossorigin>\n" +
                "    <link href=\"https://fonts.googleapis.com/css2?family=Poppins&display=swap\" rel=\"stylesheet\">\n" +
                "    <link rel=\"stylesheet\" href=\"https://pro.fontawesome.com/releases/v5.15.4/css/all.css\">\n" +
                "    <link rel=\"stylesheet\" href=\"index.css\">\n" +
                "    <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js\"></script>\n" +
                "</head>\n" +
                "\n" +
                "<body>\n" +
                "    <div class=\"sidebar\">\n" +
                "        <div class=\"items\">\n" +
                "            <a class=\"item\" href=\"index.html\"><i class=\"fas fa-chart-pie\"></i>\n" +
                "                <div class=\"tooltip\">Leaderboard</div>\n" +
                "            </a>\n" +
                "            <a class=\"item active\" href=\"players.html\"><i class=\"fas fa-users\"></i>\n" +
                "                <div class=\"tooltip\">Online Players</div>\n" +
                "            </a>\n" +
                "            <a class=\"item\" href=\"admin.html\"><i class=\"fas fa-user-shield\"></i>\n" +
                "                <div class=\"tooltip\">Admin</div>\n" +
                "            </a>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "    <div class=\"main\">\n" +
                "        <div class=\"leaderboard_category\" style=\"display: block;\">\n" +
                "            <h1>Online Players</h1>\n" +
                "            <div class=\"playerList\">\n" +
                "                <div class=\"header\">\n" +
                "                    <div>Name</div>\n" +
                "                    <div>Location</div>\n" +
                "                </div>\n";
                for(Player player : Bukkit.getOnlinePlayers()) {
                    output += "<div class=\"line\">\n" +
                            "                    <div><img src=\"https://mc-heads.net/head/" + player.getUniqueId() + "\" alt=\"\"> " + player.getName() + "</div>\n" +
                            "                    <div>" + convertWorldName(player.getLocation().getWorld().getName()) + "</div>\n" +
                            "                </div>";
                }
                output += "            </div>\n" +
                "        </div>\n" +
                "        <div class=\"leaderboard_category\" style=\"display: block;\">\n" +
                "            <h1>Offline Players</h1>\n" +
                "            <div class=\"playerList\">\n" +
                "                <div class=\"header\">\n" +
                "                    <div>Name</div>\n" +
                "                    <div>Last Online</div>\n" +
                "                </div>\n";

                for(CustomOfflinePlayer customOfflinePlayer : MinecraftStatsViewer.customOfflinePlayers) {
                    output += "<div class=\"line\">\n" +
                            "                    <div><img src=\"https://mc-heads.net/head/" + customOfflinePlayer.getUuid() + "\" alt=\"\"> " + customOfflinePlayer.getPlayerName() + "</div>\n" +
                            "                    <div>" + customOfflinePlayer.getLastOnlineStatus() + "</div>\n" +
                            "                </div>";
                }

                output += "\n" +
                "            </div>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</body>\n" +
                "\n" +
                "</html>";
                html = output;
    }

    public static String convertWorldName(String name) {
        switch (name) {
            case "world":
                return "Overworld";
            case "world_nether":
                return "Nether";
            case "world_the_end":
                return "End";
            default:
                return "Unknown";
        }
    }
}
