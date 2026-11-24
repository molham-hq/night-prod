package me.xhurricane.nightProducitivty.hallucinate;

import me.xhurricane.nightProducitivty.NightProductivity;
import me.xhurricane.nightProducitivty.util.RandomUtils;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PlayerHallucinateHelper {

    NightProductivity plugin;
    public PlayerHallucinateHelper(NightProductivity plugin) {
        this.plugin = plugin;
    }

    public void hallucinate(Player player) {

        int hallucinateTime = plugin.getConfigManager().getHallucinationTime();

        long time = player.getWorld().getTime();
        double percentage = (double) (time - hallucinateTime) / (24000 - hallucinateTime);

        if (RandomUtils.getRandomPercentage() > percentage) return;

        Hallucination hallucination = RandomUtils.getRandomHallucination();

        switch(hallucination) {
            case ENDERMAN_APPROACH -> {
                return;
            }
            case CREEPER_SNEAK -> {
                return;
            }
            case NAUSEA -> nausea(player);
            case DARKNESS -> darkness(player);
            case DROP_ITEM -> dropItem(player);
        }

    }

    public void dropItem(Player player) {
        player.dropItem(true);
    }

    public void darkness(Player player) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.DARKNESS, 100, 1));
    }

    public void nausea(Player player) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.NAUSEA, 120, 1));
    }


}
