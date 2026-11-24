package me.xhurricane.nightProducitivty;

import me.xhurricane.nightProducitivty.config.ConfigManager;
import me.xhurricane.nightProducitivty.sleep.PlayerSleepHelper;
import org.bukkit.plugin.java.JavaPlugin;

public final class NightProductivity extends JavaPlugin {

    public final int SUNRISE_TIME = 23999;

    PlayerSleepHelper sleepHelper;
    ConfigManager configManager;

    @Override
    public void onEnable() {

        this.saveDefaultConfig();

        this.sleepHelper = new PlayerSleepHelper(this);
        this.configManager = new ConfigManager(this);

    }
    @Override
    public void onDisable() {

    }

    public PlayerSleepHelper getSleepHelper() {
        return this.sleepHelper;
    }

    public ConfigManager getConfigManager() {
        return this.configManager;
    }

}
