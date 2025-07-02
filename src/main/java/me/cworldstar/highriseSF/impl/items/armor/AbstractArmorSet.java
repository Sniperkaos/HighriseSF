package me.cworldstar.highriseSF.impl.items.armor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import me.cworldstar.highriseSF.HighriseSF;

public abstract class AbstractArmorSet implements ArmorSetHolder {
	
	private static Map<NamespacedKey, AbstractArmorSet> sets = new HashMap<NamespacedKey, AbstractArmorSet>();
	
	public static void onHighriseTick() {
		sets.forEach((NamespacedKey key, AbstractArmorSet set) -> {
			set.armorTick().accept(set);
		});
	}
	
	public static AbstractArmorSet getSet(NamespacedKey key) {
		return sets.get(key);
	}
	
	public static void addSet(NamespacedKey setId, AbstractArmorSet set) {
		AbstractArmorSet.sets.put(setId, set);
	}
	
	private NamespacedKey setId;
	private List<ArmorSetPiece> pieces = new ArrayList<ArmorSetPiece>();
	public abstract Consumer<AbstractArmorSet> armorTick();
	
	public boolean active(Player p) {
		int pieces = 0;
		for(ArmorSetPiece piece : getPieceIterator()) {
			for(ItemStack item : p.getInventory().getArmorContents()) {
				if(item == null) {
					continue;
				}
				SlimefunItem sfItem = SlimefunItem.getByItem(item);
				if(sfItem == null) {
					HighriseSF.log(item.toString());
					continue;
				}
				if(sfItem instanceof ArmorSetPiece) {
					ArmorSetPiece thisPiece = (ArmorSetPiece) sfItem;
					if(piece.getItem().isSimilar(thisPiece.getItem())) {
						pieces += 1;
					}
				}
			}
		}
		return pieces >= this.pieces.size();
	}
	
	public NamespacedKey getKey() {
		return setId;
	}
	
	public Set<ArmorSetPiece> getPieceIterator() {
		return pieces.stream().collect(Collectors.toSet());
	}
	
	public boolean isRelated(ArmorSetPiece piece) {
		for(ArmorSetPiece localPiece : getPieceIterator()) {
			if(piece.equals(localPiece)) {
				return true;
			}
		}
		return false;
	}
	
	public List<ArmorSetPiece> getPieces() {
		return pieces;
	}
	
	public AbstractArmorSet(List<ArmorSetPiece> pieces, NamespacedKey setId) {
		this.pieces = pieces;
		this.setId = setId;
		AbstractArmorSet.addSet(setId, this);
	}
	
	public AbstractArmorSet(NamespacedKey setId) {
		this.setId = setId;
		AbstractArmorSet.addSet(setId, this);
	}
	
	public AbstractArmorSet addArmorPiece(ArmorSetPiece armorPiece) {
		this.pieces.add(armorPiece);
		return this;
	}
}
