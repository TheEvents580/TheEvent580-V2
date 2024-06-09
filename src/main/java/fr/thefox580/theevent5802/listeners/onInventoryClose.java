package fr.thefox580.theevent5802.listeners;

import net.kyori.adventure.text.Component;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;

public class onInventoryClose implements Listener {

    @EventHandler
    public void onInventoryCloseEvent(InventoryCloseEvent event){
        HumanEntity player = event.getPlayer();
        if (event.getInventory().getType() == InventoryType.WORKBENCH){
            if (player.getOpenInventory().title().equals(Component.text("Minecraftle"))){
                ItemStack helmet = player.getInventory().getHelmet();
                player.getInventory().clear();
                player.getInventory().setHelmet(helmet);
            }
        }
    }
}
