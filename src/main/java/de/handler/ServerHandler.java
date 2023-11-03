package de.handler;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import de.minecraftstatsviewer.MinecraftStatsViewer;
import de.webui.AdminHTML;
import de.webui.IndexCSS;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.ShulkerBox;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ServerHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String requestURI = exchange.getRequestURI().toString();

        URI uri = exchange.getRequestURI();
        String path = uri.getPath();


        // Abrufen der Anfrage-Header
        Headers requestHeaders = exchange.getRequestHeaders();
        for (String header : requestHeaders.keySet()) {
            String value = requestHeaders.getFirst(header);
        }

        String query = exchange.getRequestURI().getQuery();
        Map<String, String> parameters = parseQuery(query);

        String response = "";
        if(path.equals("/") || path.equals("/index.html")) {
            response = MinecraftStatsViewer.index.getHtml();
        } else if(path.equals("/players.html")) {
            response = MinecraftStatsViewer.players.html;
        } else if (path.equals("/index.css")) {
            response = IndexCSS.css;
        } else if (path.startsWith("/allow-flight")) {
            if(parameters.containsKey("player")) {
                Bukkit.getPlayer(parameters.get("player")).setAllowFlight(true);
            } else {
                response = "404";
            }
        } else if (path.startsWith("/deny-flight")) {
            if(parameters.containsKey("player")) {
                Bukkit.getPlayer(parameters.get("player")).setAllowFlight(false);
            } else {
                response = "404";
            }
        } else if (path.startsWith("/op")) {
            if(parameters.containsKey("player")) {
                Bukkit.getPlayer(parameters.get("player")).setOp(true);
            } else {
                response = "404";
            }
        } else if (path.startsWith("/deop")) {
            if(parameters.containsKey("player")) {
                Bukkit.getPlayer(parameters.get("player")).setOp(false);
            } else {
                response = "404";
            }
        } else if (path.equals("/console_log")) {
            File logFile = new File("logs/latest.log");
            if(logFile.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(logFile));
                StringBuilder stringBuilder = new StringBuilder();
                String line;

                // Zeilenweise die Datei lesen und zum StringBuilder hinzufügen
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line);
                    stringBuilder.append("\n"); // Füge Zeilenumbruch hinzu, wenn nötig
                }

                // Den Inhalt der Datei als String erhalten
                String fileContent = stringBuilder.toString();

                response = fileContent;

                // BufferedReader schließen
                reader.close();
            } else {
                response = "404" + MinecraftStatsViewer.getInstance().getDataFolder() + "/logs";
            }
        } else if (path.startsWith("/seed")) {
            response = Bukkit.getWorld("world").getSeed() + "";
        } else if (path.equals("/admin.html")) {
            boolean authenticated = false;
            if(MinecraftStatsViewer.getSettings().getFileConfiguration().getBoolean("admin_page.enabled")) {
                if ("POST".equalsIgnoreCase(exchange.getRequestMethod())) {
                    // Wenn ja, POST-Parameter abrufen
                    Map<String, String> postParameters = getPostParameters(exchange);
                    if(postParameters.containsKey("password")) {
                        MinecraftStatsViewer.getSettings().reload();
                        if(postParameters.get("password").equals(MinecraftStatsViewer.getSettings().getFileConfiguration().get("admin_page.password"))) {
                            InetSocketAddress remoteAddress = exchange.getRemoteAddress();
                            String clientIpAddress = remoteAddress.getAddress().getHostAddress();
                            MinecraftStatsViewer.getInstance().getLogger().info("User successfully logged in from " + clientIpAddress);
                            authenticated = true;
                        }
                    }
                }
            }
            if(authenticated) {
                response = AdminHTML.authHTML;
            } else {
                response = AdminHTML.noAuthHTML;
            }
        } else if(path.startsWith("/admin")) {
            if(parameters.containsKey("command")) {
                Bukkit.getScheduler().runTask(MinecraftStatsViewer.getInstance(), new Runnable() {
                    @Override
                    public void run() {
                        MinecraftStatsViewer.getInstance().getServer().dispatchCommand(MinecraftStatsViewer.getInstance().getServer().getConsoleSender(), parameters.get("command"));
                    }
                });
            } else {
                response = "404";
            }
        } else if (path.equals("/playersDetailedInformation")) {
            JsonArray playerArray = new JsonArray();

            for (Player player : Bukkit.getOnlinePlayers()) {
                JsonObject playerJson = playerToJson(player);
                playerArray.add(playerJson);
            }
            response = playerArray.toString();
        } else if (path.equals("/clearconsole")) {
            try {
                PrintWriter writer = new PrintWriter("logs/latest.log");
                writer.print("");
                writer.close();
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } catch (InterruptedException | IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        } else {
            response = "404";
        }
        exchange.sendResponseHeaders(200, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    private Map<String, String> getPostParameters(HttpExchange exchange) throws IOException {
        Map<String, String> postParameters = new HashMap<>();
        InputStreamReader isr = new InputStreamReader(exchange.getRequestBody(), "UTF-8");
        BufferedReader br = new BufferedReader(isr);
        String line;
        StringBuilder requestBody = new StringBuilder();

        while ((line = br.readLine()) != null) {
            requestBody.append(line);
        }

        // Hier wird angenommen, dass der Anfragekörper im URL-kodierten Format vorliegt
        String[] paramPairs = requestBody.toString().split("&");
        for (String paramPair : paramPairs) {
            String[] keyValue = paramPair.split("=");
            if (keyValue.length == 2) {
                String key = java.net.URLDecoder.decode(keyValue[0], "UTF-8");
                String value = java.net.URLDecoder.decode(keyValue[1], "UTF-8");
                postParameters.put(key, value);
            }
        }
        return postParameters;
    }

    public static JsonObject playerToJson(Player player) {
        JsonObject playerJson = new JsonObject();

        playerJson.addProperty("name", player.getName());
        playerJson.addProperty("uuid", player.getUniqueId().toString());
        playerJson.addProperty("health", player.getHealth());
        playerJson.addProperty("hunger", player.getFoodLevel());

        // Standort in JSON konvertieren
        JsonObject locationJson = new JsonObject();
        locationJson.addProperty("world", player.getWorld().getName());
        locationJson.addProperty("x", player.getLocation().getX());
        locationJson.addProperty("y", player.getLocation().getY());
        locationJson.addProperty("z", player.getLocation().getZ());
        playerJson.add("location", locationJson);

        if(player.isSleeping()) {
            Location bedLocation = player.getBedLocation();
            if (bedLocation != null) {
                playerJson.add("bedLocation", locationToJson(bedLocation));
            } else {
                playerJson.add("bedLocation", null); // Oder einen anderen Standardwert setzen
            }
        } else {
            playerJson.add("bedLocation", null); // Oder einen anderen Standardwert setzen
        }

        // Überprüfe, ob der Spieler eine Bett-Spawn-Location gesetzt hat
        Location bedSpawnLocation = player.getBedSpawnLocation();
        if (bedSpawnLocation != null) {
            playerJson.add("bedSpawnLocation", locationToJson(bedSpawnLocation));
        } else {
            playerJson.add("bedSpawnLocation", null); // Oder einen anderen Standardwert setzen
        }



        playerJson.addProperty("address", player.getAddress().getAddress().getHostAddress());
        playerJson.addProperty("ping", player.getPing());
        playerJson.addProperty("gamemode", player.getGameMode().toString());
        playerJson.addProperty("clientBrandName", player.getClientBrandName());
        playerJson.addProperty("level", player.getLevel());
        playerJson.addProperty("exp", player.getExp());
        playerJson.addProperty("idleDuration", player.getIdleDuration().getSeconds());



        // Inventar in JSON konvertieren
        JsonArray inventoryJson = new JsonArray();
        for (int slot = 0; slot < player.getInventory().getSize(); slot++) {
            ItemStack item = player.getInventory().getItem(slot);
            if (item != null) {
                JsonObject itemJson = new JsonObject();
                itemJson.addProperty("material", item.getType().toString());
                itemJson.addProperty("amount", item.getAmount());
                itemJson.addProperty("slot", slot);
                if (item.getType() == Material.SHULKER_BOX) {
                    Inventory shulkerInventory = getShulkerInventory(item);
                    if (shulkerInventory != null) {
                        JsonArray shulkerContents = new JsonArray();
                        for (int shulkerSlot = 0; shulkerSlot < shulkerInventory.getSize(); shulkerSlot++) {
                            ItemStack shulkerItem = shulkerInventory.getItem(shulkerSlot);
                            if (shulkerItem != null) {
                                JsonObject shulkerItemJson = new JsonObject();
                                shulkerItemJson.addProperty("material", shulkerItem.getType().toString());
                                shulkerItemJson.addProperty("amount", shulkerItem.getAmount());
                                shulkerItemJson.addProperty("slot", shulkerSlot);
                                shulkerContents.add(shulkerItemJson);
                            }
                        }
                        itemJson.add("shulkerContents", shulkerContents);
                    }
                }
                inventoryJson.add(itemJson);
            }
        }
        playerJson.add("inventory", inventoryJson);

        JsonArray enderChestJson = new JsonArray();
        for (int slot = 0; slot < player.getEnderChest().getSize(); slot++) {
            ItemStack item = player.getEnderChest().getItem(slot);
            if (item != null) {
                JsonObject itemJson = new JsonObject();
                itemJson.addProperty("material", item.getType().toString());
                itemJson.addProperty("amount", item.getAmount());
                itemJson.addProperty("slot", slot);
                if (item.getType() == Material.SHULKER_BOX) {
                    Inventory shulkerInventory = getShulkerInventory(item);
                    if (shulkerInventory != null) {
                        JsonArray shulkerContents = new JsonArray();
                        for (int shulkerSlot = 0; shulkerSlot < shulkerInventory.getSize(); shulkerSlot++) {
                            ItemStack shulkerItem = shulkerInventory.getItem(shulkerSlot);
                            if (shulkerItem != null) {
                                JsonObject shulkerItemJson = new JsonObject();
                                shulkerItemJson.addProperty("material", shulkerItem.getType().toString());
                                shulkerItemJson.addProperty("amount", shulkerItem.getAmount());
                                shulkerItemJson.addProperty("slot", shulkerSlot);
                                shulkerContents.add(shulkerItemJson);
                            }
                        }
                        itemJson.add("shulkerContents", shulkerContents);
                    }
                }
                enderChestJson.add(itemJson);
            }
        }
        playerJson.add("enderChest", enderChestJson);

        return playerJson;
    }

    public static JsonObject locationToJson(org.bukkit.Location location) {
        JsonObject locationJson = new JsonObject();
        locationJson.addProperty("world", location.getWorld().getName());
        locationJson.addProperty("x", location.getX());
        locationJson.addProperty("y", location.getY());
        locationJson.addProperty("z", location.getZ());
        return locationJson;
    }

    public static Inventory getShulkerInventory(ItemStack shulkerBoxItem) {
        if (shulkerBoxItem.getType() == Material.SHULKER_BOX && shulkerBoxItem.hasItemMeta()) {
            BlockStateMeta meta = (BlockStateMeta) shulkerBoxItem.getItemMeta();
            if (meta != null && meta.getBlockState() instanceof ShulkerBox) {
                ShulkerBox shulkerBox = (ShulkerBox) meta.getBlockState();
                return shulkerBox.getInventory();
            }
        }
        return null;
    }

    private Map<String, String> parseQuery(String query) {
        Map<String, String> result = new HashMap<>();
        if (query != null) {
            String[] pairs = query.split("&");
            for (String pair : pairs) {
                int idx = pair.indexOf("=");
                try {
                    result.put(URLDecoder.decode(pair.substring(0, idx), "UTF-8"),
                            URLDecoder.decode(pair.substring(idx + 1), "UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
}
