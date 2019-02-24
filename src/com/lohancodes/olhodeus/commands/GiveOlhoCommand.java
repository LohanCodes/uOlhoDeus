package com.lohancodes.olhodeus.commands;

import com.lohancodes.olhodeus.OlhoDeus;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GiveOlhoCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if (!(sender instanceof Player)) {
            if (strings.length <= 1) {
                sender.sendMessage(OlhoDeus.getInstance().getConfig().getString("Mensagens.FaltaArgumento").replace("&", "§"));
                return true;
            }

            Player target = Bukkit.getPlayerExact(strings[0]);

            if (!OlhoDeus.getInstance().isInteger(strings[1])) {
                sender.sendMessage("§cUse um número válido.");
                return true;
            }

            int amount = Integer.parseInt(strings[1]);

            if (target == null) {
                sender.sendMessage("§cJogador não encontrado.");
                return true;
            }

            if (amount < 1) {
                sender.sendMessage("§cUse um número válido.");
                return true;
            }

            ItemStack item = OlhoDeus.getInstance().getItem().clone();
            item.setAmount(amount);

            target.getInventory().addItem(item);
            sender.sendMessage(OlhoDeus.getInstance().getConfig().getString("Mensagens.Adicionado").replace("&", "§").replace("{player}", target.getName()).replace("{quantia}", Integer.toString(amount)));
        } else {
            Player player = (Player) sender;
            if (!player.hasPermission("olhodeus.admin")) {
                player.sendMessage(OlhoDeus.getInstance().getConfig().getString("Mensagens.SemPerm").replace("&", "§"));
                return true;
            }
            if (strings.length <= 1) {
                player.sendMessage(OlhoDeus.getInstance().getConfig().getString("Mensagens.FaltaArgumento").replace("&", "§"));
                return true;
            }

            Player target = Bukkit.getPlayerExact(strings[0]);

            if (!OlhoDeus.getInstance().isInteger(strings[1])) {
                sender.sendMessage("§cUse um número válido.");
                return true;
            }

            int amount = Integer.parseInt(strings[1]);

            if (target == null) {
                player.sendMessage("§cJogador não encontrado.");
                return true;
            }

            if (amount < 1) {
                player.sendMessage("§cUse um número válido.");
                return true;
            }

            ItemStack item = OlhoDeus.getInstance().getItem().clone();
            item.setAmount(amount);

            target.getInventory().addItem(item);
            player.sendMessage(OlhoDeus.getInstance().getConfig().getString("Mensagens.Adicionado").replace("&", "§").replace("{player}", target.getName()).replace("{quantia}", Integer.toString(amount)));
        }
        return false;
    }
}