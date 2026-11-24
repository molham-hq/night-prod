package me.xhurricane.nightProductivity;

import me.xhurricane.nightProductivity.config.ConfigManager;
import me.xhurricane.nightProductivity.event.CreeperTurnListener;
import me.xhurricane.nightProductivity.hallucinate.HallucinationVault;
import me.xhurricane.nightProductivity.hallucinate.PlayerHallucinateHelper;
import me.xhurricane.nightProductivity.time.HallucinateTask;
import me.xhurricane.nightProductivity.time.PlayerSleepHelper;
import org.bukkit.plugin.java.JavaPlugin;

public final class NightProductivity extends JavaPlugin {

    public final int SUNRISE_TIME = 23999;

    PlayerSleepHelper sleepHelper;
    ConfigManager configManager;
    PlayerHallucinateHelper hallucinateHelper;
    HallucinationVault hallucinationVault;

    @Override
    public void onEnable() {

        this.saveDefaultConfig();

        this.sleepHelper = new PlayerSleepHelper(this);
        this.configManager = new ConfigManager(this);
        this.hallucinateHelper = new PlayerHallucinateHelper(this);
        this.hallucinationVault = new HallucinationVault(this);

        Runnable task = new HallucinateTask(this);

        this.getServer().getScheduler().runTaskTimer(this, task, 20L, configManager.getHallucinationInterval() * 20L);

        registerListener();

    }
    @Override
    public void onDisable() {

    }

    public void registerListener() {
        this.getServer().getPluginManager().registerEvents(new CreeperTurnListener(this), this);
    }

    public PlayerSleepHelper getSleepHelper() {
        return this.sleepHelper;
    }

    public ConfigManager getConfigManager() {
        return this.configManager;
    }

    public PlayerHallucinateHelper getHallucinateHelper() { return this.hallucinateHelper; }

    public HallucinationVault getHallucinationVault() { return this.hallucinationVault; }

}
