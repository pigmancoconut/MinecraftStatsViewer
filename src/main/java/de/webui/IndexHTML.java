package de.webui;

import de.minecraftstatsviewer.CustomOfflinePlayer;
import de.minecraftstatsviewer.MinecraftStatsViewer;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class IndexHTML {
    private String html;
    private String img = "data:image/png;base64,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";
    public IndexHTML() {
        updateHTML();
    }

    public void updateHTML() {
        DecimalFormat f = new DecimalFormat("####.00");
        for(CustomOfflinePlayer customOfflinePlayer : MinecraftStatsViewer.customOfflinePlayers) {
            customOfflinePlayer.resetTotalPosition();
        }
        ArrayList<CustomOfflinePlayer> customOfflinePlayersSortedByMobKills = sortiereNachMobKills(MinecraftStatsViewer.customOfflinePlayers);
        ArrayList<CustomOfflinePlayer> customOfflinePlayersSortedByPlayerKills = sortiereNachPlayerKills(MinecraftStatsViewer.customOfflinePlayers);
        ArrayList<CustomOfflinePlayer> customOfflinePlayersSortedByDeaths = sortiereNachDeaths(MinecraftStatsViewer.customOfflinePlayers);
        ArrayList<CustomOfflinePlayer> customOfflinePlayersSortedByDistance = sortiereNachDistance(MinecraftStatsViewer.customOfflinePlayers);
        ArrayList<CustomOfflinePlayer> customOfflinePlayersSortedByTimePlayed = sortiereNachTimePlayed(MinecraftStatsViewer.customOfflinePlayers);

        int idx = 0;
        for(CustomOfflinePlayer customOfflinePlayer : customOfflinePlayersSortedByMobKills) {
            idx ++;
            customOfflinePlayer.addTotalPosition(idx);
        }

        idx = 0;
        for(CustomOfflinePlayer customOfflinePlayer : customOfflinePlayersSortedByPlayerKills) {
            idx ++;
            customOfflinePlayer.addTotalPosition(idx);
        }

        idx = 0;
        for(CustomOfflinePlayer customOfflinePlayer : customOfflinePlayersSortedByDeaths) {
            idx ++;
            customOfflinePlayer.addTotalPosition(idx);
        }

        idx = 0;
        for(CustomOfflinePlayer customOfflinePlayer : customOfflinePlayersSortedByDistance) {
            idx ++;
            customOfflinePlayer.addTotalPosition(idx);
        }

        idx = 0;
        for(CustomOfflinePlayer customOfflinePlayer : customOfflinePlayersSortedByTimePlayed) {
            idx ++;
            customOfflinePlayer.addTotalPosition(idx);
        }


        ArrayList<CustomOfflinePlayer> customOfflinePlayersSortedByOverall = sortiereNachOverall(MinecraftStatsViewer.customOfflinePlayers);


        String output = "<!DOCTYPE html>\n" +
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
                "            <a class=\"item active\" href=\"index.html\"><i class=\"fas fa-chart-pie\"></i>\n" +
                "                <div class=\"tooltip\">Leaderboard</div>\n" +
                "            </a>\n" +
                "            <a class=\"item\" href=\"players.html\"><i class=\"fas fa-users\"></i>\n" +
                "                <div class=\"tooltip\">Online Players</div>\n" +
                "            </a>\n" +
                "            <a class=\"item\" href=\"admin.html\"><i class=\"fas fa-user-shield\"></i>\n" +
                "                <div class=\"tooltip\">Admin</div>\n" +
                "            </a>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "    <div class=\"main\">\n" +
                "        <div class=\"hero\">\n" +
                "            <div class=\"img\">\n" +
                "                <img src=\"" + img + "\" alt=\"\">\n" +
                "            </div>\n" +
                "            <div class=\"text\">\n" +
                "                <h1>Leaderboard</h1>\n" +
                "                <p>Find the stats of your friends and the whole community. Your turn! Become the first!</p>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "        <div class=\"categories\">\n" +
                "            <div class=\"tag active\">Overall</div>\n" +
                "            <div class=\"tag\">Mob Kills</div>\n" +
                "            <div class=\"tag\">Player Kills</div>\n" +
                "            <div class=\"tag\">Death Counter</div>\n" +
                "            <div class=\"tag\">Travelled Distance</div>\n" +
                "            <div class=\"tag\">Playtime</div>\n" +
                "        </div>\n" +
                "        <div class=\"leaderboard_category\" style=\"display: block;\">\n" +
                "            <p class=\"text\">This category shows the overall stats by calculating the average leaderboard position.</p>\n" +
                "            <div class=\"leaderboard\">\n" +
                "                <div class=\"header\">\n" +
                "                    <div>Rank</div>\n" +
                "                    <div>Name</div>\n" +
                "                    <div>Avg Position</div>\n" +
                "                </div>\n";

                idx = 0;
                for(CustomOfflinePlayer customOfflinePlayer : customOfflinePlayersSortedByOverall) {
                    idx ++;
                    output += "<div class=\"line\">\n" +
                            "                    <div>" + idx + "</div>\n" +
                            "                    <div><img src=\"https://mc-heads.net/head/" + customOfflinePlayer.getUuid() + "\" alt=\"\"> " + customOfflinePlayer.getPlayerName() + "</div>\n" +
                            "                    <div>" + f.format(customOfflinePlayer.getTotalPosition()/5.00) + "</div>\n" +
                            "                </div>\n";
                }

                output +=
                "            </div>\n" +
                "        </div>\n" +
                "        <div class=\"leaderboard_category\">\n" +
                "            <p class=\"text\">This category shows how many Mobs a player has killed.</p>\n" +
                "            <div class=\"leaderboard\">\n" +
                "                <div class=\"header\">\n" +
                "                    <div>Rank</div>\n" +
                "                    <div>Name</div>\n" +
                "                    <div>Kills</div>\n" +
                "                </div>\n";

                idx = 0;
                for(CustomOfflinePlayer customOfflinePlayer : customOfflinePlayersSortedByMobKills) {
                    idx ++;
                    output += "<div class=\"line\">\n" +
                            "                    <div>" + idx + "</div>\n" +
                            "                    <div><img src=\"https://mc-heads.net/head/" + customOfflinePlayer.getUuid() + "\" alt=\"\"> " + customOfflinePlayer.getPlayerName() + "</div>\n" +
                            "                    <div>" + customOfflinePlayer.getMobKills() + "</div>\n" +
                            "                </div>\n";
                }

                output += "            </div>\n" +
                "        </div>\n" +
                "        <div class=\"leaderboard_category\">\n" +
                "            <p class=\"text\">This category shows how many Players the Player has killed.</p>\n" +
                "            <div class=\"leaderboard\">\n" +
                "                <div class=\"header\">\n" +
                "                    <div>Rank</div>\n" +
                "                    <div>Name</div>\n" +
                "                    <div>Kills</div>\n" +
                "                </div>\n";

                idx = 0;
                for(CustomOfflinePlayer customOfflinePlayer : customOfflinePlayersSortedByPlayerKills) {
                    idx ++;
                    output += "<div class=\"line\">\n" +
                            "                    <div>" + idx + "</div>\n" +
                            "                    <div><img src=\"https://mc-heads.net/head/" + customOfflinePlayer.getUuid() + "\" alt=\"\"> " + customOfflinePlayer.getPlayerName() + "</div>\n" +
                            "                    <div>" + customOfflinePlayer.getPlayerKills() + "</div>\n" +
                            "                </div>\n";
                }

                output +=
                "            </div>\n" +
                "        </div>\n" +
                "        <div class=\"leaderboard_category\">\n" +
                "            <p class=\"text\">This category shows how many deaths a player has.</p>\n" +
                "            <div class=\"leaderboard\">\n" +
                "                <div class=\"header\">\n" +
                "                    <div>Rank</div>\n" +
                "                    <div>Name</div>\n" +
                "                    <div>Deaths</div>\n" +
                "                </div>\n";

                idx = 0;
                for(CustomOfflinePlayer customOfflinePlayer : customOfflinePlayersSortedByDeaths) {
                    idx ++;
                    output += "<div class=\"line\">\n" +
                            "                    <div>" + idx + "</div>\n" +
                            "                    <div><img src=\"https://mc-heads.net/head/" + customOfflinePlayer.getUuid() + "\" alt=\"\"> " + customOfflinePlayer.getPlayerName() + "</div>\n" +
                            "                    <div>" + customOfflinePlayer.getDeaths() + "</div>\n" +
                            "                </div>\n";
                }

                output +=
                "            </div>\n" +
                "        </div>\n" +
                "        <div class=\"leaderboard_category\">\n" +
                "            <p class=\"text\">This category shows the total distance a player has travelled.</p>\n" +
                "            <div class=\"leaderboard\">\n" +
                "                <div class=\"header\">\n" +
                "                    <div>Rank</div>\n" +
                "                    <div>Name</div>\n" +
                "                    <div>Distance</div>\n" +
                "                </div>\n";

                idx = 0;
                for(CustomOfflinePlayer customOfflinePlayer : customOfflinePlayersSortedByDistance) {
                    idx ++;
                    output += "<div class=\"line\">\n" +
                            "                    <div>" + idx + "</div>\n" +
                            "                    <div><img src=\"https://mc-heads.net/head/" + customOfflinePlayer.getUuid() + "\" alt=\"\"> " + customOfflinePlayer.getPlayerName() + "</div>\n" +
                            "                    <div>" + new DecimalFormat("######0.000").format(customOfflinePlayer.getTravelledDistance()/100.00/1000.00) + "km" + "</div>\n" +
                            "                </div>\n";
                }

                output +=
                "            </div>\n" +
                "        </div>\n" +
                "        <div class=\"leaderboard_category\">\n" +
                "            <p class=\"text\">This category shows how much time a player spend on the server.</p>\n" +
                "            <div class=\"leaderboard\">\n" +
                "                <div class=\"header\">\n" +
                "                    <div>Rank</div>\n" +
                "                    <div>Name</div>\n" +
                "                    <div>Playtime</div>\n" +
                "                </div>\n";

                idx = 0;
                for(CustomOfflinePlayer customOfflinePlayer : customOfflinePlayersSortedByTimePlayed) {
                    idx ++;
                    output += "<div class=\"line\">\n" +
                            "                    <div>" + idx + "</div>\n" +
                            "                    <div><img src=\"https://mc-heads.net/head/" + customOfflinePlayer.getUuid() + "\" alt=\"\"> " + customOfflinePlayer.getPlayerName() + "</div>\n" +
                            "                    <div>" + convertSecondsToFormattedTime(customOfflinePlayer.getPlayTime()/20) + "</div>\n" +
                            "                </div>\n";
                }

                output +=
                "            </div>\n" +
                "        </div>\n" +
                "        <script>\n" +
                "            $(document).ready(function() {\n" +
                "                $(\".tag\").click(function() {\n" +
                "                    $(\".tag\").removeClass(\"active\");\n" +
                "                    $(this).addClass(\"active\");\n" +
                "                    $(\".leaderboard_category\").hide();\n" +
                "                    $(\".leaderboard_category\").eq($(this).index()).show();\n" +
                "                });\n" +
                "            });\n" +
                "        </script>\n" +
                "    </div>\n" +
                "</body>\n" +
                "\n" +
                "</html>";

                html = output;
    }

    public String getHtml() {
        return html;
    }

    public static ArrayList<CustomOfflinePlayer> sortiereNachMobKills(ArrayList<CustomOfflinePlayer> liste) {
        ArrayList<CustomOfflinePlayer> sortierteListe = new ArrayList<>(liste); // Erstelle eine Kopie der Liste
        Collections.sort(sortierteListe, new Comparator<CustomOfflinePlayer>() {
            @Override
            public int compare(CustomOfflinePlayer obj1, CustomOfflinePlayer obj2) {
                return Integer.compare(obj1.getMobKills(), obj2.getMobKills());
            }
        });
        Collections.reverse(sortierteListe);
        return sortierteListe;
    }

    public static ArrayList<CustomOfflinePlayer> sortiereNachPlayerKills(ArrayList<CustomOfflinePlayer> liste) {
        ArrayList<CustomOfflinePlayer> sortierteListe = new ArrayList<>(liste); // Erstelle eine Kopie der Liste
        Collections.sort(sortierteListe, new Comparator<CustomOfflinePlayer>() {
            @Override
            public int compare(CustomOfflinePlayer obj1, CustomOfflinePlayer obj2) {
                return Integer.compare(obj1.getPlayerKills(), obj2.getPlayerKills());
            }
        });
        Collections.reverse(sortierteListe);
        return sortierteListe;
    }

    public static ArrayList<CustomOfflinePlayer> sortiereNachDeaths(ArrayList<CustomOfflinePlayer> liste) {
        ArrayList<CustomOfflinePlayer> sortierteListe = new ArrayList<>(liste); // Erstelle eine Kopie der Liste
        Collections.sort(sortierteListe, new Comparator<CustomOfflinePlayer>() {
            @Override
            public int compare(CustomOfflinePlayer obj1, CustomOfflinePlayer obj2) {
                return Integer.compare(obj1.getDeaths(), obj2.getDeaths());
            }
        });
        return sortierteListe;
    }

    public static ArrayList<CustomOfflinePlayer> sortiereNachDistance(ArrayList<CustomOfflinePlayer> liste) {
        ArrayList<CustomOfflinePlayer> sortierteListe = new ArrayList<>(liste); // Erstelle eine Kopie der Liste
        Collections.sort(sortierteListe, new Comparator<CustomOfflinePlayer>() {
            @Override
            public int compare(CustomOfflinePlayer obj1, CustomOfflinePlayer obj2) {
                return Double.compare(obj1.getTravelledDistance(), obj2.getTravelledDistance());
            }
        });
        Collections.reverse(sortierteListe);
        return sortierteListe;
    }

    public static ArrayList<CustomOfflinePlayer> sortiereNachTimePlayed(ArrayList<CustomOfflinePlayer> liste) {
        ArrayList<CustomOfflinePlayer> sortierteListe = new ArrayList<>(liste); // Erstelle eine Kopie der Liste
        Collections.sort(sortierteListe, new Comparator<CustomOfflinePlayer>() {
            @Override
            public int compare(CustomOfflinePlayer obj1, CustomOfflinePlayer obj2) {
                return Integer.compare(obj1.getPlayTime(), obj2.getPlayTime());
            }
        });
        Collections.reverse(sortierteListe);
        return sortierteListe;
    }

    public static ArrayList<CustomOfflinePlayer> sortiereNachOverall(ArrayList<CustomOfflinePlayer> liste) {
        ArrayList<CustomOfflinePlayer> sortierteListe = new ArrayList<>(liste); // Erstelle eine Kopie der Liste
        Collections.sort(sortierteListe, new Comparator<CustomOfflinePlayer>() {
            @Override
            public int compare(CustomOfflinePlayer obj1, CustomOfflinePlayer obj2) {
                return Integer.compare(obj1.getTotalPosition(), obj2.getTotalPosition());
            }
        });
        return sortierteListe;
    }

    public static String convertSecondsToFormattedTime(long seconds) {
        long days = seconds / 86400;
        seconds %= 86400;
        long hours = seconds / 3600;
        seconds %= 3600;
        long minutes = seconds / 60;
        seconds %= 60;

        return String.format("%02d:%02d:%02d:%02d", days, hours, minutes, seconds);
    }
}
