package com.lohancodes.olhodeus.listeners;

import com.lohancodes.olhodeus.OlhoDeus;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class OlhoListeners implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (player.getItemInHand().hasItemMeta() && player.getItemInHand().getItemMeta().getDisplayName().equals(OlhoDeus.getInstance().getItem().getItemMeta().getDisplayName())) {
            if (player.getGameMode() != GameMode.SURVIVAL) {
                player.sendMessage(OlhoDeus.getInstance().getConfig().getString("Mensagens.SomenteSurvival").replace("&", "§"));
            } else {
                if (player.getItemInHand().getAmount() == 1) {
                    player.setItemInHand(new ItemStack(Material.AIR));
                } else {
                    player.getItemInHand().setAmount(player.getItemInHand().getAmount() - 1);
                }

                OlhoDeus.getInstance().runSystem(player);
            }
        }
    }
}