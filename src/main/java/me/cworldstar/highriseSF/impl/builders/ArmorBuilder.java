package me.cworldstar.highriseSF.impl.builders;

import org.apache.commons.lang3.Validate;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.attribute.AttributeModifier.Operation;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.meta.ArmorMeta;

public class ArmorBuilder extends ItemStackBuilder {
	public ArmorBuilder(Material armor) {
		super(armor);
		Validate.isInstanceOf(ArmorMeta.class, item.getItemMeta());
	}
	
	public ArmorBuilder setAttributeValue(Attribute attr, String id, int amount) {
		ArmorMeta meta = (ArmorMeta) item.getItemMeta();
		meta.addAttributeModifier(attr, new AttributeModifier(id, amount, Operation.ADD_NUMBER));
		item.setItemMeta(meta);
		return this;
	}
	
	public ArmorBuilder addUnsafeEnchantment(Enchantment ench, int level) {
		item.addUnsafeEnchantment(ench, level);
		return this;
	}

	public ArmorBuilder addFlag(ItemFlag flag) {
		item.addItemFlags(flag);
		return this;
	}

	public ArmorBuilder setUnbreakable(boolean b) {
		item.editMeta(meta -> {
			meta.setUnbreakable(b);
		});
		return this;
	}
}
