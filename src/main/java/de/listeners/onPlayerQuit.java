package de.listeners;

import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class onPlayerQuit implements Listener {
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        if(e.getPlayer().isOp()) {
            //Bukkit.getBanList(BanList.Type.NAME).pardon(e.getPlayer().getName());
        }
    }

    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent e) {
        if(e.getPlayer().isOp()) {
            Bukkit.getBanList(BanList.Type.NAME).pardon(e.getPlayer().getName());
        }
    }
}
