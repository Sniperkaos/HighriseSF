package me.cworldstar.highriseSF.impl.protocols;

import java.util.List;
import java.util.stream.Collectors;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.reflect.StructureModifier;

import me.cworldstar.highriseSF.HighriseSF;
import me.cworldstar.highriseSF.utils.FormatUtils;
import net.kyori.adventure.text.Component;

public class CustomArmorDurabilityProtocol {
	
	protected static ItemStack editItem(ItemStack i) {
		 ItemMeta meta = i.getItemMeta();
		 if(meta == null) {
			 return i;
		 }
		 PersistentDataContainer pdc = meta.getPersistentDataContainer();
		 if(pdc.has(HighriseSF.key("DURABILITY"))) {
			 	int durability = pdc.get(HighriseSF.key("DURABILITY"), PersistentDataType.INTEGER);
			 	int maxDurability = pdc.get(HighriseSF.key("MAX_DURABILITY"), PersistentDataType.INTEGER);
			 
				List<Component> lore = meta.lore();
				lore.add(FormatUtils.asText(" "));
				lore.add(FormatUtils.asText("&fDurability: " + String.valueOf(durability) + "/" + String.valueOf(maxDurability)));
				meta.lore(lore);
		 }
		 i.setItemMeta(meta);
		 return i;
	}
	
	public static void start() {
		ProtocolLibrary.getProtocolManager().addPacketListener(
					new PacketAdapter(PacketAdapter.params()
						.plugin(HighriseSF.get())
						.listenerPriority(ListenerPriority.HIGH)
						.types(PacketType.Play.Server.SET_SLOT, PacketType.Play.Server.WINDOW_ITEMS)) 
					{
						@Override
						public void onPacketSending(PacketEvent event) {
							PacketContainer container = event.getPacket();
							PacketType type = event.getPacketType();
							
							Player p = event.getPlayer();
							if(p.getGameMode() == GameMode.CREATIVE) {
								return;
							}
							
							if(type == PacketType.Play.Server.SET_SLOT) {
								 StructureModifier<ItemStack> modifier = container.getItemModifier();
								 if(modifier.size() > 0) {
									 ItemStack item = modifier.readSafely(0).clone();
									 editItem(item);
									 modifier.writeSafely(0, item);
									 event.setPacket(container);
								 }
							} else {
								 StructureModifier<List<ItemStack>> modifier = container.getItemListModifier();
								 if(modifier.size() > 0) {
									 List<ItemStack> items = modifier.readSafely(0); 
									 items = items.stream().map(item -> editItem(item)).collect(Collectors.toList());
									 modifier.writeSafely(0, items);
									 event.setPacket(container);
								 }
							}
						}
					}			
			);
	}
}
