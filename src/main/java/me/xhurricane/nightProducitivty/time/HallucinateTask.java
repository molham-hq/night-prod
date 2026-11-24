package me.xhurricane.nightProducitivty.time;

import me.xhurricane.nightProducitivty.NightProductivity;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class HallucinateTask implements Runnable {

    NightProductivity plugin;
    public HallucinateTask(NightProductivity plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {

        for(Player player : Bukkit.getOnlinePlayers()) {

            if(!plugin.getSleepHelper().canHallucinate(player)) continue;

            plugin.getHallucinateHelper().hallucinate(player);

        }

    }

}
