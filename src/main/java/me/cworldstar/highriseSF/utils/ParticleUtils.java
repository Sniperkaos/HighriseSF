package me.cworldstar.highriseSF.utils;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Particle.DustOptions;

public class ParticleUtils {
	public static void spawnCircle(Location location, DustOptions color, double circleSize, int particleSize, int particleAmount) {
	    for (int d = 0; d <= particleAmount; d += 1) {
	        Location particleLoc = new Location(location.getWorld(), location.getX(), location.getY(), location.getZ());
	        particleLoc.setX(location.getX() + Math.cos(d) * circleSize + 0.5);
	        particleLoc.setZ(location.getZ() + Math.sin(d) * circleSize + 0.5);
	        location.getWorld().spawnParticle(Particle.DUST, particleLoc, particleSize, color);
	    }
	}
	
	public static void spawnCircle(Location location, Particle particle, double circleSize, int particleSize, int particleAmount, int distance) {
	    for (int d = 0; d <= particleAmount; d += 1) {
	        Location particleLoc = new Location(location.getWorld(), location.getX(), location.getY(), location.getZ());
	        particleLoc.setX(location.getX() + Math.cos(d) * circleSize);
	        particleLoc.setZ(location.getZ() + Math.sin(d) * circleSize);
	        location.getWorld().spawnParticle(particle, particleLoc, particleSize);
	    }
	}
	
}
