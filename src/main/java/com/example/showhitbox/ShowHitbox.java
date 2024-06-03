package com.example.showhitbox;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class ShowHitbox extends JavaPlugin implements Listener{

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(this, this);
        getLogger().info("showHitbox plugin has been enabled");
    }

    @Override
    public void onDisable() {
        getLogger().info("showHitbox plugin has been disabled");
    }

    @EventHandler
    public void onEntitySpawn(EntitySpawnEvent event) {
        if (event.getEntityType() == EntityType.ZOMBIE) {
            LivingEntity zombie = (LivingEntity) event.getEntity();
            Location zombieLocation = zombie.getLocation();
            displayHitbox(zombieLocation);
        }
    }

    private void displayHitbox(Location location) {
        World world = location.getWorld();
        Material blockType = Material.GLASS;
        int hitboxSize = 2;

        for (int x = -hitboxSize; x <= hitboxSize; x++) {
            for (int y = -hitboxSize; y <= hitboxSize; y++) {
                for (int z = -hitboxSize; z <= hitboxSize; z++) {
                    Location blockLocation = new Location(world, location.getX() + x, location.getY() + y, location.getZ() + z);
                    world.getBlockAt(blockLocation).setType(blockType);
                }
            }
        }
    }
}
