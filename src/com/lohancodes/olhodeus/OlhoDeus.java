package com.lohancodes.olhodeus;

import com.google.common.collect.Lists;
import com.lohancodes.olhodeus.commands.GiveOlhoCommand;
import com.lohancodes.olhodeus.listeners.OlhoListeners;
import com.lohancodes.olhodeus.utilities.ActionBarAPI;
import com.lohancodes.olhodeus.utilities.TitleAPI;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OlhoDeus extends JavaPlugin {

    public static OlhoDeus getInstance() {
        return getPlugin(OlhoDeus.class);
    }

    public HashMap<String, Location> location = new HashMap<>();
    public HashMap<String, GameMode> gamemode = new HashMap<>();
    public BukkitTask bukkitTask;

    @Override
    public void onEnable() {
        saveDefaultConfig();

        Bukkit.getPluginManager().registerEvents(new OlhoListeners(), this);

        getCommand("giveolho").setExecutor(new GiveOlhoCommand());

        Bukkit.getConsoleSender().sendMessage("§e[uOlhoDeus] Habilitado com sucesso!");
        Bukkit.getConsoleSender().sendMessage("§e[uOlhoDeus] Versão do Plugin: 1.0");
        Bukkit.getConsoleSender().sendMessage("§e[uOlhoDeus] Discord do Criador: Lohan#2397");
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage("§c[uOlhoDeus] Desabilitado com sucesso!");
    }

    public ItemStack getItem() {
        int material = getInstance().getConfig().getInt("Item.Material");
        int data = getInstance().getConfig().getInt("Item.Data");
        String name = getInstance().getConfig().getString("Item.Nome").replace("&", "§");
        boolean glow = getInstance().getConfig().getBoolean("Item.Glow");

        List<String> lore = Lists.transform(getInstance().getConfig().getStringList("Item.Lore"), l -> l.replace('&', '§'));

        ItemStack item = new ItemStack(Material.getMaterial(material), 1, (short) data);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        if (glow) {
            meta.addEnchant(Enchantment.DURABILITY, 1, false);
            meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS);
        }
        meta.setLore(lore);
        item.setItemMeta(meta);

        return item;
    }

    public void runSystem(Player player) {
        location.put(player.getName(), player.getLocation());
        gamemode.put(player.getName(), player.getGameMode());

        if (getInstance().getConfig().getBoolean("ActionBar.Ativar")) {
            ActionBarAPI.sendActionBar(player, getInstance().getConfig().getString("ActionBar.Mensagens.Ativar").replace("&", "§"));
        }

        TitleAPI.sendTitle(player, 20, 60, 20, getInstance().getConfig().getString("Mensagens.Title").replace("&", "§"), getInstance().getConfig().getString("Mensagens.SubTitle").replace("&", "§"));
        player.setGameMode(GameMode.SPECTATOR);
        bukkitTask = new BukkitRunnable() {
            public void run() {
                player.setGameMode(gamemode.get(player.getName()));
                if (getInstance().getConfig().getBoolean("ActionBar.Ativar")) {
                    ActionBarAPI.sendActionBar(player, getInstance().getConfig().getString("ActionBar.Mensagens.Desativar").replace("&", "§"));
                }
                bukkitTask.cancel();
                if (getInstance().getConfig().getBoolean("VoltarSpawn")) {
                    player.teleport(player.getWorld().getSpawnLocation());
                    player.sendMessage(getInstance().getConfig().getString("Mensagens.AcabouSpawn").replace("&", "§"));
                } else {
                    Location loc = location.get(player.getName());
                    player.teleport(loc);
                    player.sendMessage(getInstance().getConfig().getString("Mensagens.AcabouVoltar").replace("&", "§"));
                }
            }
        }.runTaskTimer(getInstance(), 20 * getInstance().getConfig().getInt("Tempo"), 20 * getInstance().getConfig().getInt("Tempo"));
    }

    public boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}