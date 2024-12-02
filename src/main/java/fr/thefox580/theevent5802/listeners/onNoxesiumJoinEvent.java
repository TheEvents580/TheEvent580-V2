/*
package fr.thefox580.theevent5802.listeners;

import com.noxcrew.noxesium.api.protocol.rule.ServerRuleIndices;
import me.superneon4ik.noxesiumutils.NoxesiumUtils;
import me.superneon4ik.noxesiumutils.events.NoxesiumPlayerJoinEvent;
import me.superneon4ik.noxesiumutils.network.clientbound.ClientboundChangeServerRulesPacket;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.List;

public class onNoxesiumJoinEvent implements Listener {

    @EventHandler
    public void onNoxesiumJoinEvent(NoxesiumPlayerJoinEvent event){
        Player player = event.getPlayer();

        var noTridentCollision = NoxesiumUtils.getManager().<Boolean>getServerRule(player, ServerRuleIndices.DISABLE_SPIN_ATTACK_COLLISIONS);
        //var noBoatCollision = NoxesiumUtils.getManager().<Boolean>getServerRule(player, ServerRuleIndices.DISABLE_BOAT_COLLISIONS);

        noTridentCollision.setValue(true);
        //noBoatCollision.setValue(true);

        new ClientboundChangeServerRulesPacket(List.of(noTridentCollision)).send(player);
        //new ClientboundChangeServerRulesPacket(List.of(noBoatCollision)).send(player);
    }

}
*/