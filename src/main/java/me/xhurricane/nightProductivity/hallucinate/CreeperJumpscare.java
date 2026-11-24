package me.xhurricane.nightProductivity.hallucinate;

import org.bukkit.Location;

public record CreeperJumpscare(Location location, int id) {

    public CreeperJumpscare {
        if(location == null) throw new IllegalArgumentException("Creeper Jumpscare location cannot be null!");
    }

}
