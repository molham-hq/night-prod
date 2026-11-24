package me.xhurricane.nightProducitivty;

import me.xhurricane.nightProducitivty.config.ConfigManager;
import me.xhurricane.nightProducitivty.hallucinate.PlayerHallucinateHelper;
import me.xhurricane.nightProducitivty.time.HallucinateTask;
import me.xhurricane.nightProducitivty.time.PlayerSleepHelper;
import org.bukkit.plugin.java.JavaPlugin;

public final class NightProductivity extends JavaPlugin {

    public final int SUNRISE_TIME = 23999;

    PlayerSleepHelper sleepHelper;
    ConfigManager configManager;
    PlayerHallucinateHelper hallucinateHelper;

    @Override
    public void onEnable() {

        this.saveDefaultConfig();

        this.sleepHelper = new PlayerSleepHelper(this);
        this.configManager = new ConfigManager(this);
        this.hallucinateHelper = new PlayerHallucinateHelper(this);

        Runnable task = new HallucinateTask(this);

        this.getServer().getScheduler().runTaskTimer(this, task, 20L, 1200L);

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

    public PlayerHallucinateHelper getHallucinateHelper() { return this.hallucinateHelper; }

}
