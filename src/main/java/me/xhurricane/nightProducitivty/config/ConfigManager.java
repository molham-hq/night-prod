package me.xhurricane.nightProducitivty.config;

import me.xhurricane.nightProducitivty.NightProductivity;

public class ConfigManager {

    NightProductivity plugin;
    public ConfigManager(NightProductivity plugin) {
        this.plugin = plugin;
    }

    public int getHallucinationTime() {
        return plugin.getConfig().getInt("hallucinate-time");
    }

}
