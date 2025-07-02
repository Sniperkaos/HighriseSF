package me.cworldstar.highriseSF.impl.builders;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.attribute.AttributeModifier.Operation;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import me.cworldstar.highriseSF.HighriseSF;

public class LeatherArmorBuilder extends ItemStackBuilder {
		
	public LeatherArmorBuilder(Material leather) {
		super(leather);
	}

	public LeatherArmorBuilder setColor(Color color) {
		LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
		meta.setColor(color);
		item.setItemMeta(meta);
		return this;
	}
	
	public LeatherArmorBuilder setAttributeValue(Attribute attr, String id, int amount) {
		LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
		meta.addAttributeModifier(attr, new AttributeModifier(HighriseSF.key(id), amount, Operation.ADD_NUMBER));
		item.setItemMeta(meta);
		return this;
	}
	
	public LeatherArmorBuilder addUnsafeEnchantment(Enchantment ench, int level) {
		item.addUnsafeEnchantment(ench, level);
		return this;
	}

	public LeatherArmorBuilder addFlag(ItemFlag flag) {
		item.addItemFlags(flag);
		return this;
	}

	public LeatherArmorBuilder setUnbreakable(boolean b) {
		item.editMeta(meta -> {
			meta.setUnbreakable(b);
		});
		return this;
	}
	
}
