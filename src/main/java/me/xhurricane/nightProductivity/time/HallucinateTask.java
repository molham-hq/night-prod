package me.xhurricane.nightProductivity.time;

import com.github.retrooper.packetevents.protocol.potion.Potion;
import me.xhurricane.nightProductivity.NightProductivity;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.logging.Level;

public class HallucinateTask implements Runnable {

    NightProductivity plugin;
    public HallucinateTask(NightProductivity plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {

        plugin.getLogger().log(Level.INFO, "HALLUCINATION TASK IS RUNNING");

        for(Player player : Bukkit.getOnlinePlayers()) {

            if(!plugin.getSleepHelper().canHallucinate(player)) continue;

            player.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, Math.min(plugin.getConfigManager().getHallucinationInterval() * 20, 200), 0));

            plugin.getHallucinateHelper().hallucinate(player);

        }

    }

}
