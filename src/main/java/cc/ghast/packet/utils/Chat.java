package cc.ghast.packet.utils;

import org.bukkit.ChatColor;

/**
 * @author Ghast
 * @since 31/08/2020
 * Artemis © 2020
 */
public class Chat {
    public static String translate(String s){
        return ChatColor.translateAlternateColorCodes('&', s);
    }
}
