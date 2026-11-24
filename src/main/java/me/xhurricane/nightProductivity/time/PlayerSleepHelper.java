package me.xhurricane.nightProductivity.time;

import me.xhurricane.nightProductivity.NightProductivity;
import org.bukkit.Statistic;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class PlayerSleepHelper {

    NightProductivity plugin;
    public PlayerSleepHelper(NightProductivity plugin) {
        this.plugin = plugin;
    }

    public boolean canHallucinate(Player player) {

        int hallucinateTime = plugin.getConfigManager().getHallucinationTime();
        int timeSinceRest = player.getStatistic(Statistic.TIME_SINCE_REST);

        World world = player.getWorld();

        return world.getTime() >= hallucinateTime && world.getTime() <= plugin.SUNRISE_TIME
                && world.getEnvironment() == World.Environment.NORMAL && timeSinceRest >= hallucinateTime;
    }

}
