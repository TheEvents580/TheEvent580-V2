package fr.thefox580.theevent5802.utils;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ParticlesShaped {

    public static void createCircle(Location centerLocation, int radius, Particle particle, Particle.DustOptions dustOptions, int count, int frequency, float speed){
        List<Location> locations = new ArrayList<>();

        for (double x = centerLocation.x()-(radius*2); x<centerLocation.x()+radius; x+=frequency){
            for (double z = centerLocation.z()-(radius*2); z<centerLocation.z()+radius; z+=frequency){
                Location particleLocation = new Location(centerLocation.getWorld(), x, centerLocation.getBlockY()+0.5, z);
                if (particleLocation.distanceSquared(centerLocation) <= (Math.pow(radius, 2))){
                    particleLocation.add(0.5, 0, 0.5);
                    locations.add(particleLocation);
                }
            }
        }

        for (Location particleLoc : locations){
            for (Player player : centerLocation.getWorld().getPlayers()){
                player.spawnParticle(particle, particleLoc, count, 0, 0, 0, speed, dustOptions, true);
            }
        }
    }

    public static void createCircleOutside(Location centerLocation, int radius, Particle particle, Particle.DustOptions dustOptions, int count, int frequency, float speed){
        if (radius < 10){
            createCircle(centerLocation, radius, particle, dustOptions, count, frequency, speed);
            return;
        }
        List<Location> locations = new ArrayList<>();

        for (double x = centerLocation.x()-(radius*2); x<centerLocation.x()+radius; x+=frequency){
            for (double z = centerLocation.z()-(radius*2); z<centerLocation.z()+radius; z+=frequency){
                Location particleLocation = new Location(centerLocation.getWorld(), x, centerLocation.getBlockY()+0.5, z);
                if (particleLocation.distanceSquared(centerLocation) <= (Math.pow(radius, 2))){
                    if (particleLocation.distanceSquared(centerLocation) >= (Math.pow(radius*0.9, 2))){
                        particleLocation.add(0.5, 0, 0.5);
                        locations.add(particleLocation);
                    }
                }
            }
        }

        for (Location particleLoc : locations){
            for (Player player : centerLocation.getWorld().getPlayers()){
                player.spawnParticle(particle, particleLoc, count, 0, 0, 0, speed, dustOptions, true);
            }
        }
    }

}
