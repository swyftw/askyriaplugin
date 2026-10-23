
package com.askyria.plugin;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class AskyriaPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("AskyriaPlugin is enabled!");
    }

    @Override
    public boolean onCommand(
            CommandSender sender,
            Command command,
            String label,
            String[] args
    ) {
        if (command.getName().equalsIgnoreCase("askyria")) {
            sender.sendMessage(
                ChatColor.AQUA + "Welcome to Askyria!"
            );
            return true;
        }

        if (command.getName().equalsIgnoreCase("playertag")) {
            if (!sender.hasPermission("askyria.admin")) {
                sender.sendMessage(ChatColor.RED + "No permission!");
                return true;
            }

            if (args.length < 2) {
                sender.sendMessage(
                    ChatColor.YELLOW
                    + "Usage: /playertag <player> <color>"
                );
                return true;
            }

            Player target = getServer().getPlayerExact(args[0]);

            if (target == null) {
                sender.sendMessage(ChatColor.RED + "Player not found.");
                return true;
            }

            ChatColor color = ChatColor.getByChar(
                args[1].equalsIgnoreCase("gold") ? '6' :
                args[1].equalsIgnoreCase("red") ? 'c' :
                args[1].equalsIgnoreCase("blue") ? '9' :
                args[1].equalsIgnoreCase("green") ? 'a' :
                args[1].equalsIgnoreCase("aqua") ? 'b' :
                args[1].equalsIgnoreCase("yellow") ? 'e' :
                args[1].equalsIgnoreCase("purple") ? '5' : 'f'
            );

            target.setDisplayName(color + "[" + args[1].toUpperCase() + "] "
                + ChatColor.RESET + target.getName());

            sender.sendMessage(ChatColor.GREEN + "Tag updated!");
            return true;
        }

        return false;
    }
}
