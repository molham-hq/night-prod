package me.xhurricane.nightProductivity.config;

import me.xhurricane.nightProductivity.NightProductivity;

public class ConfigManager {

    NightProductivity plugin;
    public ConfigManager(NightProductivity plugin) {
        this.plugin = plugin;
    }

    public int getHallucinationTime() {
        return plugin.getConfig().getInt("hallucinate-time");
    }
    public double getHallucinationBias() { return plugin.getConfig().getDouble("hallucinate-bias"); }
    public int getCreeperDistance() { return plugin.getConfig().getInt("creeper-distance"); }
    public int getHallucinationInterval() { return plugin.getConfig().getInt("hallucinate-interval"); }
}
