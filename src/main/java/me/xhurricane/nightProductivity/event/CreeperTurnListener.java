package me.xhurricane.nightProductivity.event;

import com.github.retrooper.packetevents.PacketEvents;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerDestroyEntities;
import me.xhurricane.nightProductivity.NightProductivity;
import me.xhurricane.nightProductivity.hallucinate.CreeperJumpscare;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

public class CreeperTurnListener implements Listener {

    NightProductivity plugin;
    public CreeperTurnListener(NightProductivity plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {

        if(!plugin.getHallucinationVault().hasCreeper(event.getPlayer())) return;

        Player player = event.getPlayer();
        CreeperJumpscare jumpscare = plugin.getHallucinationVault().getCreeper(player);
        Location creeperLocation = jumpscare.location();
        int creeperId = jumpscare.id();

        Vector vector = creeperLocation.clone().subtract(player.getLocation()).toVector();
        Vector viewingVector = player.getLocation().getDirection();

        double viewLength = Math.sqrt( viewingVector.getX() * viewingVector.getX() + viewingVector.getZ() * viewingVector.getZ() );
        double vectorLength = Math.sqrt( vector.getX() * vector.getX() + vector.getZ() * vector.getZ() );

        double dot = vector.getX() * viewingVector.getX() + vector.getZ() * viewingVector.getZ();
        if(dot / (viewLength * vectorLength) < 0.5) return;

        plugin.getHallucinationVault().removeCreeper(player);
        // Make creeper disappear and explosion

        WrapperPlayServerDestroyEntities packet = new WrapperPlayServerDestroyEntities(creeperId);
        PacketEvents.getAPI().getPlayerManager().sendPacket(player, packet);

        player.spawnParticle(Particle.EXPLOSION_EMITTER, creeperLocation, 2);
        player.playSound(creeperLocation, Sound.ENTITY_GENERIC_EXPLODE, 1.0f, 1.0f);

    }

}
