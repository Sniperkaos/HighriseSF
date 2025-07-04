package me.cworldstar.highriseSF.impl.items;

import javax.annotation.Nonnull;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.libraries.commons.lang.math.RandomUtils;
import me.cworldstar.highriseSF.impl.handlers.TickHandler;
import me.cworldstar.highriseSF.utils.FormatUtils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import net.kyori.adventure.text.minimessage.tag.standard.StandardTags;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;

public class HostileItem extends SlimefunItem {
	
	private int armorRequirement = 0;
	private int timeToAttack = 5;
	
	public int getArmorRequirement() {
		return this.armorRequirement;
	}
	
	private void setup() {
		addItemHandler(new TickHandler() {
			@Override
			public void onTick(SlimefunItem thisItem, Player p, ItemStack eventItem) {
				
				if(RandomUtils.nextInt(1000) < 995) {
					return;
				}
				
				if(p.getAttribute(Attribute.GENERIC_ARMOR).getValue() < armorRequirement) {
					p.sendMessage(warning(PlainTextComponentSerializer.plainText().serialize(eventItem.getItemMeta().displayName())));
					p.damage(15.0, p);
				} else {
					p.sendMessage(scare(PlainTextComponentSerializer.plainText().serialize(eventItem.getItemMeta().displayName())));
				}
			}
		});
	}
	
	/**
	 * 
	 * @param armorRequirement The amount of armor the player must be wearing to survive.
	 * @param timeToAttack The median amount of time it will take for this {@link HostileItem} to attempt an attack.
	 * 
	 */
	public HostileItem(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, int armorRequirement, int timeToAttack) {
		super(itemGroup, item, recipeType, recipe);
		this.armorRequirement = armorRequirement;
		this.timeToAttack = timeToAttack;
		setup();
	}

	public static @Nonnull String lore(int armorRequirement) {
		return FormatUtils.formatString("&e&l⚠&r &7Armor Requirement: &c" + String.valueOf(armorRequirement));
	}
	
	public static @Nonnull Component warning(String itemName) {
		return MiniMessage.builder().tags(TagResolver.builder().resolver(StandardTags.gradient()).resolver(StandardTags.color()).resolver(StandardTags.decorations()).build()).build().deserialize("<dark_red><bold>→</bold> <shadow:dark_red><red>Your " + itemName + " attacks you!");
	}
	
	public static @Nonnull Component scare(String itemName) {
		return MiniMessage.builder().tags(TagResolver.builder().resolver(StandardTags.gradient()).resolver(StandardTags.color()).resolver(StandardTags.decorations()).build()).build().deserialize("<dark_red><bold>→</bold> <shadow:dark_red><red>You hear your " + itemName + " growling fiercely.");
	}
	
	public static @Nonnull String warningString() {
		return "<dark_red><bold>→</bold> <gray><underlined>This is a dangerous item! You must wear armor.";
	}
}
