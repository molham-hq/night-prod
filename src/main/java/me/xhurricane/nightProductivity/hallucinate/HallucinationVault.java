package me.xhurricane.nightProductivity.hallucinate;

import me.xhurricane.nightProductivity.NightProductivity;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class HallucinationVault {

    Map<UUID, CreeperJumpscare> creeperJumpscares;

    NightProductivity plugin;
    public HallucinationVault(NightProductivity plugin) {
        this.plugin = plugin;
        this.creeperJumpscares = new HashMap<UUID, CreeperJumpscare>();
    }

    public void putCreeper(Player player, CreeperJumpscare jumpscare) {
        creeperJumpscares.put(player.getUniqueId(), jumpscare);
    }

    public void removeCreeper(Player player) {
        creeperJumpscares.remove(player.getUniqueId());
    }

    public CreeperJumpscare getCreeper(Player player) {
        return creeperJumpscares.get(player.getUniqueId());
    }

    public boolean hasCreeper(Player player) {
        return creeperJumpscares.containsKey(player.getUniqueId());
    }



}
