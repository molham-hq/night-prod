package me.xhurricane.nightProductivity.hallucinate;

import com.github.retrooper.packetevents.PacketEvents;
import com.github.retrooper.packetevents.protocol.entity.type.EntityTypes;
import com.github.retrooper.packetevents.util.Vector3d;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerDestroyEntities;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerSpawnEntity;
import io.github.retrooper.packetevents.util.SpigotReflectionUtil;
import me.xhurricane.nightProductivity.NightProductivity;
import me.xhurricane.nightProductivity.util.RandomUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.Optional;
import java.util.UUID;
import java.util.logging.Level;

public class PlayerHallucinateHelper {



    NightProductivity plugin;
    public PlayerHallucinateHelper(NightProductivity plugin) {
        this.plugin = plugin;
    }

    public boolean hallucinateChance(Player player) {

        int hallucinateTime = plugin.getConfigManager().getHallucinationTime();
        double bias = plugin.getConfigManager().getHallucinationBias();

        long time = player.getWorld().getTime();
        double percentage = (double) (time - hallucinateTime) / (24000 - hallucinateTime) + bias;

        return RandomUtils.getRandomPercentage() <= percentage;

    }

    public void hallucinate(Player player) {

        if(!hallucinateChance(player)) return;

        Hallucination hallucination = RandomUtils.getRandomHallucination();

        plugin.getLogger().log(Level.INFO, "PLAYER " + player.getName() + " HAS HALLUCINATED OF " + hallucination.name());


        switch(hallucination) {
            case ENDERMAN_APPROACH -> {
                return;
            }
            case CREEPER_SNEAK -> creeper(player);
            case NAUSEA -> nausea(player);
            case DARKNESS -> darkness(player, 6);
            case DROP_ITEM -> dropItem(player);
        }

    }

    public void dropItem(Player player) {
        player.dropItem(true);
    }

    public void darkness(Player player, int duration) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.DARKNESS, duration * 20, 0));
    }

    public void nausea(Player player) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.NAUSEA, 260, 0));
    }

    public void creeper(Player player) {

        darkness(player, 2);

        int creeperDistance = plugin.getConfigManager().getCreeperDistance();

        Vector viewingDirection = player.getLocation().getDirection();
        Vector multipliedVector = viewingDirection.multiply(creeperDistance);

        Vector finalVector = player.getLocation().toVector().subtract(multipliedVector);

        int x = finalVector.getBlockX();
        int z = finalVector.getBlockZ();
        int y = 0;

        World world = player.getWorld();
        int currentDistance = 10000;

        for(int i = 255; i >= -64; i--) {
            if(world.getBlockAt(x, i, z).getType().isAir()) continue;

            if(Math.abs(player.getLocation().getBlockY() - i) <= currentDistance) {
                currentDistance = Math.abs(player.getLocation().getBlockY() - i);
                y = i;
            }
        }

        if(currentDistance > 2) return;
        y++;



        int creeperId = SpigotReflectionUtil.generateEntityId();
        Location creeperLocation = new Location(world, x+0.5, y, z+0.5);

        plugin.getHallucinationVault().putCreeper(player, new CreeperJumpscare(creeperLocation, creeperId));

        // Spawn Creeper standing still
        WrapperPlayServerSpawnEntity packet = new WrapperPlayServerSpawnEntity(
                creeperId,
                Optional.of(UUID.randomUUID()),
                EntityTypes.CREEPER,
                new Vector3d(creeperLocation.getX(), creeperLocation.getY(), creeperLocation.getZ()),
                player.getPitch(),
                player.getYaw(),
                player.getYaw(),
                0,
                Optional.empty()
        );
        PacketEvents.getAPI().getPlayerManager().sendPacket(player, packet);

        player.playSound(player.getLocation(), Sound.ENTITY_CREEPER_PRIMED, 1F, 1F);

        expireCreeper(player, creeperId);
    }

    private void expireCreeper(Player player, int creeperId) {
        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            plugin.getHallucinationVault().removeCreeper(player);
            WrapperPlayServerDestroyEntities packet = new WrapperPlayServerDestroyEntities(creeperId);
            PacketEvents.getAPI().getPlayerManager().sendPacket(player, packet);
        }, 140L);
    }


}
